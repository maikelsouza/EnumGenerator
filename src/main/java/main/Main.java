package main;

import service.GenerateEnumService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) throws Exception {

        var generateEnumService = new GenerateEnumService();

        List<String> datasEnum = getDatasEnum();
        System.out.println();
        Integer typeProperty = getTypeValueProperty();
        System.out.println("** Generating Enum **");

        String enumName = generateEnumService.generate(datasEnum.get(0), datasEnum.get(1), datasEnum.get(2), typeProperty);

        System.out.println("** The Enum: "+ enumName + " was generated **");
    }

    private static List<String> getDatasEnum(){
        System.out.println("***** Enter with datas for to create an Enum: ***** ");
        List<String> datasEnum = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter table name: ");
        String dataTable = sc.next();
        System.out.println("Table Name: ".concat(dataTable));
        datasEnum.add(dataTable);
        System.out.print("Enter key name of table ".concat(dataTable).concat(": "));
        String data = sc.next();
        System.out.println("Key Name: ".concat(data));
        datasEnum.add(data);
        System.out.print("Enter valeu name of table ".concat(dataTable).concat(": "));
        data = sc.next();
        System.out.println("Value Name: ".concat(data));
        datasEnum.add(data);
        return datasEnum;
    }

    private static Integer getTypeValueProperty(){
        System.out.println("***** The type of the Enum value property: 0 or 1: ***** ");

        Scanner sc = new Scanner(System.in);
        var typeProperty = "";
        do{
            System.out.print("Enter 0 for String or 1 for Long: ");
            typeProperty = sc.next();
            System.out.println("Value type property: "+typeProperty);
            if (!typeProperty.equals("0") && !typeProperty.equals("1")){
                System.out.println("Error: Value type property is different than 0 or 1  ");
            }
        }while (!typeProperty.equals("0") && !typeProperty.equals("1"));
        return Integer.valueOf(typeProperty);
    }

}