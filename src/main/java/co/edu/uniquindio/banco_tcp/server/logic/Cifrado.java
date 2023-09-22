package co.edu.uniquindio.banco_tcp.server.logic;

public class Cifrado {

    private static Cifrado instance;
    private final String ALFABETO = "abcdefghijklmnopqrstuvwxyz0123456789";

    private Cifrado(){


    }

    public static Cifrado getInstance() {

        if (instance == null) {
            instance = new Cifrado();
        }

        return instance;
    }

    public String dencriptarCesar(String texto, int clave){    //Se usa para encriptar y desencriptar

        String textoCesar = "";
        int indiceAux;
        int claveInversa = -clave;

        for(int i = 0; i<= texto.length()-1; i++){                       //Se toma cada letra del texto ingresado y se compara
                                                                         //cada una con todas las letras del alfabeto
            String caracterEncriptado = "";

            for(int j = 0; j<=ALFABETO.length()-1; j++){

                if(texto.charAt(i)==' '){                               //Se valida si hay un espacio en blanco dentro del texto
                    caracterEncriptado+=' ';                            //ingresado por el usuario
                    break;

                }else {
                    if(texto.charAt(i)==ALFABETO.charAt(j)){          //Se valida entonces si la letra del texto es igual a la letra del alfabeto
                                                                      //Se tiene en cuenta el índice de la letra del alfabeto
                        if(j + claveInversa>ALFABETO.length()-1){            //Si este indice sumado a la clave es mayor que el tamaño del alfabeto

                            indiceAux = Math.abs(36 - (j + claveInversa));             //Entonces se procede a calcular el indice correspondiente para esa letra
                                                                                      //Es decir que le da la vuelta al alfabeto y vuelve a empezar por las primeras letras
                        }else{

                            if(j + claveInversa < 0){                        //En este caso se valida si el indice de la letra del alfabeto
                                indiceAux = 36 + (j + claveInversa);         //sumado a la clave, es menor que cero, es decir, la clave es negativa
                                                                            //Se calcula el nuevo indice que en este caso dará la vuelta al alfabeto y llegará a las ultimas letras
                            }else{

                                indiceAux = j + claveInversa;                //En caso de que ninguno de los casos anteriores se cumpla, entonces simplemente
                            }                                         //el indice nuevo es el indice de la letra del alfabeto sumado a la clave
                        }
                        caracterEncriptado+=ALFABETO.charAt(indiceAux); //En este String se van concatenando todos los caracteres que se han encriptado mediante la clave
                        break;
                    }
                }
            }
            textoCesar+=caracterEncriptado;                            //Terminado el ciclo interno, se asigna a la variable textoCesar el String
        }                                                              //almacenado en la variable caracterEncriptado y por último se retorna
        return textoCesar;                                             //ese String que corresponde a la palabra totalmente encriptada
    }

    public String desencriptarChino(String texto){

        char[][] matrizOriginal = construirMatrizOriginal(texto);  //Con el texto encriptado se procede a construir la matriz original
        return quitarAstericos(desencriptarMatrizOriginal(matrizOriginal));

    }

    public char[][] construirMatrizOriginal(String texto){

        boolean snake = true;                                    //Al igual que en el método para encriptar, se recorre la matriz
        int rows = texto.length()/3;                             //en forma de serpiente, pero en este caso en lugar de generar un String
        char [][] matrizOriginal = new char[3][rows];            //se toma el String encriptado para generar la matriz original
        int indiceTexto = 0;

        for(int j = rows-1; j>=0; j--){

            if(snake){

                for(int i = 0; i < 3; i++, indiceTexto++){

                    matrizOriginal[i][j] = texto.charAt(indiceTexto);

                }
                snake = false;

            }else {

                for(int i = 2; i>= 0; i--, indiceTexto++){

                    matrizOriginal[i][j] = texto.charAt(indiceTexto);
                }
                snake = true;
            }
        }
        return matrizOriginal;
    }

    public String desencriptarMatrizOriginal(char[][]matriz){

        int indiceTexto = 0;                                      //Al igual que en el método para encriptar, se recorre
        String textoOriginal = "";                                //el texto para generar la matriz, pero en este caso
        int rows = matriz[0].length;                              //se construye el String desencriptado a partir de la matriz
        //original obtenida en el método anterior
        for(int i = 0; i<3; i++){

            String textoAux = "";

            for(int j = 0; j<rows; j++, indiceTexto++){

                textoAux+=matriz[i][j];
            }
            textoOriginal+=textoAux;
        }

        return textoOriginal;
    }

    public String quitarAstericos(String texto){

        String textoSinAsteriscos = "";

        for(int i = 0; i<texto.length(); i++){

            if(!(texto.charAt(i)=='*')){

                textoSinAsteriscos+=texto.charAt(i);
            }
        }

        return textoSinAsteriscos;
    }

}
