package co.edu.uniquindio.banco_tcp.server.model;

import java.time.LocalDate;

public class Transaccion {

    private LocalDate fecha;
    private double valor;
    private String cuentaDestino;

    public Transaccion(String cuentaDestino, double valor){

       fecha=LocalDate.now();
       this.cuentaDestino=cuentaDestino;
       this.valor=valor;

    }

    public String getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(String cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
