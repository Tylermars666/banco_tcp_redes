package co.edu.uniquindio.banco_tcp.server.logic;

import co.edu.uniquindio.banco_tcp.server.database.DataBase;
import co.edu.uniquindio.banco_tcp.server.interfaces.UsuarioCrud;
import co.edu.uniquindio.banco_tcp.server.model.Usuario;

public class UsuarioCrudImplement implements UsuarioCrud {

    private static UsuarioCrudImplement instance;
    private final DataBase dataBase;

    private UsuarioCrudImplement(){

        dataBase = DataBase.getInstance();

    }

    public static UsuarioCrudImplement getInstance(){

        if(instance==null){

            instance = new UsuarioCrudImplement();
        }

        return instance;
    }

    public DataBase getDataBase (){

        return dataBase;

    }

    @Override
    public String buscarUsuario(String cedula) {

        String datosUsuario = "";

        try{
            for(Usuario u: dataBase.getListaUsuarios()){

                if(u.getCedula().equalsIgnoreCase(cedula)){

                    datosUsuario = u.getNombre()+":"+u.getCedula()+":"+u.getCuenta().getClave()+":"+u.getCuenta().getNumeroCuenta()+":"+u.getCuenta().getSaldo();   //nombre:cedula:clave:numCuenta:saldo
                    break;
                }else{

                    datosUsuario = "error:user:not:found:here";

                }

            }
        }catch (NullPointerException npe){
            datosUsuario = "error:user:not:found:here";
        }

        return datosUsuario;
    }

    @Override
    public String registrarUsuario(String nombre, String cedula, String clave, double capitalInicial) {

        if(!existeUsuario(cedula)){
            Usuario usuario = new Usuario(nombre, cedula, clave, capitalInicial);
            dataBase.getListaUsuarios().add(usuario);
            dataBase.getListaCuentas().add(usuario.getCuenta());
            return "registrado:"+usuario.getNombre()+":"+usuario.getCuenta().getNumeroCuenta()+":"+usuario.getCuenta().getSaldo(); //existe:nombre:numCuenta:saldo
        }else{

            return "existente";
        }
    }

    @Override
    public boolean existeUsuario(String cedula) {

        for(Usuario u : dataBase.getListaUsuarios()){

            if(u.getCedula().equalsIgnoreCase(cedula)){

                return true;
            }

        }

        return false;

    }

    public String eliminarUsuario(String cedula){

        String numCuenta = "";

        for(Usuario usuario: dataBase.getListaUsuarios()){

            if(usuario.getCedula().equalsIgnoreCase(cedula)){

                numCuenta = usuario.getCuenta().getNumeroCuenta();
                dataBase.getListaUsuarios().remove(usuario);
                return numCuenta;

            }
        }
        return numCuenta;



    }

    @Override
    public String editarUsuario(String clave, String nombre, String cedula) {

        for(Usuario usuario : dataBase.getListaUsuarios()){

            if(usuario.getCedula().equalsIgnoreCase(cedula)){

                usuario.setNombre(nombre);
                usuario.getCuenta().setClave(clave);
                return "exito:"+nombre;

            }
        }
        return "fracaso";
    }
}
