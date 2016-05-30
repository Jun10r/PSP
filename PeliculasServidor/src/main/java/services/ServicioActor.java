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
    private final ActorDAO actoresDAO;

    public ServicioActor() {
        actoresDAO = new ActorDAO();
    }
    
    public Actor getActor(String name){
        return actoresDAO.selectActor(name);
    }
    public ArrayList<Actor> getAllActors(){
        ArrayList<Actor> actores = null;
        actores = actoresDAO.getAllActors();
        return actores;
    }
    public ArrayList<Actor> getAllActorsByMovie(int codRef){
        ArrayList<Actor> actores = null;
        actores = actoresDAO.getAllActorsByMovie(codRef);
        return actores;
    }
    
     public void update(Actor a) {
      //  actoresDAO.updateActor(a);
    }

    public boolean inserted(Actor a) {
        return actoresDAO.insertActor(a);
    }
    public boolean insertActuan(Actor a, int idPelicula){
        return actoresDAO.insertActuan(a, idPelicula);
    }
    
    public void delete(int l){
        //actoresDAO.deleteActor(l);
    }
}
