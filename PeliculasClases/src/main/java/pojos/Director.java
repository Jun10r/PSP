package pojos;

/**
 *
 * @author Junior
 */
public class Director {
    private String nombre;
    private String apellido;

    public Director(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Director() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String edad) {
        this.apellido = edad;
    }

    @Override
    public String toString() {
        return "Director{" + "nombre=" + nombre + ", apellido=" + apellido + '}';
    }
    
}
