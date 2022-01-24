package view.AdminViews;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
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

    private pedidos p;


    boolean diferente = false;

    String produto;
    int qtdPedido;
    String obs;


    public void initialize()
    {
        this.cbEditQTD.getItems().addAll(1,2,3,4,5,6,7,8,9);
        
    }


    public pedidos getProduto(pedidos linhaPedidos)
    {

        pedidos p = linhaPedidos;
        //recolhe valores iniciais do objeto e guarda em variaveis
         produto =p.getProduto();
         qtdPedido = p.getQtd();
         obs= p.getObs();

        //mostra os valores iniciais ao utilizador
        this.tfEditProduto.setText(produto);
        this.tfEditObs.setText(obs);





        return linhaPedidos;
    }

    @FXML
    public void continuar(ActionEvent actionEvent) {


        int qtdEdit=this.cbEditQTD.getValue();
        String obsEdit=this.tfEditObs.getText();


        if(qtdPedido != qtdEdit || !obsEdit.equals(obs))
        {
            System.out.println(qtdEdit);
            p.setQtd(qtdEdit);
            p.setObs(obsEdit);
            diferente = true;

        }else{

            diferente = false;
        }

        Stage stage = (Stage) this.btnContinuar.getScene().getWindow();
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
