package co.edu.uniquindio.banco_tcp.client.controller;

import co.edu.uniquindio.banco_tcp.client.exception.CamposVaciosException;
import co.edu.uniquindio.banco_tcp.client.exception.SaldoInicialException;
import co.edu.uniquindio.banco_tcp.client.exception.SaldoInsuficienteException;
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

import java.net.URL;
import java.util.ResourceBundle;

public class RetiroController implements Initializable {

    InterfazPrincipalController interfazPrincipal;

    EchoTCPClient cliente;

    @FXML
    private Button btnRetirar;

    @FXML
    private ImageView imgUniqui;

    @FXML
    private TextField txtValorRetiro;

    @FXML
    void retirarDinero(ActionEvent event) throws Exception{

        try{
            String cantidad = this.txtValorRetiro.getText();
            String numCuenta = UsuarioActual.getInstance().getNumCuenta();

            if(cantidad.equalsIgnoreCase("")) throw new CamposVaciosException();
            if(Double.parseDouble(cantidad)>UsuarioActual.getInstance().getSaldo()) throw new SaldoInsuficienteException();

            cliente.sendRequest("retiro:"+numCuenta+":"+cantidad);
            String respuestaRetiro = cliente.readResponse();

            interfazPrincipal.updateValues(UsuarioActual.getInstance().getNombre(), Double.parseDouble(respuestaRetiro));

        }catch (CamposVaciosException | SaldoInsuficienteException e){

            if(e instanceof CamposVaciosException){
                CamposVaciosException ce = (CamposVaciosException) e;
                ce.printMessage();
            } else if (e instanceof SaldoInsuficienteException) {
                SaldoInsuficienteException se = (SaldoInsuficienteException) e;
                se.printMessage();
            }

        }



        //APLICAR LÓGICA PARA HACER PETICIÓN DE RETIRAR DINERO, ACTUALIZAR EL SALDO DE LA CUENTA EN LA BASE DE DATOS
        //Y FINALMENTE ACTUALIZAR EL USUARIO ACTUAL PARA VER REFLEJADO LOS VALORES EN LA INTERFAZ PRINCIPAL
    }

    public void updateListener(InterfazPrincipalController interfazPrincipal){

        this.interfazPrincipal = interfazPrincipal;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cliente = EchoTCPClient.getInstance();

    }
}
