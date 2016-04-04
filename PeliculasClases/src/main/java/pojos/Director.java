package pojos;

/**
 *
 * @author Junior
 */
public class Director {
    private String nombre;
    private int edad;

    public Director(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public Director() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Director{" + "nombre=" + nombre + ", edad=" + edad + '}';
    }
    
}