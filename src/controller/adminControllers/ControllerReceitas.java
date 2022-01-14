package controller.adminControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;

public class ControllerReceitas {

    @FXML
    private ImageView IVLogo;

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

    public void initialize()
    {
        File file = new File("logo.png");
        Image image = new Image(file.toURI().toString());
        IVLogo.setImage(image);


    }
}
