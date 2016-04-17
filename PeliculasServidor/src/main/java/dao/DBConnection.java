/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sqlite.SQLiteConfig;

/**
 *
 * @author oscar
 */
public class DBConnection {

    public static final String DB_URL = Configuration.getInstance().getDburl();
    public static final String DRIVER = "org.sqlite.JDBC";

    public Connection getConnection() throws ClassNotFoundException {
        Class.forName(DRIVER);
        Connection connection = null;
        try {
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            connection = DriverManager.getConnection(DB_URL, config.toProperties());
            
        } catch (SQLException ex) {
        }
        return connection;
    }
    
    public void cerrarConexion( Connection connection )
    {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
