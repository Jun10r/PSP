/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import static daos.ActorDAO.SELECT_ACTOR_BY_MOVIE;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojos.Actor;
import pojos.Genero;

/**
 *
 * @author Junior
 */
public class GeneroDAO {
    public static final String SELECT_GENERO_BY_MOVIE="SELECT * FROM GENERO G JOIN PELICULA_GENERO PG ON G.ID=PG.ID_GENERO AND PG.N_PELICULA=?";
    
    public ArrayList<Genero> getAllGeneroByMovie(int codRef) {
        ArrayList<Genero> generos = new ArrayList<>();
        Connection connection = null;
        DBConnection con = new DBConnection();

        try {
            connection = con.getConnection();
             PreparedStatement pst = connection.prepareStatement(SELECT_GENERO_BY_MOVIE,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
             pst.setInt(1,codRef);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID");
                String nombre = rs.getString("NOMBRE");
                Genero d = new Genero(id, nombre);
                generos.add(d);
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
        return generos;
    }
}
