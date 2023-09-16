package co.edu.uniquindio.banco_tcp.client.exception;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class SaldoInicialException extends Exception{

    public SaldoInicialException(){

    }

    public void printMessage(){

        Alert alert = new Alert(Alert.AlertType.ERROR,"El saldo inicial ingresado debe ser mínimo de 50 lukas", ButtonType.OK);
        alert.setHeaderText(null);
        alert.setTitle("Saldo inicial insuficiente");
        alert.showAndWait();

    }

}
