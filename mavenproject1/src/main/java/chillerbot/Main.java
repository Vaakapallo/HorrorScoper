/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chillerbot;

import chillerbot.colorblender.ColorBlender;
import chillerbot.dataloader.DataLoader;
import chillerbot.domain.StereotypeColor;
import java.awt.Color;
import java.io.IOException;
import java.util.Arrays;
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
        
        loader.printWordPairs();

        loader.randomStereotypeColor();

        System.out.println("The color red is:");
        for (int i = 0; i < 5; i++) {
            blender.mix(Color.red, loader.randomStereotypeColor(), loader.randomStereotypeColor());
        }

        StereotypeColor one = loader.randomStereotypeColor();
        StereotypeColor two = loader.randomStereotypeColor();

        StereotypeColor mix = blender.blend(one, two, 0.5);
        StereotypeColor mix2 = blender.blend(two, one, 0.5);

        System.out.println(one);
        System.out.println(two);
        System.out.println(mix);
        System.out.println(mix2);

        System.out.println("Similarity of " + one.stereotypeName() + " and " + mix.stereotypeName() + " is " + blender.percentageSimilarity(one, mix));
        System.out.println("Similarity of " + two.stereotypeName() + " and " + mix.stereotypeName() + " is " + blender.percentageSimilarity(two, mix));

        System.out.println("Similarity of " + one.stereotypeName() + " and " + mix2.stereotypeName() + " is " + blender.percentageSimilarity(one, mix2));
        System.out.println("Similarity of " + two.stereotypeName() + " and " + mix2.stereotypeName() + " is " + blender.percentageSimilarity(two, mix2));

        //new ChillTweet().start();
    }

}
