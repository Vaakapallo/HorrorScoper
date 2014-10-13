/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chillerbot.domain;

import java.awt.Color;

/**
 *
 * @author Lassi
 */
public class StereotypeColor {
    private String stereotype;
    private String colorName;
    private Color color;
    private int spookiness;

    public StereotypeColor(String stereotype, String colorName, Color color, int spookiness) {
        this.stereotype = stereotype;
        this.colorName = colorName;
        this.color = color;
        this.spookiness = spookiness;
    }

    public StereotypeColor(String stereotype, String colorName, int hex, int spookiness) {
        this(stereotype,colorName,new Color(hex), spookiness);
    }
    
    public StereotypeColor(String stereotype, String colorName, int red,int green,int blue, int spookiness){
        this(stereotype,colorName,new Color(red,green,blue), spookiness);
    }

    public String getStereotype() {
        return stereotype;
    }

    public String getColorName() {
        return colorName;
    }

    public Color getColor() {
        return color;
    }

    public int getSpookiness() {
        return spookiness;
    }
    
    public String stereotypeName(){
        return stereotype + " " + colorName;
    }

    @Override
    public String toString() {
        return Integer.toHexString(color.getRGB()) + ": " + stereotype + " " + colorName + "Spook-O-Meter: " + spookiness;
    }
}
