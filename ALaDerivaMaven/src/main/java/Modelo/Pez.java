/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author 50684
 */
public class Pez extends Objetos implements Runnable{
    
    private boolean pescado;
    private String nombre;
    private int velocidad;
    private boolean tipo;
    private int dificultad;
    private int condicion=0;

    public Pez(String nombre,boolean pescado, int dificultad, int velocidad, int coordX, int coordY, ImageIcon imagen) {
        super(coordX, coordY, imagen);
        this.nombre=nombre;
        this.pescado=pescado;
        this.velocidad=velocidad;
        this.dificultad=dificultad;
    }

    
    public boolean getPescado() {
        return pescado;
    }

    public void setPescado(boolean pescado) {
        this.pescado = pescado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }
    
    public boolean getTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }
    
    public void tipoMovimiento(int dificultad){
        
        switch (dificultad) {
            case 1:
                if (getCoordX()>-200) {
                setCoordX(getCoordX() - 10);
            } else {
                setCoordX(1600);
            }
                break;
                
            case 2:
                
                if (getCoordX()<1600) {
                setCoordX(getCoordX() + 10);
            } else {
                setCoordX(-500);
            }
                
                //logica para subir y bajar
                
                break;
                
            case 3:
                
                if (getCoordX()>-200) {
                setCoordX(getCoordX() - 200);
            } else {
                setCoordX(1600);
            }
                
                if(condicion==0){
                
                    setCoordY(getCoordY()+50);
                    condicion=1;
                
                }else{
                
                    setCoordY(getCoordY()-50);
                    condicion=0;
                
                }
                //logica para moviento nivel 3
                break;
            default:
                throw new AssertionError();
        }
    
    }
    
    public Rectangle getBounds(){
        
        Rectangle hitbox = null;
    
        switch (getNombre()) {
            case "Morena":
                
                hitbox=new Rectangle(getCoordX(),getCoordY()+20, 12,12);
                
                break;
                
                case "Atun":
                
                hitbox=new Rectangle(getCoordX(),getCoordY()+20, 12,12);
                
                break;
                
                case "Dorado":
                
                hitbox=new Rectangle(getCoordX(),getCoordY()+62, 12,12);
                
                break;
                
                case "Medusa":
                
                hitbox=new Rectangle(getCoordX()+20,getCoordY()+39, 40,25);
                
                break;
                
                case "Tiburon":
                
                hitbox=new Rectangle(getCoordX(),getCoordY()+124, 65,20);
                
                break;
                
                case "Pez Espada":
                
                hitbox=new Rectangle(getCoordX()+58,getCoordY()+52, 12,12);
                
                break;
        }
        return hitbox;
        
    }

    @Override
    public void run() {

        while (!pescado) {

            tipoMovimiento(dificultad);
            

            try {
                Thread.sleep(getVelocidad());

            } catch (InterruptedException e) {
                //maejar interrupciones
            }

        }

    }
    
    
    
}
