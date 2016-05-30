/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daw.UsuariosDAO;
import org.apache.http.impl.client.CloseableHttpClient;
import pojos.Usuario;


/**
 *
 * @author Junior
 */
public class ControlUsuario {

    public boolean insertUser(Usuario u , CloseableHttpClient httpclient) {
        UsuariosDAO dao = new UsuariosDAO();
        return dao.insertUser(u,httpclient);
    }

    public boolean login(Usuario u,CloseableHttpClient httpclient) {
        UsuariosDAO dao = new UsuariosDAO();
        return dao.login(u,httpclient);
    }
}
