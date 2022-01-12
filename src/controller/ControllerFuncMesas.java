package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.MySQlConnection;
import model.funcionarios;
import model.produtos;


import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class ControllerFuncMesas {

    @FXML
    private ImageView IVLogo;

    @FXML
    private Button btnMCinco;

    @FXML
    private Button btnMDez;

    @FXML
    private Button btnMDois;

    @FXML
    private Button btnMNove;

    @FXML
    private Button btnMOito;

    @FXML
    private Button btnMQuatro;

    @FXML
    private Button btnMSeis;

    @FXML
    private Button btnMSete;

    @FXML
    private Button btnMTres;

    @FXML
    private Button btnMUm;

    @FXML
    private Button btnBalcao;

    private MySQlConnection connection;

    public void initialize()
    {
        File file = new File("logo.png");
        Image image = new Image(file.toURI().toString());
        IVLogo.setImage(image);


    }

    @FXML
    void mesaUM(ActionEvent event) {
        int numMesa = 1;
        // true= verde   false = vermelho
        boolean corButao=true;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/FuncViewDetMesas.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("GESRES 1.0");

            stage.resizableProperty().setValue(Boolean.FALSE);

            ControllerFuncDetMesas controller = loader.getController();
            controller.getNmMesa(numMesa);


            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void mesaDois(ActionEvent event) {
        int numMesa = 2;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/FuncViewDetMesas.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("GESRES 1.0");
            stage.resizableProperty().setValue(Boolean.FALSE);

            ControllerFuncDetMesas controller = loader.getController();
            controller.getNmMesa(numMesa);

            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void mesaTres(ActionEvent event) {
        int numMesa = 3;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/FuncViewDetMesas.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("GESRES 1.0");
            stage.resizableProperty().setValue(Boolean.FALSE);

            ControllerFuncDetMesas controller = loader.getController();
            controller.getNmMesa(numMesa);

            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void mesaQuatro(ActionEvent event) {
        int numMesa = 4;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/FuncViewDetMesas.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("GESRES 1.0");
            stage.resizableProperty().setValue(Boolean.FALSE);

            ControllerFuncDetMesas controller = loader.getController();
            controller.getNmMesa(numMesa);

            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void mesaCinco(ActionEvent event) {
        int numMesa = 5;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/FuncViewDetMesas.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("GESRES 1.0");
            stage.resizableProperty().setValue(Boolean.FALSE);

            ControllerFuncDetMesas controller = loader.getController();
            controller.getNmMesa(numMesa);

            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void mesaSeis(ActionEvent event) {
        int numMesa = 6;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/FuncViewDetMesas.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("GESRES 1.0");
            stage.resizableProperty().setValue(Boolean.FALSE);

            ControllerFuncDetMesas controller = loader.getController();
            controller.getNmMesa(numMesa);

            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void mesaSete(ActionEvent event) {
        int numMesa = 7;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/FuncViewDetMesas.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("GESRES 1.0");
            stage.resizableProperty().setValue(Boolean.FALSE);

            ControllerFuncDetMesas controller = loader.getController();
            controller.getNmMesa(numMesa);

            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void mesaOito(ActionEvent event) {
        int numMesa = 8;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/FuncViewDetMesas.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("GESRES 1.0");
            stage.resizableProperty().setValue(Boolean.FALSE);

            ControllerFuncDetMesas controller = loader.getController();
            controller.getNmMesa(numMesa);

            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void mesaNove(ActionEvent event) {
        int numMesa = 9;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/FuncViewDetMesas.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("GESRES 1.0");
            stage.resizableProperty().setValue(Boolean.FALSE);

            ControllerFuncDetMesas controller = loader.getController();
            controller.getNmMesa(numMesa);

            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void mesaDez(ActionEvent event) {
        int numMesa = 10;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/FuncViewDetMesas.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("GESRES 1.0");
            stage.resizableProperty().setValue(Boolean.FALSE);

            ControllerFuncDetMesas controller = loader.getController();
            controller.getNmMesa(numMesa);

            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void balcao(ActionEvent event) throws SQLException {
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
                Scene scene = new Scene(root, 1400, 900);
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
        } else {  //se nao existar lança alerta
            alert(Alert.AlertType.ERROR,"Código inválido!","O código introduzido não está disponivel.");
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
