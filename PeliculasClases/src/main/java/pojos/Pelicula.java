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
    private int cod_director;

    public Pelicula() {
    }

    public Pelicula(int n_referencia, String titulo, int calificacion, int cod_director) {
        this.n_referencia = n_referencia;
        this.titulo = titulo;
        this.calificacion = calificacion;
        this.cod_director = cod_director;
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

    public int getCod_director() {
        return cod_director;
    }

    public void setCod_director(int cod_director) {
        this.cod_director = cod_director;
    }

    @Override
    public String toString() {
        return "Pelicula{" + "n_referencia=" + n_referencia + ", titulo=" + titulo + ", calificacion=" + calificacion + ", cod_director=" + cod_director + '}';
    }

 
    
   
    
    
    
}
