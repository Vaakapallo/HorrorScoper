/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chillerbot.colorblender;

import chillerbot.domain.StereotypeColor;
import java.awt.Color;

/**
 *
 * @author Lassi
 */
public class ColorBlender {

    public static final double MAX_COLOR_DISTANCE = 765.0;

    public int[] RGBDistance(Color first, Color second) {
        int[] RGB = new int[3];
        RGB[0] = (first.getRed() - second.getRed());
        RGB[1] = (first.getGreen() - second.getGreen());
        RGB[2] = (first.getBlue() - second.getBlue());

        return RGB;
    }

    public int absoluteRGBDistance(Color first, Color second) {
        return Math.abs(first.getRed() - second.getRed()) + Math.abs(first.getGreen() - second.getGreen()) + Math.abs(first.getBlue() - second.getBlue());
    }

    public String distanceAsText(Color first, Color second) {
        String returnable = "";
        int[] differences = RGBDistance(first, second);

        returnable += "Distances:\n" + "Red: " + differences[0] + "\nGreen: " + differences[1] + "\nBlue: " + differences[2] + "\n";

        return returnable;
    }

    public Color calculateMiddle(Color first, Color second) {
        int[] differences = RGBDistance(first, second);
        Color color = new Color(first.getRed() - differences[0] / 2, first.getGreen() - differences[1] / 2, first.getBlue() - differences[2] / 2);

        return color;
    }

    public double percentageSimilarity(Color first, Color second) {
        int absoluteRGB = absoluteRGBDistance(first, second);

        return 1 - (absoluteRGB / MAX_COLOR_DISTANCE);
    }
    
    public double percentageSimilarity(StereotypeColor first, StereotypeColor second) {
        return percentageSimilarity(first.getColor(), second.getColor());
    }

    public void mix(Color target, StereotypeColor first, StereotypeColor second) {
        System.out.println(percentageSimilarity(target, first.getColor()) + " " + first.stereotypeName());
        System.out.println(percentageSimilarity(target, second.getColor()) + " " + second.stereotypeName());
    }

    public StereotypeColor blend(StereotypeColor first, StereotypeColor second, double ratio) {
        int[] differences = RGBDistance(first.getColor(), second.getColor());
        int newRed = first.getColor().getRed() - (int)((differences[0] / 2)*ratio);
        int newGreen = first.getColor().getGreen() - (int)((differences[1] / 2)*ratio);
        int newBlue = first.getColor().getBlue() - (int)((differences[2] / 2)*ratio);
        StereotypeColor color = new StereotypeColor(first.getStereotype() + " " + second.getStereotype(),"mix",newRed, newGreen, newBlue);
        return color;
    }

}
