/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chillerbot;

import chillerbot.colorblender.ColorToWord;
import chillerbot.domain.TweetCandidate;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import twitter4j.ResponseList;
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
    private Twitter twitter;
    private ColorToWord convert;
    private List<Status> homeTimeline;

    public ChillTweet(ColorToWord convert) throws TwitterException {
        setUpTwitter();
        this.convert = convert;
    }

    public boolean tweet(TweetCandidate candidate) throws TwitterException, IOException {
        if (evaluateTweet(candidate)) {
            tweetPassedCandidate(candidate);
            return true;
        }
        return false;
    }

    public List<String> getNewLinks(HashMap<Color, String> colorsToLinks) throws TwitterException {
        ArrayList<String> links = new ArrayList();
        for (Status each : getStatusesFromUser("@everycolorbot")) {
            String[] statusText = (each.getText().replace("0x", "#").split(" "));
            if (!colorsToLinks.values().contains(statusText[1])) {
                links.add(statusText[0] + " " + statusText[1]);
            }
        }
        return links;
    }
    
    public void retweetEveryColorBot() throws TwitterException {
        for (Status each : getStatusesFromUser("@everycolorbot")) {
            String[] statusText = (each.getText().replace("0x", "#").split(" "));
            TweetCandidate candidate = convert.nameAndLinkForColor(new Color(Integer.parseInt(statusText[0].substring(1), 16)));
            if(evaluateTweet(candidate)){
                System.out.println("tweeting " + candidate.toString());
                reTweetPassedCandidate(candidate, each.getText());
                return;
            }
        }
    }

    public ResponseList<Status> getStatusesFromUser(String username) throws TwitterException {
        return twitter.getUserTimeline(username);
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

    private String randomScaryThing() {
        String[] scaryCreatures = {"werewolf", "vampire", "zombie", "murderer", "killer", "swamp monster", "ghost"};
        String[] scaryPrepositions = {"behind", "under", "beside", "within", "among", "staring at", "about to eat", "stalking", "about to murder"};

        String string = "And there's a ";

        Random random = new Random();

        string += scaryCreatures[random.nextInt(scaryCreatures.length)] + " ";

        string += scaryPrepositions[random.nextInt(scaryPrepositions.length)] + " you!";

        return string;
    }

    private void tweetPassedCandidate(TweetCandidate candidate) throws TwitterException {
        twitter.updateStatus("This " + spookinator(candidate.getSpookiness()) + " color is called " + candidate.toString() + randomScaryThing() + candidate.getLink());
    }
    
    private void reTweetPassedCandidate(TweetCandidate candidate, String retweet) throws TwitterException {
        twitter.updateStatus("RT @everycolorbot: " + retweet + " This " + spookinator(candidate.getSpookiness()) + " color is called " + candidate.toString() + randomScaryThing());
    }

    private void setUpTwitter() throws TwitterException {
        twitter = new TwitterFactory().getInstance();
        if (loadKeys()) {
            twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_KEY_SECRET);

            AccessToken oathAccessToken = new AccessToken(ACCESS_TOKEN,
                    ACCESS_TOKEN_SECRET);

            twitter.setOAuthAccessToken(oathAccessToken);
            this.homeTimeline = twitter.getHomeTimeline();
        } else {
            System.out.println("Authentication failed, check keys.");
        }
    }

    private boolean evaluateTweet(TweetCandidate candidate) throws TwitterException {
        if(inLatestTweets(candidate.getName())){
            return false;
        }
        if (candidate.getSpookiness() > 3 && candidate.getDistance() < 75) {
            return true;
        } else {
            System.out.println("Bad color, not tweeting!");
            return false;
        }
    }

    private String spookinator(int spookiness) {
        String spoo = "sp";
        for (int i = 0; i < spookiness - 2; i++) {
            spoo += "o";
        }
        return spoo + "ky";
    }

    private boolean inLatestTweets(String name) {
        String[] nameParts = name.split(" ");
        // I'm reading your timeline
        
        for (Status each : this.homeTimeline) {
            String text = each.getText();
            if(!text.contains("called")){
                return false;
            }
            text = text.substring(text.indexOf("called"), text.indexOf("And"));
            if(text.contains(nameParts[0]) || text.contains(nameParts[1])){
                System.out.println(name + " found from latest tweets");
                return true;
            }
        }
        return false;
    }
}
