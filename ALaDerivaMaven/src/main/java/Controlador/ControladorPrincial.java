/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Vista.GUI_Menu;
import Vista.PanelAyuda;
import Vista.PanelControles;
import Vista.PanelMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author 50684
 */
public class ControladorPrincial implements ActionListener{
    
    private GUI_Menu guiMenu;
    private ControladorPartida controladorPartida;
    private PanelControles panelControles;
    private PanelAyuda panelAyuda;
    private PanelMenu panelMenu;

    public ControladorPrincial() {
        guiMenu=new GUI_Menu();
        panelMenu=new PanelMenu();
        panelAyuda = new PanelAyuda();
        panelControles = new PanelControles();
        guiMenu.setVisible(true);
        guiMenu.add(panelMenu);
        guiMenu.setExtendedState(guiMenu.MAXIMIZED_BOTH);
        panelMenu.escuchar(this);
        panelControles.escuchar(this);
        panelAyuda.escuchar(this);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        switch (e.getActionCommand()) {
            case "Jugar":

                controladorPartida = new ControladorPartida();

                break;

            case "Controles":
                guiMenu.remove(panelMenu);
                guiMenu.add(panelControles);
                guiMenu.revalidate();
                guiMenu.repaint();
                
                break;

            case "Ayuda":

                guiMenu.remove(panelMenu);
                guiMenu.add(panelAyuda);
                guiMenu.revalidate();
                guiMenu.repaint();
                
                break;

            case "Salir":

                System.exit(0);
                
                break;

            case "regresar":

                guiMenu.remove(panelAyuda);
                guiMenu.remove(panelControles);
                guiMenu.add(panelMenu);
                guiMenu.revalidate();
                guiMenu.repaint();
                
                break;
                
                

            case "Estadisticas":

                String cadena=new String();
                JSONParser parser=new JSONParser();
                cadena="Registro de partidas\n";

        try {
            FileReader leer=new FileReader(new File("Registro de Partidas"));
           Object obj=parser.parse(leer);
           JSONObject objBase=(JSONObject)obj;
           
           JSONArray arregloDatos=(JSONArray)objBase.get("Estadisticas");
           
           for(Object objDatos:arregloDatos){
           
               JSONObject datosJSON=(JSONObject) objDatos;
               
               cadena+=datosJSON.get("Jugador: ").toString()+" en esta partida "+datosJSON.get("En esta partida ").toString()+" Superaste el nivel "+datosJSON.get("Ultimo nivel superado: ").toString()+"\n";

           
           }
           
            JOptionPane.showMessageDialog(null, cadena);
            cadena=null;
           
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Actualmente no hay partidas registradas.");
            System.out.println("Error al leer el arhivo");
        } catch (ParseException ex) {
            System.out.println("Error en el momento de parsear archivo");
        }
                
                break;

            case "Reiniciar":
                File jSon=new File("Registro de Partidas");
        if(jSon.exists()){
        
           jSon.delete();
           JOptionPane.showMessageDialog(null, "El registro solo puede ser borrado al reiniciar el juego. Si ya lo hiciste:\n"+"Acabas de borrar las partidas registradas");
        
        }else{
        
            JOptionPane.showMessageDialog(null, "Actualmente no hay Registros disponibles para borrar.");
        
        }
                break;
        }
        
    }
    
    
}
