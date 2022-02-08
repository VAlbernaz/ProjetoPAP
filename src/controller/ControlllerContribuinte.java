package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Faturas;
import model.Formas;
import model.MySQlConnection;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControlllerContribuinte {

    @FXML
    private ImageView    IVLogo;

    @FXML
    private Button btnConcluir;

    @FXML
    private ComboBox<String> cbForma;

    @FXML
    private TextField tfContribuinte;

    @FXML
    private Label lbPedido;

    private MySQlConnection connection;

    private Formas linhaForma;

    @FXML
    private TextField tfTotal;

    private int  numPedido=0;
    private int numMesa;



    public void initialize() throws SQLException {
        File file = new File("logo.png");
        Image image = new Image(file.toURI().toString());
        IVLogo.setImage(image);


        preencheCB();

        connection = new MySQlConnection();
        ResultSet result =  connection.getNumPedidoMesa(numMesa);
        while (result.next())
        {
            numPedido = result.getInt(1);
        }


        String str =this.lbPedido.getText();
        this.lbPedido.setText( str + numPedido);



        ResultSet result1 = connection.getValorPedido(numPedido,numMesa);
        double valor = 0.0;
        while (result1.next())
        {
            valor = result1.getDouble(1);
        }
        this.tfTotal.setText(String.valueOf(valor));



    }

    public void getnMesa(int nmesa)
    {
        numMesa = nmesa;
    }
    public void preencheCB() throws SQLException {
        connection = new MySQlConnection();
        ResultSet result = connection.getFormasPagamento();

        while (result.next())
        {
            this.cbForma.getItems().addAll(result.getString(2));
        }
    }

    @FXML
    void concluir(ActionEvent event) {

        /**
         * buscar id do tipo
         * adicionar a tabela de faturas com os valores do id do pedido, contribuinte, id da forma de pagamento*/



        /**
         * ver melhor a questao dos nº de pedidos na pagina de pagamento*/


    }

}
