package co.edu.uniquindio.banco_tcp.client.controller;

import co.edu.uniquindio.banco_tcp.client.model.UsuarioActual;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class RetiroController {

    InterfazPrincipalController interfazPrincipal;

    @FXML
    private Button btnRetirar;

    @FXML
    private ImageView imgUniqui;

    @FXML
    private TextField txtValorRetiro;

    @FXML
    void retirarDinero(ActionEvent event) {

        UsuarioActual.getInstance().setSaldo(8000);
        interfazPrincipal.updateValues(UsuarioActual.getInstance().getNombre(),UsuarioActual.getInstance().getSaldo());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Retiro excitante", ButtonType.OK);
        alert.setHeaderText(null);
        alert.setTitle("Excitante");
        alert.showAndWait();

        //APLICAR LÓGICA PARA HACER PETICIÓN DE RETIRAR DINERO, ACTUALIZAR EL SALDO DE LA CUENTA EN LA BASE DE DATOS
        //Y FINALMENTE ACTUALIZAR EL USUARIO ACTUAL PARA VER REFLEJADO LOS VALORES EN LA INTERFAZ PRINCIPAL
    }

    public void updateListener(InterfazPrincipalController interfazPrincipal){

        this.interfazPrincipal = interfazPrincipal;
    }

}
