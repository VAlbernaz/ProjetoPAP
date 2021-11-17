package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import model.Pedidos;

public class ControllerFuncPedidos {

    @FXML
    private ImageView IVLogo;

    @FXML
    private Button btnAddRetalho;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnFinalizar;

    @FXML
    private Button btnNovoPedido;

    @FXML
    private ComboBox<String> cbAlcool;

    @FXML
    private ComboBox<String> cbCafes;

    @FXML
    private ComboBox<String> cbCozinha;

    @FXML
    private ComboBox<String> cbDoces;

    @FXML
    private ComboBox<String> cbSnacks;

    @FXML
    private ComboBox<String> cbSumos;

    @FXML
    private TableColumn<Pedidos, String> colProduto;

    @FXML
    private TableColumn<Pedidos, String> colQTD;

    @FXML
    private TableColumn<Pedidos, String> colValor;

    @FXML
    private TableView<Pedidos> tblPedido;

    @FXML
    private TextField tfRetalho;

    public void initialize()
    {
        String str = "Coca-Cola"+"%20s"+"1.20€";
        cbSumos.getItems().addAll(str);
    }
    @FXML
    void addRetalho(ActionEvent event) {
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

}

