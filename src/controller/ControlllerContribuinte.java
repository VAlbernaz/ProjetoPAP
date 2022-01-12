package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

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
    private TextField tfTotal;

    public void initialize(){
        File file = new File("logo.png");
        Image image = new Image(file.toURI().toString());
        IVLogo.setImage(image);

        this.cbForma.getItems().addAll("Multibanco","Dinheiro");
    }

    @FXML
    void concluir(ActionEvent event) {

    }

}
