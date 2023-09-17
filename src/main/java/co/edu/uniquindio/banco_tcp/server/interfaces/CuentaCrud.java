package co.edu.uniquindio.banco_tcp.server.interfaces;

import co.edu.uniquindio.banco_tcp.server.model.Cuenta;

public interface CuentaCrud {


    boolean eliminarCuenta(String cedula);
    Cuenta buscarCuenta(String numCuenta);

}
