package co.edu.uniquindio.banco_tcp.client.controller;

import co.edu.uniquindio.banco_tcp.client.model.EchoTCPClient;
import co.edu.uniquindio.banco_tcp.client.model.Transaccion;
import co.edu.uniquindio.banco_tcp.client.model.UsuarioActual;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableView<Transaccion> tblMovimientos;

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

            movimientos = FXCollections.observableArrayList();
            this.cuentaOrigenColumn.setCellValueFactory(new PropertyValueFactory<>("cuentaOrigen"));
            this.montoColumn.setCellValueFactory(new PropertyValueFactory<>("valor"));
            this.fechaColumn.setCellValueFactory(new PropertyValueFactory<>("fecha"));

            this.movimientos.addAll(UsuarioActual.getInstance().getListaTransacciones());
            this.tblMovimientos.setItems(movimientos);




        }catch (Exception e){
            e.printStackTrace();
        }





    }
}
