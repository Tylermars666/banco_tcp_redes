package co.edu.uniquindio.banco_tcp.client.controller;

import co.edu.uniquindio.banco_tcp.client.exception.CamposVaciosException;
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
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EdicionController implements Initializable, UpdateListener {

    InterfazPrincipalController interfazPrincipalController;

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
    void actualizarDatos(ActionEvent event) throws Exception {

        try{
            String claveNueva = this.txtClaveNueva.getText();
            String nombreNuevo = this.txtDatosNuevos.getText();
            if(claveNueva.equalsIgnoreCase("")) throw new CamposVaciosException();
            if(nombreNuevo.equalsIgnoreCase("")) nombreNuevo = UsuarioActual.getInstance().getNombre();
            cliente.sendRequest("edicion:"+claveNueva+":"+nombreNuevo+":"+UsuarioActual.getInstance().getCedula());
            String response = cliente.readResponse();   //RECIBE DATOS ACTUALIZADOS PARA SETTEAR EN EL USUARIO ACTUAL A TRAVÉS DEL UPDATE
            String [] cadena = response.split(":");

            if(cadena[0].equalsIgnoreCase("exito")){

                interfazPrincipalController.updateValues(cadena[1],UsuarioActual.getInstance().getSaldo());
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Datos actualizados con éxito",ButtonType.OK);
                alerta.setHeaderText(null);
                alerta.setTitle("Actualización exitosa");
                alerta.showAndWait();
            }

        }catch (CamposVaciosException ce){

            ce.printMessage();
        }

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

            for(Stage stage : UsuarioActual.getInstance().getListaStages()){
                stage.close();
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.cliente = EchoTCPClient.getInstance();

    }

    public void updateListener(InterfazPrincipalController interfazPrincipalController){
        this.interfazPrincipalController=interfazPrincipalController;
    }
}
