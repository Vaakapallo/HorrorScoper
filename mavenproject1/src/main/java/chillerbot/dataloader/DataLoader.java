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

    private HashMap<StereotypeColor, Integer> colors = new HashMap();
    private HashSet<WordPair> wordPairs = new HashSet();

    public void loadColors(String path) {
        Scanner fileReader = createScanner(path);
        while (fileReader.hasNext()) {
            String stereotype = fileReader.next();
            String name = fileReader.next();
            int hex = Integer.parseInt(fileReader.next().substring(1), 16);
            colors.put(new StereotypeColor(stereotype, name, hex),hex);
        }

    }

    public void printColors() {
        for (StereotypeColor color : colors.keySet()) {
            System.out.println(color);
        }
    }
    
    public void printWordPairs(){
        for (WordPair pair : wordPairs) {
            System.out.println(pair);
        }
    }

    public void loadWordPairs(String URL) {
        Scanner fileReader = createScanner(URL);
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

    private Scanner createScanner(String URL) {
        Scanner fileReader = null;
        try {
            File file = new File(URL);
            fileReader = new Scanner(file);
        } catch (FileNotFoundException ex) {
            System.out.println("Invalid file path.");
        }
        return fileReader;
    }
    
    public HashMap<StereotypeColor, Integer> getColors() {
        return colors;
    }

    public HashSet<WordPair> getWordPairs() {
        return wordPairs;
    }
    
}
