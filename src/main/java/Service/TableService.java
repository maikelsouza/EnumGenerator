package Service;

import Dao.Impl.TableOracleDAO;
import model.Row;

import java.util.List;

public class TableService {

    private final TableOracleDAO tableDAO = new TableOracleDAO();

    public List<Row> findAll(final String tableName, final String key, final String value){
        return this.tableDAO.findAll(tableName,key,value);
    }

    public boolean existTable(final String tableName){
        return this.tableDAO.existTable(tableName);
    }

    public boolean existColumns(final String tableName, final String key, final String value){
        return this.tableDAO.existColumns(tableName,key, value );
    }

}
