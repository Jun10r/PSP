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
    public ArrayList<Pelicula> getAllPeliculas(CloseableHttpClient httpclient){
        ArrayList<Pelicula> peliculas = null;
        PeliculaDAO peliculasDAO = new PeliculaDAO();
        peliculas = peliculasDAO.getAllPeliculas(httpclient);
        return peliculas;
    }
}
