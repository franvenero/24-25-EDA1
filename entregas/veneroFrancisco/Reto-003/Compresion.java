import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Compresion {

    public static class ResultadoCompresion {
        public String textoComprimido;
        public List<String> diccionario;

        public ResultadoCompresion(String textoComprimido, List<String> diccionario) {
            this.textoComprimido = textoComprimido;
            this.diccionario = diccionario;
        }
    }

    public static ResultadoCompresion comprime(String cadena) {
        List<String> diccionario = new ArrayList<>();
        List<String> resultado = new ArrayList<>();
        int posicionActual = 0;

        while (posicionActual < cadena.length()) {
            String coincidencia = "";
            int longitud = 0;

            for (int i = 1; i <= cadena.length() - posicionActual; i++) {
                String ventana = cadena.substring(posicionActual, posicionActual + i);
                if (diccionario.contains(ventana)) {
                    coincidencia = ventana;
                    longitud = ventana.length();
                } else {
                    break;
                }
            }

            if (longitud > 0) {
                int indice = diccionario.indexOf(coincidencia);

                if (posicionActual + longitud < cadena.length()) {
                    char siguienteCaracter = cadena.charAt(posicionActual + longitud);
                    resultado.add("(" + (indice + 1) + "," + siguienteCaracter + ")");
                    diccionario.add(coincidencia + siguienteCaracter);
                    posicionActual += longitud + 1;
                } else {
                    resultado.add("(" + (indice + 1) + ",)");
                    posicionActual += longitud;
                }
            } else {
                char actual = cadena.charAt(posicionActual);
                diccionario.add(String.valueOf(actual));
                resultado.add("(0," + actual + ")");
                posicionActual++;
            }
        }

        return new ResultadoCompresion(String.join("", resultado), diccionario);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese la cadena a comprimir:");
        String cadena = scanner.nextLine();

        ResultadoCompresion resultado = comprime(cadena);

        System.out.println("El resultado comprimido es:");
        System.out.println(resultado.textoComprimido);

        System.out.println("El diccionario es:");
        List<String> dicc = resultado.diccionario; 
        for (int i = 0; i < dicc.size(); i++) {
            System.out.println((i + 1) + ":" + dicc.get(i));
        }

        scanner.close();
    }
}