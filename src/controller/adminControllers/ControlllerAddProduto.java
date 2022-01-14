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
import java.sql.ResultSet;
import java.sql.SQLException;
import model.fornecedor;
import model.produtos;
import model.tipos;

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

    private produtos p;
    private fornecedor f;
    private tipos t;

    private ObservableList<fornecedor> listaFornecedores;
    private ObservableList<tipos> listaTipos;
    private ObservableList<produtos> listaProdutos;

    public void initialize()
    {
        File file = new File("logo.png");
        Image image = new Image(file.toURI().toString());
        IVLogo.setImage(image);

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
                fornecedor f = new fornecedor(ID,nome);
                this.listaFornecedores.add(f);

            }
        }catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        for(fornecedor f : listaFornecedores)
        {
            this.cbFornecedor.getItems().add(f.getNome());
        }
        //preenche comboBox de tipos
        ResultSet resultT = connection.getTipo();
        try {

            while (resultT.next()) {
                int ID = resultT.getInt(1);
                String tipo = resultT.getString(2);
                tipos t = new tipos(ID,tipo);
                this.listaTipos.add(t);

            }
        }catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        for(tipos t: listaTipos)
        {
            this.cbTipo.getItems().add(t.getTipo());
        }

    }



    @FXML
    void concluir(ActionEvent event) {
        int nFornecedor =0;
        int nTipo=0;
        if(!this.tfProduto.getText().isEmpty()||this.tfPreco.getText().isEmpty()||!(this.cbFornecedor.getValue()==null)||!(this.cbTipo.getValue()==null)) {
            String produto = this.tfProduto.getText();
            String preco = this.tfPreco.getText();
            double precoD = Double.parseDouble(preco);
            String fornecedor = this.cbFornecedor.getValue();
            nFornecedor = nFornecedor(fornecedor);
            String tipo = this.cbTipo.getValue();
            nTipo= nTipo(tipo);

            produtos p = new produtos(produto,precoD,nFornecedor,nTipo);
            System.out.println(p);
            //resolver erro
            // adicionar na bd

        }


    }

    public  int nFornecedor(String fornecedor) {
        int nFornecedor= 0;
        connection = new MySQlConnection();
        ResultSet result = connection.getIdFornecedor(fornecedor);
        try {
            while (result.next()) {
                try {
                    nFornecedor = result.getInt(1);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return  nFornecedor;
    }

    public  int nTipo(String tipo) {
        int nTipo= 0;
        connection = new MySQlConnection();
        ResultSet result = connection.getIdTipo(tipo);
        try {
            while (result.next()) {
                try {
                    nTipo = result.getInt(1);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return  nTipo;
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

