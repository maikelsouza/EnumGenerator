package org.example;

import Config.ConnectionFactory;
import Service.GenerateEnumService;
import Service.TableService;
import Util.FileUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Normalizer;


public class Main {


    public static void main(String[] args) throws IOException {

        var generateEnumService = new GenerateEnumService();

        try {

            //generateEnumService.generate("TB_TIPO_DOCUMENTO", "NO_TIPO_DOCUMENTO", "ID_TIPO_DOCUMENTO" );
            //generateEnumService.generate("TB_ESPECIE_ARMA", "NO_ESPECIE_ARMA", "ID_ESPECIE_ARMA" );
            //generateEnumService.generate("TB_DISCIPLINA", "NO_DISCIPLINA", "ID_DISCIPLINA" );
            //generateEnumService.generate("TB_CURSO", "DS_CURSO", "ID_CURSO" );
            generateEnumService.generate("TB_SITUACAO_PESSOA", "DS_SITUACAO_PESSOA", "ID_SITUACAO_PESSOA" );
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}