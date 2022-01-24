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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.MySQlConnection;
import model.Funcionarios;


import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerAdmin {

    private MySQlConnection connection;

    @FXML
    private ImageView IVLogo;

    @FXML
    private MenuItem MItemFaturasDiarias;

    @FXML
    private MenuItem MItemFaturasMensal;

    @FXML
    private MenuItem MItemFaturasSemanal;

    @FXML
    private MenuItem MItemReceitas;

    @FXML
    private MenuItem MItemStock;

    @FXML
    private Button btnAdd;

    @FXML
    private ComboBox<String> cbSexo;

    @FXML
    private AnchorPane panePrincipal;

    @FXML
    private TableColumn<Funcionarios,String> tcAtividade;

    @FXML
    private TableColumn<Funcionarios,String> tcFunc;

    @FXML
    private TableColumn<Funcionarios,String> tcNumFunc;

    @FXML
    private DatePicker dpDataNascimento;

    @FXML
    private TextField tfNumFunc;

    @FXML
    private TextField tfPrimNome;

    @FXML
    private TextField tfUltiNome;

    @FXML
    private TableView<Funcionarios> tvFunc;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnRefresh;

    @FXML
    private ComboBox<String> cbAtividade;

    private ObservableList<Funcionarios> listaFuncionarios;

    private Funcionarios linhaFuncionario;

    public void initialize()
    {
        File file = new File("logo.png");
        Image image = new Image(file.toURI().toString());
        IVLogo.setImage(image);

        this.cbAtividade.getItems().addAll("PRESENTE","AUSENTE");
        this.cbSexo.getItems().addAll("F","M");
        this.cbAtividade.setValue("AUSENTE");

        listaFuncionarios = FXCollections.observableArrayList();

        this.tcFunc.setCellValueFactory(new PropertyValueFactory<Funcionarios,String>("nome"));
        this.tcAtividade.setCellValueFactory(new PropertyValueFactory<Funcionarios,String>("atividade"));
        this.tcNumFunc.setCellValueFactory(new PropertyValueFactory<Funcionarios,String>("numFunc"));
        this.tvFunc.setItems(listaFuncionarios);


        connection = new MySQlConnection();
        tabela();


    }

    public void tabela()
    {

        ResultSet result = connection.getFunc();
        try {

            while (result.next()) {
                int ID = result.getInt(1);
                String nome = result.getString(2);
                String atividade = result.getString(3);
                int numFunc = result.getInt(4);
                Funcionarios f= new Funcionarios( ID,nome,atividade, numFunc);
                this.listaFuncionarios.add(f);
            }
        }catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        this.tvFunc.setItems(listaFuncionarios);
        this.tvFunc.refresh();
    }

    public void atualizar(ActionEvent actionEvent) {
        linhaFuncionario=this.tvFunc.getSelectionModel().getSelectedItem();

        if(linhaFuncionario==null)
        {
            alert(Alert.AlertType.ERROR,"ERRO!","Selecione uma linha!");
        }else {
            String atividade = this.cbAtividade.getValue();
            int ID=linhaFuncionario.getID();

            Funcionarios f = new Funcionarios(ID,atividade);
            if(connection.editarPessoa(f)){
                alert(Alert.AlertType.CONFIRMATION,"SUCESSO","Editado com sucesso!");
                this.tvFunc.getItems().clear();
                tabela();
            }else{
                alert(Alert.AlertType.ERROR,"ATENÇÃO","Ocorreu um problema!");
            }

        }
    }

    public void refresh(ActionEvent actionEvent) {
        this.tvFunc.getItems().clear();
        tabela();
    }

    @FXML
    void AddFunc(ActionEvent event) {
        if(!this.tfPrimNome.getText().isEmpty()||this.tfUltiNome.getText().isEmpty()||!(this.cbSexo.getValue()==null)||!(this.dpDataNascimento.getValue()==null)||!this.tfNumFunc.getText().isEmpty())
        {
            String primNome = this.tfPrimNome.getText();
            String ultiNome= this.tfUltiNome.getText();
            Date dataNascimento= Date.valueOf(this.dpDataNascimento.getValue());
            String sexo = this.cbSexo.getValue();
            int numFunc = Integer.parseInt(this.tfNumFunc.getText());

            Funcionarios f = new Funcionarios(primNome,ultiNome,dataNascimento,sexo,numFunc);

            if(connection.inserirFuncionario(f)) {
                alert(Alert.AlertType.INFORMATION,"SUCESSO","Funcionário adicionada com sucesso!");
                this.tvFunc.getItems().clear();
                tabela();
            } else {
                alert(Alert.AlertType.ERROR,"ATENÇÃO","Ocorreu um problema!");
            }
        }else {
            alert(Alert.AlertType.WARNING,"ATENÇÃO","Preencha todos os campos");
        }

    }

//menubar
    @FXML
    void showStock(ActionEvent event){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AdminViews/StockAdminView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("GESRES 1.0");
            //nao permitir maximizar tela
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void showFaturasDiarias(ActionEvent event){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AdminViews/FaturasAdminView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("GESRES 1.0");
            //nao permitir maximizar tela
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void showFaturasMensal(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AdminViews/FaturasAdminView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("GESRES 1.0");
            //nao permitir maximizar tela
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showFaturasSemanal(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AdminViews/FaturasAdminView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("GESRES 1.0");
            //nao permitir maximizar tela
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showReceitas(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AdminViews/ReceitaAdminView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("GESRES 1.0");
            //nao permitir maximizar tela
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addProduto(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AdminViews/AddProdutoAdminView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("GESRES 1.0");
            //nao permitir maximizar tela
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
