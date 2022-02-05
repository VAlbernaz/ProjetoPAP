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
    private TableColumn<Faturas, String> colProduto;

    @FXML
    private TableColumn<Faturas, String> colQtd;

    @FXML
    private TableColumn<Faturas, String> colValorTPedido;

    @FXML
    private TableColumn<Faturas, String> colvalor;

    @FXML
    private AnchorPane paneStock;

    @FXML
    private TableView<Faturas> tblDetalhes;

    @FXML
    private TableView<Faturas> tblPedidos;

    @FXML
    private TextField tfContribuinte;

    @FXML
    private TextField tfNomeFunc;

    private MySQlConnection connection;

    private ObservableList<Faturas> listaFaturas;
    private ObservableList<Faturas> listaDetalhes;
    private Faturas linhaFaturas;

    public void initialize()
    {
        File file = new File("logo.png");
        Image image = new Image(file.toURI().toString());
        IVLogo1.setImage(image);

        listaFaturas = FXCollections.observableArrayList();
        listaDetalhes = FXCollections.observableArrayList();

        this.colData.setCellValueFactory(new PropertyValueFactory<Faturas,String>("dataHora"));
        this.colIdPedido.setCellValueFactory(new PropertyValueFactory<Faturas,String>("numPedido"));
        this.colValorTPedido.setCellValueFactory(new PropertyValueFactory<Faturas,String>("valorPedido"));
        this.tblPedidos.setItems(listaFaturas);

        this.colProduto.setCellValueFactory(new PropertyValueFactory<Faturas, String>("produto"));
        this.colQtd.setCellValueFactory(new PropertyValueFactory<Faturas, String>("qtd"));
        this.colvalor.setCellValueFactory(new PropertyValueFactory<Faturas, String>("preco"));

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
    void consultar(ActionEvent event) throws SQLException {
        this.tblDetalhes.getItems().clear();
        this.tfNomeFunc.setText("");
        this.tfContribuinte.setText("");



        linhaFaturas = this.tblPedidos.getSelectionModel().getSelectedItem();

        ResultSet result = connection.getDetPedidos(this.linhaFaturas.getNumPedido());

        while(result.next())
        {
            String produto = result.getString(1);
            String qtd = result.getString(2);
            String preco = result.getString(3);
            String func= result.getString(4);
            //String contribuinte = result.getString(5);

            Faturas f = new Faturas(produto,qtd,preco,func);
            this.listaDetalhes.add(f);
            this.tblDetalhes.setItems(listaDetalhes);
            this.tfNomeFunc.setText(func);
            //this.tfContribuinte.setText(contribuinte);

        }

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
