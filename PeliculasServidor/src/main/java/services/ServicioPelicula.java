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

    private final PeliculaDAO pelisDAO;

    public ServicioPelicula() {
        this.pelisDAO = new PeliculaDAO();
    }

    public ArrayList<Pelicula> getAllPeliculas() {
        ArrayList<Pelicula> peliculas = null;
        peliculas = pelisDAO.getAllPeliculas();
        return peliculas;
    }

    public void update(Pelicula p) {
        pelisDAO.updatePelicula(p);
    }

    public int inserted(Pelicula p) {
        return pelisDAO.insertPelicula(p);
    }
    
    public void delete(int l){
        pelisDAO.deletePelicula(l);
    }
}
