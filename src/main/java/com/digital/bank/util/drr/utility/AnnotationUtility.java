package com.digital.bank.util.drr.utility;

import com.digital.bank.util.drr.annotation.Column;
import com.digital.bank.util.drr.annotation.Model;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AnnotationUtility {

    public static boolean isAnnotatedWith(Class<?> element, Class<? extends Annotation> annotation){
        return element.isAnnotationPresent(annotation);
    }

    public static String getTableName(Class<?> clazz){
        Model modelAnnotation = clazz.getAnnotation(Model.class);
        if (modelAnnotation != null) {
            return modelAnnotation.table();
        }
        throw new IllegalArgumentException("Class is not annotated with @Model");
    }

    public static List<String> getColumns(Class<?> clazz) {
        List<String> columns = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                columns.add(field.getAnnotation(Column.class).name());
            }
        }

        return columns;
    }

    public static Method getMethod(Class<?> clazz, String columnName, String methodType) throws NoSuchMethodException {
         Field field = getAnnotatedField(clazz, columnName);
            Class<?> parameterType = field.getType();

            String methodName = methodType.equals("setter") ? "set" : "get";
            methodName += ucFirst(field.getName());

            return
                    methodType.equals("setter") ? clazz.getMethod(methodName, parameterType) : clazz.getMethod(methodName);
    }

    public static String ucFirst(String entry){
        return Character.toUpperCase(entry.charAt(0)) + entry.substring(1);
    }

    public static Field getAnnotatedField(Class<?> clazz, String columnName){
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class) && field.getAnnotation(Column.class).name().equals(columnName)) {
                return field;
            }
        }
        throw new IllegalArgumentException("Field with column name " + columnName + " not found");
    }

    public static Field getField(Class<?> clazz, Class<? extends Annotation> annotationClass){
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(annotationClass)) {
                return field;
            }
        }
        throw new IllegalArgumentException("Field with annotation " + annotationClass.getSimpleName() + " not found");
    }
}
