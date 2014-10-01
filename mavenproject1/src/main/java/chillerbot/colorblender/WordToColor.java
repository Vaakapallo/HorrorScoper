/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chillerbot.colorblender;

import chillerbot.domain.StereotypeColor;
import chillerbot.domain.WordPair;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Lassi
 */
public class WordToColor {
    
    private HashMap<StereotypeColor, WordPair> colorsToPairs = new HashMap();
    private HashMap<StereotypeColor, Integer> colors;
    private HashSet<WordPair> wordPairs;
    private ColorBlender blender = new ColorBlender();
    private int nullCounter = 0;
    private int successes = 0;

    public WordToColor(HashMap<StereotypeColor, Integer> colors, HashSet<WordPair> wordPairs) {
        this.colors = colors;
        this.wordPairs = wordPairs;
    }
    
    public void findColorsForWords(){
        StereotypeColor first, second, result;
        for (WordPair wordPair : wordPairs) {
            first = findColorForWord(wordPair.getFirst());
            second = findColorForWord(wordPair.getSecond());
            if(first == null || second == null){
                nullCounter++;
                System.out.println(wordPair.getFirst() + wordPair.getSecond() + "NULLLLLLLLLLLLLL");
                continue;
            }
            result = blender.blend(first, second, 0.75);
            successes++;
            colorsToPairs.put(result, wordPair);
        }
        System.out.println("Nullcounter: " + nullCounter + " successes: " + successes);
    }
    
    public void printPairsToColors(){
        for (StereotypeColor stColor : colorsToPairs.keySet()) {
            System.out.println(colorsToPairs.get(stColor).toString() + " is " + stColor.getColor());
        }
    }
    
    public StereotypeColor findColorForWord(String word){
        StereotypeColor rightColor = null;
        for (StereotypeColor color : colors.keySet()) {
            if(color.getStereotype().equalsIgnoreCase(word)){
                return color;
            }
        }
        return rightColor;
    }

    public HashMap<StereotypeColor, WordPair> getColorsToPairs() {
        return colorsToPairs;
    }
    
}
