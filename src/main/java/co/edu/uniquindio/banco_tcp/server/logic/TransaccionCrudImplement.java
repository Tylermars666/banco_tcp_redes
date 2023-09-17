package co.edu.uniquindio.banco_tcp.server.logic;

import co.edu.uniquindio.banco_tcp.server.database.DataBase;
import co.edu.uniquindio.banco_tcp.server.interfaces.TransaccionCrud;
import co.edu.uniquindio.banco_tcp.server.model.Cuenta;

public class TransaccionCrudImplement implements TransaccionCrud {

    private static TransaccionCrudImplement instance;
    private final DataBase dataBase;

    private TransaccionCrudImplement(){

        dataBase = DataBase.getInstance();

    }

    public static TransaccionCrudImplement getInstance(){

        if(instance==null){

            instance = new TransaccionCrudImplement();
        }

        return instance;
    }

    public DataBase getDataBase (){

        return dataBase;

    }

    @Override
    public String realizarRetiro(String numCuenta, double cantidad) {

        String saldoActualizado = "0";

        for(Cuenta cuenta : dataBase.getListaCuentas()){

            if(cuenta.getNumeroCuenta().equalsIgnoreCase(numCuenta)){

                double saldoActual = cuenta.getSaldo();
                cuenta.setSaldo(saldoActual-cantidad);
                saldoActualizado = String.valueOf(saldoActual-cantidad);
                break;

            }
        }
        return saldoActualizado;
    }

    @Override
    public String realizarTransferencia(String numCuentaOrigen,String numCuentaDestino, double cantidad) {

        Cuenta origen = CuentaCrudImplement.getInstance().buscarCuenta(numCuentaOrigen);
        Cuenta destino = CuentaCrudImplement.getInstance().buscarCuenta(numCuentaDestino);

        if (origen == null || destino == null){
            return "fracaso";
        }else {

            origen.setSaldo(origen.getSaldo()-cantidad);
            destino.setSaldo(destino.getSaldo()+cantidad);
            return "exito:"+(origen.getSaldo()-cantidad);

        }
    }
}
