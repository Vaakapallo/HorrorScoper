/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chillerbot.dataloader;

import chillerbot.domain.StereotypeColor;
import chillerbot.domain.WordPair;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Lassi
 */
public class DataLoader {

    private HashMap<StereotypeColor, Integer> colors = new HashMap();
    private HashSet<WordPair> wordPairs = new HashSet();
    private HashMap<Color, String> colorsToLinks = new HashMap();

    public void loadColors(String path) {
        Scanner fileReader = createScanner(path);
        while (fileReader.hasNext()) {
            String stereotype = fileReader.next();
            String name = fileReader.next();
            int hex = Integer.parseInt(fileReader.next().substring(1), 16);
            int spookiness = Integer.parseInt(fileReader.next());
            colors.put(new StereotypeColor(stereotype, name, hex, spookiness),hex);
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
    
    public void loadCustomWordPairs(String url){
        Scanner fileReader = createScanner(url);
        while (fileReader.hasNext()) {
            String first = fileReader.next();
            String second = fileReader.next();
            WordPair pair = new WordPair(first, second);
            wordPairs.add(pair);
        }
    }
    
    public void loadLinks(String url){
        Scanner fileReader = createScanner(url);
        while (fileReader.hasNext()) {
            int hex = Integer.parseInt(fileReader.next().substring(1), 16);
            String link = fileReader.next();
            colorsToLinks.put(new Color(hex), link);
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

    public HashMap<Color, String> getColorsToLinks() {
        return colorsToLinks;
    }

    public void addNewLinksToFile(String url, List<String> newLinks) throws IOException {
        if(newLinks.isEmpty()){
            return;
        }
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(url, true)));
        out.println();
        for (String string : newLinks) {
            out.println(string);
        }
        out.close();
    }
}
