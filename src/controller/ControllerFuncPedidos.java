package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.MySQlConnection;
import model.pedidos;
import model.produtos;

import java.io.File;
import java.io.IOException;
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
    private TableColumn<pedidos, String> colProduto;

    @FXML
    private TableColumn<pedidos, String> colQTD;

    @FXML
    private TableColumn<pedidos, String> colValor;

    @FXML
    private TableView<pedidos> tblPedido;

    //tabela produtos
    @FXML
    private TableView<produtos> tblProdutos;

    @FXML
    private TableColumn<produtos, String> colProdutos;

    @FXML
    private TableColumn<produtos, String> colValorProduto;

    @FXML
    private TextField tfRetalho;

    @FXML
    private Label lbNumFunc;

    private ObservableList<produtos> listaProdutos;

    private ObservableList<pedidos> listaPedidos;

    private MySQlConnection connection;

    private produtos linhaProduto;
    private pedidos linhaPedido;

    private int tipo;
    public void initialize()
    {
        File file = new File("logo.png");
        Image image = new Image(file.toURI().toString());
        IVLogo.setImage(image);

        this.cbQTD.getItems().addAll(1,2,3,4,5,6,7,8,9);

        listaProdutos = FXCollections.observableArrayList();
        listaPedidos = FXCollections.observableArrayList();

        this.colProdutos.setCellValueFactory(new PropertyValueFactory<produtos,String>("produto"));
        this.colValorProduto.setCellValueFactory(new PropertyValueFactory<produtos,String>("preco"));
        this.tblProdutos.setItems(listaProdutos);

        this.colProduto.setCellValueFactory(new PropertyValueFactory<pedidos,String>("produto"));
        this.colQTD.setCellValueFactory(new PropertyValueFactory<pedidos,String>("qtd"));
        this.colValor.setCellValueFactory(new PropertyValueFactory<pedidos,String>("valor"));



    }
    void setNumFunc(String numFunc) throws SQLException {
        connection = new MySQlConnection();
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
                String produto = result.getString(1);
                double preco = result.getDouble(2);
                produtos p = new produtos(produto, preco);
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
                String produto = linhaProduto.getProduto();
                double preco = linhaProduto.getPreco();
                int qtd = this.cbQTD.getValue();
                String obs = this.taOBS.getText();
                pedidos pedido = new pedidos(produto, preco, qtd, obs,tipo);
                this.listaPedidos.add(pedido);
                System.out.println(tipo);
                linhaProduto = null;
                this.cbQTD.setValue(null);
            }
        }else
        {
            tipo=8;
            double preco = Double.parseDouble(precoR);
            pedidos linhaPedido= new pedidos("Retalho",preco,1,"",tipo);
            this.listaPedidos.add(linhaPedido);
            System.out.println(tipo);
            this.tfRetalho.setText("");
        }


    }
    @FXML
    void add(ActionEvent event) {
        getlinha();
        this.tblPedido.setItems(listaPedidos);
    }



    @FXML
    void editar(ActionEvent event) {
        linhaPedido = this.tblPedido.getSelectionModel().getSelectedItem();
        if(linhaPedido==null)
        {
            alert(Alert.AlertType.ERROR,"ERRO!","Selecione uma linha do pedido!");
        }else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/EditProdutoPedidoView.fxml"));
                Parent root = loader.load();

                ControlllerEditProdutoPedido  controller = loader.getController();
                controller.getProduto(linhaPedido);

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setTitle("GESRES 1.0");
                //nao permitir maximizar tela
                stage.resizableProperty().setValue(Boolean.FALSE);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.setScene(scene);
                stage.showAndWait();

                this.tblPedido.setItems(listaPedidos);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    void eliminar(ActionEvent event) {

    }

    @FXML
    void finalizar(ActionEvent event) {

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

