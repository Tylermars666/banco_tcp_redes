package co.edu.uniquindio.banco_tcp.server.interfaces;

public interface UsuarioCrud {

    String buscarUsuario(String cedula);

    String registrarUsuario(String nombre, String cedula, String clave, double capitalInicial);

    boolean existeUsuario(String cedula);

    String eliminarUsuario(String cedula);
}
