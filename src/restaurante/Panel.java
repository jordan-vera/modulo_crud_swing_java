/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante;

import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jordan
 */
public class Panel extends javax.swing.JFrame {

    /**
     * Creates new form Panel
     */
    public Panel() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        meserosBtn = new javax.swing.JButton();
        ClienteBtn = new javax.swing.JButton();
        AlimentoBtn = new javax.swing.JButton();
        VentasBtn = new javax.swing.JButton();
        escritorioM = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        meserosBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        meserosBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ClienteBoton.png"))); // NOI18N
        meserosBtn.setText("Meseros");
        meserosBtn.setFocusable(false);
        meserosBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meserosBtnActionPerformed(evt);
            }
        });

        ClienteBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ClienteBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/empleadosBoton.png"))); // NOI18N
        ClienteBtn.setText("Clientes");
        ClienteBtn.setFocusable(false);
        ClienteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClienteBtnActionPerformed(evt);
            }
        });

        AlimentoBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        AlimentoBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/productoBoton.png"))); // NOI18N
        AlimentoBtn.setText("Alimentos");
        AlimentoBtn.setFocusable(false);
        AlimentoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlimentoBtnActionPerformed(evt);
            }
        });

        VentasBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        VentasBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ventasBoton.png"))); // NOI18N
        VentasBtn.setText("Ventas");
        VentasBtn.setFocusable(false);
        VentasBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VentasBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ClienteBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AlimentoBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(VentasBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(meserosBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(meserosBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(ClienteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(AlimentoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(VentasBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(384, Short.MAX_VALUE))
        );

        escritorioM.setBackground(new java.awt.Color(255, 255, 255));
        escritorioM.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/panel.jpg"))); // NOI18N

        escritorioM.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout escritorioMLayout = new javax.swing.GroupLayout(escritorioM);
        escritorioM.setLayout(escritorioMLayout);
        escritorioMLayout.setHorizontalGroup(
            escritorioMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 820, Short.MAX_VALUE)
        );
        escritorioMLayout.setVerticalGroup(
            escritorioMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(escritorioMLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(escritorioM)
                .addGap(2, 2, 2))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(escritorioM)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ClienteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClienteBtnActionPerformed
        Clientes cliente = new Clientes();
        this.escritorioM.removeAll();
        this.escritorioM.add(cliente);
        try {
            cliente.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
        }
        cliente.show();
    }//GEN-LAST:event_ClienteBtnActionPerformed

    private void meserosBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meserosBtnActionPerformed
        Meseros mesero = new Meseros();
        this.escritorioM.removeAll();
        this.escritorioM.add(mesero);
        try {
            mesero.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
        }
        mesero.show();
    }//GEN-LAST:event_meserosBtnActionPerformed

    private void AlimentoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlimentoBtnActionPerformed
        Alimentos alimento = new Alimentos();
        this.escritorioM.removeAll();
        this.escritorioM.add(alimento);
        try {
            alimento.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
        }
        alimento.show();
    }//GEN-LAST:event_AlimentoBtnActionPerformed

    private void VentasBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VentasBtnActionPerformed
        Ventas venta = new Ventas();
        this.escritorioM.removeAll();
        this.escritorioM.add(venta);
        try {
            venta.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
        }
        venta.show();
    }//GEN-LAST:event_VentasBtnActionPerformed

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
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Panel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AlimentoBtn;
    private javax.swing.JButton ClienteBtn;
    private javax.swing.JButton VentasBtn;
    private javax.swing.JDesktopPane escritorioM;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton meserosBtn;
    // End of variables declaration//GEN-END:variables
}
