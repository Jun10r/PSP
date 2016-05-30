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
import pojos.Actor;

/**
 *
 * @author Junior
 */
//select Peliculas.nombre,Actuan.nombre from Pelicula Inner Join Actuan On Pelicula.nombre = Actuan.NOMBREPELICULA
public class ActorDAO {

    public static final String SELECT_ALL_ACTORES = "SELECT * FROM ACTOR";
    public static final String INSERT_ACTOR = "INSERT INTO ACTOR (DNI,NOMBRE) VALUES(?,?)";
    public static final String SELECT_ACTOR_BY_MOVIE = "SELECT * FROM ACTOR A JOIN ACTUAN P ON P.DNI_ACTOR=A.DNI AND P.REF_PELICULA=?";
    public static final String INSERT_ACTUAN = "INSERT INTO ACTUAN(DNI_ACTOR,REF_PELICULA) VALUES(?,?)";
    public static final String SELECT_ACTOR = "SELECT * FROM ACTOR WHERE NOMBRE=?";

    public Actor selectActor(String name) {
        Actor a = null;
        Connection connection = null;
        DBConnection con;
        con = new DBConnection();
        try {
            connection = con.getConnection();
            PreparedStatement pst = connection.prepareStatement(SELECT_ACTOR);
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int dni = rs.getInt("DNI");
                String nombre = rs.getString("NOMBRE");
                a = new Actor(dni, nombre);
            }
        } catch (Exception e) {
        }
        return a;
    }

    public boolean insertActor(Actor a) {
        boolean inserted = false;
        Connection connection = null;
        DBConnection con;
        con = new DBConnection();
        try {
            connection = con.getConnection();
            PreparedStatement pst = connection.prepareStatement(INSERT_ACTOR);
            pst.setInt(1, a.getDni());
            pst.setString(2, a.getNombre());
            int result = pst.executeUpdate();
            inserted = result != 0;
        } catch (SQLException ex) {
            Logger.getLogger(DirectorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DirectorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                con.cerrarConexion(connection);
            }
        }
        return inserted;
    }

    public boolean insertActuan(Actor a, int idPelicula) {
        boolean inserted = false;
        Connection connection = null;
        DBConnection con;
        con = new DBConnection();
        try {
            connection = con.getConnection();
            PreparedStatement pst2 = connection.prepareStatement(INSERT_ACTUAN);
            pst2.setInt(1, a.getDni());
            pst2.setInt(2, idPelicula);
            int result = pst2.executeUpdate();
            inserted = result != 0;
        } catch (SQLException ex) {
            Logger.getLogger(DirectorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DirectorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                con.cerrarConexion(connection);
            }
        }
        return inserted;
    }

    public ArrayList<Actor> getAllActorsByMovie(int codRef) {
        ArrayList<Actor> actores = new ArrayList<>();
        Connection connection = null;
        DBConnection con = new DBConnection();

        try {
            connection = con.getConnection();
            PreparedStatement pst = connection.prepareStatement(SELECT_ACTOR_BY_MOVIE, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            pst.setInt(1, codRef);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int dni = rs.getInt("DNI");
                String nombre = rs.getString("NOMBRE");
                Actor d = new Actor(dni, nombre);
                actores.add(d);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ActorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ActorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                con.cerrarConexion(connection);
            }
        }
        return actores;
    }

    public ArrayList<Actor> getAllActors() {
        ArrayList<Actor> actores = new ArrayList<>();
        Connection connection = null;
        DBConnection con = new DBConnection();

        try {
            connection = con.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL_ACTORES);

            while (rs.next()) {
                int dni = rs.getInt("DNI");
                String nombre = rs.getString("NOMBRE");
                Actor d = new Actor(dni, nombre);
                actores.add(d);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ActorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ActorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                con.cerrarConexion(connection);
            }
        }
        return actores;
    }

}
