/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daw.UsuariosDAO;
import pojos.Usuario;


/**
 *
 * @author Junior
 */
public class ControlUsuario {

    public boolean insertUser(Usuario u ) {
        UsuariosDAO dao = new UsuariosDAO();
        return dao.insertUser(u);
    }

    public boolean login(Usuario u) {
        UsuariosDAO dao = new UsuariosDAO();
        return dao.login(u);
    }
}
