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
    private int n_referencia;
    private String titulo;
    private int calificacion;

    public Pelicula() {
    }

    public Pelicula(int n_referencia, String titulo, int calificacion) {
        this.n_referencia = n_referencia;
        this.titulo = titulo;
        this.calificacion = calificacion;
    }

    public int getN_referencia() {
        return n_referencia;
    }

    public void setN_referencia(int n_referencia) {
        this.n_referencia = n_referencia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    @Override
    public String toString() {
        return "Pelicula{" + "n_referencia=" + n_referencia + ", titulo=" + titulo + ", calificacion=" + calificacion + "}";
    }

 
    
   
    
    
    
}
