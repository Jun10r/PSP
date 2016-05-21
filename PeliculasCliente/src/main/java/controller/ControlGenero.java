/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daw.ActorDAO;
import daw.GeneroDAO;
import java.util.ArrayList;
import org.apache.http.impl.client.CloseableHttpClient;
import pojos.Actor;
import pojos.Genero;

/**
 *
 * @author Junior
 */
public class ControlGenero {
    
     public ArrayList<Genero> getGeneroByMovie(CloseableHttpClient httpclient,int codRef){
        ArrayList<Genero> generos = null;
        GeneroDAO generosDAO = new GeneroDAO();
        generos = generosDAO.getGeneroByMovie(httpclient, codRef);
        return generos;
    }
}
