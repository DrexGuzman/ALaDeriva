/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista;

import Controlador.ControladorPrincial;

/**
 *
 * @author 50684
 */
public class PanelAyuda extends javax.swing.JPanel {

    /**
     * Creates new form PanelAyuda
     */
    public PanelAyuda() {
        initComponents();
    }
    
    public void escuchar(ControladorPrincial controlador) {
        btnRegresar.addActionListener(controlador);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnRegresar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/regreso.png"))); // NOI18N
        btnRegresar.setActionCommand("regresar");
        btnRegresar.setContentAreaFilled(false);
        add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1350, 690, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UsoAyuda1.1.png"))); // NOI18N
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}