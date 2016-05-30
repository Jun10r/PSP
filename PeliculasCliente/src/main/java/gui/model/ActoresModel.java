/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.model;

import static constantes.Constantes.COUNT_COLUMN_ACTORES;
import controller.ControlActor;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import org.apache.http.impl.client.CloseableHttpClient;
import pojos.Actor;

/**
 *
 * @author Junior
 */
public class ActoresModel extends AbstractTableModel {

    private ArrayList<Actor> actores;
    private ControlActor ctrl;
    private boolean insertando = false;

    public ActoresModel(CloseableHttpClient httpclient) {
        this.ctrl = new ControlActor();
        this.actores = ctrl.getAllActors(httpclient);
    }

    public ActoresModel(CloseableHttpClient httpclient, int codRef) {
        this.ctrl = new ControlActor();
        this.actores = ctrl.getActorsByMovie(httpclient, codRef);
    }

    public boolean isInsertando() {
        return insertando;
    }

    public void insertRow() {
        actores.add(new Actor(-1, ""));
        fireTableRowsInserted(actores.size() - 1, actores.size() - 1);
        insertando = true;
    }
    
    public void insertActor(Actor a){
        actores.add(a);
        fireTableRowsInserted(actores.size() - 1, actores.size() - 1);
        insertando = true;
    }

    public void setActoresLasId(Actor a) {
        actores.remove(actores.size() - 1);
        actores.add(a);
    }

    public void insert_true(int dni, String nombre) {
        if (insertando) {
            if (dni != 0 && !nombre.isEmpty()) {
                insertando = false;
            }
        }
        fireTableRowsInserted(actores.size() - 1, actores.size() - 1);
    }
    
    public Actor getActoresAtRow(int row){
        return actores.get(row);
    }
    
    public void deleteActor(int a){
        actores.remove(a);
        fireTableDataChanged();
    }
    public void deleteRow(int a){
        actores.remove(a);
    }

    public ArrayList<Actor> getActores() {
        return actores;
    }

    public void setActores(ArrayList<Actor> actores) {
        this.actores = actores;
    }

    
    @Override
    public int getRowCount() {
        return actores.size();
    }

    @Override
    public int getColumnCount() {
        return COUNT_COLUMN_ACTORES;
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
        Actor actor = actores.get(rowIndex);
        Object o = new Object();
        switch (columnIndex) {
            case 0:
                o = actor.getDni();
                break;
            case 1:
                o = actor.getNombre();
                break;
        }
        return o;
    }

    @Override
    public void setValueAt(Object o, int rowIndex, int columnIndex) {
        Actor a = actores.get(rowIndex);
        switch (columnIndex) {
            case 0:
                int i = Integer.parseInt((String) o);
                a.setDni(i);
                break;
            case 1:
                a.setNombre((String) o);
                break;

        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
    
    
}
