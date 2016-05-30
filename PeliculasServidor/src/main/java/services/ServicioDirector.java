/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import daos.DirectorDAO;
import java.util.ArrayList;
import pojos.Director;

/**
 *
 * @author Junior
 */
public class ServicioDirector {
    private final DirectorDAO directorDAO;

    public ServicioDirector() {
        this.directorDAO = new DirectorDAO();
    }
    
    public ArrayList<Director> getAllDirectores(){
        ArrayList<Director> directores= null;
        directores = directorDAO.getAllDirector();
        return directores;
    }
    public ArrayList<Director> getAllDirectoresByMovie(int codRef){
        ArrayList<Director> directores = null;
        directores = directorDAO.getAllDirectoresByMovie(codRef);
        return directores;
    }
    public boolean insertDirector(Director d,int idPelicula){
       return directorDAO.insertDirector(d, idPelicula);
    }
}
