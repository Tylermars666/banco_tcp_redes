package co.edu.uniquindio.banco_tcp.server.logic;

import co.edu.uniquindio.banco_tcp.server.database.DataBase;
import co.edu.uniquindio.banco_tcp.server.interfaces.TransaccionCrud;

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
}
