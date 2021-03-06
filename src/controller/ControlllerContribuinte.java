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
        // recolhe o numero da mesa
        numMesa = nmesa;
        connection = new MySQlConnection();
        ResultSet result =  connection.getNumPedidoMesa(numMesa);
        while (result.next())
        {
            numPedido = result.getInt(1);

        }

        String str =this.lbPedido.getText();
        this.lbPedido.setText( str + numPedido);
        //mostra o valor do ultimo pedido mesa na textfield
        ResultSet result1 = connection.getValorPedido(numPedido,numMesa);
        double valor = 0.0;
        while (result1.next()) {
            valor = result1.getDouble(1);
        }
        this.tfTotal.setText(String.valueOf(valor));
    }

    public void preencheCB() throws SQLException {
        //preenche a combobox com as formas de pagamento existentes na base de dados
        connection = new MySQlConnection();
        ResultSet result = connection.getFormasPagamento();

        while (result.next()) {
            this.cbForma.getItems().addAll(result.getString(2));
        }
    }

    @FXML
    void concluir(ActionEvent event) {

        /**
         * buscar id da forma de pagamento
         * adicionar a tabela de faturas com os valores do id do pedido, contribuinte, id da forma de pagamento
         */
        if(this.cbForma.getValue() != null) {
            connection = new MySQlConnection();
            //vai buscar id correspondente ?? forma de pagamento escolhida
            ResultSet result = connection.getIdFormaPagamento(this.cbForma.getValue());
            int idForma = 0;
            try {
                while (result.next()) {
                    idForma = result.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //guarda o valor do contribuinte
            String contribuinte = this.tfContribuinte.getText();

            if (contribuinte.length() > 0) {
                //verifica de o contribuinte ?? constituido apenas por numeros
                boolean isNumeric = true;
                for (int i = 0; i < contribuinte.length(); i++) {
                    if (!Character.isDigit(contribuinte.charAt(i))) {
                        isNumeric = false;
                    }
                }

                if (contribuinte.length() == 9 && isNumeric) {
                    //cria objeto com todos os valores necessarios para adicionar na bd
                    Pagamento p = new Pagamento(numPedido, contribuinte, idForma);
                    //se a inser??ao correr bem lan??a alerta e muda o estado da mesa
                    if (connection.setFatura(p)) {
                        alert(Alert.AlertType.INFORMATION, "Obrigado!", "Pedido pago com sucesso!");
                        connection.trocaEstadoMesa(numMesa, "True");
                    } else {
                        alert(Alert.AlertType.WARNING, "Ocorreu um erro", "Tente outra vez");
                    }
                    Stage stage = (Stage) this.btnConcluir.getScene().getWindow();
                    stage.close();
                } else {
                    alert(Alert.AlertType.WARNING, "Aten????o!", "O contribuinte tem que ter nove numeros.");

                }
            } else {
                //cria objeto com todos os valores necessarios para adicionar na bd
                Pagamento p = new Pagamento(numPedido, contribuinte, idForma);
                //se a inser??ao correr bem lan??a alerta e muda o estado da mesa
                if (connection.setFatura(p)) {
                    alert(Alert.AlertType.INFORMATION, "Obrigado!", "Pedido pago com sucesso!");
                    connection.trocaEstadoMesa(numMesa, "True");
                } else {
                    alert(Alert.AlertType.WARNING, "Ocorreu um erro", "Tente outra vez");
                }
                Stage stage = (Stage) this.btnConcluir.getScene().getWindow();
                stage.close();

            }
        }else {
            alert(Alert.AlertType.WARNING, "Aten????o!", "Selecione uma forma de pagamento.");
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
