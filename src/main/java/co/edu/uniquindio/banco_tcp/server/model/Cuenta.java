package co.edu.uniquindio.banco_tcp.server.model;

import java.util.ArrayList;

public class Cuenta {

    private String numeroCuenta;
    private String titular;
    private double saldo;
    private String clave;
    private ArrayList<Transaccion> listaTransacciones;

    public Cuenta (String titular,double saldo, String clave){

        this.listaTransacciones = new ArrayList<>();
        this.titular=titular;
        this.saldo=saldo;
        this.clave=clave;
        this.numeroCuenta= String.valueOf((int) (Math.floor(Math.random()*100000000)));  //Número de cuenta es un número aleatorio entre 00000000 y 99999999

    }

    public ArrayList<Transaccion> getListaTransacciones() {
        return listaTransacciones;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
