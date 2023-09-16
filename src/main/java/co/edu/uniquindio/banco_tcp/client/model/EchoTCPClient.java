package co.edu.uniquindio.banco_tcp.client.model;

import javafx.scene.control.Alert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class EchoTCPClient {

    private static EchoTCPClient instance;
    public static final String SERVER = "localhost";
    public static final int PORT = 3400;

    private PrintWriter toNetwork;
    private BufferedReader fromNetwork;

    private Socket clientSideSocket;

    public Socket getClientSideSocket() {
        return clientSideSocket;
    }

    public static EchoTCPClient getInstance(){

        if(instance==null){
            instance = new EchoTCPClient();
        }
        return instance;
    }


    private EchoTCPClient() {
        System.out.println("Cliente ejecutandose...");
    }

    public void init() {

        try{
            clientSideSocket = new Socket(SERVER, PORT);
            createStreams(clientSideSocket);
            protocol(clientSideSocket);
        }catch (Exception e){

            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setTitle("Error de Conexión");
            alerta.setContentText("El servidor está fuera de línea mi rey, conectese luego");
            alerta.showAndWait();
        }

    }

    public void sendRequest(String request) throws IOException
    {
        try{
            toNetwork.println(request);
        }catch (Exception e){

            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setTitle("Error de Conexión");
            alerta.setContentText("No se pudo establecer conexión con el servidor, intente luego");
            alerta.showAndWait();

        }

    }

    public String readResponse() throws IOException
    {
        return fromNetwork.readLine();
    }

    public void protocol(Socket socket) throws Exception {
        System.out.print("Hola, me escucha? ");

        toNetwork.println("\n Soy el cliente");

        String fromServer = fromNetwork.readLine();
        System.out.println("El servidor dice: " + fromServer);
    }

    private void createStreams(Socket socket) throws Exception {
        toNetwork = new PrintWriter(socket.getOutputStream(), true);
        fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
}
