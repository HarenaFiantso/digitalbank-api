package com.digital.bank.util.drr.utility;

public enum PgQuery {
    SELECT("select %s from \"%s\""),
    INSERT("insert into \"%s\" (%s) values (%s) returning *"),
    UPDATE("update %s set %s where %s = ? returning *"),
    SELECT_BY_COLUMN("select %s from \"%s\" where %s"),
    DELETE_BY_COLUMN("delete from %s where %s returning *");

    public final String value;

    PgQuery(String value){
        this.value = value;
    }
}
