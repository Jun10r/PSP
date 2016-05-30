/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daw.DirectorDAO;
import java.util.ArrayList;
import org.apache.http.impl.client.CloseableHttpClient;
import pojos.Director;

/**
 *
 * @author Junior
 */
public class ControlDirector {
    private DirectorDAO directoresDAO;

    public ControlDirector() {
        directoresDAO = new DirectorDAO();
    }
    
    
    public ArrayList<Director> getDirectorByMovie(CloseableHttpClient httpclient,int codRef){
        ArrayList<Director> directores = null;
        directores = directoresDAO.getDirectorByMovie(httpclient,codRef);
        return directores;
    }
    public ArrayList<Director> getAllDirectores(CloseableHttpClient httpclient){
        ArrayList<Director> actores = null;
        actores = directoresDAO.getAllDirectores(httpclient);
        return actores;
    }
    
    public void update(Director a, CloseableHttpClient httpclient) {
        directoresDAO.updateDirectores(a, httpclient);
    }

    public boolean inserted(Director a, int id, CloseableHttpClient httpclient) {
        return directoresDAO.insertDirectores(a,id, httpclient);
    }
    
    public void delete(int a, CloseableHttpClient httpclient){
        directoresDAO.deleteDirectores(a, httpclient);
    }
}
