package co.edu.uniquindio.banco_tcp.client.controller;

import co.edu.uniquindio.banco_tcp.client.exception.CamposVaciosException;
import co.edu.uniquindio.banco_tcp.client.exception.CuentaInexistenteException;
import co.edu.uniquindio.banco_tcp.client.exception.SaldoInsuficienteException;
import co.edu.uniquindio.banco_tcp.client.interfaces.UpdateListener;
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

public class TransferenciaController implements UpdateListener, Initializable {

    InterfazPrincipalController interfazPrincipalController;

    EchoTCPClient cliente;

    @FXML
    private Button btnTransferir;

    @FXML
    private ImageView imgUniqui;

    @FXML
    private TextField txtCuentaDestino;

    @FXML
    private TextField txtValorTransferencia;

    @FXML
    void transferirDinero(ActionEvent event) throws Exception {

        try{
            String cuentaDestino = this.txtCuentaDestino.getText();
            String valorTransferencia = this.txtValorTransferencia.getText();

            if(Double.parseDouble(valorTransferencia)> UsuarioActual.getInstance().getSaldo()) throw new SaldoInsuficienteException();
            if(cuentaDestino.equalsIgnoreCase("") || valorTransferencia.equalsIgnoreCase("")) throw new CamposVaciosException();

            cliente.sendRequest("transferencia:"+UsuarioActual.getInstance().getNumCuenta()+":"+cuentaDestino+":"+valorTransferencia);
            String response = cliente.readResponse();
            String [] cadena = response.split(":");
            if(cadena[0].equalsIgnoreCase("fracaso")) throw new CuentaInexistenteException();

            interfazPrincipalController.updateValues(UsuarioActual.getInstance().getNombre(), Double.parseDouble(cadena[1]));
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Transferencia realizada con exito", ButtonType.OK);
            alerta.setHeaderText(null);
            alerta.setTitle("Transferencia");
            alerta.showAndWait();

        }catch (SaldoInsuficienteException | CuentaInexistenteException | CamposVaciosException e){

            if(e instanceof SaldoInsuficienteException){
                SaldoInsuficienteException se = (SaldoInsuficienteException) e;
                se.printMessage();
            } else if (e instanceof CuentaInexistenteException) {
                CuentaInexistenteException ce = (CuentaInexistenteException) e;
                ce.printMessage();
            } else if (e instanceof CamposVaciosException) {
                CamposVaciosException cve = (CamposVaciosException) e;
                cve.printMessage();
            }

        }


    }

    @Override
    public void updateListener(InterfazPrincipalController interfazPrincipalController) {

        this.interfazPrincipalController = interfazPrincipalController;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cliente = EchoTCPClient.getInstance();
    }
}
