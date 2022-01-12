package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class MySQlConnection {
    //atributos
    private Properties p;
    private Connection connection;

    //construtor
    public MySQlConnection() {
        setConnection();
    }

    //metodo para fazer a ligaçao
    public void setConnection() {
        p = new Properties();
        try {
            InputStream input = new FileInputStream("dbConfig.properties");
            p.load(input);
            connection = DriverManager.getConnection(p.getProperty("url"), p.getProperty("username"), p.getProperty("password"));
            System.out.println("Ligado à DB");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            System.out.println("Ocorreu um problema");
        }
    }
    //Metodo para pesquisar na base de dados
    public ResultSet getFunc() {
        ResultSet result = null;
        String sql = "SELECT idfuncionario, CONCAT(primNome,' ',ultiNome) AS nome, atividade, numFunc\n" +
                "FROM funcionario;";
        try {
            Statement s = connection.createStatement();
            result = s.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public ResultSet getProduto(int id){
        ResultSet result = null;
        String sql = "SELECT produto,preco\n" +
                "FROM produto\n" +
                "WHERE idtipo="+id+";";
        try {
            Statement s = connection.createStatement();
            result = s.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public boolean editarPessoa(funcionarios f) {
        ResultSet result = null;
        try {
            String sql = "UPDATE funcionario SET funcionario.atividade = (?) WHERE funcionario.idfuncionario = " + f.getID();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, f.getAtividadeEdit());

            int linhas = statement.executeUpdate();
            if (linhas == 1) {
                return true;
            } else return false;



        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

    }

    public boolean inserirFuncionario(funcionarios f) {
        try {
            //preprar a inserção da nova linha
            String sql = "INSERT INTO funcionario (primNome,ultiNome,dataNascimento,sexo,numFunc) VALUES (?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, f.getPrimNome());
            statement.setString(2, f.getUltiNome());
            statement.setString(3, String.valueOf(f.getDataNascimento()));
            statement.setString(4, String.valueOf(f.getSexo()));
            statement.setInt(5, f.getNumFunc());
            //executar a inserção
            int linhas = statement.executeUpdate();
            if (linhas == 1) {
                return true;

            } else return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    public ResultSet verificaNumFunc(String numFunc){
        ResultSet result = null;
        String sql = "SELECT numFunc\n" +
                "FROM funcionario\n" +
                "WHERE numFunc ="+ Integer.parseInt(numFunc)+";";
        try {
            Statement s = connection.createStatement();
            result = s.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
    public ResultSet nomeFunc(String numFunc){
        ResultSet result = null;
        String sql = "SELECT CONCAT(primNome, \" \",ultiNome)\n" +
                "FROM funcionario\n" +
                "WHERE numFunc ="+ Integer.parseInt(numFunc)+";";
        try {
            Statement s = connection.createStatement();
            result = s.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}

