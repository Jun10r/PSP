/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.DirectorDAO;
import pojos.Director;

/**
 *
 * @author Junior
 */
public class ServicioDirector {
    
    public boolean insertDirector(Director d){
        DirectorDAO directorDAO = new DirectorDAO();
        return directorDAO.insertDirector(d);
    }
}
