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
  
}