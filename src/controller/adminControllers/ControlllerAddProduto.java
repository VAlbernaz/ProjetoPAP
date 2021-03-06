package controller.adminControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.MySQlConnection;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Fornecedor;
import model.Produtos;
import model.Tipos;

public class ControlllerAddProduto {

    @FXML
    private ImageView IVLogo;

    @FXML
    private Button btnConcluir;

    @FXML
    private ComboBox<String> cbFornecedor;

    @FXML
    private ComboBox<String> cbTipo;

    @FXML
    private TextField tfPreco;

    @FXML
    private TextField tfProduto;

    private MySQlConnection connection;

    private Produtos p;
    private Fornecedor f;
    private Tipos t;

    private ObservableList<Fornecedor> listaFornecedores;
    private ObservableList<Tipos> listaTipos;
    private ObservableList<Produtos> listaProdutos;

    private int nFornecedor=0;
    private int nTipo=0;

    public void initialize()
    {
        File file = new File("logo.png");
        Image image = new Image(file.toURI().toString());
        IVLogo.setImage(image);

        connection = new MySQlConnection();

       listaFornecedores = FXCollections.observableArrayList();
       listaTipos = FXCollections.observableArrayList();
       listaProdutos = FXCollections.observableArrayList();

        preencheComboBox();


    }

    public void preencheComboBox(){
        connection = new MySQlConnection();
        //preenche comboBox de fornecedores
        ResultSet resultF = connection.getFornecedor();
        try {

            while (resultF.next()) {
                int ID = resultF.getInt(1);
                String nome = resultF.getString(2);
                Fornecedor f = new Fornecedor(ID,nome);
                this.listaFornecedores.add(f);

            }
        }catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        for(Fornecedor f : listaFornecedores)
        {
            this.cbFornecedor.getItems().add(f.getNome());
        }
        //preenche comboBox de tipos
        ResultSet resultT = connection.getTipo();
        try {

            while (resultT.next()) {
                int ID = resultT.getInt(1);
                String tipo = resultT.getString(2);
                Tipos t = new Tipos(ID,tipo);
                this.listaTipos.add(t);

            }
        }catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        for(Tipos t: listaTipos)
        {
            this.cbTipo.getItems().add(t.getTipo());
        }

    }

    @FXML
    void concluir(ActionEvent event) {
        try {
            //se nenhum dos campos for igual a null recolhe os dados dos componentes
            if(!this.tfProduto.getText().isEmpty()||this.tfPreco.getText().isEmpty()||!(this.cbFornecedor.getValue()==null)||!(this.cbTipo.getValue()==null)) {
                String produto = this.tfProduto.getText();
                String preco = this.tfPreco.getText();

                    double precoD = Double.parseDouble(preco);
                    String fornecedor = this.cbFornecedor.getValue();
                    ResultSet result = connection.getIdFornecedor(fornecedor);
                    nFornecedor(fornecedor);
                    String tipo = this.cbTipo.getValue();
                    nTipo(tipo);


                    //cria um objeto do tipo Produtos com os valores recolhidos
                    Produtos p = new Produtos(produto, precoD, nFornecedor, nTipo);


                    // adiciona na bd
                    if (connection.inserirProduto(p)) {
                        alert(Alert.AlertType.INFORMATION, "SUCESSO", "Produto adicionado com sucesso!");

                        this.tfProduto.setText("");
                        this.tfPreco.setText("");
                        this.cbFornecedor.setValue("");
                        this.cbTipo.setValue("");

                    } else {
                        alert(Alert.AlertType.ERROR, "ATEN????O", "Ocorreu um problema!");
                    }

            }else {
                alert(Alert.AlertType.WARNING,"ATEN????O","Preencha todos os campos");
            }
        } catch (Exception e) {
            alert(Alert.AlertType.ERROR, "ATEN????O", "Verifique o pre??o!");
        }


    }

    public  void nFornecedor(String fornecedor) {

        //recolhe o id do fornecedor
        ResultSet result = connection.getIdFornecedor(fornecedor);
        try {
            while (result.next()) {
                    nFornecedor = result.getInt(1);
            }
        }catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

    }

    public  void nTipo(String tipo) {

        // recolhe o id do tipo do produto
        connection = new MySQlConnection();
        ResultSet result = connection.getIdTipo(tipo);
        try {
            while (result.next()) {
                nTipo = result.getInt(1);
            }
        }catch (SQLException throwables)
        {
            throwables.printStackTrace();
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

