package co.edu.uniquindio.banco_tcp.server.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class GeneralTesting {

    @Test
    void aleatorio(){

        System.out.println((int) (Math.floor(Math.random()*100000000)));
    }

    @Test
    void splitting(){

        String splin = "omar:mamawebo:victor:chupandonga:2231";

        String [] claves = splin.split(":");

        for(int i = 0 ; i <= claves.length-1; i++){

            System.out.println(claves[i]);
        }

    }

    @Test
    public void quitarAstericos(){

        String texto = "mamawebo***";
        String textoSinAsteriscos = "";

        for(int i = 0; i<texto.length(); i++){

            if(!(texto.charAt(i)=='*')){

                textoSinAsteriscos+=texto.charAt(i);

            }

        }

        System.out.println(textoSinAsteriscos);
    }

    @Test
    public void encriptarCesar(){

        String ALFABETO = "abcdefghijklmnopqrstuvwxyz0123456789";
        String textoCesar = "";
        int indiceAux;
        int clave = 6;
        String texto = "ana";

        for(int i = 0; i<= texto.length()-1; i++){

            String caracterEncriptado = "";

            for(int j = 0; j<=ALFABETO.length()-1; j++){

                if(texto.charAt(i)==' '){
                    caracterEncriptado+=' ';
                    break;

                }else {
                    if(texto.charAt(i)==ALFABETO.charAt(j)){

                        if(j + clave>ALFABETO.length()-1){

                            indiceAux = Math.abs(36 - (j + clave));

                        }else{

                            if(j + clave < 0){
                                indiceAux = 36 + (j + clave);

                            }else{

                                indiceAux = j + clave;
                            }
                        }
                        caracterEncriptado+=ALFABETO.charAt(indiceAux);
                        break;
                    }
                }
            }
            textoCesar+=caracterEncriptado;                            //Terminado el ciclo interno, se asigna a la variable textoCesar el String
        }                                                              //almacenado en la variable caracterEncriptado y por último se retorna
        System.out.println(textoCesar);                                             //ese String que corresponde a la palabra totalmente encriptada
    }
  
}