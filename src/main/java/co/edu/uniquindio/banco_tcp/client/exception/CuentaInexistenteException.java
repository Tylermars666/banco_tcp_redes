package co.edu.uniquindio.banco_tcp.client.exception;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class CuentaInexistenteException extends Exception{

    public CuentaInexistenteException(){

    }

    public void printMessage(){

        Alert alerta = new Alert(Alert.AlertType.ERROR,"Por favor, valide la información de la cuenta a la que intenta transferir", ButtonType.OK);
        alerta.setHeaderText(null);
        alerta.setTitle("Cuenta inexistente");
        alerta.showAndWait();

    }


}
