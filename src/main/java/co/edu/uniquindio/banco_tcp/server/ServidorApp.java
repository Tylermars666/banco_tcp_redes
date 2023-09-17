package co.edu.uniquindio.banco_tcp.server;

import co.edu.uniquindio.banco_tcp.server.interfaces.CuentaCrud;
import co.edu.uniquindio.banco_tcp.server.interfaces.TransaccionCrud;
import co.edu.uniquindio.banco_tcp.server.interfaces.UsuarioCrud;
import co.edu.uniquindio.banco_tcp.server.logic.CuentaCrudImplement;
import co.edu.uniquindio.banco_tcp.server.logic.EchoTCPServer;
import co.edu.uniquindio.banco_tcp.server.logic.TransaccionCrudImplement;
import co.edu.uniquindio.banco_tcp.server.logic.UsuarioCrudImplement;
import co.edu.uniquindio.banco_tcp.server.model.Cuenta;

public class ServidorApp {

    private EchoTCPServer server;
    private CuentaCrud cuentaCrud = CuentaCrudImplement.getInstance();
    private TransaccionCrud transCrud = TransaccionCrudImplement.getInstance();
    private UsuarioCrud userCrud = UsuarioCrudImplement.getInstance();


    public static void main(String[] args) {
        ServidorApp servidorApp = new ServidorApp();
    }

    public ServidorApp(){

        server = new EchoTCPServer(this);

        try{
            server.init();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public String loggearUsuario(String cedula, String clave){

        String usuarioEncontrado = userCrud.buscarUsuario(cedula);
        String [] datosUsuario = usuarioEncontrado.split(":");

        if(clave.equalsIgnoreCase(datosUsuario[2])){                //compara si la clave ingresada con ese usuario concuerda

            return "true:"+usuarioEncontrado;
        }

        return "false:usernotfound";

    }

    public String registrarUsuario(String nombre, String cedula, String clave, double capitalInicial){

        return userCrud.registrarUsuario(nombre,cedula,clave,capitalInicial);
    }

    public String cancelaraCuentaUsuario(String cedula){

        String numCuenta = userCrud.eliminarUsuario(cedula);
        if(cuentaCrud.eliminarCuenta(numCuenta)){

            return "exito";

        }

        return "fracaso";

    }

    public String retirarDinero(String numCuenta, double cantidad){

        return transCrud.realizarRetiro(numCuenta,cantidad);

    }

    public String editarDatos(String claveNueva, String nombreNuevo,String cedula){

        return userCrud.editarUsuario(claveNueva, nombreNuevo, cedula);
    }

    public String realizarTransferencia(String cuentaOrigen,String cuentaDestino, double cantidad){

        return transCrud.realizarTransferencia(cuentaOrigen,cuentaDestino,cantidad);

    }

    //AQUÍ VOY A TENER MÉTODOS COMO BUSCAR USUARIO, BUSCAR CUENTA, ACTUALIZAR SALDO, ETC, Y USARÉ LAS IMPLEMENTACIONES CRUD

}
