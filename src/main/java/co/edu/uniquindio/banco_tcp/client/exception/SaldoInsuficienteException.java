package co.edu.uniquindio.banco_tcp.client.exception;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class SaldoInsuficienteException extends Exception {

    public SaldoInsuficienteException(){

    }

    public void printMessage(){

        Alert alerta = new Alert(Alert.AlertType.ERROR,"El saldo actual no es suficiente para realizar esta transacción", ButtonType.OK);
        alerta.setHeaderText(null);
        alerta.setTitle("Saldo Insuficiente");
        alerta.showAndWait();

    }

}
