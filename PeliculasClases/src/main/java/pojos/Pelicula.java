/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

/**
 *
 * @author Junior
 */
public class Pelicula {
    private String nombre;
    private Actor actor;
    private Director director;
    private int calificacion;

    public Pelicula() {
    }

    
    public Pelicula(int calificacion, String nombre, Actor actor, Director director) {
        this.calificacion = calificacion;
        this.nombre = nombre;
        this.actor = actor;
        this.director = director;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "Pelicula{" + "nombre=" + nombre + ", actor=" + actor + ", director=" + director + ", calificacion=" + calificacion + '}';
    }

   
    
    
    
}
