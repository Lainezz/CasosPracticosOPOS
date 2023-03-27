package org.practicos.canarias.clases;

import java.io.*;

public class Cine {
    private static final int FILAS = 10;
    private static final int ASIENTOS = 15;
    private static String rutaArchivo;
    public static String nombreCine = "Cine Club Lumiere";
    public String[][] cine = new String[FILAS][ASIENTOS];

    public Cine(){
        // Inicializamos el array cine vacío, es decir, con todos los asientos vacíos
        for (int i=0; i<cine.length; i++) {
            for (int j=0; j<cine[i].length; j++) {
                cine[i][j] = "__";
            }
        }
    }

    public void mostrarAsientos() {

        // Se puede hacer una atención a la diversidad en este método. A la hora de mostrar el menú,
        // un estudiante lo puede hacer con System.out.println normal... o con la función .format. La
        // cual es más compleja de entender y está relacionada con otros lenguajes de programación como C
        System.out.format("%5s", " ");
        for (int i=0; i<ASIENTOS; i++) {
            System.out.format("%4s", "A"+(i));
        }
        System.out.println();

        for (int i=0; i<cine.length; i++) {
            System.out.format("F.%2s.", i);
            for (int j=0; j<cine[i].length; j++) {
                System.out.format("%4s", cine[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * @see <a href="https://chuidiang.org/index.php?title=Lectura_y_Escritura_de_Ficheros_en_Java#Lectura_de_un_fichero_de_texto_en_java">Link</a>
     * @param rutaArchivo
     */
    public void leerAsientos(String rutaArchivo) {
        /*  A la hora de leer un archivo .txt. Podemos hacerlo de diversas formas.
            Lo podemos hacer usando la clase FileWriter, la cual tiene métodos para leer carácter por carácter.
            En este caso, como se nos indica que la información contenida en el .txt va a estar en líneas
            independientes, podemos usar la clase BufferedReader, la cual tiene métodos para leer línea por línea
        */

        // Declaramos los objetos y variables que vamos a usar.
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        // A la hora de leer archivos, se pueden producir errores, así que englobamos la lectura dentro
        // de un bloque try-catch
        try {
            // Abrimos el archivo estableciendo un flujo de lectura.
            archivo = new File(this.rutaArchivo);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Leemos línea por línea
            String linea = null;
            while ((linea = br.readLine()) != null){

                // En este punto debemos usar algún método de la clase String para separar la fila del asiento
                Butaca b = new Butaca(linea);
                cine[b.getFila()][b.getAsiento()] = "XX";
            }

        } catch (IOException e) {
            System.err.println("Error leyendo el archivo. Compruebe la validez de la uri");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error genérico. Se recomienda reiniciar la aplicación");
            e.printStackTrace();
        } finally {
            // Es buena práctica cerrar los flujos de lectura/escritura
            try {
                if (br != null){
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (Exception e) {
                System.err.println("Error al cerrar los flujos de lectura");
                e.printStackTrace();
            }
        }

    }

    public void escribirAsientos() {
        File archivo = null;
        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            archivo = new File(this.rutaArchivo);
            fw = new FileWriter(archivo);
            bw = new BufferedWriter(fw);

            for (int i=0; i<cine.length;i++){
                for (int j=0; j<cine[i].length;j++){

                    if (cine[i][j].equalsIgnoreCase("XX")){
                        Butaca b = new Butaca(i,j);
                        bw.write(b.toString());
                        bw.newLine();
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error leyendo el archivo. Compruebe la validez de la uri");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error genérico. Se recomienda reiniciar la aplicación");
            e.printStackTrace();
        } finally {
            // Es buena práctica cerrar los flujos de lectura/escritura
            try {
                if (bw != null){
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (Exception e) {
                System.err.println("Error al cerrar los flujos de escritura");
                e.printStackTrace();
            }
        }
    }

    public int comprarEntrada(Butaca butaca) {
        if (butaca == null) {
            // -1 indica error a la hora de realizar la compra/venta
            return -1;
        }
        if (cine[butaca.getFila()][butaca.getAsiento()].equalsIgnoreCase("__")) {
            cine[butaca.getFila()][butaca.getAsiento()] = "XX";
            // 1 indica que el asiento está libre y que la compra ha sido efectuada
            return 1;
        } else {
            // 0 indica que el asiento está ocupado
            return 0;
        }
    }

    public int devolverEntrada(Butaca butaca) {
        if (butaca == null) {
            // -1 indica error a la hora de realizar la compra/venta
            return -1;
        }
        if (cine[butaca.getFila()][butaca.getAsiento()].equalsIgnoreCase("XX")) {
            cine[butaca.getFila()][butaca.getAsiento()] = "__";
            // 1 indica que el asiento pasa de estar ocupado a estar libre y que la devolucion ha sido efectuada
            return 1;
        } else {
            // 0 indica que el asiento está ya libre, y no se puede devolver algo que no está ocupado
            return 0;
        }
    }
}
