package controller;

import controller.adminControllers.Controllerfaturas;
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
    private Button btnLogout;

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

        this.cbAtividade.getItems().addAll("Ativo", "De férias", "Em atestado", "Baixa Médica");
        this.cbSexo.getItems().addAll("Masculino","Feminino","Outro");
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
        //preenche tabela com os funcionarios que estao adicionados na base de dados
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

        //se a linhafuncionario nao for null atualiza na base de dados o estado do funcionario em questao
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
        //se nenhum dos compos for igual a null recolhe os valores dos componentes
        if(!this.tfPrimNome.getText().isEmpty()||this.tfUltiNome.getText().isEmpty()||!(this.cbSexo.getValue()==null)||!(this.dpDataNascimento.getValue()==null)||!this.tfNumFunc.getText().isEmpty())
        {
            String primNome = this.tfPrimNome.getText();
            String ultiNome= this.tfUltiNome.getText();
            Date dataNascimento= Date.valueOf(this.dpDataNascimento.getValue());
            String sexo = this.cbSexo.getValue();
            int numFunc = Integer.parseInt(this.tfNumFunc.getText());

            Funcionarios f = new Funcionarios(primNome,ultiNome,dataNascimento,sexo,numFunc);

            //adiciona funcionario na base de dados
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


    @FXML
    void logout(ActionEvent event) {
        Stage stage = (Stage) this.btnLogout.getScene().getWindow();
        stage.close();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MainView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage1 = new Stage();
            stage.setTitle("GESRES 1.0");
            //nao permitir maximizar tela
            stage1.resizableProperty().setValue(Boolean.FALSE);
            stage1.setScene(scene);
            stage1.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//menubar
    @FXML
    void showStock(ActionEvent event){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/adminViews/StockAdminView.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/adminViews/FaturasAdminView.fxml"));
            Parent root = loader.load();

            /**
            *chama as funçoes do controlador das faturas passando o querry sql que é pertendido executar
             * na string sql na 4ª linha tem a limitação da data que se pretende mostrar, no caso no intervalo de 1 dia, ou seja, hoje
             * */
            Controllerfaturas controller = loader.getController();
            controller.getStringSql("SELECT p.dataHora, p.idpedidos, SUM(dp.preco*dp.qtd)\n" +
                    "FROM pedidos p, detalhespedidos dp\n" +
                    "WHERE p.idpedidos = dp.idpedidos\n" +
                    "AND dataHora  BETWEEN DATE_SUB(NOW(), INTERVAL 1 DAY) AND NOW()\n" +
                    "GROUP BY idpedidos;");
            controller.getFaturas();

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/adminViews/FaturasAdminView.fxml"));
            Parent root = loader.load();

            Controllerfaturas controller = loader.getController();
            controller.getStringSql("SELECT p.dataHora, p.idpedidos, SUM(dp.preco*dp.qtd)\n" +
                    "FROM pedidos p, detalhespedidos dp\n" +
                    "WHERE p.idpedidos = dp.idpedidos\n" +
                    "AND dataHora  BETWEEN DATE_SUB(NOW(), INTERVAL 30 DAY) AND NOW()\n" +
                    "GROUP BY idpedidos;");
            controller.getFaturas();

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/adminViews/FaturasAdminView.fxml"));
            Parent root = loader.load();

            Controllerfaturas controller = loader.getController();
            controller.getStringSql("SELECT p.dataHora, p.idpedidos, SUM(dp.preco*dp.qtd)\n" +
                    "FROM pedidos p, detalhespedidos dp\n" +
                    "WHERE p.idpedidos = dp.idpedidos\n" +
                    "AND dataHora  BETWEEN DATE_SUB(NOW(), INTERVAL 7 DAY) AND NOW()\n" +
                    "GROUP BY idpedidos;");
            controller.getFaturas();

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/adminViews/AddProdutoAdminView.fxml"));
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
