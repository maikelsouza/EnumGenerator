package org.example;

import Config.ConnectionFactory;
import Service.EnumGenerateService;

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




    public static void main(String[] args){

        EnumGenerateService enumGenerateService = new EnumGenerateService();
        try {


        File file = new File("EstadoEnum.java");



        boolean newFile = file.createNewFile();




            Connection connection = null;

            connection = ConnectionFactory.createConnection();

        System.out.println(connection);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


            StringBuffer query = new StringBuffer();

            int total = 0;
//            while (resultSet.next()){
//                total = resultSet.getInt("maikel");
//            }

            System.out.println("total "+ enumGenerateService.generate("TB_TIPO_DOCUMENTO","NO_TIPO_DOCUMENTO", "ID_TIPO_DOCUMENTO" ));





        query = new StringBuffer();
        //query.append("SELECT * FROM (SELECT * FROM  TB_TIPO_DOCUMENTO)   where rownum <= 3");
        query.append("SELECT * FROM  TB_TIPO_DOCUMENTO ORDER BY ID_TIPO_DOCUMENTO");
            preparedStatement  = connection.prepareStatement(query.toString());

        resultSet = preparedStatement.executeQuery();

            FileWriter fw = new FileWriter( file );
            BufferedWriter bw = new BufferedWriter( fw );

            bw.write( "package DAO;" );
            bw.newLine();
            bw.write( "public enum EstadoEnum { " );
            bw.newLine();



        while(resultSet.next()){
            System.out.println("CHAVE: "+removerAcentos(resultSet.getString("NO_TIPO_DOCUMENTO")));
            System.out.println("VALOR: "+resultSet.getString("ID_TIPO_DOCUMENTO"));
            System.out.println("ROW "+ resultSet.getRow());

            bw.write( removerAcentos(resultSet.getString("NO_TIPO_DOCUMENTO")));
//            bw.write( "(\"");
            bw.write( "(");
            bw.write( resultSet.getString("ID_TIPO_DOCUMENTO"));

            if (total == resultSet.getRow()){
                bw.write( "L);");
            }else{
                bw.write( "L),");
            }

            //bw.write( "\"),");
            bw.newLine();

        }

            bw.write( "}" );
            bw.close();
            fw.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



    public static String removerAcentos(String str) {
        return replaceCloseParenthesesForUnderscore5(replaceCloseParenthesesForUnderscore(replaceOpenParenthesesForUnderscore(replaceSlashForUnderscore(replaceBlankForUnderscore(Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""))))));
    }

    public static String replaceBlankForUnderscore(String str){
        return str.replace(" ","_");
    }

    public static String replaceSlashForUnderscore(String str){
        return str.replace("/","_");
    }

    public static String replaceOpenParenthesesForUnderscore(String str){
        return str.replace("(","");
    }

    public static String replaceCloseParenthesesForUnderscore(String str){
        return str.replace(")","");
    }

    public static String replaceCloseParenthesesForUnderscore5(String str){
        return str.replace("-","_");
    }







//    public List<Condominio> buscarListaCondominios() throws SQLException, Exception{
//        List<Condominio> listaCondominio = new ArrayList<Condominio>();
//        List<UsuarioCondominio> listaUsuarioCondominio = this.usuarioCondominioDAO.get().buscarListaPorUsuario(AplicacaoUtil.getUsuarioAutenticado());
//        if(!listaUsuarioCondominio.isEmpty()){
//            StringBuffer query = new StringBuffer();
//            query.append("SELECT * FROM ");
//            query.append(CONDOMINIO);
//            query.append(" WHERE ");
//            query.append(ID);
//            query.append(" in (");
//            for (int i = 1; i <= listaUsuarioCondominio.size(); i++) {
//                if(i != listaUsuarioCondominio.size()){
//                    query.append("?,");
//                }
//            }
//            query.append("?)");
//            query.append(" ORDER BY ");
//            query.append(NOME);
//            query.append(";");
//            Connection con = C3P0DataSource.getInstance().getConnection();
//            PreparedStatement preparedStatement = null;
//            ResultSet resultSet = null;
//            Condominio condominio = null;
//            try {
//                Integer quantidadeInterrogacao = 0;
//                preparedStatement = con.prepareStatement(query.toString());
//                Iterator<UsuarioCondominio>  iteratorListaUsuarioCondominio =  listaUsuarioCondominio.iterator();
//                while(iteratorListaUsuarioCondominio.hasNext()){
//                    UsuarioCondominio usuarioCondominio = iteratorListaUsuarioCondominio.next();
//                    SQLUtil.setValorPpreparedStatement(preparedStatement, ++quantidadeInterrogacao, usuarioCondominio.getIdCondominio(), java.sql.Types.INTEGER);
//                }
//                resultSet = preparedStatement.executeQuery();
//                while(resultSet.next()){
//                    condominio = new Condominio();
//                    condominio.setId((Integer) SQLUtil.getValorResultSet(resultSet, ID, java.sql.Types.INTEGER));
//                    condominio.setNome(String.valueOf(SQLUtil.getValorResultSet(resultSet, NOME, java.sql.Types.VARCHAR)));
//                    condominio.setSituacao((Integer) SQLUtil.getValorResultSet(resultSet, SITUACAO, java.sql.Types.INTEGER));
//                    condominio.setCnpj((Long) SQLUtil.getValorResultSet(resultSet, CNPJ, java.sql.Types.BIGINT));
//                    condominio.setTelefoneCelular((Long) SQLUtil.getValorResultSet(resultSet, TELEFONE_CELULAR, java.sql.Types.BIGINT));
//                    condominio.setTelefoneFixo((Long) SQLUtil.getValorResultSet(resultSet, TELEFONE_FIXO, java.sql.Types.BIGINT));
//                    listaCondominio.add(condominio);
//                }
//            } catch (SQLException e) {
//                throw e;
//            } catch (Exception e) {
//                throw e;
//            }finally{
//                try {
//                    preparedStatement.close();
//                    con.close();
//                } catch (SQLException e) {
//                    logger.error("erro sqlstate "+e.getSQLState(), e);
//                }
//            }
//        }
//        return listaCondominio;
//    }
}