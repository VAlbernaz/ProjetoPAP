package controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.MySQlConnection;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ControllerFuncMesas implements Initializable {

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

    private  ArrayList<String> disponibilidade = new ArrayList<String>();

    ObservableList<Button> botoes;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file = new File("logo.png");
        Image image = new Image(file.toURI().toString());
        IVLogo.setImage(image);

        botoes = FXCollections.observableArrayList();

        //https://stackoverflow.com/questions/21848068/javafx-share-object-between-controllers

          botoes.addAll(btnMUm,btnMDois,btnMTres,btnMQuatro,btnMCinco,btnMSeis,btnMSete,btnMOito,btnMNove,btnMDez);

        alteraEstilo();
        connection = new MySQlConnection();

    }

    public static final long TEMPO = (200); // atualiza a cada 5 segundo

    public void alteraEstilo()
    {

        Timer timer = null;
        if (timer == null) {
            timer = new Timer();
            TimerTask tarefa = new TimerTask() {
                public void run() {
                    Platform.runLater(() -> {
                        try {
                            connection = new MySQlConnection();
                            ResultSet result = connection.getDisponibilidade();
                            disponibilidade.clear();
                            try {
                                while (result.next()) {

                                    disponibilidade.add(result.getString(1));
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                            //System.out.println(disponibilidade);

                            //System.out.println(Arrays.toString(botoes.toArray()));

                            for(int i =0; i<10;i++) {

                                botoes.get(i).setStyle(null);
                                if (disponibilidade.get(i).equals("True")) {

                                    botoes.get(i).setStyle("-fx-background-color: #70eb80");
                                } else {
                                    botoes.get(i).setStyle("-fx-background-color: #FE2E2E");
                                }

                            }

                        } catch (Exception e) {
                            alert(Alert.AlertType.ERROR, "ATENÇÃO", "Verifique o valor!");
                        }
                    });
                }
            };
            timer.scheduleAtFixedRate(tarefa, TEMPO, TEMPO);
        }
    }

    @FXML
    void mesaUM(ActionEvent event) {

        int numMesa = 1;
        // true= verde   false = vermelho


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
        int numMesa =11;
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
        try {
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
                    controller.setNumFunc(Integer.parseInt(numFunc));
                    controller.getNmMesa(numMesa);

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
                        controlller.getnMesa(11);

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
        }catch (Exception e)
        {

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
