/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Controlador.ControladorPrincial;

/**
 *
 * @author 50684
 */
public class GUI_Menu extends javax.swing.JFrame {

    /**
     * Creates new form GUI_Menu
     */
    public GUI_Menu() {
        initComponents();
    }
    
    public void escuchar(ControladorPrincial controlador) {
        //panelMenu1.escuchar(controlador);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMenu2 = new Vista.PanelMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Vista.PanelMenu panelMenu2;
    // End of variables declaration//GEN-END:variables
}
