/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;


import Modelo.Carnada;
import Modelo.Pescador;
import Modelo.Pez;
import Vista.GUI_Partida;
import Vista.GUI_Resultado;
import Vista.PanelPartida;
import Vista.PanelResultado;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 *
 * @author 50684
 */
//el original
public class ControladorPartida implements MouseListener, KeyListener, MouseWheelListener, Runnable, ActionListener{
    
    private GUI_Partida guiPartida;
    private PanelPartida panelPartida;
    private GUI_Resultado gui_Resultado;
    private PanelResultado panelResultado;
    private Pescador jugador;
    private Carnada carnada;
    private Pez pezDeseado1,pezDeseado2, PezIndeseado1, PezIndeseado2;
    private Thread movPezDeseado1, movPezIndeseado1,movPezDeseado2, movPezIndeseado2, reloj, ejecutarNivel, carrete;
    private Clip musicaFondo, sonidoCarrete;
    private int resultado=1;
    private JSONObject objetoBase;

    public ControladorPartida() {
        objetoBase=new JSONObject();
        guiPartida = new GUI_Partida();
        panelPartida = new PanelPartida(this, 1);
        gui_Resultado=new GUI_Resultado();
        panelResultado=new PanelResultado();
        panelResultado.escuchar(this);
        carnada = new Carnada(928, 48, 259, new ImageIcon("./src/main/resources/Carnada1.1.png"));
        jugador = new Pescador(JOptionPane.showInputDialog("Digite su nombre"), 13, 1, null, 50, -5, new ImageIcon("./src/main/resources/PescadorDespiertoDerecho1.1.gif"));
        pezDeseado1 = new Pez("Atun", false, 1, 50, 1600, 432, new ImageIcon("./src/main/resources/Atun1.1.gif"));
        PezIndeseado1 = new Pez("Morena", false, 1, 30, 1700, 339, new ImageIcon("./src/main/resources/Morena1.1.gif"));
        pezDeseado2 = new Pez("Atun", false, 2, 50, -500, 600, new ImageIcon("./src/main/resources/Atun1.2.gif"));
        PezIndeseado2 = new Pez("Morena", false, 2, 30, -500, 514, new ImageIcon("./src/main/resources/Morena1.2.gif"));
        movPezDeseado1 = new Thread(pezDeseado1);
        movPezIndeseado1 = new Thread(PezIndeseado1);
        movPezDeseado2 = new Thread(pezDeseado2);
        movPezIndeseado2 = new Thread(PezIndeseado2);
        reloj = new Thread(panelPartida);
        ejecutarNivel = new Thread(this);
        carrete = new Thread(jugador);


        //Ejecucion de hilos
        movPezDeseado1.start();
        movPezIndeseado1.start();
        movPezDeseado2.start();
        movPezIndeseado2.start();
        ejecutarNivel.start();
        reloj.start();

        panelPartida.addMouseListener(this);
        panelPartida.addKeyListener(this);
        panelPartida.addMouseWheelListener(this);
        panelPartida.setFocusable(true);

        panelPartida.setCantCarnada(jugador.getCantCarnada());
        guiPartida.setContentPane(panelPartida);
        guiPartida.setExtendedState(guiPartida.MAXIMIZED_BOTH);
        guiPartida.setVisible(true);

    }
    
