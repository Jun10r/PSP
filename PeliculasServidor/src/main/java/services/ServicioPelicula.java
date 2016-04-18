/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import daos.PeliculaDAO;
import java.util.ArrayList;
import pojos.Pelicula;

/**
 *
 * @author Junior
 */
public class ServicioPelicula {
    
    public ArrayList<Pelicula> getAllPeliculas(){
        ArrayList<Pelicula> peliculas = null;
        PeliculaDAO pelisDAO = new PeliculaDAO();
        peliculas = pelisDAO.getAllPeliculas();
        return peliculas;
    }
}
