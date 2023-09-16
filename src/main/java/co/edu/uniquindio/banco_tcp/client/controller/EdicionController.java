package co.edu.uniquindio.banco_tcp.client.controller;

import co.edu.uniquindio.banco_tcp.client.model.EchoTCPClient;
import co.edu.uniquindio.banco_tcp.client.model.UsuarioActual;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EdicionController implements Initializable {

    private EchoTCPClient cliente;

    @FXML
    private Button btnActualizarDatos;

    @FXML
    private Button btnCancelarCuenta;

    @FXML
    private ImageView imgUniqui;

    @FXML
    private TextField txtClaveNueva;

    @FXML
    private TextField txtDatosNuevos;

    @FXML
    void actualizarDatos(ActionEvent event) {

    }

    @FXML
    void cancelarCuenta(ActionEvent event) throws Exception {

        cliente.sendRequest("cancelar:"+UsuarioActual.getInstance().getCedula());
        String response = cliente.readResponse();

        if(response.equalsIgnoreCase("exito")){

            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION,"Cuenta cancelada con exito", ButtonType.OK);
            alerta.setTitle("Confirmación de cancelación");
            alerta.setHeaderText(null);
            alerta.showAndWait();
        }
        for(Stage stage : UsuarioActual.getInstance().getListaStages()){
            stage.close();
        }

        //LOGICA PARA HACER PETICIÓN, ELIMINAR CUENTA DE LA BASE DE DATOS
        //ELIMINAR LOS DATOS DEL USUARIO ACTUAL Y CERRAR TODAS LAS VENTANAS A EXCEPCIÓN DE LA VENTANA LOGIN

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.cliente = EchoTCPClient.getInstance();

    }
}
