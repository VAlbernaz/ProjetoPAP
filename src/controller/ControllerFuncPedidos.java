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

