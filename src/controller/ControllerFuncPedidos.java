package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.MySQlConnection;
import model.Pedidos;
import model.Produtos;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerFuncPedidos {

    @FXML
    private ImageView IVLogo;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBebidasAlcool;

    @FXML
    private Button btnCafes;

    @FXML
    private Button btnCozinha;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnDoces;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnFinalizar;

    @FXML
    private Button btnNovoPedido;

    @FXML
    private Button btnSnacks;

    @FXML
    private Button btnSumos;


    @FXML
    private TextArea taOBS;

    @FXML
    private ComboBox<Integer> cbQTD;

    //tabela pedidos
    @FXML
    private TableColumn<Pedidos, String> colProduto;

    @FXML
    private TableColumn<Pedidos, String> colQTD;

    @FXML
    private TableColumn<Pedidos, String> colValor;

    @FXML
    private TableView<Pedidos> tblPedido;

    //tabela produtos
    @FXML
    private TableView<Produtos> tblProdutos;

    @FXML
    private TableColumn<Produtos, String> colProdutos;

    @FXML
    private TableColumn<Produtos, String> colValorProduto;

    @FXML
    private TextField tfRetalho;

    @FXML
    private Label lbNumFunc;

    @FXML
    private Label lbPedido;

    private ObservableList<Produtos> listaProdutos;

    private ObservableList<Pedidos> listaPedidos;

    private ObservableList<Pedidos> pedidoEditado;

    private MySQlConnection connection;

    private Produtos linhaProduto;
    private Pedidos linhaPedido;

    private Pedidos pedido;

    private Pedidos pedidoFinal;

    private int tipo;

    private int numPdd=0;
    private int numFuncio=0;

    private int numMesa=0;

    public void initialize()
    {
        File file = new File("logo.png");
        Image image = new Image(file.toURI().toString());
        IVLogo.setImage(image);

        this.cbQTD.getItems().addAll(1,2,3,4,5,6,7,8,9);

        listaProdutos = FXCollections.observableArrayList();
        listaPedidos = FXCollections.observableArrayList();
        pedidoEditado = FXCollections.observableArrayList();

        this.colProdutos.setCellValueFactory(new PropertyValueFactory<Produtos,String>("produto"));
        this.colValorProduto.setCellValueFactory(new PropertyValueFactory<Produtos,String>("preco"));
        this.tblProdutos.setItems(listaProdutos);

        this.colProduto.setCellValueFactory(new PropertyValueFactory<Pedidos,String>("produto"));
        this.colQTD.setCellValueFactory(new PropertyValueFactory<Pedidos,String>("qtd"));
        this.colValor.setCellValueFactory(new PropertyValueFactory<Pedidos,String>("valor"));

        numPedido();


    }
    public void getNmMesa(int num) {
        numMesa= num;
    }

    void numPedido()  {
        connection = new MySQlConnection();
        ResultSet result = connection.getNumPedido();

        try {
            while(result.next()) {


                numPdd = result.getInt(1) + 1;
            }
        } catch (SQLException e) {
        e.printStackTrace();
    }

        String str = this.lbPedido.getText();
        this.lbPedido.setText(str + String.valueOf(numPdd));
    }

    void setNumFunc(String numFunc) throws SQLException {
        connection = new MySQlConnection();
        numFuncio = Integer.parseInt(numFunc);
        ResultSet result = connection.nomeFunc(numFunc);
        String nomeFunc="";
        while (result.next()) {
            nomeFunc = result.getString(1);
        }
        String str= this.lbNumFunc.getText();
        this.lbNumFunc.setText(str+" "+nomeFunc);
    }

    public void tabelaProdutos(int tipos)
    {
        connection = new MySQlConnection();
        ResultSet result = connection.getProduto(tipos);
        try {
            while (result.next()) {
                int id = result.getInt(1);
                String produto = result.getString(2);
                double preco = result.getDouble(3);
                Produtos p = new Produtos(id,produto, preco);
                this.listaProdutos.add(p);
            }
        }catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        this.tblProdutos.setItems(listaProdutos);
        this.tblProdutos.refresh();
    }

    public void getlinha()
    {
        linhaProduto=this.tblProdutos.getSelectionModel().getSelectedItem();
        String precoR=this.tfRetalho.getText();
        //fazer validaçao correta
        if(precoR.equals(""))
        {
            if(linhaProduto == null || this.cbQTD.getValue() == null) {
                alert(Alert.AlertType.ERROR,"ERRO!","Selecione todos os campos! (produto e quantidade)");
            }else {
                int idProduto = linhaProduto.getID();
                String produto = linhaProduto.getProduto();
                double preco = linhaProduto.getPreco();
                int qtd = this.cbQTD.getValue();
                String obs = this.taOBS.getText();
                pedido = new Pedidos(idProduto,produto, preco, qtd, obs,tipo);
                this.listaPedidos.add(pedido);
                System.out.println(tipo);
                linhaProduto = null;

            }
        }else
        {
            tipo=7;
            double preco = Double.parseDouble(precoR);
            pedido= new Pedidos(7,"Retalho",preco,1,"",tipo);
            this.listaPedidos.add(pedido);
            System.out.println(tipo);
            this.tfRetalho.setText("");
        }


    }
    @FXML
    void add(ActionEvent event) {
        getlinha();
        tblPedido.setItems(listaPedidos);
        this.cbQTD.setValue(null);
    }

    public void refreshTabela()
    {
        this.tblPedido.getItems().clear();
        this.tblPedido.setItems(listaPedidos);
    }


   /* @FXML
    void editar(ActionEvent event) {
        linhaPedido = this.tblPedido.getSelectionModel().getSelectedItem();
        if(linhaPedido==null)
        {
            alert(Alert.AlertType.ERROR,"ERRO!","Selecione uma linha do pedido!");
        }else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../vistas e controllers nao usados/EditProdutoPedidoView.fxml"));
                Parent root = loader.load();

                ControlllerEditProdutoPedido controller = loader.getController();
                pedidos novoEditado = controller.getProduto(linhaPedido);


                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setTitle("GESRES 1.0");
                //nao permitir maximizar tela
                stage.resizableProperty().setValue(Boolean.FALSE);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.setScene(scene);
                stage.showAndWait();

                this.listaPedidos.remove(linhaPedido);
                this.listaPedidos.add(novoEditado);
                this.tblPedido.setItems(listaPedidos);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }*/

    @FXML
    void eliminar(ActionEvent event) {
        Pedidos linhaDelete = this.tblPedido.getSelectionModel().getSelectedItem();
        this.listaPedidos.remove(linhaDelete);
    }

    @FXML
    void finalizar(ActionEvent event) throws SQLException {
        //recolhe id do funcionario
        int idFunc=0;
        ResultSet resultFunc = connection.getIdFunc(numFuncio);
        while (resultFunc.next())
        {
            idFunc = resultFunc.getInt(1);
        }

        if(this.listaPedidos.size() != 0) {
            //criar pedido
            connection.createPedido(numMesa, idFunc);

            //pega id do pedido
            int idPedido = 0;

            ResultSet result = connection.getNumPedido();

            try {
                while (result.next()) {


                    idPedido = result.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


            //adicionar na tabela de detalhes de pedido
            for (Pedidos p : listaPedidos) {
                //tirar dados da observeblelist
                pedidoFinal = new Pedidos(idPedido, p.getIdProduto(), p.getQtd(), p.getObs());
                connection.setPedidoDetalhes(pedidoFinal);
            }


            alert(Alert.AlertType.INFORMATION, "Obrigado!", "Pedido Finalizado");

            connection.trocaEstadoMesa(numMesa,"False");



            //mudar cor do butao da mesa
            /*String resultado="";
            while (resultMesa.next())
            {
                resultado = resultMesa.getString(1);
            }
            if(resultado.equals("False"))
            {
                //mudar cor do botao
                /*
                * usar num da mesa
                * */
            //}


        }else {
            alert(Alert.AlertType.WARNING,"Nenhum Pedido!","Não fez nenhum pedido!");
        }

        Stage stage = (Stage) this.btnFinalizar.getScene().getWindow();
        stage.close();
    }

    int getNlista()
    {
        return this.listaPedidos.size();
    }

    @FXML
    void preencheBebidasAlcool(ActionEvent event) {
        tipo=3;
        this.tblProdutos.getItems().clear();
        tabelaProdutos(tipo);

    }

    @FXML
    void preencheCafes(ActionEvent event) {
        tipo=1;
        this.tblProdutos.getItems().clear();
        tabelaProdutos(tipo);

    }

    @FXML
    void preencheCozinha(ActionEvent event) {
        tipo=4;
        this.tblProdutos.getItems().clear();
        tabelaProdutos(tipo);

    }

    @FXML
    void preencheDoces(ActionEvent event) {
        tipo=6;
        this.tblProdutos.getItems().clear();
        tabelaProdutos(tipo);

    }

    @FXML
    void preencheSnacks(ActionEvent event) {
        tipo=5;
        this.tblProdutos.getItems().clear();
        tabelaProdutos(tipo);
    }

    @FXML
    void preencheSumos(ActionEvent event) {
        tipo=2;
        this.tblProdutos.getItems().clear();
        tabelaProdutos(tipo);
    }

    public void alert(Alert.AlertType type, String tit, String texto)
    {
        Alert alerta=new Alert(type);
        alerta.setTitle(tit);
        alerta.setHeaderText(null);
        alerta.setContentText(texto);
        alerta.showAndWait();
    }


}

