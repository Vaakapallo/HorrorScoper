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
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws TwitterException, IOException {
        ColorBlender blender = new ColorBlender();
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
        //    System.out.println(convert.nameAndLinkForColor(color).getDistance());             
        }
        
        ChillTweet tweet = new ChillTweet(convert);
        
        tweet.retweetEveryColorBot();
        
        //loader.addNewLinksToFile("links.txt", tweet.getNewLinks(loader.getColorsToLinks()));
      
        //System.out.println("Name for color: " + convert.nameAndLinkForColor(new Color(10, 166, 153)));
        //while(!tweet.tweet(convert.nameAndLinkForColor(Randomizer.randomColor())));
        
        //new ChillTweet().start();
    }

}
