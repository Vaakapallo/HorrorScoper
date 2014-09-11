/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chillerbot.colorblender;

import java.awt.Color;

/**
 *
 * @author Lassi
 */
public class ColorBlender {
    
    
    public int RGBDistance(Color first, Color second){        
        
        return first.getRGB() - second.getRGB();
    }
    
}
