/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import daos.UsuarioDAO;
import pojos.Usuario;

/**
 *
 * @author Junior
 */
public class ServicioUsuario {
     public boolean insertUser(Usuario u) {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.insertUser(u);
    }

    public Usuario loginUser(Usuario u) {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.loginUser(u);
    }
}
