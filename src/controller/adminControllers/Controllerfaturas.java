package controller.adminControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;

public class Controllerfaturas {

    @FXML
    private ImageView IVLogo1;

    @FXML
    private Button btnConsultar;

    @FXML
    private Button btnvoltar;

    @FXML
    private TableColumn<?, ?> colData;

    @FXML
    private TableColumn<?, ?> colIdPedido;

    @FXML
    private TableColumn<?, ?> colProduto;

    @FXML
    private TableColumn<?, ?> colQtd;

    @FXML
    private TableColumn<?, ?> colValorPPedido;

    @FXML
    private TableColumn<?, ?> colvalor;

    @FXML
    private AnchorPane paneStock;

    @FXML
    private TableView<?> tblDetalhes;

    @FXML
    private TableView<?> tblPedidos;

    @FXML
    private TextField tfContribuinte;

    @FXML
    private TextField tfNumFunc;

    @FXML
    void consultar(ActionEvent event) {

    }

    @FXML
    void voltar(ActionEvent event) {

    }



    public void initialize()
    {
        File file = new File("logo.png");
        Image image = new Image(file.toURI().toString());
        IVLogo1.setImage(image);


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
