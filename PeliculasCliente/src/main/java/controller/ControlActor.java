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

    private ActorDAO actoresDAO;

    public ControlActor() {
        actoresDAO = new ActorDAO();
    }

    public Actor getActor(CloseableHttpClient httpclient, String name) {
        Actor actor = actoresDAO.getActor(httpclient, name);
        return actor;
    }

    public ArrayList<Actor> getActorsByMovie(CloseableHttpClient httpclient, int codRef) {
        ArrayList<Actor> actores = null;
        actores = actoresDAO.getActorsByMovie(httpclient, codRef);
        return actores;
    }

    public ArrayList<Actor> getAllActors(CloseableHttpClient httpclient) {
        ArrayList<Actor> actores = null;
        actores = actoresDAO.getAllActors(httpclient);
        return actores;
    }

    public void update(Actor a, CloseableHttpClient httpclient) {
        actoresDAO.updateActores(a, httpclient);
    }

    public boolean inserted(Actor a, CloseableHttpClient httpclient) {
        return actoresDAO.insertActores(a, httpclient);
    }
    public boolean insertActuan(Actor a, int id,CloseableHttpClient httpclient){
        return actoresDAO.insertActuan(a, id, httpclient);
    }

    public void delete(int a, CloseableHttpClient httpclient) {
        actoresDAO.deleteActores(a, httpclient);
    }
}
