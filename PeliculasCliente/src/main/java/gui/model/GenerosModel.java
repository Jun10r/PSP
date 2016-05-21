/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.model;

import static constantes.Constantes.COUNT_COLUMN_GENERO;
import controller.ControlGenero;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import org.apache.http.impl.client.CloseableHttpClient;
import pojos.Genero;

/**
 *
 * @author Junior
 */
public class GenerosModel extends AbstractTableModel {

    private ArrayList<Genero> generos;
    private ControlGenero ctrl;

    //HAcer Constructor para obtener todos los generos
    public GenerosModel(CloseableHttpClient httpclient, int codRef) {
        this.ctrl = new ControlGenero();
        this.generos = ctrl.getGeneroByMovie(httpclient, codRef);
    }

    @Override
    public int getRowCount() {
        return generos.size();
    }

    @Override
    public int getColumnCount() {
        return COUNT_COLUMN_GENERO;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Genero g = generos.get(rowIndex);
        Object o = new Object();
        switch (columnIndex) {
            case 0:
                o = g.getId();
                break;
            case 1:
                o = g.getNombre();
                break;
        }
        return o;

    }

    @Override
    public String getColumnName(int column) {
        String columnName = "";
        switch (column) {
            case 0:
                columnName = "ID";
                break;
            case 1:
                columnName = "NOMBRE";
                break;
        }
        return columnName;
    }

    @Override
    public void setValueAt(Object o, int rowIndex, int columnIndex) {
        Genero g = generos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                g.setId(Integer.parseInt((String) o));
                break;
            case 1:
                g.setNombre((String) o);
                break;

        }
    }
}
