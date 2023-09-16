package co.edu.uniquindio.banco_tcp.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

public class MovimientosController {

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

}
