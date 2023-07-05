/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 *
 * @author 50684
 */
public class Objetos {
    
    private int coordX, coordY, recorridoMax;
    private ImageIcon imagen;

    public Objetos(int coordX, int coordY, ImageIcon imagen) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.imagen = imagen;
    }

    public Objetos(int coordX, int coordY, int recorridoMax, ImageIcon imagen) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.recorridoMax = recorridoMax;
        this.imagen = imagen;
    }

    public int getCoordX() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    public int getRecorridoMax() {
        return recorridoMax;
    }

    public void setRecorridoMax(int recorridoMax) {
        this.recorridoMax = recorridoMax;
    }

    public ImageIcon getImagen() {
        return imagen;
    }

    public void setImagen(ImageIcon imagen) {
        this.imagen = imagen;
    }
    
    public void show(Graphics g) {
        imagen.paintIcon(null, g, this.coordX, this.coordY);

    }
    
    
    
}
