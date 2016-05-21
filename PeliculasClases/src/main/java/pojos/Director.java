package pojos;

/**
 *
 * @author Junior
 */
public class Director {
    private int dni;
    private String nombre;

    public Director(int dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
    }

    public Director() {
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Director{" + "DNI=" + dni + ", Nombre=" + nombre + '}';
    }
    
}
