package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ControllerAdmin {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private AnchorPane panePrincipal;

    @FXML
    private MenuItem MItemStock;

    @FXML
    private MenuItem MItemReceitas;

    @FXML
    private MenuItem MItemFaturasDiarias;

    @FXML
    private MenuItem MItemFaturasSemanal;

    @FXML
    private MenuItem MItemFaturasMensal;

    @FXML
    private ImageView IVLogo;

    @FXML
    void showStock(ActionEvent event){

        try {
            System.out.println("1");
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("ExamplesViews/StockAdminView")));
            System.out.println("2");
            Stage stage = new Stage();
            System.out.println("3");
            stage.setTitle("GESRES 1.0");
            System.out.println("4");
            stage.setScene(new Scene(root));
            System.out.println("5");
            stage.show();
            System.out.println("6");
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();

        }

    }
    @FXML
    void showFaturasDiarias(ActionEvent event){

        try {
            root =FXMLLoader.load(getClass().getResource("/ExamplesViews/FaturasAdminView.fxml"));
            stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void showFaturasMensal(ActionEvent event) {
        try {
            root =FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ExamplesViews/FaturasAdminView.fxml")));
            stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showFaturasSemanal(ActionEvent event) {
        try {
            root =FXMLLoader.load(getClass().getResource("/ExamplesViews/FaturasAdminView.fxml"));
            stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showReceitas(ActionEvent event) {
        try {
            root =FXMLLoader.load(getClass().getResource("/ExamplesViews/ReceitaAdminView.fxml"));
            stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
