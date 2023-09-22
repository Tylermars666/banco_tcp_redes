package co.edu.uniquindio.banco_tcp.server.database;

import co.edu.uniquindio.banco_tcp.server.model.Cuenta;
import co.edu.uniquindio.banco_tcp.server.model.Transaccion;
import co.edu.uniquindio.banco_tcp.server.model.Usuario;

import java.util.ArrayList;

public class DataBase {

    private static DataBase instance;
    private ArrayList<Usuario> listaUsuarios;
    private ArrayList<Cuenta> listaCuentas;
    private ArrayList<Transaccion> listaTransacciones;

    private DataBase(){

        listaUsuarios = new ArrayList<>();
        listaCuentas = new ArrayList<>();
        listaTransacciones = new ArrayList<>();

        Usuario omar = new Usuario("omar","1094953624","1234",50000);
        Usuario victor = new Usuario("victor","9846880","1234",60000);
        Cuenta cuentaOmar = omar.getCuenta();
        Cuenta cuentaVictor = victor.getCuenta();

        this.listaUsuarios.add(omar);
        this.listaUsuarios.add(victor);
        this.listaCuentas.add(cuentaOmar);
        this.listaCuentas.add(cuentaVictor);

    }

    public static DataBase getInstance(){

        if(instance==null){
            instance = new DataBase();
        }

        return instance;
    }

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public ArrayList<Cuenta> getListaCuentas() {
        return listaCuentas;
    }

    public ArrayList<Transaccion> getListaTransacciones() {
        return listaTransacciones;
    }

}
