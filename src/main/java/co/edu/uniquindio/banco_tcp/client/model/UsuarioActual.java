package co.edu.uniquindio.banco_tcp.client.model;

import javafx.stage.Stage;

import java.util.ArrayList;

public class UsuarioActual {                         //Clase para albergar los datos del usuario loggeado
                                                     //Esto sería como una clase caché, en donde se almacenan algunos datos
    private static UsuarioActual instance;           //de la base de datos para no consultarla todo el tiempo
    private String nombre;
    private double saldo;
    private String numCuenta;
    private String cedula;
    private ArrayList<Stage> listaStages = new ArrayList<>();
    private ArrayList<Transaccion> listaTransacciones = new ArrayList<>();

    private UsuarioActual (){

    }

    public static UsuarioActual getInstance(){

        if(instance==null){
            instance = new UsuarioActual();
        }

        return instance;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }

    public ArrayList<Stage> getListaStages(){
        return this.listaStages;
    }

    public void setListaStages(ArrayList<Stage>stages){
        this.listaStages=stages;
    }

    public void setCedula(String cedula){
        this.cedula=cedula;
    }
    public String getCedula(){
        return cedula;
    }

    public ArrayList<Transaccion> getListaTransacciones(){return listaTransacciones;}
}
