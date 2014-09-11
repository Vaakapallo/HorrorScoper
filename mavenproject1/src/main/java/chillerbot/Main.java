/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chillerbot;

import java.io.IOException;
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
        System.out.println("KUKKUUU");
        new ChillTweet().start();
    }
    
}
