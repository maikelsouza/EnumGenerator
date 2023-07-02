package Service;

import Util.FileUtil;
import model.Row;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GenerateEnumFileService {

    private static final String DOT_JAVA = ".java";

    private static final String ENUM = "Enum";

    private static final String UNDERLINE = "_";

    private static final Integer QUANTITY_CHARACTER_REMOVE_PREFIX = 3;

    private static final Integer KIND_OF_STRING = 0;

    private static final Integer KIND_OF_LONG = 1;

    private TableService tableService;


    public void createFile(final String tableName){

        String fileName = this.constructorFileName(tableName);

        var file = new File(tableName);
    }

    private void writeFile(File file, String fileName, List<Row> rows) throws IOException {

        var fw = new FileWriter(file);
        var bw = new BufferedWriter( fw );

        writePackageName(bw);
        bw.newLine();
        bw.newLine();
        writeImports(bw);
        bw.newLine();
        bw.newLine();
        writeAnnotations(bw);
        writeNameEnum(bw, fileName);
        bw.newLine();
        bw.newLine();

        for (Row row: rows) {
            writeKeyAndValueEnum(bw, row.getKey(), row.getValue(),KIND_OF_LONG);
            bw.newLine();
        }
        bw.newLine();
        writePropertieEnum(bw,KIND_OF_LONG);
        bw.write( "}" );
        bw.close();
        fw.close();
    }

    private String constructorFileName(final String tableName){

        String fileName = FileUtil.removePrefix(tableName,QUANTITY_CHARACTER_REMOVE_PREFIX);
        fileName = FileUtil.transformCamelCase(fileName,UNDERLINE);
        fileName = fileName.concat(ENUM).concat(DOT_JAVA);

        return  fileName;
    }

    private void writePackageName( BufferedWriter bw ) throws IOException {
        bw.write( "package Enuns;" );
    }

    private void writeImports( BufferedWriter bw ) throws IOException {
        bw.write( "import lombok.AllArgsConstructor;" );
        bw.newLine();
        bw.write( "import lombok.Getter;" );
    }

    private void writeAnnotations( BufferedWriter bw ) throws IOException {
        bw.write( "@AllArgsConstructor" );
        bw.newLine();
        bw.write( "@Getter;" );
    }

    private void writeNameEnum( BufferedWriter bw, final String enumName ) throws IOException {
        bw.write( "public enum " );
        bw.write( enumName );
        bw.write( "{ " );
    }

    private void writeKeyAndValueEnum( BufferedWriter bw, final String key, final String valeu, final Integer type) throws IOException {
        bw.write(key);
        bw.write( "(" );
        bw.write(valeu);
        if (type == KIND_OF_LONG) {
            bw.write("L)");
        }
        if (type == KIND_OF_STRING) {
            bw.write(");");
        }
    }

    private void writePropertieEnum( BufferedWriter bw, final Integer type ) throws IOException {
        if (type == KIND_OF_LONG){
            bw.write("private Long id;");
        }
        if (type == KIND_OF_STRING){
            bw.write("private String id;");
        }
    }
}
