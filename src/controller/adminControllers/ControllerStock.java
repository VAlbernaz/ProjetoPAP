package controller.adminControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.MySQlConnection;
import model.Produtos;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerStock {

    @FXML
    private ImageView IVLogo1;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableColumn<Produtos, String> colProduto;

    @FXML
    private TableColumn<Produtos, String> colQtd;

    @FXML
    private AnchorPane paneStock;

    @FXML
    private TableView<Produtos> tblStock;

    @FXML
    private TextField tfEditProduto;

    @FXML
    private TextField tfEditQtd;

    private MySQlConnection connection;


    private Produtos linhaProduto;
    private Produtos linhaEliminar;

    private ObservableList<Produtos> listaProdutos;

    public void initialize()
    {
        File file = new File("logo.png");
        Image image = new Image(file.toURI().toString());
        IVLogo1.setImage(image);

        connection = new MySQlConnection();

        this.colProduto.setCellValueFactory(new PropertyValueFactory<Produtos,String>("produto"));
        this.colQtd.setCellValueFactory(new PropertyValueFactory<Produtos,String>("qtd"));
        this.tblStock.setItems(listaProdutos);

        listaProdutos = FXCollections.observableArrayList();

        tabela();



    }

    @FXML
    void selecionaLinha(MouseEvent event) {
        //da valor à observableList apenas selecionando a linha
        linhaProduto=this.tblStock.getSelectionModel().getSelectedItem();
        this.tfEditProduto.setText(linhaProduto.getProduto());

    }

    @FXML
    void editar(ActionEvent event) {

        if(tfEditProduto==null)
        {
            alert(Alert.AlertType.ERROR,"ERRO!","Selecione uma linha!");
        }else {
            try {
                //recolhe o id do produto
                int ID= this.linhaProduto.getID();
                int newQtd= Integer.parseInt(tfEditQtd.getText());
                String produto= this.tfEditProduto.getText();
                Produtos p = new Produtos(ID,produto,newQtd);
                //faz o update na base de dados
                if(connection.esditQtd(p)){
                    alert(Alert.AlertType.CONFIRMATION,"SUCESSO","Editado com sucesso!");
                    this.tblStock.getItems().clear();
                    tabela();
                }else{
                    alert(Alert.AlertType.ERROR,"ATENÇÃO","Ocorreu um problema!");
                }
            }catch (Exception e) // caso ocrorra alguma excessao lança o alerta
            {
                alert(Alert.AlertType.ERROR, "ATENÇÃO", "Verifique a quantidade!");
            }


        }

    }
    @FXML
    void eliminar(ActionEvent event) {
        linhaEliminar =this.tblStock.getSelectionModel().getSelectedItem();

        if(linhaEliminar==null)
        {
            alert(Alert.AlertType.ERROR,"ERRO!","Selecione uma linha!");
        }else {
            int id=linhaEliminar.getID();
            String produto= this.linhaEliminar.getProduto();

            /**
             * lança alerta para confirmação
             * se o utilizador preswsionar o botão cancel o produto nao é eliminado
             * apenas é eçiminado apos pressionar o botão OK
             * */
            Alert alerta=new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Atenção!");
            alerta.setHeaderText(null);
            alerta.setContentText("Tem a certeza que deseja eliminar o produto: " + produto);
            alerta.showAndWait().ifPresent(response ->{
                if (response == ButtonType.OK) {
                    connection=new MySQlConnection();
                    if(connection.deleteProd(id))
                    {
                        alert(Alert.AlertType.INFORMATION, "Produto eliminado!","Produto eliminado com sucesso.");
                        this.tblStock.getItems().clear();
                        tabela();
                    }
                } else if (response == ButtonType.CANCEL) {

                }
            });
        }
    }
    public void tabela()
    {
        //mostra na tabela todos os produtos existentes na base de dados
        ResultSet result = connection.getProdutos();
        try {

            while (result.next()) {
                int ID = result.getInt(1);
                String produto = result.getString(2);
                int qtd = result.getInt(3);
                Produtos p = new Produtos(ID,produto,qtd);
                this.listaProdutos.add(p);
            }
        }catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        this.tblStock.setItems(listaProdutos);
        this.tblStock.refresh();
    }

    @FXML
    void voltar(ActionEvent event) {
        //fecha a vista
        Stage stage = (Stage) this.btnVoltar.getScene().getWindow();
        stage.close();
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
