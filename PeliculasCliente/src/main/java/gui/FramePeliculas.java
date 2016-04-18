/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import constantes.Constantes;
import controller.ControlDirector;
import controller.ControlPelicula;
import java.util.Date;
import javax.swing.JPanel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import pojos.Director;

/**
 *
 * @author Junior
 */
public class FramePeliculas extends javax.swing.JFrame {

    ControlDirector ctrlDirector;
    ControlPelicula ctrlPeliculas;
    CloseableHttpClient httpclient;
    public FramePeliculas() {
        initComponents();
        panelVisible(jPanelPeliculas);
        httpclient = HttpClients.createDefault();
        ctrlPeliculas = new ControlPelicula();
        ctrlDirector = new ControlDirector();
    }
    
    public void datosPeliculas(){
        PeliculasModel model = new PeliculasModel(httpclient);
        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                    switch (e.getType()) {
                    case TableModelEvent.INSERT:
                        System.out.println("I" + e.getColumn() + " " + e.getFirstRow());
                        break;
                    case TableModelEvent.UPDATE:
                        /*
                        if (((ClientesModel) jTable1.getModel()).isInsertando()) {
                            ((ClientesModel) jTable1.getModel()).checkInsert(
                                    (String) jTable1.getModel().getValueAt(e.getFirstRow(), Constantes.COLUMN_NOMBRE_CLIENTE),
                                    (String) jTable1.getModel().getValueAt(e.getFirstRow(), Constantes.COLUMN_APELLIDO_CLIENTE),
                                    (Integer) jTable1.getModel().getValueAt(e.getFirstRow(), Constantes.COLUMN_EDAD_CLIENTE),
                                    (Date) jTable1.getModel().getValueAt(e.getFirstRow(), Constantes.COLUMN_DATE_CLIENTE));

                            if (!((ClientesModel) jTable1.getModel()).isInsertando()) {
                                Cliente c = ((ClientesModel) jTable1.getModel()).getClienteAtRow(e.getFirstRow());

                                boolean inserted = controlClient.insertClient(c, httpclient);
                                ((ClientesModel) jTable1.getModel()).setClienteLastId(c);
                                if (inserted) {
                                    System.out.println("INSERTADOO");
                                }
                            }
                        } else {
                            Cliente c = ((ClientesModel) jTable1.getModel()).getClienteAtRow(e.getFirstRow());
                            controlClient.updateCliente(c, httpclient);
                        }
                        /*
                        System.out.println("U" + e.getColumn() + " " + e.getFirstRow());
                        System.out.println("valor " + jTable1.getModel().getValueAt(e.getFirstRow(), e.getColumn()));
                        
                            Cliente c = ((ClientesModel) jTable1.getModel()).getClienteAtRow(e.getFirstRow());
                            controlClient.updateCliente(c, httpclient);
                        

                        //Cambiar tipo de datos
                         */
                        break;
                    case TableModelEvent.DELETE:

                        System.out.println("D" + e.getColumn() + " " + e.getFirstRow());
                        break;
                }
            }
        });
        jTable1.setModel(model);
    }
    
    public void panelVisible(JPanel panel) {
        jPanelAltas.setVisible(false);
        jPanelPeliculas.setVisible(false);
        panel.setVisible(true);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelAltas = new javax.swing.JPanel();
        jTextName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextApellido = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanelPeliculas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextNameActionPerformed(evt);
            }
        });

        jLabel1.setText("Nombre");

        jLabel2.setText("Apellido");

        jTextApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextApellidoActionPerformed(evt);
            }
        });

        jButton1.setText("Alta Director");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Registrar Director/Actor");

        jButton2.setText("Alta Actor");

        javax.swing.GroupLayout jPanelAltasLayout = new javax.swing.GroupLayout(jPanelAltas);
        jPanelAltas.setLayout(jPanelAltasLayout);
        jPanelAltasLayout.setHorizontalGroup(
            jPanelAltasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAltasLayout.createSequentialGroup()
                .addGroup(jPanelAltasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAltasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jTextApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelAltasLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel3))
                    .addGroup(jPanelAltasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jTextName, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelAltasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelAltasLayout.setVerticalGroup(
            jPanelAltasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAltasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelAltasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanelAltasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelAltasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanelPeliculasLayout = new javax.swing.GroupLayout(jPanelPeliculas);
        jPanelPeliculas.setLayout(jPanelPeliculasLayout);
        jPanelPeliculasLayout.setHorizontalGroup(
            jPanelPeliculasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 472, Short.MAX_VALUE)
            .addGroup(jPanelPeliculasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelPeliculasLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanelPeliculasLayout.setVerticalGroup(
            jPanelPeliculasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 393, Short.MAX_VALUE)
            .addGroup(jPanelPeliculasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelPeliculasLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel4.setText("Filmaffinity");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanelAltas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jPanelPeliculas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(196, 196, 196)
                        .addComponent(jLabel4)))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelPeliculas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelAltas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextNameActionPerformed

    private void jTextApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextApellidoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String nombre = jTextName.getText();
        String apellido = jTextApellido.getText();
        Director d = new Director(nombre, apellido);
        
        if (ctrlDirector.insert(d)) {
            System.out.println("Insertado Correctamente");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FramePeliculas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FramePeliculas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FramePeliculas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FramePeliculas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FramePeliculas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanelAltas;
    private javax.swing.JPanel jPanelPeliculas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextApellido;
    private javax.swing.JTextField jTextName;
    // End of variables declaration//GEN-END:variables
}
