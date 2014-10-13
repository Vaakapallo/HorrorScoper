/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chillerbot;

import chillerbot.colorblender.ColorBlender;
import chillerbot.colorblender.ColorToWord;
import chillerbot.colorblender.WordToColor;
import chillerbot.dataloader.DataLoader;
import chillerbot.domain.Randomizer;
import chillerbot.domain.StereotypeColor;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import twitter4j.TwitterException;

/**
 *
 * @author Lassi
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws TwitterException, IOException {
        ColorBlender blender = new ColorBlender();
        System.out.println("Difference between gray and red:" + blender.distanceAsText(Color.gray, Color.red));
        System.out.println("Difference between blue and red:" + blender.distanceAsText(Color.blue, Color.red));
        System.out.println("Difference between blue and gray:" + blender.distanceAsText(Color.blue, Color.gray));
        System.out.println("Difference between gray and gray:" + blender.distanceAsText(Color.gray, Color.gray));

        System.out.println("Blend of " + Color.red + " and " + Color.blue + ":\n " + blender.calculateMiddle(Color.red, Color.blue));
        System.out.println("Blend of " + Color.gray + " and " + Color.blue + ":\n " + blender.calculateMiddle(Color.gray, Color.blue));

        System.out.println("Percentage distance of " + Color.gray + " and " + Color.blue + ":\n " + blender.percentageSimilarity(Color.gray, Color.blue));
        System.out.println("Percentage distance of " + Color.black + " and " + Color.white + ":\n " + blender.percentageSimilarity(Color.black, Color.white));

        DataLoader loader = new DataLoader();

        loader.loadColors("colormap.txt");
        loader.loadWordPairs("bigrams.txt");
        loader.loadCustomWordPairs("horrorgrams.txt");
        loader.loadLinks("links.txt");
                
        WordToColor mapper = new WordToColor(loader.getColors(), loader.getWordPairs());
        
        mapper.findColorsForWords();

        mapper.printPairsToColors();
        
        HashMap<Color, String> colors = loader.getColorsToLinks();
        
        for (Color color : colors.keySet()) {
            //System.out.println(color + colors.get(color));
        }
        
        ColorToWord convert = new ColorToWord(mapper.getColorsToPairs(), colors);
        
        for (int i = 0; i < 100; i++) {
            Color color = Randomizer.randomColor();
            System.out.println(color);
            System.out.println(convert.nameAndLinkForColor(color));             
        }
        
        ChillTweet tweet = new ChillTweet();
        
        List<String> newLinks = tweet.getNewLinks(loader.getColorsToLinks());
        
        for (StereotypeColor color : mapper.getColorsToPairs().keySet()) {
            if(color.getSpookiness() > 5){
                System.out.println(color);
            }
        }
        
        //loader.addNewLinksToFile("links.txt", newLinks);
      
        
        //tweet.tweet(convert.nameAndLinkForColor(Randomizer.randomColor()).toString());
        
        //new ChillTweet().start();
    }

}
