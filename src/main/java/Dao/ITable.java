package Dao;

import model.Row;

import java.util.List;

public interface ITable {


    boolean existTable(final String tableName) throws Exception;

    List<Row> findAll(final String tableName, final String key, final String value) throws Exception;

    boolean existColumns(final String tableName, final String key, final String value) throws Exception;
}
