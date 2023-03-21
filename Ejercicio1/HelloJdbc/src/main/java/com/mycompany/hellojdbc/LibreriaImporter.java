package com.mycompany.hellojdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LibreriaImporter {
    public static void importar(String archivo) {
        String url = "jdbc:mysql://localhost:3306/libreria";
        String usuario = "root";
        String contrasena = "";
        try {
            Connection conexion = DriverManager.getConnection(url, usuario, contrasena);
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(";");
                String titulo = campos[0];
                String autor = campos[1];
                int anio = Integer.parseInt(campos[2]);
                String genero = campos[3];
                String idioma_original = campos[4];
                String sinopsis = campos[5];

                String sql = "INSERT INTO libros (titulo, autor, anio, genero, idioma_original, sinopsis) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = conexion.prepareStatement(sql);
                ps.setString(1, titulo);
                ps.setString(2,autor);
                ps.setInt(3, anio);
                ps.setString(4, genero);
                ps.setString(5, idioma_original);
                ps.setString(6, sinopsis);
                ps.executeUpdate();

                System.out.println("Contenido añadido correctamente");
            }
            br.close();
            fr.close();
            conexion.close();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
