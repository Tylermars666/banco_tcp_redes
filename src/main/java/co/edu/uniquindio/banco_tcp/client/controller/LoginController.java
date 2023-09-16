package co.edu.uniquindio.banco_tcp.client.controller;

import co.edu.uniquindio.banco_tcp.client.model.EchoTCPClient;
import co.edu.uniquindio.banco_tcp.client.model.UsuarioActual;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private EchoTCPClient cliente;

    @FXML
    private Button btnIngresar;

    @FXML
    private ImageView imgUniqui;

    @FXML
    private Label labelRegistrarme;

    @FXML
    private TextField txtCedula;

    @FXML
    private PasswordField txtContrasenia;

    @FXML
    void ingresar(ActionEvent event) {

        String cedula = this.txtCedula.getText();
        String clave = this.txtContrasenia.getText();

        try{

            cliente.sendRequest("login"+":"+cedula+":"+clave);
            String response = cliente.readResponse();
            String cadena [] = response.split(":");                             //Se espera un true:seguido:de:otros:parametros

            if(Boolean.parseBoolean(cadena[0])){

                UsuarioActual.getInstance().setCedula(cadena[2]);
                UsuarioActual.getInstance().setNombre(cadena[1]);                    //true:nombre:cedula:clave:numCuenta:saldo
                UsuarioActual.getInstance().setNumCuenta(cadena[4]);                 //Usuario Actualmente loggeado
                UsuarioActual.getInstance().setSaldo(Double.parseDouble(cadena[5]));

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/banco_tcp/view/interfaz-principal-view.fxml"));

                Scene scene = new Scene(loader.load());
                Stage stagePrincipal = new Stage();
                stagePrincipal.setTitle("Principal");
                stagePrincipal.setScene(scene);
                UsuarioActual.getInstance().getListaStages().add(stagePrincipal);

                InterfazPrincipalController controller = loader.getController();
                stagePrincipal.show();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Bienvenido " + cadena[1].toUpperCase(), ButtonType.OK);
                alert.setHeaderText(null);
                alert.setTitle("Bienvenido");
                alert.showAndWait();

            }else{

                Alert alert = new Alert(Alert.AlertType.ERROR,"Verifique los datos e intente nuevamente", ButtonType.OK);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.showAndWait();

            }

        }catch (IOException ioe){

            ioe.printStackTrace();
        }

    }

    @FXML
    void registrarNuevoUsuario(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/banco_tcp/view/registro-view.fxml"));

        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Registro");
        stage.setScene(scene);

        RegistroController controller = loader.getController();
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cliente = EchoTCPClient.getInstance();

        try{
            cliente.init();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
