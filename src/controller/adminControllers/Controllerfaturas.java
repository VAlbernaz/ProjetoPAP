package controller.adminControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Faturas;
import model.MySQlConnection;
import model.Produtos;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controllerfaturas {

    @FXML
    private ImageView IVLogo1;

    @FXML
    private Button btnConsultar;

    @FXML
    private Button btnvoltar;

    @FXML
    private TableColumn<Faturas, String> colData;

    @FXML
    private TableColumn<Faturas, String> colIdPedido;

    @FXML
    private TableColumn<?, ?> colProduto;

    @FXML
    private TableColumn<?, ?> colQtd;

    @FXML
    private TableColumn<Faturas, String> colValorTPedido;

    @FXML
    private TableColumn<?, ?> colvalor;

    @FXML
    private AnchorPane paneStock;

    @FXML
    private TableView<?> tblDetalhes;

    @FXML
    private TableView<Faturas> tblPedidos;

    @FXML
    private TextField tfContribuinte;

    @FXML
    private TextField tfNumFunc;

    private MySQlConnection connection;

    private ObservableList<Faturas> listaFaturas;

    public void initialize()
    {
        File file = new File("logo.png");
        Image image = new Image(file.toURI().toString());
        IVLogo1.setImage(image);

        listaFaturas = FXCollections.observableArrayList();

        this.colData.setCellValueFactory(new PropertyValueFactory<Faturas,String>("dataHora"));
        this.colIdPedido.setCellValueFactory(new PropertyValueFactory<Faturas,String>("numPedido"));
        this.colValorTPedido.setCellValueFactory(new PropertyValueFactory<Faturas,String>("valorPedido"));
        this.tblPedidos.setItems(listaFaturas);

        getFaturas();
    }

    public void getFaturas()  {
        connection = new MySQlConnection();

        ResultSet result = connection.getFaturas();
        try {
            while (result.next()){
                String data = result.getString(1);
                String numPedido = result.getString(2);
                String valorTotal = result.getString(3);
                Faturas f= new Faturas(data,Integer.parseInt(numPedido),Double.parseDouble(valorTotal));
                this.listaFaturas.add(f);

            }
        }catch (SQLException throwables) {
        throwables.printStackTrace();
        }

        this.tblPedidos.setItems(listaFaturas);
    }

    @FXML
    void consultar(ActionEvent event) {

    }

    @FXML
    void voltar(ActionEvent event) {

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
