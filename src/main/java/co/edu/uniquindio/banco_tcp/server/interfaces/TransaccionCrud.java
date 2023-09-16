package co.edu.uniquindio.banco_tcp.server.interfaces;

public interface TransaccionCrud {

    String realizarRetiro(String numCuenta, double cantidad);

    String realizarTransferencia(String numCuentaOrigen, String numCuentaDestino, double cantidad);


}
