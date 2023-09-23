package co.edu.uniquindio.banco_tcp.server.logic;

import co.edu.uniquindio.banco_tcp.server.database.DataBase;
import co.edu.uniquindio.banco_tcp.server.interfaces.CuentaCrud;
import co.edu.uniquindio.banco_tcp.server.model.Cuenta;
import co.edu.uniquindio.banco_tcp.server.model.Transaccion;

import java.time.LocalDate;

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
                System.out.println("Cuenta " + numCuenta + " eliminada");
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

    public String retornarTransacciones(String numCuenta, int contador){

        String origen="";
        double valor=0;
        LocalDate fecha = null;

        Cuenta c = buscarCuenta(numCuenta);

        if(c==null) return "end";

        try{
            Transaccion trans = c.getListaTransacciones().get(contador);
            origen = trans.getCuentaOrigen();
            valor = trans.getValor();
            fecha = trans.getFecha();

            return "continue:"+origen+":"+valor+":"+fecha;
        }catch (Exception e){

            return "end";

        }


    }
}
