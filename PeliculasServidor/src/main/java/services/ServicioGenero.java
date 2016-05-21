/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import daos.ActorDAO;
import daos.GeneroDAO;
import java.util.ArrayList;
import pojos.Genero;

/**
 *
 * @author Junior
 */
public class ServicioGenero {

    public ArrayList<Genero> getAllGeneroByMovie(int codRef) {
        ArrayList<Genero> generos = null;
        GeneroDAO generosDAO = new GeneroDAO();
        generos = generosDAO.getAllGeneroByMovie(codRef);
        return generos;
    }
}
