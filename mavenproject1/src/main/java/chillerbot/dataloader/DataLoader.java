/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chillerbot.dataloader;

import chillerbot.domain.WordPair;
import chillerbot.domain.StereotypeColor;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Lassi
 */
public class DataLoader {

    private HashMap<Integer, StereotypeColor> colors = new HashMap();
    private ArrayList<Integer> keys = new ArrayList();
    private HashSet<WordPair> wordPairs = new HashSet();

    public void loadColors(String path) {
        Scanner fileReader = null;
        try {
            File file = new File(path);
            fileReader = new Scanner(file);
        } catch (FileNotFoundException ex) {
            System.out.println("Invalid file path.");
            return;
        }
        while (fileReader.hasNext()) {
            String stereotype = fileReader.next();
            String name = fileReader.next();
            int hex = Integer.parseInt(fileReader.next().substring(1), 16);
            keys.add(hex);
            colors.put(hex, new StereotypeColor(stereotype, name, hex));
        }
    }

    public void printColors() {
        for (StereotypeColor color : colors.values()) {
            System.out.println(color);
        }
    }
    
    public void printWordPairs(){
        for (WordPair pair : wordPairs) {
            System.out.println(pair);
        }
    }

    public void loadWordPairs(String URL) {
        Scanner fileReader = null;
        try {
            File file = new File(URL);
            fileReader = new Scanner(file);
        } catch (FileNotFoundException ex) {
            System.out.println("Invalid file path.");
            return;
        }
        while (fileReader.hasNext()) {
            fileReader.next();
            String first = fileReader.next();
            String second = fileReader.next();
            fileReader.next();
            int freq = Integer.parseInt(fileReader.next());
            WordPair pair = new WordPair(first, second);
            wordPairs.add(pair);
        }
    }

    public StereotypeColor randomStereotypeColor() {
        Random random = new Random();
        return colors.get(keys.get(random.nextInt(keys.size())));
    }
}
