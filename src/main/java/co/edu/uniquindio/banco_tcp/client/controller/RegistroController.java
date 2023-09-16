package co.edu.uniquindio.banco_tcp.client.controller;

import co.edu.uniquindio.banco_tcp.client.model.EchoTCPClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistroController implements Initializable {

    private EchoTCPClient cliente;

    @FXML
    private Button btnRegistrarme;

    @FXML
    private ImageView imgUniqui;

    @FXML
    private TextField txtCedula;

    @FXML
    private PasswordField txtClave;

    @FXML
    private TextField txtNombreApellido;

    @FXML
    private TextField txtSaldoInicial;

    @FXML
    void registrarCliente(ActionEvent event) {

        Alert alerta = new Alert(Alert.AlertType.ERROR);
        String nombre = this.txtNombreApellido.getText();
        String cedula = this.txtCedula.getText();
        String clave = this.txtClave.getText();
        double capitalInicial = Double.parseDouble(this.txtSaldoInicial.getText());

        try {
            cliente.sendRequest("registro"+":"+nombre+":"+cedula+":"+clave+":"+capitalInicial);    //registro:nombre:cedula:clave:saldo
            String response = cliente.readResponse();
            String cadena [] = response.split(":");

            switch (cadena[0]){

                case "existe":
                    alerta.setTitle("Error");
                    alerta.setHeaderText(null);
                    alerta.setContentText("Ya existe un usuario con la cedula " + cedula);
                    alerta.showAndWait();
                    break;

                case "registrado":
                    alerta.setAlertType(Alert.AlertType.CONFIRMATION);
                    alerta.setTitle("Registro Exitoso");
                    alerta.setHeaderText(null);
                    alerta.setContentText("Registro realizado satisfactoriamente \n" +
                            "Usuario: " + cadena[1] + "\n" +
                            "No. Cuenta: " + cadena[2] + "\n" +
                            "Saldo Inicial: " + cadena[3]);
                    alerta.showAndWait();
                    break;

                default:
                    alerta.setTitle("Error");
                    alerta.setHeaderText(null);
                    alerta.setContentText("Verifique que los datos sean correctos e intente nuevamente");
                    alerta.showAndWait();
                    break;
            }

        }catch (IOException ioe){

            ioe.printStackTrace();
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cliente = EchoTCPClient.getInstance();

    }
}