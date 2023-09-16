package co.edu.uniquindio.banco_tcp.server.model;

public class Usuario {

    private String nombre;
    private String cedula;
    private Cuenta cuenta;

    public Usuario(String nombre, String cedula, String clave, double capitalInicial){

        this.nombre=nombre;
        this.cedula=cedula;
        this.cuenta = new Cuenta(nombre,capitalInicial,clave);

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

}
