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

    public StereotypeColor(String stereotype, String colorName, int hex) {
        this.stereotype = stereotype;
        this.colorName = colorName;
        this.color = new Color(hex);
    }
    
    public StereotypeColor(String stereotype, String colorName, int red,int green,int blue){
        this.stereotype = stereotype;
        this.colorName = colorName;
        this.color = new Color(red,green,blue); 
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
    
    public String stereotypeName(){
        return stereotype + " " + colorName;
    }

    @Override
    public String toString() {
        return Integer.toHexString(color.getRGB()) + ": " + stereotype + " " + colorName;
    }
}
