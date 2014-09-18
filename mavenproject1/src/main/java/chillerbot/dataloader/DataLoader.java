/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chillerbot.dataloader;

import chillerbot.domain.StereotypeColor;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Lassi
 */
public class DataLoader {
    
    ArrayList<StereotypeColor> colors = new ArrayList();
    
    public void loadColors(String path){
        Scanner fileReader = null;
        try {
            File file = new File(path);
            fileReader = new Scanner(file);
        } catch (FileNotFoundException ex) {
            System.out.println("Invalid file path.");
            return;
        }
        while(fileReader.hasNext()){
            String stereotype = fileReader.next();
            String name = fileReader.next();
            int hex = Integer.parseInt(fileReader.next().substring(1), 16);
            colors.add(new StereotypeColor(stereotype, name, hex));
        }
    }
    
    public void printColors(){
        for (StereotypeColor color : colors) {
            System.out.println(color);
        }
    }
    
    public void loadWordPairs(String URL){
        
    }
    
}
