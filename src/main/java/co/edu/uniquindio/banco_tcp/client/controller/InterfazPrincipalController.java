package co.edu.uniquindio.banco_tcp.client.controller;

import co.edu.uniquindio.banco_tcp.client.model.UsuarioActual;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class InterfazPrincipalController implements Initializable{

    @FXML
    private ImageView imgEditar;

    @FXML
    private ImageView imgMovimientos;

    @FXML
    private ImageView imgRetirar;

    @FXML
    private ImageView imgTransferir;

    @FXML
    private ImageView imgUniqui;

    @FXML
    private Label lblNombre;

    @FXML
    private Label lblNumeroCuenta;

    @FXML
    private Label lblSaldo;

    @FXML
    void editarCuenta(MouseEvent event) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/banco_tcp/view/edicion-view.fxml"));

        Scene scene = new Scene(loader.load());
        Stage stageEditar = new Stage();
        stageEditar.setTitle("Actualizar datos de cuenta");
        stageEditar.setScene(scene);
        UsuarioActual.getInstance().getListaStages().add(stageEditar);

        EdicionController controller = loader.getController();
        controller.updateListener(this);
        stageEditar.show();

    }

    @FXML
    void retirarDinero(MouseEvent event) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/banco_tcp/view/retiro-view.fxml"));

        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Retiros");
        stage.setScene(scene);

        RetiroController controller = loader.getController();
        controller.updateListener(this);
        stage.show();

    }

    @FXML
    void transferirDinero(MouseEvent event) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/banco_tcp/view/transferencia-view.fxml"));

        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Transferencias");
        stage.setScene(scene);

        TransferenciaController controller = loader.getController();
        controller.updateListener(this);
        stage.show();

    }

    @FXML
    void verMovimientos(MouseEvent event) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/banco_tcp/view/movimientos-view.fxml"));

        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Movimientos");
        stage.setScene(scene);

        MovimientosController controller = loader.getController();
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.lblNombre.setText(UsuarioActual.getInstance().getNombre().toUpperCase());
        this.lblNumeroCuenta.setText(UsuarioActual.getInstance().getNumCuenta());
        this.lblSaldo.setText(String.valueOf(UsuarioActual.getInstance().getSaldo()));

    }

    public void updateValues(String nombre, double saldo){

        this.lblSaldo.setText(String.valueOf(saldo));
        this.lblNombre.setText(nombre.toUpperCase());

    }
}
