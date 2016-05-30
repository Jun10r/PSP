package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojos.Director;

/**
 *
 * @author Junior
 */
public class DirectorDAO {

    public static final String SELECT_ALL_DIRECTORES = "SELECT * FROM DIRECTOR";
    public static final String INSERT_DIRECTOR = "INSERT INTO DIRECTOR (DNI,NOMBRE) VALUES(?,?)";
    public static final String UPDATE_DIRECTOR = "UPDATE DIRECTOR ";
    public static final String SELECT_DIRECTOR_BY_MOVIE = "SELECT * FROM DIRECTOR A JOIN DIRIGEN P ON P.DNI_DIRECTOR=A.DNI AND P.REF_PELICULA=?";
    public static final String INSERT_DIRIGEN = "INSERT INTO DIRIGEN(DNI_DIRECTOR,REF_PELICULA) VALUES(?,?)";

    public boolean insertDirector(Director n,int idPelicula) {
        boolean inserted = false;
        Connection connection = null;

        DBConnection con;
        con = new DBConnection();
        try {
            connection = con.getConnection();
            PreparedStatement pst = connection.prepareStatement(INSERT_DIRECTOR);
            pst.setInt(1, n.getDni());
            pst.setString(2, n.getNombre());
            int result = pst.executeUpdate();
            PreparedStatement pst2 = connection.prepareStatement(INSERT_DIRIGEN);
            pst2.setInt(1,n.getDni());
            pst2.setInt(2, idPelicula);
            int result2 = pst2.executeUpdate();
            
            inserted = result !=0 && result2 !=0;
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

    public ArrayList<Director> getAllDirectoresByMovie(int codRef) {
        ArrayList<Director> directores = new ArrayList<>();
        Connection connection = null;
        DBConnection con = new DBConnection();

        try {
            connection = con.getConnection();
            PreparedStatement pst = connection.prepareStatement(SELECT_DIRECTOR_BY_MOVIE, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            pst.setInt(1, codRef);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int dni = rs.getInt("DNI");
                String nombre = rs.getString("NOMBRE");
                Director d = new Director(dni, nombre);
                directores.add(d);
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
        return directores;
    }

    public ArrayList<Director> getAllDirector() {
        ArrayList<Director> directores = new ArrayList<>();
        Connection connection = null;
        DBConnection con = new DBConnection();

        try {
            connection = con.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL_DIRECTORES);

            while (rs.next()) {
                int dni = rs.getInt("DNI");
                String nombre = rs.getString("NOMBRE");
                Director d = new Director(dni, nombre);
                directores.add(d);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DirectorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DirectorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                con.cerrarConexion(connection);
            }
        }
        return directores;
    }
    /*
    public void updateDirector(Director u) {
        System.out.println("URL BASTEDATOS:"+config.Configuration.getInstance().getDburl());
        Connection connection = null;
        DBConnection con = new DBConnection();
        try {
            connection = con.getConnection();
            PreparedStatement pst = connection.prepareStatement(UPDATE_DIRECTOR);

            pst.setString(1, u.getName());
            pst.setString(2, u.getApellidos());
            pst.setInt(3, u.getEdad());
            pst.setDate(5, new java.sql.Date(u.getFecnac().getTime()));
            //pst.setDate(4, new java.sql.Date(u.getFecnac().getTime()));
            pst.setInt(5, u.getId());
            System.out.println("Filas Actualizadas: " + pst.executeUpdate());
            //STEP 5: Extract data from result set
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion(connection);
        }
     */
}
