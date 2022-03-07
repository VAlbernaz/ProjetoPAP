package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;

public class ControllerCodigoFunc {

    @FXML
    private ImageView IVLogo;

    @FXML
    private Button btnConcluir;

    @FXML
    private Label lbNumPedido;

    @FXML
    private TextField tfCodigoFunc;

    public void initialize()
    {
        File file = new File("logo.png");
        Image image = new Image(file.toURI().toString());
        IVLogo.setImage(image);
    }

    public String numFunc(){
        //recolhe o numero de funcionario retornando o mesmo
        String num =this.tfCodigoFunc.getText();
        return num;
    }

    @FXML
    void concluir(ActionEvent event) {
        Stage stage = (Stage) this.btnConcluir.getScene().getWindow();
        stage.close();
    }

}

