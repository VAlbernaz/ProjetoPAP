package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Faturas;
import model.Formas;
import model.MySQlConnection;
import model.Pagamento;

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
    }

    public void getnMesa(int nmesa) throws SQLException {
        numMesa = nmesa;

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
        while (result1.next()) {
            valor = result1.getDouble(1);
        }
        this.tfTotal.setText(String.valueOf(valor));
    }

    public void preencheCB() throws SQLException {
        connection = new MySQlConnection();
        ResultSet result = connection.getFormasPagamento();

        while (result.next()) {
            this.cbForma.getItems().addAll(result.getString(2));
        }
    }

    @FXML
    void concluir(ActionEvent event) {

        /**
         * buscar id do tipo
         * adicionar a tabela de faturas com os valores do id do pedido, contribuinte, id da forma de pagamento
         */
        connection = new MySQlConnection();
        //vai buscar id correspondente à forma de pagamento escolhida
        ResultSet result= connection.getIdFormaPagamento(this.cbForma.getValue());
        int idForma=0;
        try {
            while (result.next())
            {
                idForma = result.getInt(1);
            }
        } catch (SQLException e) {
        e.printStackTrace();
        }
        //guarda o valor do contribuinte
        String contribuinte = this.tfContribuinte.getText();

        if(!contribuinte.equals(""))
        {
            contribuinte=null;
        }
        //cria objeto com todos os valores necessarios para adicionar na bd
        Pagamento p = new Pagamento(numPedido,contribuinte,idForma);
        //se a inserçao correr bem lança alerta e muda o estado da mesa
        if(connection.setFatura(p)){
            alert(Alert.AlertType.INFORMATION, "Obrigado!", "Pedido pago com sucesso!");
            connection.trocaEstadoMesa(numMesa,"True");
        }else{
            alert(Alert.AlertType.WARNING,"Ocorreu um erro","Tente outra vez");
        }

        Stage stage = (Stage) this.btnConcluir.getScene().getWindow();
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
