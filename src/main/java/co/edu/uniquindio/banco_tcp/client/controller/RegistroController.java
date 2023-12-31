package co.edu.uniquindio.banco_tcp.client.controller;

import co.edu.uniquindio.banco_tcp.client.exception.CamposVaciosException;
import co.edu.uniquindio.banco_tcp.client.exception.SaldoInicialException;
import co.edu.uniquindio.banco_tcp.client.logic.Cifrado;
import co.edu.uniquindio.banco_tcp.client.model.EchoTCPClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private ComboBox<String> cmbMetodoCifrado;

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

    private String metodoSeleccionado;

    ObservableList<String> opcionesCifrado;

    @FXML
    void seleccionarMetodoCifrado(ActionEvent event) {

        metodoSeleccionado = this.cmbMetodoCifrado.getSelectionModel().getSelectedItem();

        this.txtClaveCesar.setVisible(metodoSeleccionado.equalsIgnoreCase("cesar"));

    }

    @FXML
    void registrarCliente(ActionEvent event) {

        try{
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            String claveCesar = "";
            String nombre = this.txtNombreApellido.getText().toLowerCase();
            String cedula = this.txtCedula.getText();
            String clave = this.txtClave.getText();
            String capital = this.txtSaldoInicial.getText();

            if(nombre.equalsIgnoreCase("") || cedula.equalsIgnoreCase("") || clave.equalsIgnoreCase("") || capital.equalsIgnoreCase("")) throw new CamposVaciosException();
            double capitalInicial = Double.parseDouble(capital);
            if(capitalInicial<50000) throw new SaldoInicialException();

            if(this.metodoSeleccionado == null) throw new CamposVaciosException();
            if(this.metodoSeleccionado.equalsIgnoreCase("cesar")){

                claveCesar = this.txtClaveCesar.getText();
                if(claveCesar.equalsIgnoreCase("") || claveCesar.equalsIgnoreCase("0")) throw new CamposVaciosException();
                nombre = Cifrado.getInstance().encriptarCesar(nombre, Integer.parseInt(claveCesar));
                cedula = Cifrado.getInstance().encriptarCesar(cedula,Integer.parseInt(claveCesar));
                clave = Cifrado.getInstance().encriptarCesar(clave,Integer.parseInt(claveCesar));

            } else if (this.metodoSeleccionado.equalsIgnoreCase("chino")) {
                claveCesar = "0";
                nombre = Cifrado.getInstance().encriptarChino(nombre);
                cedula = Cifrado.getInstance().encriptarChino(cedula);
                clave = Cifrado.getInstance().encriptarChino(clave);
            }





            try {
                cliente.sendRequest("registro"+":"+nombre+":"+cedula+":"+clave+":"+capitalInicial+":"+claveCesar);    //registro:nombre:cedula:clave:saldo
                String response = cliente.readResponse();
                String cadena [] = response.split(":");

                switch (cadena[0]){

                    case "existente":
                        alerta.setTitle("Error");
                        alerta.setHeaderText(null);
                        alerta.setContentText("Ya existe un usuario con el número de cédula ingresado");
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
        this.txtClaveCesar.setVisible(false);
        opcionesCifrado = FXCollections.observableArrayList("Cesar","Chino");
        this.cmbMetodoCifrado.setItems(opcionesCifrado);

    }
}
