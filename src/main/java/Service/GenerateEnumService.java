package Service;

import model.Row;

import java.io.File;
import java.util.List;

public class GenerateEnumService {

    private GenerateEnumFileService generateEnumFileService = new GenerateEnumFileService();

    private TableService tableService = new TableService();


    public String generate(final String tableName, final String key, final String value, final Integer typeProperty) throws Exception {

        this.validate(tableName,key,value);
        String fileName = this.generateEnumFileService.constructorFileName(tableName);
        String absoluteFileName = generateEnumFileService.buildAbsoluteFileName(fileName);
        File file = this.generateEnumFileService.createFile(absoluteFileName);
        String enumName = this.generateEnumFileService.constructorNameEnum(tableName);
        List<Row> rows = this.tableService.findAll(tableName,key,value);
        this.generateEnumFileService.writeFile(file,enumName,rows, typeProperty);
        return enumName;
    }

    private void validate(final String tableName,final String key, final String value) throws Exception {
        if (!this.tableService.existTable(tableName)){
            throw new Exception("Error to generate enum: The table \"" + tableName + "\" is not found in the database");
        }
        if (!this.tableService.existColumns(tableName, key, value)){
            throw new Exception("Error to generate enum: The Columns \"" + key + "\" and/or \"" + value + "\" is not found in the database in the table \"" + tableName + "\"");
        }
    }
}
