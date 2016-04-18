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
    private String genero;
    private int calificacion;

    public Pelicula() {
    }

    
    public Pelicula( String nombre,String genero,int calificacion) {
        this.calificacion = calificacion;
        this.nombre = nombre;
        this.genero = genero;
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Pelicula{" + "nombre=" + nombre + ", genero=" + genero + ", calificacion=" + calificacion + '}';
    }

    
   
    
    
    
}
