package Service;

import Util.FileUtil;
import model.Row;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;
import java.util.List;
import java.util.Objects;

public class GenerateEnumFileService {

    private static final String DOT_JAVA = ".java";

    private static final String ENUM = "Enum";

    private static final String UNDERLINE = "_";

    private static final Integer QUANTITY_CHARACTER_REMOVE_PREFIX = 3;

    private static final Integer TYPE_PROPERTY_STRING = 0;

    private static final Integer TYPE_PROPERTY_LONG = 1;

    private static final String DIRECTORY_PATH_ENUM = "src"+ File.separator+"main"+File.separator+"java"+File.separator+"Enuns";


    public File createFile(final String tableName){
        return new File(tableName);
    }

    public String buildAbsoluteFileName(final String fileName){
        String pathProject = System.getProperty("user.dir");
        return pathProject+File.separator+DIRECTORY_PATH_ENUM+File.separator+fileName;
    }

    public void writeFile(File file, String enumName, List<Row> rows, final Integer typeProperty){

        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(file);
            bw = new BufferedWriter( fw );
            writePackageName(bw);
            bw.newLine();
            bw.newLine();
            writeImports(bw);
            bw.newLine();
            bw.newLine();
            writeAnnotations(bw);
            bw.newLine();
            writeNameEnum(bw, enumName);
            bw.newLine();
            bw.newLine();
            for (Row row: rows) {
                writeKeyAndValueEnum(bw, row.getKey(), row.getValue(), typeProperty, rows.get(rows.size()-1).getKey());
                bw.newLine();
                bw.newLine();
            }
            bw.newLine();
            writePropertieEnum(bw,typeProperty);
            bw.newLine();
            bw.write( "}" );
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    if (bw != null){
                        bw.close();
                    }
                    if (fw != null){
                        fw.close();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    public String constructorFileName(final String tableName){

        String fileName = constructorNameEnum(tableName);
        return  fileName.concat(DOT_JAVA);
    }

    public String constructorNameEnum(String tableName) {

        String fileName = FileUtil.removePrefix(tableName,QUANTITY_CHARACTER_REMOVE_PREFIX);
        fileName = FileUtil.transformCamelCase(fileName,UNDERLINE);
        return fileName.concat(ENUM);
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
        bw.write( "@Getter" );
    }

    private void writeNameEnum( BufferedWriter bw, final String enumName ) throws IOException {
        bw.write( "public enum " );
        bw.write( enumName );
        bw.write( "{ " );
    }

    private void writeKeyAndValueEnum( BufferedWriter bw, final String key, final String valeu,
                                       final Integer typeProperty, final String lastKey) throws IOException {
        bw.write("    ");
        bw.write(treatData(key));
        if (Objects.equals(typeProperty, TYPE_PROPERTY_LONG)) {
            bw.write( "(" );
            bw.write(valeu);
            bw.write("L)");
        }
        if (Objects.equals(typeProperty, TYPE_PROPERTY_STRING)) {
            bw.write( "(\"" );
            bw.write(valeu);
            bw.write("\")");
        }
        if (key.equals(lastKey)){
            bw.write(";");
        }else{
            bw.write(",");
        }

    }

    private void writePropertieEnum( BufferedWriter bw, final Integer typeProperty) throws IOException {
        bw.write("    ");
        if (Objects.equals(typeProperty,TYPE_PROPERTY_LONG)){
            bw.write("private final Long id;");
        }
        if (Objects.equals(typeProperty,TYPE_PROPERTY_STRING)){
            bw.write("private final String id;");
        }
    }

    private String treatData(String str){
        str = this.upperCase(str);
        str = this.removeAccents(str);
        str = this.replaceBlankSpaceForUnderscore(str);
        str = this.replaceSlashForUnderscore(str);
        str = this.removeOpenParentheses(str);
        str = this.removeCloseParentheses(str);
        str = this.removeComma(str);
        str = this.removeDot(str);
        str = this.removeTwoPoint(str);
        str = this.replaceAmpersandForUnderscoreEUnderscore(str);
        return this.replaceDashParenthesesForUnderscore(str);
    }

    public  String removeAccents(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    private String replaceBlankSpaceForUnderscore(String str){
        return str.replace(" ","_");
    }
    private String replaceSlashForUnderscore(String str){
        return str.replace("/","_");
    }
    private String removeOpenParentheses(String str){
        return str.replace("(","");
    }
    private String removeCloseParentheses(String str){ return str.replace(")",""); }
    private String removeComma(String str){ return str.replace(",",""); }
    private String removeTwoPoint(String str){ return str.replace(":",""); }
    private String removeDot(String str){ return str.replace(".",""); }
    private String upperCase(String str){ return str.toUpperCase(); }
    public String replaceDashParenthesesForUnderscore(String str){
        return str.replace("-","_");
    }
    public  String replaceAmpersandForUnderscoreEUnderscore(String str){
        return str.replace("&","_E_");
    }
}
