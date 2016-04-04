package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojos.Director;

/**
 *
 * @author Junior
 */
public class DirectorDAO {

    public static final String INSERT_DIRECTOR = "INSERT INTO DIRECTOR (NOMBRE,EDAD) VALUES(?,?)";
    
    public boolean insertNotes(Director n) {
        boolean inserted = false;
        Connection connection = null;
        DBConnection con = new DBConnection();
        try {
            connection = con.getConnection();
            PreparedStatement pst = connection.prepareStatement(INSERT_NOTAS);
            pst.setString(1, n.getNombre());
            pst.setInt(2, n.getValor());
            int result = pst.executeUpdate();
            if (result != 0) {
                inserted = true;
            } else {
                inserted = false;
            }
            System.out.println("FILAS INSERTADAS: " + result);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NotasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NotasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                con.cerrarConexion(connection);
            }
        }
        return inserted;
    }
}
