package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.pedidos;

public class ControlllerEditProdutoPedido {

    @FXML
    private ImageView IVLogo;

    @FXML
    private Button btnContinuar;

    @FXML
    private ComboBox<Integer> cbEditQTD;

    @FXML
    private TextField tfEditObs;

    @FXML
    private TextField tfEditProduto;



    boolean diferente = false;

    public void initialize()
    {
        this.cbEditQTD.getItems().addAll(1,2,3,4,5,6,7,8,9);
        
    }
    public void getProduto(pedidos linhaPedidos)
    {


        //recolhe valores iniciais do objeto e guarda em variaveis
        String produto=linhaPedidos.getProduto();
        int qtdPedido = linhaPedidos.getQtd();
        String  obs= linhaPedidos.getObs();

        //mostra os valores iniciais ao utilizador
        this.tfEditProduto.setText(produto);
        this.cbEditQTD.setValue(qtdPedido);
        this.tfEditObs.setText(obs);



        if(!(this.cbEditQTD.getValue() == qtdPedido) || this.tfEditObs.getText().equals(obs))
        {
            linhaPedidos.setQtd(this.cbEditQTD.getValue());
            linhaPedidos.setObs(this.tfEditObs.getText());
            diferente = true;

        }else{

            diferente = false;
        }

    }
    
    @FXML
    public void continuar(ActionEvent actionEvent) {
        if(diferente)
        {
            alert(Alert.AlertType.CONFIRMATION,"Sucesso!","Editado com sucesso!");
            Stage stage = (Stage) this.btnContinuar.getScene().getWindow();
            stage.close();
        }else {
            alert(Alert.AlertType.WARNING,"Atenção!","Não editou nada no produto!");
            Stage stage = (Stage) this.btnContinuar.getScene().getWindow();
            stage.close();
        }
        System.out.println(diferente);

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
