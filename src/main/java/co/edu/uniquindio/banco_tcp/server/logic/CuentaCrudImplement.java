package co.edu.uniquindio.banco_tcp.server.logic;

import co.edu.uniquindio.banco_tcp.server.database.DataBase;
import co.edu.uniquindio.banco_tcp.server.interfaces.CuentaCrud;
import co.edu.uniquindio.banco_tcp.server.model.Cuenta;

public class CuentaCrudImplement implements CuentaCrud {

    private static CuentaCrudImplement instance;
    private final DataBase dataBase;

    private CuentaCrudImplement(){

        dataBase = DataBase.getInstance();

    }

    public static CuentaCrudImplement getInstance(){

        if(instance==null){

            instance = new CuentaCrudImplement();
        }

        return instance;
    }

    public DataBase getDataBase (){

        return dataBase;

    }

    @Override
    public boolean eliminarCuenta(String numCuenta) {

        for(Cuenta cuenta : dataBase.getListaCuentas()){

            if(cuenta.getNumeroCuenta().equalsIgnoreCase(numCuenta)){

                dataBase.getListaCuentas().remove(cuenta);
                return true;
            }
        }
        return false;
    }

    @Override
    public Cuenta buscarCuenta(String numCuenta) {

        for(Cuenta c : dataBase.getListaCuentas()){

            if(c.getNumeroCuenta().equalsIgnoreCase(numCuenta)){

                return c;
            }
        }
        return null;
    }
}
