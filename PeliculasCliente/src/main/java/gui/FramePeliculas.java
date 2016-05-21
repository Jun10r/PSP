/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.model.PeliculasModel;
import constantes.Constantes;
import static constantes.Constantes.COLUMN_GENERO_PELICULA;
import controller.ButtonColumn;

import controller.ControlDirector;
import controller.ControlPelicula;
import controller.ControlUsuario;
import controller.jDialogActor;
import controller.jDialogGenero;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumnModel;
import jdk.nashorn.internal.objects.NativeString;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import pojos.Director;
import pojos.Pelicula;
import pojos.Usuario;

/**
 *
 * @author Junior
 */
public class FramePeliculas extends javax.swing.JFrame {

    ControlDirector ctrlDirector;
    ControlPelicula ctrlPeliculas;
    ControlUsuario controlUser;
    private jDialogActor dialogActores;
    private jDialogGenero dialogGenero;
    CloseableHttpClient httpclient;
    private int idReferencia;

    public FramePeliculas() {
        initComponents();
        ctrlPeliculas = new ControlPelicula();
        ctrlDirector = new ControlDirector();
        controlUser = new ControlUsuario();
        panelVisible(jPanelLogin);
        //datosPeliculas();
    }

    public void datosPeliculas() {
        panelVisible(jPanelPeliculas);
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
        jTablePeliculas.setModel(model);

        ButtonColumn botonActores = new ButtonColumn(jTablePeliculas, verActores(), Constantes.COLUMN_ACTORES_PELICULA);
        ButtonColumn botonGenero = new ButtonColumn(jTablePeliculas, verGeneroByMovie(), COLUMN_GENERO_PELICULA);
        // botonActores.setMnemonic(KeyEvent.VK_D);

    }

