package co.edu.uniquindio.banco_tcp.client.controller;

import co.edu.uniquindio.banco_tcp.client.model.EchoTCPClient;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class MovimientosController implements Initializable {

    EchoTCPClient cliente;

    @FXML
    private TableColumn<?, ?> cuentaOrigenColumn;

    @FXML
    private TableColumn<?, ?> fechaColumn;

    @FXML
    private ImageView imgUniqui;

    @FXML
    private TableColumn<?, ?> montoColumn;

    @FXML
    private TableView<?> tblMovimientos;

    ObservableList<String> movimientos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cliente = EchoTCPClient.getInstance();

    }
}
