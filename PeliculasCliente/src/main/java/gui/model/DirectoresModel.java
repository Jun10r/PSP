/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.model;

import constantes.Constantes;
import static constantes.Constantes.COUNT_COLUMN_ACTORES;
import static constantes.Constantes.COUNT_COLUMN_DIRECTORES;
import controller.ControlActor;
import controller.ControlDirector;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import org.apache.http.impl.client.CloseableHttpClient;
import pojos.Actor;
import pojos.Director;

/**
 *
 * @author Junior
 */
public class DirectoresModel extends AbstractTableModel {

    private ArrayList<Director> directores;
    private ControlDirector ctrl;
    private boolean insertando = false;

    public DirectoresModel(CloseableHttpClient httpclient) {
        this.ctrl = new ControlDirector();
        this.directores = ctrl.getAllDirectores(httpclient);
    }

    public DirectoresModel(CloseableHttpClient httpclient, int codRef) {
        this.ctrl = new ControlDirector();
        this.directores = ctrl.getDirectorByMovie(httpclient, codRef);
    }

    public boolean isInsertando() {
        return insertando;
    }

    public void insertRow() {
        directores.add(new Director(-1, ""));
        fireTableRowsInserted(directores.size() - 1, directores.size() - 1);
        insertando = true;
    }
    
    public void insertActor(Director a){
        directores.add(a);
        fireTableRowsInserted(directores.size() - 1, directores.size() - 1);
        insertando = true;
    }

    public void setActoresLasId(Director a) {
        directores.remove(directores.size() - 1);
        directores.add(a);
    }

    public void insert_true(int dni, String nombre) {
        if (insertando) {
            if (dni != 0 && !nombre.isEmpty()) {
                insertando = false;
            }
        }
        fireTableRowsInserted(directores.size() - 1, directores.size() - 1);
    }
    
    public Director getDirectoresAtRow(int row){
        return directores.get(row);
    }
    
    public void deleteDirector(int a){
        directores.remove(a);
        fireTableDataChanged();
    }
    public void deleteRow(int a){
        directores.remove(a);
    }

    public ArrayList<Director> getDirectores() {
        return directores;
    }

    public void setDirectores(ArrayList<Director> directores) {
        this.directores = directores;
    }

    
    @Override
    public int getRowCount() {
        return directores.size();
    }

    @Override
    public int getColumnCount() {
        return COUNT_COLUMN_DIRECTORES;
    }

    @Override
    public String getColumnName(int column) {
        String columnName = "";
        switch (column) {
            case 0:
                columnName = "DNI";
                break;
            case 1:
                columnName = "NOMBRE";
                break;
        }
        return columnName;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Director director = directores.get(rowIndex);
        Object o = new Object();
        switch (columnIndex) {
            case 0:
                o = director.getDni();
                break;
            case 1:
                o = director.getNombre();
                break;
        }
        return o;
    }

    @Override
    public void setValueAt(Object o, int rowIndex, int columnIndex) {
        Director d = directores.get(rowIndex);
        switch (columnIndex) {
            case 0:
                int i = Integer.parseInt((String) o);
                d.setDni(i);
                break;
            case 1:
                d.setNombre((String) o);
                break;

        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
    
    
}
