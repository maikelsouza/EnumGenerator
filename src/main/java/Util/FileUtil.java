package Util;

public class FileUtil {


    public static String removePrefix(final String nameFile, Integer characterQuantity){
        return  characterQuantity > 0 ? nameFile.substring(characterQuantity) : nameFile;
    }

    public static String transformCamelCase(String fileName, String delimiter){

        var fileNameTransformedCamelCase = new StringBuffer();
        for (String PartFileName : fileName.split(delimiter)) {
            fileNameTransformedCamelCase.append(Character.toUpperCase(PartFileName.charAt(0)));
            if (PartFileName.length() > 1) {
                fileNameTransformedCamelCase.append(PartFileName.substring(1, PartFileName.length()).toLowerCase());
            }
        }
        return fileNameTransformedCamelCase.toString();
    }

}
