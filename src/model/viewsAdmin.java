package model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class viewsAdmin {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void SwitchToFaturasView(ActionEvent event)
    {
        try {
            Parent root =FXMLLoader.load(getClass().getResource("/ExamplesViews/FaturasAdminView.fxml"));
            stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void SwitchToReceitasView(ActionEvent event)
    {
        try {
            Parent root =FXMLLoader.load(getClass().getResource("/ExamplesViews/ReceitaAdminView.fxml"));
            stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void SwitchToStockView(ActionEvent event)
    {
        try {
            Parent root =FXMLLoader.load(getClass().getResource("/ExamplesViews/StockAdminView.fxml"));
            stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
