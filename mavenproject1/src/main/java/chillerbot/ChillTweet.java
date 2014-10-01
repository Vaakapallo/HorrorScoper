/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chillerbot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class ChillTweet {

    private String CONSUMER_KEY;
    private String CONSUMER_KEY_SECRET;
    private String ACCESS_TOKEN;
    private String ACCESS_TOKEN_SECRET;

    public void tweet(String tweet) throws TwitterException, IOException {

        Twitter twitter = new TwitterFactory().getInstance();
        if (loadKeys()) {
            twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_KEY_SECRET);

            AccessToken oathAccessToken = new AccessToken(ACCESS_TOKEN,
                    ACCESS_TOKEN_SECRET);

            twitter.setOAuthAccessToken(oathAccessToken);

            twitter.updateStatus(tweet + randomScaryThing());

            System.out.println("\nMy Timeline:");

            // I'm reading your timeline
            for (Status each : twitter.getHomeTimeline()) {

                System.out.println("Sent by: @" + each.getUser().getScreenName()
                        + " - " + each.getUser().getName() + "\n" + each.getText()
                        + "\n");
            }
        } else {
            System.out.println("Authentication failed, check keys.");
        }
    }

    private boolean loadKeys() {
        Scanner reader = null;
        try {
            File file = new File("keys.txt");
            reader = new Scanner(file);
        } catch (FileNotFoundException ex) {
            System.out.println("Failed to get keys, sorry. Maybe you should add your keys in a file?");
            return false;
        }
        if (reader.hasNext()) {
            CONSUMER_KEY = reader.nextLine();
            CONSUMER_KEY_SECRET = reader.nextLine();
            ACCESS_TOKEN = reader.nextLine();
            ACCESS_TOKEN_SECRET = reader.nextLine();
            return true;
        }
        return false;
    }
    
    private String randomScaryThing(){
        String[] scaryCreatures = {"werewolf", "vampire", "zombie", "murderer", "killer", "swamp monster"};
        
        String string = "And there's a ";
        
        Random random = new Random();
        
        string += scaryCreatures[random.nextInt(scaryCreatures.length)];
        
        string += " behind you!";
        
        return string;
    }

}
