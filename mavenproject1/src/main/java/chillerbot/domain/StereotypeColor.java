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
    private final String stereotype;
    private final String colorName;
    private final int hex;
    private final Color color;

    public StereotypeColor(String stereotype, String colorName, int hex) {
        this.stereotype = stereotype;
        this.colorName = colorName;
        this.hex = hex;
        this.color = new Color(hex);
    }

    public String getStereotype() {
        return stereotype;
    }

    public String getColorName() {
        return colorName;
    }

    public int getHex() {
        return hex;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return Integer.toHexString(hex) + ": " + stereotype + " " + colorName;
    }
    
}
