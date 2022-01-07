package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

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
        this.cbForma.getItems().addAll("Multibanco","Dinheiro");
    }

    @FXML
    void concluir(ActionEvent event) {

    }

}
