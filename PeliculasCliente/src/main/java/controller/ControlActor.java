/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daw.ActorDAO;
import java.util.ArrayList;
import org.apache.http.impl.client.CloseableHttpClient;
import pojos.Actor;

/**
 *
 * @author Junior
 */
public class ControlActor {
    
    public ArrayList<Actor> getActorsByMovie(CloseableHttpClient httpclient,int codRef){
        ArrayList<Actor> actores = null;
        ActorDAO actoresDAO = new ActorDAO();
        actores = actoresDAO.getActorsByMovie(httpclient,codRef);
        return actores;
    }
    public ArrayList<Actor> getAllActors(CloseableHttpClient httpclient){
        ArrayList<Actor> actores = null;
        ActorDAO actoresDAO = new ActorDAO();
        actores = actoresDAO.getAllActors(httpclient);
        return actores;
    }
}
