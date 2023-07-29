package Service;

import model.Row;

import java.io.File;
import java.util.List;

public class GenerateEnumService {

    private GenerateEnumFileService generateEnumFileService = new GenerateEnumFileService();

    private TableService tableService = new TableService();


    public void generate(final String tableName, final String key, final String value) throws Exception {

        this.validate(tableName,key,value);
        String fileName = this.generateEnumFileService.constructorFileName(tableName);
        String absoluteFileName = generateEnumFileService.buildAbsoluteFileName(fileName);
        File file = this.generateEnumFileService.createFile(absoluteFileName);
        String EnumName = this.generateEnumFileService.constructorNameEnum(tableName);
        List<Row> rows = this.tableService.findAll(tableName,key,value);
        this.generateEnumFileService.writeFile(file,EnumName,rows);
    }

    private void validate(final String tableName,final String key, final String value) throws Exception {
        if (!this.tableService.existTable(tableName)){
            throw new Exception("A tabela " + tableName + " não foi encontrada no banco de dados");
        }
        if (!this.tableService.existColumns(tableName, key, value)){
            throw new Exception("As Colunas " + key + " e/ou " + value + " não formam encontradas na tabela " + tableName );
        }
    }
}
