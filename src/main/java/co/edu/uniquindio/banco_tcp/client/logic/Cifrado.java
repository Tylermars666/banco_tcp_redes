package co.edu.uniquindio.banco_tcp.client.logic;

public class Cifrado {

    private static Cifrado instance;
    private final String ALFABETO = "abcdefghijklmnopqrstuvwxyz0123456789";

    private Cifrado(){


    }

    public static Cifrado getInstance(){

        if(instance==null){
            instance = new Cifrado();
        }

        return instance;

    }

    public String encriptarCesar(String texto, int clave){    //Se usa para encriptar y desencriptar

        String textoCesar = "";
        int indiceAux;

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
                        if(j + clave>ALFABETO.length()-1){            //Si este indice sumado a la clave es mayor que el tamaño del alfabeto

                            indiceAux = Math.abs(36 - (j + clave));             //Entonces se procede a calcular el indice correspondiente para esa letra
                                                                                //Es decir que le da la vuelta al alfabeto y vuelve a empezar por las primeras letras
                        }else{

                            if(j + clave < 0){                        //En este caso se valida si el indice de la letra del alfabeto
                                indiceAux = 36 + (j + clave);         //sumado a la clave, es menor que cero, es decir, la clave es negativa
                                                                       //Se calcula el nuevo indice que en este caso dará la vuelta al alfabeto y llegará a las ultimas letras
                            }else{

                                indiceAux = j + clave;                //En caso de que ninguno de los casos anteriores se cumpla, entonces simplemente
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

    public String encriptarChino(String texto){                       //Este método de encriptamiento siempre usará una matriz de 3 filas

        int rows = texto.length()/3;                                 //Entonces se calculan el número de columnas que tendrá la matriz
        if(texto.length()%3!=0){                                     //Si el tamaño de texto no cabe en la matriz, se agrega una columna más
            rows = rows + 1;
        }

        char [][] matriz = llenarMatriz(texto, rows);                //Llenamos la matriz con el texto escrito por el usuario
        //Enviamos unicamente el texto y el número de columnas, ya que las filas siempre serán 3
        return obtenerTextoChino(matriz, rows);                      //Retornamos el texto obtenido al recorrer la matriz en forma de serpiente

    }

    private char[][] llenarMatriz(String texto, int rows){

        char [][] matriz = new char[3][rows];                        //Se inicializa una matriz de caracteres con 3 filas y las columnas enviadas por parámetro
        int indiceTexto = 0;

        if(3*rows>texto.length()){                                   //En caso de que al texto le hagan falta caracteres para completar la matriz
            texto = completarTexto(texto, 3*rows-texto.length() );   //Se invoca un método para completarlo con asteriscos  EJ: universidad*
        }

        for(int i = 0; i<3; i++){                                    //Se llena la matriz en el orden normal

            for(int j = 0; j<rows; j++, indiceTexto++){

                matriz[i][j] = texto.charAt(indiceTexto);
            }
        }

        return matriz;                                              //Se retorna la matriz ya llenada

    }

    private String completarTexto(String texto, int diferencia){     //Método para completar el texto con asteriscos
        //Se recibe por parámetro la diferencia que son los caracteres
        String textoAsterisco = texto;                              //que le hacen falta al texto para completar la matriz

        for(int i = 0; i<diferencia; i++){                          //Se concatenan los asteriscos al texto original

            textoAsterisco+="*";
        }
        return textoAsterisco;                                     //Se retorna el texto completado con los asteriscos
    }

    private String obtenerTextoChino(char[][] matriz, int rows){

        String textoChino = "";                                   //Este método recorre la matriz en forma de serpiente
        boolean snake = true;                                     //y construye el nuevo texto que será finalmente el encriptado chino

        for(int j = rows-1; j>=0; j--){                           //Siempre se recorren columnas de derecha a izquierda

            String textoChinoAux = "";

            if(snake){                                            //Las filas se recorren de arriba a abajo

                for(int i = 0; i < 3; i++){

                    textoChinoAux += matriz[i][j];

                }
                snake = false;

            }else {

                for(int i = 2; i>= 0; i--){                      //Y luego de abajo hacia arriba

                    textoChinoAux+=matriz[i][j];
                }
                snake = true;
            }
            textoChino += textoChinoAux;                         //Se almacena el texto de la concatenación de caracteres resultante
        }
        return textoChino;                                       //Y se retorna para imprimirse en la interfaz gráfica

    }

}
