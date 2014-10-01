/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chillerbot.domain;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Lassi
 */
public class Randomizer {
    
    private static Random random = new Random();
    
    public static Color randomColor(){
        return new Color(randomRGBValue(), randomRGBValue(), randomRGBValue());
    }
    
    public static int randomRGBValue(){
       return random.nextInt(256);
    }
    
}
