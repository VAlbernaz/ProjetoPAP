package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import model.MySQlConnection;
import model.funcionarios;
import model.produtos;

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
    private TableColumn<produtos, String> colProduto;

    @FXML
    private TableColumn<?,?> colQTD;

    @FXML
    private TableColumn<?,?> colValor;

    @FXML
    private TableView<?> tblPedido;

    //tabela produtos
    @FXML
    private TableView<produtos> tblProdutos;

    @FXML
    private TableColumn<produtos, String> colProdutos;

    @FXML
    private TableColumn<produtos, String> colValorProduto;

    @FXML
    private TextField tfRetalho;


    private ObservableList<produtos> listaProdutos;

    private MySQlConnection connection;

    public void initialize()
    {

        this.cbQTD.getItems().addAll(1,2,3,4,5,6,7,8,9);

        listaProdutos = FXCollections.observableArrayList();

        this.colProdutos.setCellValueFactory(new PropertyValueFactory<produtos,String>("produto"));
        this.colValorProduto.setCellValueFactory(new PropertyValueFactory<produtos,String>("preco"));
        this.tblProdutos.setItems(listaProdutos);
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


    @FXML
    void add(ActionEvent event) {
        String valor = this.tfRetalho.getText();
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
    void novoPedido(ActionEvent event) {

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