    public Action verActores() {
        Action action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                int row = jTablePeliculas.getSelectedRow();
                Pelicula pelicula = ((PeliculasModel) jTablePeliculas.getModel()).getPeliculasAtRow(row);
                System.out.println("PELICULA: " + pelicula.getTitulo());
                dialogActores = new jDialogActor(null, true, httpclient, pelicula.getN_referencia());
                dialogActores.setVisible(true);
            }
        };
        return action;
    }

    public Action verGeneroByMovie() {
        Action action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                int row = jTablePeliculas.getSelectedRow();
                Pelicula pelicula = ((PeliculasModel) jTablePeliculas.getModel()).getPeliculasAtRow(row);
                System.out.println("PELICULA: " + pelicula.getTitulo());
                dialogGenero = new jDialogGenero(null, true, httpclient, pelicula.getN_referencia());
                dialogGenero.setVisible(true);
            }
        };
        return action;
    }

    public void panelVisible(JPanel panel) {
        jPanelAltas.setVisible(false);
        jPanelPeliculas.setVisible(false);
        jPanelLogin.setVisible(false);
        panel.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jPanelPeliculas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePeliculas = new javax.swing.JTable();
        jPanelAltas = new javax.swing.JPanel();
        jTextName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextApellido = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanelLogin = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldUsuario = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButtonLogin = new javax.swing.JButton();
        jButtonRegistro = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuPelicula = new javax.swing.JMenu();
        jMenuInsert = new javax.swing.JMenuItem();
        jMenuUpdate = new javax.swing.JMenuItem();
        jMenuDelete = new javax.swing.JMenuItem();
        jMenuExtras = new javax.swing.JMenu();
        jMenuAlta = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(500, 500));

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel4.setText("Filmaffinity");

        jPanelPeliculas.setName("jPanelPeliculas"); // NOI18N
        jPanelPeliculas.setPreferredSize(new java.awt.Dimension(400, 400));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(350, 200));

        jTablePeliculas.setModel(new javax.swing.table.DefaultTableModel(
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
        jTablePeliculas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(jTablePeliculas);

        javax.swing.GroupLayout jPanelPeliculasLayout = new javax.swing.GroupLayout(jPanelPeliculas);
        jPanelPeliculas.setLayout(jPanelPeliculasLayout);
        jPanelPeliculasLayout.setHorizontalGroup(
            jPanelPeliculasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPeliculasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelPeliculasLayout.setVerticalGroup(
            jPanelPeliculasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPeliculasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanelAltas.setName("jPanelAltas"); // NOI18N

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

        jPanelLogin.setPreferredSize(new java.awt.Dimension(100, 100));

        jLabel5.setText("Bienvenidos a Filmaffinity");

        jLabel6.setText("Usuario: ");

        jTextFieldUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldUsuarioActionPerformed(evt);
            }
        });

        jLabel7.setText("Password: ");

        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });

        jButtonLogin.setText("Iniciar Sesion");
        jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoginActionPerformed(evt);
            }
        });

        jButtonRegistro.setText("Registrarse");
        jButtonRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelLoginLayout = new javax.swing.GroupLayout(jPanelLogin);
        jPanelLogin.setLayout(jPanelLoginLayout);
        jPanelLoginLayout.setHorizontalGroup(
            jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLoginLayout.createSequentialGroup()
                .addGroup(jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLoginLayout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(jLabel5))
                    .addGroup(jPanelLoginLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLoginLayout.createSequentialGroup()
                                .addComponent(jButtonLogin)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonRegistro))
                            .addGroup(jPanelLoginLayout.createSequentialGroup()
                                .addGroup(jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(0, 71, Short.MAX_VALUE))
        );
        jPanelLoginLayout.setVerticalGroup(
            jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLoginLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel5)
                .addGap(27, 27, 27)
                .addGroup(jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonLogin)
                    .addComponent(jButtonRegistro))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        jMenuPelicula.setText("Acciones Pelicula");

        jMenuInsert.setText("Insertar Pelicula");
        jMenuPelicula.add(jMenuInsert);

        jMenuUpdate.setText("Actualizar Pelicula");
        jMenuPelicula.add(jMenuUpdate);

        jMenuDelete.setText("Eliminar Pelicula");
        jMenuPelicula.add(jMenuDelete);

        jMenuBar1.add(jMenuPelicula);

        jMenuExtras.setText("Extras");

        jMenuAlta.setText("Alta Director/Actor");
        jMenuExtras.add(jMenuAlta);

        jMenuBar1.add(jMenuExtras);

        jMenu1.setText("Acciones Actores");

        jMenuItem1.setText("Ver Actores");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

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
                        .addGap(113, 113, 113)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelPeliculas, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelPeliculas, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jPanelAltas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        int dni = Integer.parseInt(jTextName.getText());
        String nombre = jTextApellido.getText();
        Director d = new Director(dni, nombre);

        if (ctrlDirector.insert(d)) {
            System.out.println("Insertado Correctamente");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        dialogActores = new jDialogActor(null, true, httpclient);
        dialogActores.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jTextFieldUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldUsuarioActionPerformed

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1ActionPerformed

    private void jButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoginActionPerformed
        httpclient = HttpClients.createDefault();
        String login = jTextFieldUsuario.getText();
        String password = new String(jPasswordField1.getPassword());

        if (!login.isEmpty() && !password.isEmpty()) {
            Usuario user = new Usuario(login.trim(), password.trim());

            if (controlUser.login(user)) {
                httpclient = HttpClients.createDefault();
                panelVisible(jPanelPeliculas);
                datosPeliculas();
            } else {
                JOptionPane.showConfirmDialog(FramePeliculas.this, "Login y/o contraseña Incorrecta", "", JOptionPane.PLAIN_MESSAGE);
            }
        } else {
            JOptionPane.showConfirmDialog(FramePeliculas.this, "Rellenar todos los datos", "", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_jButtonLoginActionPerformed

    private void jButtonRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistroActionPerformed

        String login = jTextFieldUsuario.getText();
        String password = new String(jPasswordField1.getPassword());
        if (!login.isEmpty() && !password.isEmpty()) {
            int confirm = JOptionPane.showConfirmDialog(FramePeliculas.this, "Se va a registrar un usuario.¿Está Seguro?", "", JOptionPane.OK_CANCEL_OPTION);
            if (confirm == 0) {
                Usuario usuario = new Usuario(login, password);
                if (controlUser.insertUser(usuario)) {
                    JOptionPane.showConfirmDialog(FramePeliculas.this, "El usuario ha sido Registrado Correctamente", "", JOptionPane.PLAIN_MESSAGE);
                    clearRegister();
                } else {
                    JOptionPane.showConfirmDialog(FramePeliculas.this, "Ha habido un error", "", JOptionPane.PLAIN_MESSAGE);
                }
            }
        } else {
            JOptionPane.showConfirmDialog(FramePeliculas.this, "Rellenar todos los datos", "", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_jButtonRegistroActionPerformed

    public void clearRegister() {
        jTextFieldUsuario.setText(null);
        jPasswordField1.setText(null);
    }

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
    private javax.swing.JButton jButtonLogin;
    private javax.swing.JButton jButtonRegistro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuAlta;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuDelete;
    private javax.swing.JMenu jMenuExtras;
    private javax.swing.JMenuItem jMenuInsert;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenu jMenuPelicula;
    private javax.swing.JMenuItem jMenuUpdate;
    private javax.swing.JPanel jPanelAltas;
    private javax.swing.JPanel jPanelLogin;
    private javax.swing.JPanel jPanelPeliculas;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablePeliculas;
    private javax.swing.JTextField jTextApellido;
    private javax.swing.JTextField jTextFieldUsuario;
    private javax.swing.JTextField jTextName;
    // End of variables declaration//GEN-END:variables
}
