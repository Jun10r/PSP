/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daw.PeliculaDAO;
import java.util.ArrayList;
import org.apache.http.impl.client.CloseableHttpClient;
import pojos.Pelicula;

/**
 *
 * @author Junior
 */
public class ControlPelicula {

    private PeliculaDAO peliculasDAO;

    public ControlPelicula() {
        this.peliculasDAO = new PeliculaDAO();
    }

    public ArrayList<Pelicula> getAllPeliculas(CloseableHttpClient httpclient) {
        ArrayList<Pelicula> peliculas = null;
        peliculas = peliculasDAO.getAllPeliculas(httpclient);
        return peliculas;
    }

    public void update(Pelicula p, CloseableHttpClient httpclient) {
        peliculasDAO.updatePeliculas(p, httpclient);
    }

    public int inserted(Pelicula p, CloseableHttpClient httpclient) {
        return peliculasDAO.insertPelicula(p, httpclient);
    }
    
    public void delete(int p, CloseableHttpClient httpclient){
        peliculasDAO.deletePeliculas(p, httpclient);
    }
}
