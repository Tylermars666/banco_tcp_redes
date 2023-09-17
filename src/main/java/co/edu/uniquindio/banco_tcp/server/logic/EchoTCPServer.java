package co.edu.uniquindio.banco_tcp.server.logic;

import co.edu.uniquindio.banco_tcp.server.ServidorApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoTCPServer {

    public static final int PORT = 3400;
    private ServerSocket listener;
    private Socket serverSideSocket;
    private PrintWriter toNetwork;
    private BufferedReader fromNetwork;
    private ServidorApp serv;

    public EchoTCPServer(ServidorApp servidorApp){

        serv = servidorApp;
        System.out.println("Servidor ejecutandose en el puerto : " + PORT);

    }

    public void init() throws Exception{

        listener = new ServerSocket(PORT);
        serverSideSocket = listener.accept();
        createStreams(serverSideSocket);
        protocol(serverSideSocket);
        while (true){

            String response = createResponse();
            sendResponse(response);
        }
    }



    private void protocol(Socket serverSideSocket) throws Exception{

        String message = fromNetwork.readLine();
        System.out.println("El cliente dice: " + message);

        String answer = "Sisas, lo escucho";
        toNetwork.println(answer);

    }

    public String createResponse() throws IOException, Exception{

        createStreams(serverSideSocket);
        String clientRequest = fromNetwork.readLine();
        String[] cadena = clientRequest.split(":");
        String response = "";

        switch (cadena[0]){

            case "login":
                response = serv.loggearUsuario(cadena[1],cadena[2]);                //[cedula,clave]
                break;

            case "registro":
               response = serv.registrarUsuario(cadena[1],cadena[2],cadena[3],Double.parseDouble(cadena[4]));  //registro, nombre, cedula, clave, capital
               break;

            case "cancelar":
                response = serv.cancelaraCuentaUsuario(cadena[1]);  //cancelar, cedula
                break;

            case "retiro":
                response = serv.retirarDinero(cadena[1],Double.parseDouble(cadena[2]));
                break;

            case "edicion":
                response = serv.editarDatos(cadena[1],cadena[2],cadena[3]);
                break;

            case "transferencia":
                response = serv.realizarTransferencia(cadena[1],cadena[2],Double.parseDouble(cadena[3]));
                break;

            case "movimientos":
                response = serv.retornarTransacciones(cadena[1],Integer.parseInt(cadena[2]));
                break;
        }

        return response;
    }

    private void sendResponse(String response) {

        toNetwork.println(response);

    }

    private void createStreams(Socket socket) throws Exception {

        toNetwork = new PrintWriter(socket.getOutputStream(), true);
        fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }


}
