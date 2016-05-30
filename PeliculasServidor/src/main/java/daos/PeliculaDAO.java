/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public static final String UPDATE_PELICULA = "UPDATE pelicula SET TITULO=?,CALIFICACION=? WHERE N_REFERENCIA=?";
    public static final String SELECT_ALL_PELICULAS = "SELECT * FROM PELICULA";
    public static final String INSERT = "INSERT into pelicula (TITULO,CALIFICACION) VALUES (?,?)";
    public static final String DELETE_PELICULA = "DELETE from PELICULA where N_REFERENCIA=?";

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
                Pelicula p = new Pelicula(n_referencia, titulo, calificacion);
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

    public void updatePelicula(Pelicula p) {
        System.out.println("URL BASTEDATOS:" + config.Configuration.getInstance().getDburl());
        Connection connection = null;
        DBConnection con = new DBConnection();
        try {
            connection = con.getConnection();
            PreparedStatement pst = connection.prepareStatement(UPDATE_PELICULA);

            pst.setString(1, p.getTitulo());
            pst.setInt(2, p.getCalificacion());
            pst.setInt(3, p.getN_referencia());
            System.out.println("Filas Actualizadas: " + pst.executeUpdate());
            //STEP 5: Extract data from result set
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PeliculaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PeliculaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion(connection);
        }
    }

    public int insertPelicula(Pelicula p) {
        Connection connection = null;
        int ins = 0;
        DBConnection con = new DBConnection();
        try {
            connection = con.getConnection();
            String sqlLastId = "select last_insert_rowid()";
            PreparedStatement pst = connection.prepareStatement(INSERT);
            pst.setString(1, p.getTitulo());
            pst.setInt(2, p.getCalificacion());
           pst.executeUpdate();
            Statement state = connection.createStatement();
            ResultSet rs = state.executeQuery(sqlLastId);
            ins=rs.getInt(1);
            System.out.println("IDCALE: " + rs.getInt(1));
            //STEP 5: Extract data from result set
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PeliculaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PeliculaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion(connection);
        }
        return ins;
    }

    public void deletePelicula(int l) {
        Connection connection = null;
        DBConnection con = new DBConnection();
        try {
            connection = con.getConnection();
            PreparedStatement stmt = connection.prepareStatement(DELETE_PELICULA);
            stmt.setInt(1, l);
            System.out.println("Filas Borradas: " + stmt.executeUpdate());
            //STEP 5: Extract data from result set
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PeliculaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PeliculaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion(connection);
        }
    }
}
