

package com.mycompany.hellojdbc;


public class Principal {
    public static void main(String[] args) {
        String rutaArchivo = "C:\\Users\\belen\\Downloads\\libreria.csv";
        LibreriaImporter.importar(rutaArchivo);
    }
}