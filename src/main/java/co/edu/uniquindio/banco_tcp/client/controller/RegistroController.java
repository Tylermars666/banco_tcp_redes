package co.edu.uniquindio.banco_tcp.client.controller;

import co.edu.uniquindio.banco_tcp.client.exception.CamposVaciosException;
import co.edu.uniquindio.banco_tcp.client.exception.SaldoInicialException;
import co.edu.uniquindio.banco_tcp.client.exception.SaldoInsuficienteException;
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
    private TextField txtClaveCesar;

    @FXML
    private ComboBox<?> cmbMetodoCifrado;

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
    void seleccionarMetodoCifrado(ActionEvent event) {

    }

    @FXML
    void registrarCliente(ActionEvent event) {

        try{
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            String nombre = this.txtNombreApellido.getText();
            String cedula = this.txtCedula.getText();
            String clave = this.txtClave.getText();
            double capitalInicial = Double.parseDouble(this.txtSaldoInicial.getText());

            if(capitalInicial<50000) throw new SaldoInicialException();
            if(nombre.equalsIgnoreCase("") || cedula.equalsIgnoreCase("") || clave.equalsIgnoreCase("")) throw new CamposVaciosException();

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


        }catch (SaldoInicialException | CamposVaciosException e){

            if( e instanceof SaldoInicialException){
                SaldoInicialException se = (SaldoInicialException) e;
                se.printMessage();
            } else if (e instanceof CamposVaciosException) {
                CamposVaciosException ce = (CamposVaciosException) e;
                ce.printMessage();
            }
        }




    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cliente = EchoTCPClient.getInstance();

    }
}
