package co.edu.uniquindio.banco_tcp.client.controller;

import co.edu.uniquindio.banco_tcp.client.model.EchoTCPClient;
import co.edu.uniquindio.banco_tcp.client.model.Transaccion;
import co.edu.uniquindio.banco_tcp.client.model.UsuarioActual;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class MovimientosController implements Initializable {

    EchoTCPClient cliente;

    @FXML
    private TableColumn<?, ?> cuentaOrigenColumn;

    @FXML
    private TableColumn<?, ?> fechaColumn;

    @FXML
    private ImageView imgUniqui;

    @FXML
    private TableColumn<?, ?> montoColumn;

    @FXML
    private TableView<?> tblMovimientos;

    ObservableList<Transaccion> movimientos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        cliente = EchoTCPClient.getInstance();

        try{
            int cont = 0;

            while(true){

                cliente.sendRequest("movimientos:"+cont);
                String response = cliente.readResponse();
                String [] cadena = response.split(":");

                if(cadena[0].equalsIgnoreCase("end")) break;

                UsuarioActual.getInstance().getListaTransacciones().add(new Transaccion(cadena[1],Double.parseDouble(cadena[2]),cadena[3]));
                cont+=1;
            }



        }catch (Exception e){
            e.printStackTrace();
        }





    }
}
