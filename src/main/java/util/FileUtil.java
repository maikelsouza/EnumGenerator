package util;

public class FileUtil {


    public static String removePrefix(final String nameFile, Integer characterQuantity){
        return  characterQuantity > 0 ? nameFile.substring(characterQuantity) : nameFile;
    }

    public static String transformCamelCase(String fileName, String delimiter){

        var fileNameTransformedCamelCase = new StringBuilder();
        for (String PartFileName : fileName.split(delimiter)) {
            fileNameTransformedCamelCase.append(Character.toUpperCase(PartFileName.charAt(0)));
            if (PartFileName.length() > 1) {
                fileNameTransformedCamelCase.append(PartFileName.substring(1).toLowerCase());
            }
        }
        return fileNameTransformedCamelCase.toString();
    }

}
