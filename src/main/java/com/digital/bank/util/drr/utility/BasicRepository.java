package com.digital.bank.util.drr.utility;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface BasicRepository<T> {
    List<T> findAll() throws SQLException;
    List<T> saveAll(List<T> toSave) throws SQLException;
    T save(T toSave) throws SQLException;
    T delete(String id) throws SQLException;
    T getById(String id) throws SQLException;
}
