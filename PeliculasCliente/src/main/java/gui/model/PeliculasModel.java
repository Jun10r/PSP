/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.model;

import static constantes.Constantes.COUNT_COLUMN_PELICULAS;
import controller.ControlPelicula;
import java.awt.Button;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;
import org.apache.http.impl.client.CloseableHttpClient;
import pojos.Pelicula;

/**
 *
 * @author Junior
 */
public class PeliculasModel extends AbstractTableModel {

    private ArrayList<Pelicula> peliculas;
    private boolean insertando = false;
    private final ControlPelicula cp;

    public PeliculasModel(CloseableHttpClient httpclient) {
        super();
        cp = new ControlPelicula();
        peliculas = cp.getAllPeliculas(httpclient);
    }

    public boolean isInsertando() {
        return insertando;
    }

    public void insertRow() {
       
        peliculas.add(new Pelicula(-1, "", 1));
        fireTableRowsInserted(peliculas.size() - 1, peliculas.size() - 1);
        insertando = true;
    }

    public void insert_true(String titulo, int calificacion ) {
        if (insertando) {
            if (!titulo.isEmpty() && calificacion != 0) {
                insertando = false;
            }
        }
        fireTableRowsInserted(peliculas.size() - 1, peliculas.size() - 1);
    }

    public void setPeliculaLastId(Pelicula l) {
        peliculas.remove(peliculas.size() - 1);
        peliculas.add(l);
    }

    public Pelicula getPeliculasAtRow(int row) {
        return peliculas.get(row);
    }

    //ELIMINAR PELICULA
    public void deletePelicula(int p) {
        peliculas.remove(p);
        fireTableDataChanged();
    }

    public void deleteRow(int p) {
        peliculas.remove(p);
    }

    public ArrayList<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(ArrayList<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    @Override
    public int getRowCount() {
        return peliculas.size();
    }

    @Override
    public int getColumnCount() {
        return COUNT_COLUMN_PELICULAS;
    }

    @Override
    public String getColumnName(int i) {
        String columnName = "";
        switch (i) {
            case 0:
                columnName = "N_REFERENCIA";
                break;
            case 1:
                columnName = "TITULO";
                break;
            case 2:
                columnName = "CALIFICACION";
                break;
            case 3:
                columnName = "DIRECTOR";
                break;
            case 4:
                columnName = "ACTORES";
                break;
            case 5:
                columnName = "GENERO";
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
                o = pelis.getN_referencia();
                break;
            case 1:
                o = pelis.getTitulo();
                break;
            case 2:
                o = pelis.getCalificacion();
                break;
            case 3:
                o = "Directores";
                break;
            case 4:
                o = "Actores";
                break;
            case 5:
                o = "Generos";
                break;
        }
        return o;
    }

    @Override
    public void setValueAt(Object o, int rowIndex, int columnIndex) {

        Pelicula p = peliculas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                p.setN_referencia((Integer) o);
                break;
            case 1:
                p.setTitulo((String) o);
                break;
            case 2:
                p.setCalificacion(Integer.parseInt((String) o));
                break;
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        // Sobrescribimos este método para evitar que la columna que contiene los botones sea editada.
        return !(this.getColumnClass(column).equals(JButton.class));
    }
}
