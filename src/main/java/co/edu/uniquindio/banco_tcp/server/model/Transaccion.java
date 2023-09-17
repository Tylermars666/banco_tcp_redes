package co.edu.uniquindio.banco_tcp.server.model;

import java.time.LocalDate;

public class Transaccion {

    private LocalDate fecha;
    private double valor;
    private String cuentaOrigen;

    public Transaccion(String cuentaOrigen, double valor){

       fecha=LocalDate.now();
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
}
