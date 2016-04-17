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
public class Actor {
    private String nombre;
    private String apellido;

    public Actor() {
    }

    public Actor(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return apellido;
    }

    public void setEdad(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "Actor{" + "nombre=" + nombre + ", apellido=" + apellido + '}';
    }

}
