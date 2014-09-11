/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chillerbot;

import java.io.IOException;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class ChillTweet {

    private final static String CONSUMER_KEY = "";
    private final static String CONSUMER_KEY_SECRET = "";

    public void start() throws TwitterException, IOException {

        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_KEY_SECRET);

        // here's the difference
        String accessToken = getSavedAccessToken();
        String accessTokenSecret = getSavedAccessTokenSecret();
        AccessToken oathAccessToken = new AccessToken(accessToken,
                accessTokenSecret);

        twitter.setOAuthAccessToken(oathAccessToken);
        // end of difference

        twitter.updateStatus("Hi, im updating status from a Twitter App!");

        System.out.println("\nMy Timeline:");

        // I'm reading your timeline
        for (Status each : twitter.getHomeTimeline()) {

            System.out.println("Sent by: @" + each.getUser().getScreenName()
                    + " - " + each.getUser().getName() + "\n" + each.getText()
                    + "\n");
        }

    }

    private String getSavedAccessTokenSecret() {
 // consider this is method to get your previously saved Access Token
        // Secret
        return "";
    }

    private String getSavedAccessToken() {
        // consider this is method to get your previously saved Access Token
        return "";
    }

}
