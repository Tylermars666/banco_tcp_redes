package co.edu.uniquindio.banco_tcp.client.exception;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class CamposVaciosException extends Exception{

    public CamposVaciosException(){

    }

    public void printMessage(){

        Alert alerta = new Alert(Alert.AlertType.ERROR, "Asegurese de llenar todos los campos", ButtonType.OK);
        alerta.setHeaderText(null);
        alerta.setTitle("Campos sin rellenar");
        alerta.showAndWait();

    }
}
