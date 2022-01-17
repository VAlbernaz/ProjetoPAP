package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.MySQlConnection;
import model.pedidos;
import model.produtos;

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
        if(precoR!=null)
        {
            double precoRetalho = Double.parseDouble(precoR);
            pedidos linhaPedido= new pedidos("Retalho",precoRetalho,1,"");
            this.listaPedidos.add(linhaPedido);
        }else
        {

            String produto = linhaProduto.getProduto();
            double preco = linhaProduto.getPreco();
            int qtd = this.cbQTD.getValue();
            String obs = linhaProduto.getObs();
            pedidos linhaPedidos = new pedidos(produto,preco,qtd,obs);
            this.listaPedidos.add(linhaPedidos);
        }
    }
    @FXML
    void add(ActionEvent event) {
        getlinha();
        this.tblPedido.setItems(listaPedidos);
    }

    @FXML
    void editar(ActionEvent event) {

    }

    @FXML
    void eliminar(ActionEvent event) {

    }

    @FXML
    void finalizar(ActionEvent event) {

    }

    @FXML
    void preencheBebidasAlcool(ActionEvent event) {
        this.tblProdutos.getItems().clear();
        tabelaProdutos(3);
    }

    @FXML
    void preencheCafes(ActionEvent event) {
        this.tblProdutos.getItems().clear();
        tabelaProdutos(1);
    }

    @FXML
    void preencheCozinha(ActionEvent event) {
        this.tblProdutos.getItems().clear();
        tabelaProdutos(4);
    }

    @FXML
    void preencheDoces(ActionEvent event) {
        this.tblProdutos.getItems().clear();
        tabelaProdutos(6);
    }

    @FXML
    void preencheSnacks(ActionEvent event) {
        this.tblProdutos.getItems().clear();
        tabelaProdutos(5);
    }

    @FXML
    void preencheSumos(ActionEvent event) {
        this.tblProdutos.getItems().clear();
        tabelaProdutos(2);
    }


}

