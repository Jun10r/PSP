package daos;

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

    public static final String INSERT_DIRECTOR = "INSERT INTO DIRECTOR (NOMBRE,APELLIDO) VALUES(?,?)";
    
    public boolean insertDirector(Director n) {
        boolean inserted = false;
        Connection connection = null;
        
        DBConnection con;
        con = new DBConnection();
        try {
            connection = con.getConnection();
            PreparedStatement pst = connection.prepareStatement(INSERT_DIRECTOR);
            pst.setString(1, n.getNombre());
            pst.setString(2, n.getApellido());
            int result = pst.executeUpdate();
            inserted = result != 0;
            System.out.println("FILAS INSERTADAS: " + result);
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

}
