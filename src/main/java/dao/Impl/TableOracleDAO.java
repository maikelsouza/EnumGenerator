package dao.Impl;


import config.ConnectionFactory;
import dao.ITable;
import model.Row;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TableOracleDAO implements ITable {

    @Override
    public List<Row> findAll(final String tableName, final String key, final String value) throws Exception {

        List<Row> rows = new ArrayList<>();
        Row row;
        var query = "SELECT * FROM " + tableName + " ORDER BY " + value;
        try (Connection connection = ConnectionFactory.createConnection();  PreparedStatement preparedStatement  = connection.prepareStatement(query)  ) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                row = new Row();
                row.setKey(resultSet.getString(key.toUpperCase()));
                row.setValue(resultSet.getString(value.toUpperCase()));
                rows.add(row);
            }
        }
        return rows;
    }


    @Override
    public boolean existTable(final String tableName) throws Exception {

        var query = "SELECT * FROM ALL_TABLES WHERE TABLE_NAME = '" + tableName + "'";
        try (Connection connection = ConnectionFactory.createConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            var resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
    }


    @Override
    public boolean existColumns(final String tableName, final String key, final String value) throws Exception{

        boolean existKey = false;
        boolean existValue = false;
        var query = "SELECT * FROM " + tableName;

        try (Connection connection = ConnectionFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columns = metaData.getColumnCount();
            for (int i = 1; i <= columns; i++ ){
                if (metaData.getColumnName(i).equals(key)){
                    existKey = true;
                }else if (metaData.getColumnName(i).equals(value)){
                    existValue = true;
                }
            }
        }
        return existKey && existValue;
    }
}