    public void dibujar(Graphics g){
    
        jugador.show(g);
        pezDeseado1.show(g);
        PezIndeseado1.show(g);
        pezDeseado2.show(g);
        PezIndeseado2.show(g);
        carnada.show(g);
        panelPartida.repaint();
    
    }
    public void verificarColision(int nivel){
    
        if(carnada.getBounds().intersects(pezDeseado1.getBounds())){
        
            jugador.agregarPescado(pezDeseado1);
            pezDeseado1.setCoordX(1800);
            pezDeseado1.setCoordY((int)(Math.random()*400)+300);
            
            carnada.setCoordY(259);
            
            jugador.setCantCarnada(jugador.getCantCarnada()-1);
            panelPartida.setCantPeces(jugador.devolverPeces(nivel));
            panelPartida.setCantCarnada(jugador.getCantCarnada());
            System.out.println(jugador.devolverPeces());
        }
        if(carnada.getBounds().intersects(pezDeseado2.getBounds())){
        
            jugador.agregarPescado(pezDeseado2);
            pezDeseado2.setCoordX(-200);
            pezDeseado2.setCoordY((int)(Math.random()*400)+300);
            
            carnada.setCoordY(259);
            
            jugador.setCantCarnada(jugador.getCantCarnada()-1);
            panelPartida.setCantPeces(jugador.devolverPeces(nivel));
            panelPartida.setCantCarnada(jugador.getCantCarnada());
            System.out.println(jugador.devolverPeces());
        }
        
        if(carnada.getBounds().intersects(PezIndeseado1.getBounds())){
        
            jugador.agregarPescado(PezIndeseado1);
            PezIndeseado1.setCoordX(1800);
            PezIndeseado1.setCoordY((int) (Math.random() * 400) + 260);
            
            carnada.setCoordY(259);
            
            jugador.setCantCarnada(jugador.getCantCarnada()-1);
            panelPartida.setCantCarnada(jugador.getCantCarnada());
            
            System.out.println(jugador.devolverPeces());

        }
        
        if(carnada.getBounds().intersects(PezIndeseado2.getBounds())){
        
            jugador.agregarPescado(PezIndeseado2);
            PezIndeseado2.setCoordX(-200);
            PezIndeseado2.setCoordY((int) (Math.random() * 400) + 260);
            
            carnada.setCoordY(259);
            
            jugador.setCantCarnada(jugador.getCantCarnada()-1);
            panelPartida.setCantCarnada(jugador.getCantCarnada());
            
            System.out.println(jugador.devolverPeces());

        }
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("CoordenadaX: "+e.getX()+" CoordenadaY: "+e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
            
            case KeyEvent.VK_A:
                
                jugador.setImagen(new ImageIcon("./src/main/resources/Pescador-despierto-izquierdo1.1.gif"));
                jugador.setCoordX(jugador.getCoordX()-10);
                carnada.setCoordY(259);
                carnada.setCoordX(jugador.getCoordX()+428);
                
                break;
                
            case KeyEvent.VK_D:
                
                jugador.setImagen(new ImageIcon("./src/main/resources/PescadorDespiertoDerecho1.1.gif"));
                jugador.setCoordX(jugador.getCoordX()+10);
                carnada.setCoordY(259);
                carnada.setCoordX(jugador.getCoordX()-2);
                
                break;
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        
        carnada.setCoordY(carnada.getCoordY()+2*e.getWheelRotation());
    }

    @Override
    public void run() {

        ///////////////////////////////////////////////////////
        try {
            File archivoMusica = new File("./src/main/resources/sonidos/ALonelyCherryTree.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(archivoMusica);
            musicaFondo = AudioSystem.getClip();
            musicaFondo.open(audioStream);

        } catch (Exception e) {
        }
        while (resultado == 1 && reloj.isAlive() && panelPartida.getCantCarnada() > 0) {

            if (musicaFondo != null) {

                musicaFondo.start();
                if (!musicaFondo.isActive()) {
                    musicaFondo.setFramePosition(0);// Reproducir el audio
                }
                musicaFondo.start();
            }

            if (panelPartida.verificarMision()) {

                musicaFondo.stop();
                musicaFondo = null;
                resultado++; //432,339,600,514
                pezDeseado1 = new Pez("Pez Espada", false, 1, 50, 600, 432, new ImageIcon("./src/main/resources/PezEspada1.1.gif"));
                PezIndeseado1 = new Pez("Tiburon", false, 1, 20, 1000, 200, new ImageIcon("./src/main/resources/Tiburon1.1.gif"));
                pezDeseado2 = new Pez("Pez Espada", false, 2, 50, -500, 600, new ImageIcon("./src/main/resources/PezEspada1.2.gif"));
                PezIndeseado2 = new Pez("Tiburon", false, 2, 25, -500, 514, new ImageIcon("./src/main/resources/Tiburon1.2.gif"));
                panelPartida.setNivel(2);

            } else {

                verificarColision(1);

            }

        }

        movPezDeseado1 = new Thread(pezDeseado1);
        movPezIndeseado1 = new Thread(PezIndeseado1);
        movPezDeseado2 = new Thread(pezDeseado2);
        movPezIndeseado2 = new Thread(PezIndeseado2);
        movPezDeseado1.start();
        movPezIndeseado1.start();
        movPezDeseado2.start();
        movPezIndeseado2.start();
        try {
            File archivoMusica = new File("./src/main/resources/sonidos/RunAsFastAs YouCan.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(archivoMusica);
            musicaFondo = AudioSystem.getClip();
            musicaFondo.open(audioStream);
        } catch (Exception e) {
        }

        while (resultado == 2 && reloj.isAlive() && panelPartida.getCantCarnada() > 0) {

            if (musicaFondo != null) {

                musicaFondo.start();
                if (!musicaFondo.isActive()) {
                    musicaFondo.setFramePosition(0);// Reproducir el audio
                }
                musicaFondo.start();
            }

            if (panelPartida.verificarMision()) {

                musicaFondo.stop();
                musicaFondo = null;
                resultado++;
                pezDeseado1 = new Pez("Dorado", false, 1, 100, 600, 600, new ImageIcon("./src/main/resources/Dorado1.1.gif"));
                PezIndeseado1 = new Pez("Medusa", false, 3, 100, 1000, 270, new ImageIcon("./src/main/resources/Medusa1.1.gif"));
                pezDeseado2 = new Pez("Dorado", false, 2, 50, -500, 600, new ImageIcon("./src/main/resources/Dorado1.2.gif"));
                PezIndeseado2 = new Pez("Medusa", false, 3, 80, -500, 514, new ImageIcon("./src/main/resources/Medusa1.1.gif"));
                panelPartida.setNivel(3);

            } else {

                verificarColision(2);

            }
        }

        movPezDeseado1 = new Thread(pezDeseado1);
        movPezIndeseado1 = new Thread(PezIndeseado1);
        movPezDeseado2 = new Thread(pezDeseado2);
        movPezIndeseado2 = new Thread(PezIndeseado2);
        movPezDeseado1.start();
        movPezIndeseado1.start();
        movPezDeseado2.start();
        movPezIndeseado2.start();
        try {
            File archivoMusica = new File("./src/main/resources/sonidos/BeginYourJourney.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(archivoMusica);
            musicaFondo = AudioSystem.getClip();
            musicaFondo.open(audioStream);
        } catch (Exception e) {
        }

        while (resultado == 3 && reloj.isAlive() && panelPartida.getCantCarnada() > 0) {

            if (musicaFondo != null) {

                musicaFondo.start();
                if (!musicaFondo.isActive()) {
                    musicaFondo.setFramePosition(0);// Reproducir el audio
                }
                musicaFondo.start();
            }

            if (panelPartida.verificarMision()) {

                musicaFondo.stop();
                musicaFondo = null;
                resultado++;

            } else {

                verificarColision(3);

            }
        }
        
        gui_Resultado.setExtendedState(guiPartida.MAXIMIZED_BOTH);

        
        if (resultado == 4) {
            musicaFondo = null;
            guiPartida.dispose();
            panelResultado.setFondo(new ImageIcon("./src/main/resources/Win1.1.png"));
            gui_Resultado.setContentPane(panelResultado);
            gui_Resultado.setVisible(true);

        } else {
            musicaFondo.stop();
            musicaFondo = null;
            guiPartida.dispose();
            panelResultado.setFondo(new ImageIcon("./src/main/resources/GameOver1.1.png"));
            gui_Resultado.setContentPane(panelResultado);
            gui_Resultado.setVisible(true);

        }
        
        jugador.setNivel(resultado);
        File jSon=new File("Registro de Partidas");
        if(!jSon.exists()){
            
            System.out.println("no existe");
            
            JSONObject Partida=new JSONObject();
            JSONArray listaBase=new JSONArray();
            
            Partida.put("Jugador: ", jugador.getNombre());
            Partida.put("Ultimo nivel superado: ", (resultado - 1));
            Partida.put("En esta partida ",jugador.devolverPeces());
            
            listaBase.add(Partida);
            objetoBase.put("Estadisticas", listaBase);
            try {
            FileWriter escribir=new FileWriter(new File("Registro de Partidas"));
            escribir.write(objetoBase.toJSONString());
            escribir.flush();
            escribir.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Actualmente no hay partidas registradas.");
            System.out.println("Error al crear el archivo");
        }
        
        }else{
            System.out.println("si existe");
            JSONParser parser=new JSONParser();
        try {
            FileReader leer=new FileReader(new File("Registro de Partidas"));
           Object obj=parser.parse(leer);
           JSONObject objBase=(JSONObject)obj;
           
           JSONArray arregloDatos=(JSONArray)objBase.get("Estadisticas");
           
           JSONObject Partida=new JSONObject();
           
           Partida.put("Jugador: ", jugador.getNombre());
            Partida.put("Ultimo nivel superado: ", (resultado - 1));
            Partida.put("En esta partida ",jugador.devolverPeces());
            
            arregloDatos.add(Partida);
            objetoBase.put("Estadisticas", arregloDatos);
            try {
            FileWriter escribir=new FileWriter(new File("Registro de Partidas"));
            escribir.write(objetoBase.toJSONString());
            escribir.flush();
            escribir.close();
        } catch (IOException ex) {
            System.out.println("Error al crear el archivo");
        }
           
        } catch (IOException ex) {
            System.out.println("Error al leer el arhivo");
        } catch (ParseException ex) {
            System.out.println("Error en el momento de parsear archivo");
        }
                  
        }
        

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getActionCommand().equals("RegresarMenu")){
        
            gui_Resultado.dispose();
        
        }
    }

    
    
    
}
