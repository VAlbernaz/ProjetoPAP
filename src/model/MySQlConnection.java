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

    public ResultSet getFornecedor(){
        ResultSet result = null;
        String sql = "SELECT idfornecedor,fornecedor " +
                "FROM fornecedor;";
        try {
            Statement s = connection.createStatement();
            result = s.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public ResultSet getTipo(){
        ResultSet result = null;
        String sql = "SELECT idtipo,tipo \n" +
                "FROM tipo;";
        try {
            Statement s = connection.createStatement();
            result = s.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public ResultSet getIdFornecedor(String fornecedor){
        ResultSet result = null;
        String sql = "SELECT * \n" +
                "FROM fornecedor\n" +
                "WHERE fornecedor = \"" + fornecedor +"\";";
        try {
            Statement s = connection.createStatement();
            result = s.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
    public ResultSet getIdTipo(String tipo){
        ResultSet result = null;
        String sql = "SELECT * FROM tipo\n" +
                "WHERE tipo= \"" + tipo +"\";";
        try {
            Statement s = connection.createStatement();
            result = s.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public boolean inserirProduto(produtos p)
    {
        try {
            //preprar a inserção da nova linha
            String sql = "INSERT INTO produto (produto,preco,idfornecedor,idtipo) VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, p.getProduto());
            statement.setDouble(2, p.getPreco());
            statement.setInt(3, p.getnFornecedor());
            statement.setInt(4, p.getnTipo());
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
    public ResultSet getProdutos()
    {
        ResultSet result = null;
        String sql = "SELECT idproduto,produto,qtd FROM gesres.produto;";
        try {
            Statement s = connection.createStatement();
            result = s.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }
    public boolean esditQtd(produtos p) {
        try {
            String sql = "UPDATE produto SET produto.qtd = (?) WHERE produto.idproduto=" + p.getID();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, String.valueOf(p.getQtd()));

            int linhas = statement.executeUpdate();
            if (linhas == 1) {
                return true;
            } else return false;



        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

    }
}

