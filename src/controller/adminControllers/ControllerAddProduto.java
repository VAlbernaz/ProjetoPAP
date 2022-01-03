package controller.adminControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerAddProduto {

    @FXML
    private ImageView IVLogo1;

    @FXML
    private MenuItem MItemAddProduto;

    @FXML
    private MenuItem MItemFaturasDiarias;

    @FXML
    private MenuItem MItemFaturasMensal;

    @FXML
    private MenuItem MItemFaturasSemanal;

    @FXML
    private MenuItem MItemReceitas;

    @FXML
    private MenuItem MItemStock;

    @FXML
    private AnchorPane paneStock;

    //menubar
    @FXML
    void showStock(ActionEvent event){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AdminViews/StockAdminView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("GESRES 1.0");
            //nao permitir maximizar tela
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void showFaturasDiarias(ActionEvent event){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AdminViews/FaturasAdminView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("GESRES 1.0");
            //nao permitir maximizar tela
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void showFaturasMensal(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AdminViews/FaturasAdminView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("GESRES 1.0");
            //nao permitir maximizar tela
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showFaturasSemanal(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AdminViews/FaturasAdminView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("GESRES 1.0");
            //nao permitir maximizar tela
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showReceitas(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AdminViews/ReceitaAdminView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("GESRES 1.0");
            //nao permitir maximizar tela
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addProduto(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AdminViews/AddProdutoAdminView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("GESRES 1.0");
            //nao permitir maximizar tela
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
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
