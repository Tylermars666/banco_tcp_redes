package co.edu.uniquindio.banco_tcp.client.model;

import java.time.LocalDate;

public class Transaccion {

    private String fecha;
    private double valor;
    private String cuentaOrigen;

    public Transaccion(String cuentaOrigen, double valor,String fecha){

        this.fecha = fecha;
        this.cuentaOrigen =cuentaOrigen;
        this.valor=valor;

    }

    public String getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(String cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getFecha() {return fecha;}

    public void setFecha(String fecha) {this.fecha = fecha;}
}