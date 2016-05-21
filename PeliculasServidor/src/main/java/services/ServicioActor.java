/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import daos.ActorDAO;
import java.util.ArrayList;
import pojos.Actor;

/**
 *
 * @author Junior
 */
public class ServicioActor {
    
    public ArrayList<Actor> getAllActors(){
        ArrayList<Actor> actores = null;
        ActorDAO actoresDAO = new ActorDAO();
        actores = actoresDAO.getAllActors();
        return actores;
    }
    public ArrayList<Actor> getAllActorsByMovie(int codRef){
        ArrayList<Actor> actores = null;
        ActorDAO actoresDAO = new ActorDAO();
        actores = actoresDAO.getAllActorsByMovie(codRef);
        return actores;
    }
}
