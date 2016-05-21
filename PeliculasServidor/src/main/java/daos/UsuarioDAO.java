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
import java.util.logging.Level;
import java.util.logging.Logger;
import pojos.Usuario;

/**
 *
 * @author Junior
 */
public class UsuarioDAO {

    public static final String SELECT_LOGIN = "SELECT * from USUARIO WHERE LOGIN = ?";
    public static final String INSERT_USER = "INSERT INTO USUARIO (LOGIN,PASS)VALUES(?,?)";

    public boolean insertUser(Usuario u) {
        boolean inserted = false;
        Connection connection = null;
        DBConnection con = new DBConnection();
        try {
            connection = con.getConnection();
            connection = con.getConnection();
            PreparedStatement pst = connection.prepareStatement(INSERT_USER);
            pst.setString(1, u.getUser());
            pst.setString(2, u.getPassword());
            int result = pst.executeUpdate();
            if (result != 0) {
                inserted = true;
            } else {
                inserted = false;
            }
            System.out.println("FILAS INSERTADAS: " + result);
            //STEP 5: Extract data from result set
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                con.cerrarConexion(connection);
            }

        }
        return inserted;
    }
    public Usuario loginUser(Usuario u) {
        System.out.println("LOGIN DE USUARIO de CLIENTE:" + u.getUser());
        Connection connection = null;
        DBConnection con = new DBConnection();
        Usuario user = null;
        try {
            connection = con.getConnection();
            PreparedStatement pst = connection.prepareStatement(SELECT_LOGIN);
            pst.setString(1, u.getUser());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String login = rs.getString("Login");
                String password = rs.getString("PASS");
                System.out.println(password);
                user = new Usuario(login, password);
            }
            //STEP 5: Extract data from result set
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                con.cerrarConexion(connection);
            }
        }
        return user;
    }
    
    
}
