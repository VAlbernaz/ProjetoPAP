package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Mesas;
import model.MySQlConnection;
import model.Pedidos;
import model.Produtos;

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
    private TableColumn<Pedidos, String> colProduto;

    @FXML
    private TableColumn<Pedidos, String> colQtd;

    @FXML
    private TableColumn<Pedidos, String> colValor;

    @FXML
    private Label lbMesa;

    @FXML
    private Label lbPedido;

    @FXML
    private TableView<Pedidos> tblDetPedido;

    private int numesa;



    private MySQlConnection connection;

    private ObservableList<Pedidos> detalhesPedido;



    int nPedido = 0;
    int idfunc =0;

    public void initialize() throws SQLException {
        File file = new File("logo.png");
        Image image = new Image(file.toURI().toString());
        IVLogo.setImage(image);

        detalhesPedido = FXCollections.observableArrayList();


        this.colProduto.setCellValueFactory(new PropertyValueFactory<Pedidos,String>("produto"));
        this.colQtd.setCellValueFactory(new PropertyValueFactory<Pedidos,String>("qtd"));
        this.colValor.setCellValueFactory(new PropertyValueFactory<Pedidos,String>("valor"));


    }

    void getNmMesa(int mesa){
        numesa=mesa;
        String str = lbMesa.getText();
        this.lbMesa.setText(str + String.valueOf(numesa));


        try {
            //mostra na tabela o pedido caso esteja algum ativo na mesa
            connection = new MySQlConnection();
            ResultSet result = connection.getDisponibilidadeMesa(numesa);

            String disponibilidade = null;

            while (result.next()) {
                disponibilidade = result.getString(1);
            }

            //se a mesa nao tiver disponivel recolhe o ultimo pedido da mesa e mostra o pedido na tabela
            if (disponibilidade.equals("False")) {
                //recolhe o numero do ultimo pedido que esta ativo na mesa
                ResultSet resultNPedido= connection.getNumUltimoPedidomesa(numesa);

                while (resultNPedido.next()) {
                    nPedido = Integer.parseInt(resultNPedido.getString(1));
                }
                //mostra o numero do pedido na label
                String str1 = this.lbPedido.getText();
                this.lbPedido.setText(str1 + nPedido);

                //recolhe os detalhes do pedido
                ResultSet resultPedido = connection.getPedidoAtivo(nPedido);
                while (resultPedido.next())
                {
                    int id = resultPedido.getInt(1);
                    System.out.println(id);
                    String produto = resultPedido.getString(2);
                    int qtd = resultPedido.getInt(3);
                    double valor = resultPedido.getDouble(4);
                    String obs = resultPedido.getString(5);
                    System.out.println(obs);
                    idfunc = resultPedido.getInt(6);
                    int idtipo = resultPedido.getInt(7);
                    System.out.println("num func "+idfunc);

                    Pedidos p = new Pedidos(id,produto,valor,qtd,obs,idtipo);
                    detalhesPedido.add(p);

                }
                //mostra o pedido na tabela
                this.tblDetPedido.setItems(detalhesPedido);

            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    @FXML
    void ClearMesa(ActionEvent event) {
        connection = new MySQlConnection();
        connection.trocaEstadoMesa(numesa,"True");

        Stage stage = (Stage) this.btnClearMesa.getScene().getWindow();
        stage.close();
    }

    @FXML
    void editPedido(ActionEvent event) {
        Stage stageFechar = (Stage) this.btnEditPedido.getScene().getWindow();
        stageFechar.close();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/FuncViewPedidos.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root,1400 , 900); // abre para aproximadamente 15''
            Stage stage = new Stage();
            stage.setTitle("GESRES 1.0");

            ControllerFuncPedidos controller = loader.getController();
            controller.getNmMesa(numesa);
            controller.getDetPedido(detalhesPedido, nPedido,idfunc);


            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    @FXML
    void novoPedido(ActionEvent event) throws SQLException {
        connection = new MySQlConnection();
        ResultSet result = connection.getDisponibilidadeMesa(numesa);
        String disponibilidade=null;


        while (result.next())
        {
            disponibilidade = result.getString(1);
        }

        if(disponibilidade.equals("True")) {
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

            Stage stage1 = (Stage) this.btnNovoPedido.getScene().getWindow();
            stage1.close();

            //procura na base de dados um numero de funcionario igual ao numero introduzido
            connection = new MySQlConnection();
            ResultSet result1 = connection.verificaNumFunc(numFunc);
            int numeros = 0;
            while (result1.next()) {
                try {
                    numeros = result1.getInt(1);

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
                    if (nLista != 0 && numesa==11) {
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
                alert(Alert.AlertType.ERROR, "Código inválido!", "O código introduzido não está disponivel.");
            }
        }else{
            alert(Alert.AlertType.WARNING,"Atenção!","Pedido aberto na mesa");
        }
    }

    @FXML
    void pagamento(ActionEvent event) {
        Stage stageFechar = (Stage) this.btnPagar.getScene().getWindow();
        stageFechar.close();
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

