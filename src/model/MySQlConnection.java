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
        String sql = "SELECT idproduto,produto,preco\n" +
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

    public boolean editarPessoa(Funcionarios f) {
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

    public boolean inserirFuncionario(Funcionarios f) {
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

    public boolean inserirProduto(Produtos p)
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
    public boolean esditQtd(Produtos p) {
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

    public ResultSet getNumPedido()
    {
        ResultSet result = null;
        String sql = "SELECT MAX(idpedidos)\n" +
                "FROM pedidos;";
        try {
            Statement s = connection.createStatement();
            result = s.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }


    public boolean createPedido(int mesa, int func)
    {
        try {
            //preprar a inserção da nova linha
            String sql = "INSERT INTO pedidos(mesa,dataHora,idfuncionario)\n" +
                    "VALUES(?,CURRENT_TIMESTAMP,?);\n";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, mesa);
            statement.setInt(2, func);

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

    public boolean setPedidoDetalhes(Pedidos p)
    {
        try {
            //preprar a inserção da nova linha
            String sql = "INSERT INTO detalhespedidos(idpedidos,idproduto,qtd,obs,preco)\n" +
                    "VALUES(?,?,?,?,?);\n";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, p.getId());
            statement.setInt(2, p.getIdProduto());
            statement.setInt(3, p.getQtd());
            statement.setString(4, p.getObs());
            statement.setDouble(5, p.getValor());

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
    public ResultSet getIdFunc(int numFunc){
        ResultSet result = null;
        String sql = "SELECT idfuncionario\n" +
                "FROM funcionario\n" +
                "WHERE numFunc ="+ numFunc+";";
        try {
            Statement s = connection.createStatement();
            result = s.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public ResultSet getNumPedidoMesa(int nMesa) {
        ResultSet result=null;
        String sql = "SELECT IF((SELECT mesas.disponibilidade FROM mesas WHERE idmesas="+nMesa+") = 'False', (SELECT MAX(idPedidos) FROM pedidos p WHERE p.mesa="+nMesa+"), (SELECT null));";
        try {
            Statement s = connection.createStatement();
            result = s.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  result;
    }

    public Boolean trocaEstadoMesa(int numMesa, String estado) {
        ResultSet result = null;
        try{
        String sql = "UPDATE mesas SET mesas.disponibilidade = (\""+estado+"\") WHERE mesas.idmesas ="+numMesa+";";

        PreparedStatement statement = connection.prepareStatement(sql);



            int linhas = statement.executeUpdate();
            if (linhas == 1) {
                return true;

            } else return false;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

    }
    public ResultSet getFaturas(String sql)
    {
        ResultSet result = null;
        try {
            Statement s = connection.createStatement();
            result = s.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
    public ResultSet getDetPedidos(int idPedido)
    {
        ResultSet result = null;
        String sql = "SELECT p.produto, dp.qtd, dp.preco, CONCAT(f.primNOme , ' ', f.ultiNome)\n" +
                "FROM pedidos pd, detalhespedidos dp, produto p, funcionario f\n" +
                "WHERE pd.idpedidos= dp.idpedidos\n" +
                "AND p.idproduto = dp.idproduto\n" +
                "AND f.idfuncionario = pd.idfuncionario\n" +
                "AND pd.idpedidos =" + idPedido +";";
        try {
            Statement s = connection.createStatement();
            result = s.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public ResultSet getValorPedido(int numPedido,int numMesa)
    {
        ResultSet result = null;
        String sql = "SELECT IF((SELECT mesas.disponibilidade FROM mesas WHERE idmesas="+numMesa+") = 'False', " +
                "(SELECT SUM(dp.preco*dp.qtd) FROM detalhespedidos dp WHERE dp.idpedidos="+numPedido+"), " +
                "(SELECT null));";
        try {
            Statement s = connection.createStatement();
            result = s.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
    public ResultSet getFormasPagamento()
    {
        ResultSet result = null;
        String sql = "SELECT * FROM tipospagamento;";
        try {
            Statement s = connection.createStatement();
            result = s.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
    public ResultSet getIdFormaPagamento(String tipo)
    {
        ResultSet result = null;
        String sql = "\n" +
                "SELECT idtipospagamento\n" +
                "FROM tipospagamento\n" +
                "WHERE tipopagamento =(\""+tipo+"\");";
        try {
            Statement s = connection.createStatement();
            result = s.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
    public boolean setFatura(Pagamento p)
    {
        try {
            //preprar a inserção da nova linha
            String sql = "INSERT INTO faturas(idpedidos,idtipospagamento,contribuinte)\n" +
                    "VALUES (?,?,?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, p.getIdPedido());
            statement.setInt(2, p.getIdForma());
            statement.setString(3, p.getContribuinte());

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

}

