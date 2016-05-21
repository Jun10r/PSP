/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojos.Pelicula;

/**
 *
 * @author Junior
 */
public class PeliculaDAO {

    public static final String SELECT_ALL_PELICULAS = "SELECT * FROM PELICULA";

    public ArrayList<Pelicula> getAllPeliculas() {
        ArrayList<Pelicula> pelis = new ArrayList<>();
        Connection connection = null;
        DBConnection con = new DBConnection();

        try {
            connection = con.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL_PELICULAS);

            while (rs.next()) {
                int n_referencia = rs.getInt("N_REFERENCIA");
                String titulo = rs.getString("TITULO");
                int calificacion = rs.getInt("CALIFICACION");
                int cod_director = rs.getInt("DIRECTOR");
                Pelicula p = new Pelicula(n_referencia, titulo, calificacion,cod_director);
                pelis.add(p);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PeliculaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PeliculaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                con.cerrarConexion(connection);
            }
        }
        return pelis;
    }

}
