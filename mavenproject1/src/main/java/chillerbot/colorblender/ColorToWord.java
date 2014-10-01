/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chillerbot.colorblender;

import chillerbot.domain.NameLinkPair;
import chillerbot.domain.StereotypeColor;
import chillerbot.domain.WordPair;
import java.awt.Color;
import java.util.HashMap;

/**
 *
 * @author Lassi
 */
public class ColorToWord {
    
    private HashMap<StereotypeColor, WordPair> colorsToPairs;
    private ColorBlender blender = new ColorBlender();
    private HashMap<Color, String> colorsToLinks;

    public ColorToWord(HashMap<StereotypeColor, WordPair> colorsToPairs, HashMap<Color, String> colorsToLinks) {
        this.colorsToPairs = colorsToPairs;
        this.colorsToLinks = colorsToLinks;
    }
    
    public NameLinkPair nameAndLinkForColor(Color color){
        NameLinkPair pair = new NameLinkPair(null, null, color);
        int minimumdistance = blender.absoluteRGBDistance(color, colorsToPairs.keySet().iterator().next().getColor());
        for (StereotypeColor stColor : colorsToPairs.keySet()) {
            int distance = blender.absoluteRGBDistance(stColor.getColor(), color);
            if(distance < minimumdistance){
                minimumdistance = distance;
                pair.setColor(stColor.getColor());
                pair.setName(colorsToPairs.get(stColor).toString());
                pair.setLink(findClosestLink(stColor.getColor()));
            }
        }
        return pair;
    }
    
    private String findClosestLink(Color color){
        String link = "";
        int minimumdistance = blender.absoluteRGBDistance(color, colorsToLinks.keySet().iterator().next());
        for (Color colorIterable : colorsToLinks.keySet()) {
            int distance = blender.absoluteRGBDistance(colorIterable, color);
            if(distance < minimumdistance){
                minimumdistance = distance;
                link = colorsToLinks.get(colorIterable);
            }
        }
        return link;
    }
    
}
