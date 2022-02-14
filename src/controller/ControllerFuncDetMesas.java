package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.MySQlConnection;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class ControllerFuncDetMesas {

    @FXML
    private ImageView IVLogo;

    @FXML
    private Button btnClearMesa;

    @FXML
    private Button btnEditPedido;

    @FXML
    private Button btnNovoPedido;

    @FXML
    private Button btnPagar;

    @FXML
    private Label lbMesa;

    private int numesa;
    int numPedido;

    private MySQlConnection connection;



    public void initialize()
    {
        File file = new File("logo.png");
        Image image = new Image(file.toURI().toString());
        IVLogo.setImage(image);
    }



    void getNmMesa(int mesa){
        numesa=mesa;
        String str = lbMesa.getText();
        this.lbMesa.setText(str + String.valueOf(numesa));
    }


    @FXML
    void ClearMesa(ActionEvent event) {
        connection = new MySQlConnection();
        connection.trocaEstadoMesa(numesa,"True");
        ControllerFuncMesas controller = new ControllerFuncMesas();
        controller.alteraEstilo();
    }

    @FXML
    void editPedido(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/FuncViewPedidos.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root,1400 , 900); // abre para aproximadamente 15''
            Stage stage = new Stage();
            stage.setTitle("GESRES 1.0");
            //stage.setMaximized(Boolean.TRUE);
            //stage.resizableProperty().setValue(Boolean.FALSE);

            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @FXML
    void novoPedido(ActionEvent event) throws SQLException {
        String numFunc = "";
        //abre a vista de pedir o numero de funcionario
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/CodigoFuncView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("GESRES 1.0");
            //stage.setMaximized(Boolean.TRUE);
            stage.resizableProperty().setValue(Boolean.FALSE);


            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            ControllerCodigoFunc controller = loader.getController();
            numFunc = controller.numFunc();


        } catch (IOException e) {
            e.printStackTrace();
        }

        //procura na base de dados um numero de funcionario igual ao numero introduzido
        connection = new MySQlConnection();
        ResultSet result = connection.verificaNumFunc(numFunc);
        int numeros = 0;
        while (result.next()) {
            try {
                numeros = result.getInt(1);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // se o codigo do funcionario existir na bd
        if (Objects.equals(numFunc, String.valueOf(numeros))) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/FuncViewPedidos.fxml"));
                Parent root = loader.load();
                ControllerFuncPedidos controller = loader.getController();
                controller.setNumFunc(numFunc);
                controller.getNmMesa(numesa);
                Scene scene = new Scene(root, 1400, 900);
                Stage stage = new Stage();
                stage.setTitle("GESRES 1.0");
                //stage.setMaximized(Boolean.TRUE);
                //stage.resizableProperty().setValue(Boolean.FALSE);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.setScene(scene);
                stage.showAndWait();

                int nLista = controller.getNlista();
                if(nLista != 0)
                {
                    //abre Vista pagamento
                    loader = new FXMLLoader(getClass().getResource("../view/ContribuinteView.fxml"));
                    root = loader.load();

                    ControlllerContribuinte controlller = loader.getController();
                    controlller.getnMesa(numesa);

                    scene = new Scene(root);
                    stage = new Stage();
                    stage.setTitle("GESRES 1.0");
                    stage.resizableProperty().setValue(Boolean.FALSE);

                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.setScene(scene);
                    stage.show();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


        } else {  //se nao existar lança alerta
            alert(Alert.AlertType.ERROR,"Código inválido!","O código introduzido não está disponivel.");
        }
    }

    @FXML
    void pagamento(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ContribuinteView.fxml"));
            Parent root = loader.load();

            ControlllerContribuinte controlller = loader.getController();
            controlller.getnMesa(numesa);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("GESRES 1.0");
            stage.resizableProperty().setValue(Boolean.FALSE);

            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(scene);
            stage.show();

        } catch (IOException | SQLException e) {
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

