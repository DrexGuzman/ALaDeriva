/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

/**
 *
 * @author 50684
 */
public class Pescador extends Objetos implements Runnable{
    
    private int cantCarnada, dificultad,nivel;
    private Carnada carnadas;
    private ArrayList<Pez> pecesCapturados;
    private String nombre, listaPeces;
    
    

    public Pescador(String nombre, int cantCarnada, int dificultad, Carnada carnadas, int coordX, int coordY, ImageIcon imagen) {
        super(coordX, coordY, imagen);
        this.cantCarnada = cantCarnada;
        this.dificultad = dificultad;
        this.carnadas = carnadas;
        pecesCapturados=new ArrayList<>();
        this.nombre=nombre;
    }
    
    public int getCantCarnada() {
        return cantCarnada;
    }

    public void setCantCarnada(int cantCarnada) {
        this.cantCarnada = cantCarnada;
    }

    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    public Carnada getCarnadas() {
        return carnadas;
    }

    public void setCarnadas(Carnada carnadas) {
        this.carnadas = carnadas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getListaPeces() {
        return listaPeces;
    }

    public void setListaPeces(String listaPeces) {
        this.listaPeces = listaPeces;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
    

    public void agregarPescado(Pez pez) {
        pecesCapturados.add(pez);
        System.out.println(pez.getNombre());
    }
    
    public String devolverPeces(){
    
        String cadenaPez="has capturado ";
        
        for (int i = 0; i < pecesCapturados.size(); i++) {
            cadenaPez+=pecesCapturados.get(i).getNombre()+", ";
            listaPeces+=pecesCapturados.get(i).getNombre()+", ";
        }
    
        return cadenaPez;
    }
    
    public int devolverPeces(int nivel){
    
        int cantidad=0;
        
        switch (nivel) {
            case 1:

                for (int i = 0; i < pecesCapturados.size(); i++) {

                    if (pecesCapturados.get(i).getNombre().equals("Atun")) {
                        cantidad++;
                    }
                }

                break;
                
            case 2:

                for (int i = 0; i < pecesCapturados.size(); i++) {

                    if (pecesCapturados.get(i).getNombre().equals("Pez Espada")) {
                        cantidad++;
                    }
                }

                break;
                
            case 3:

                for (int i = 0; i < pecesCapturados.size(); i++) {

                    if (pecesCapturados.get(i).getNombre().equals("Dorado")) {
                        cantidad++;
                    }
                }

                break;
        }
        
        return cantidad;
    }

    @Override
    public void run() {
        try {
            File carreteSonido = new File("./src/main/resources/sonidos/Carrete.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(carreteSonido);
            Clip sonido = AudioSystem.getClip();
            sonido.open(audioStream);
        } catch (Exception e) {
        }
    }
}
