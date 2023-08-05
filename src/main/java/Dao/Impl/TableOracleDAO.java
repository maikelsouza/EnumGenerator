package Dao.Impl;


import Config.ConnectionFactory;
import model.Row;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TableOracleDAO implements ITable {


    public long findCount(final String tableName)  {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        long count = 0;
        try {
            connection = ConnectionFactory.createConnection();
            String query = "SELECT count(*) as countElements FROM " + tableName;
            preparedStatement  = connection.prepareStatement(query);
            var resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                count = resultSet.getLong("countElements");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    public List<Row> findAll(final String tableName, final String key, final String value)  {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Row> rows = new ArrayList<>();
        Row row;
        try {
            connection = ConnectionFactory.createConnection();
            String query = "SELECT * FROM " + tableName + " ORDER BY " + value;
            preparedStatement  = connection.prepareStatement(query);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                row = new Row();
                row.setKey(resultSet.getString(key.toUpperCase()));
                row.setValue(resultSet.getString(value.toUpperCase()));
                rows.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rows;
    }


    @Override
    public boolean existTable(final String tableName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean exist = false;
        try {
            connection = ConnectionFactory.createConnection();
            String query = "SELECT * FROM ALL_TABLES WHERE TABLE_NAME = '" + tableName +"'";
            preparedStatement = connection.prepareStatement(query);
            var resultSet = preparedStatement.executeQuery();
            exist = resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return exist;
    }

    public boolean existColumns(final String tableName, final String key, final String value) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean existKey = false;
        boolean existValue = false;
        try {
            connection = ConnectionFactory.createConnection();
            String query = "SELECT * FROM " + tableName;
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columns = metaData.getColumnCount();
            for (int i = 1; i <= columns; i++ ){
                if (metaData.getColumnName(i).equals(key)){
                    existKey = true;
                }else if (metaData.getColumnName(i).equals(value)){
                    existValue = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return existKey && existValue;
    }
}
