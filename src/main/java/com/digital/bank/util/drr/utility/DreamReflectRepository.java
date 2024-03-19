package com.digital.bank.util.drr.utility;

import java.lang.reflect.*;
import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.digital.bank.util.drr.annotation.*;

public abstract class DreamReflectRepository<T> implements BasicRepository<T>{
    private final Connection connection;
    public DreamReflectRepository(Connection connection) {
        this.connection = connection;
    }

    //Get the class of the generic parameter <T>
    private Class<?> parameterToClass(){
        return ((Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    @Override
    public List<T> findAll() throws SQLException {
        QueryFormatterUtility queryFormatter = QueryFormatterUtility.getInstance(
                AnnotationUtility.getTableName(this.parameterToClass()),
                AnnotationUtility.getColumns(this.parameterToClass())
        );

        String query = queryFormatter.formatQuery(PgQuery.SELECT);

        List<T> results = new ArrayList<>();
            Statement statement = this.connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                results.add(this.mapResultSet(resultSet));
            }

        return results;
    }

    @Override
    public T save(T t) throws SQLException {
        T saved;
        List<String> columns = AnnotationUtility.getColumns(this.parameterToClass());

        QueryFormatterUtility queryFormatter = QueryFormatterUtility.getInstance(
                AnnotationUtility.getTableName(this.parameterToClass()),
                columns
        );

        Field id = AnnotationUtility.getField(
                this.parameterToClass(),
                Id.class
        );
        Method getId;
        try {
            getId = AnnotationUtility.getMethod(
                    t.getClass(),
                    id.getAnnotation(Column.class).name(),
                    "getter"
            );

            String query = getId.invoke(t) == null ? queryFormatter.formatQuery(PgQuery.INSERT)
                    : queryFormatter.formatQuery(PgQuery.UPDATE);

            PreparedStatement statement = this.connection.prepareStatement(query);

            for (int i = 1; i < columns.size(); i++) {
                this.setColumnValueIntoStatement(columns.get(i), statement, i, t);
            }

            if(getId.invoke(t) != null){
                statement.setObject(columns.size(),getId.invoke(t));
            }

            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            if(!resultSet.next())
                return null;
            saved = mapResultSet(resultSet);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        return saved;
    }

    @Override
    public T getById(String toGet) throws SQLException {
        T occurrenceFound;
        QueryFormatterUtility queryFormatter = QueryFormatterUtility.getInstance(
                AnnotationUtility.getTableName(this.parameterToClass()),
                AnnotationUtility.getColumns(this.parameterToClass())
        );

        Field id = AnnotationUtility.getField(
                this.parameterToClass(),
                Id.class
        );

        String query = queryFormatter.formatQuery(PgQuery.SELECT_BY_COLUMN, id.getAnnotation(Column.class).name());

        PreparedStatement statement = this.connection.prepareStatement(query);

        statement.setObject(1, toGet);

        ResultSet resultSet = statement.executeQuery();

        if(!resultSet.next())
            return null;
        occurrenceFound = this.mapResultSet(resultSet);

        return occurrenceFound;
    }

    @Override
    public List<T> saveAll(List<T> toSave) throws SQLException{
        return toSave.stream()
                .map(t -> {
                    try {
                        return this.save(t);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public T delete(String toDelete) throws SQLException{
        T deleted;
        List<String> columns = AnnotationUtility.getColumns(this.parameterToClass());

        QueryFormatterUtility queryFormatter = QueryFormatterUtility.getInstance(
                AnnotationUtility.getTableName(this.parameterToClass()),
                columns
        );

        Field id = AnnotationUtility.getField(
                this.parameterToClass(),
                Id.class
        );

        String query = queryFormatter.formatQuery(PgQuery.DELETE_BY_COLUMN, id.getAnnotation(Column.class).name());

        PreparedStatement statement = this.connection.prepareStatement(query);

        statement.setObject(1, toDelete);

        statement.execute();

        ResultSet resultSet = statement.getResultSet();

        if(!resultSet.next())
            return null;
        deleted = this.mapResultSet(resultSet);

        return deleted;
    }

    private void setColumnValueIntoStatement(String columnName, PreparedStatement statement, Integer i, T t) throws SQLException {
        Field field = AnnotationUtility.getAnnotatedField(
                this.parameterToClass(),
                columnName);
        Method getter;
        try {
            getter = AnnotationUtility.getMethod(
                    this.parameterToClass(),
                    columnName ,
                    "getter");

            Object value = getter.invoke(t);

            if(getter.invoke(t) instanceof Instant)
                value = Timestamp.from((Instant) value);

            statement.setObject(i,
                    field.isAnnotationPresent(CustomType.class)
                            ? value.toString() : value
            );
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract T mapResultSet(ResultSet resultSet);
}
