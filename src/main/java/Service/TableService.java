package Service;

import Dao.Impl.TableOracleDAO;
import model.Row;

import java.io.File;
import java.util.List;

public class TableService {

    private TableOracleDAO tableDAO = new TableOracleDAO();

    public long generate(final String tableName, final String key, final String value){

        //File file = new File("tableName".concat(".java"));

        System.out.println(tableDAO.existColumns(tableName, key, value));


        return this.tableDAO.findCount("TB_TIPO_DOCUMENTO");
    }

//    private void createFile(final String tableName){
//        File file = new File(tableName.concat(".java"));
//    }

    private long findCount(final String tableName){
        return  this.tableDAO.findCount(tableName);
    }

    public List<Row> findAll(final String tableName, final String key, final String value){
        return this.tableDAO.findAll(tableName,key,value);
    }

    private boolean existTable(final String tableName){
        return this.tableDAO.existTable(tableName);
    }

    private boolean existColumns(final String tableName, final String key, final String value){
        return this.tableDAO.existColumns(tableName,key, value );
    }


}
