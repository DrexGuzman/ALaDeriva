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
public class Carnada extends Objetos{
    
    private int profMax;

    public Carnada(int profMax, int coordX, int coordY, ImageIcon imagen) {
        super(coordX, coordY, imagen);
        this.profMax = profMax;
    }

    public int getProfMax() {
        return profMax;
    }

    public void setProfMax(int profMax) {
        this.profMax = profMax;
    }
    
    public Rectangle getBounds(){
    
        return new Rectangle(getCoordX(),getCoordY(), 12,12);
        
    }
    
    
    
}
