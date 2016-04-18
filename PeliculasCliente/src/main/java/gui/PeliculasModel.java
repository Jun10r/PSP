/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static constantes.Constantes.COUNT_COLUMN_PELICULAS;
import controller.ControlPelicula;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import org.apache.http.impl.client.CloseableHttpClient;
import pojos.Pelicula;

/**
 *
 * @author Junior
 */
public class PeliculasModel extends AbstractTableModel {

    private ArrayList<Pelicula> peliculas;

    public PeliculasModel(CloseableHttpClient httpclient) {
        super();
        ControlPelicula cp = new ControlPelicula();
        peliculas = cp.getAllPeliculas(httpclient);
    }

    public Pelicula getPeliculasAtRow(int row) {
        return peliculas.get(row);
    }

    @Override
    public int getRowCount() {
        return peliculas.size();
    }

    @Override
    public int getColumnCount() {
        return COUNT_COLUMN_PELICULAS;
    }

    public String getColumnName(int i) {
        String columnName = "";
        switch (i) {
            case 0:
                columnName = "NOMBRE";
                break;
            case 1:
                columnName = "GENERO";
                break;
            case 2:
                columnName = "CALIFICACION";
                break;
        }
        return columnName;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pelicula pelis = peliculas.get(rowIndex);
        Object o = new Object();
        switch (columnIndex) {
            case 0:
                o = pelis.getNombre();
                break;
            case 1:
                o = pelis.getGenero();
                break;
            case 2:
                o = pelis.getCalificacion();
                break;
        }
        return o;
    }

    @Override
    public void setValueAt(Object o, int rowIndex, int columnIndex) {

        Pelicula p = peliculas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                p.setNombre((String) o);
                break;
            case 1:
                p.setGenero((String) o);
                break;
            case 2:
                p.setCalificacion(Integer.parseInt((String) o));
                break;
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

}
