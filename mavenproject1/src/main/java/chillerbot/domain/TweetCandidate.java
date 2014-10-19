/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chillerbot.domain;

import java.awt.Color;

/**
 *
 * @author Lassi
 */
public class TweetCandidate {
    
    private String name;
    private String link;
    private Color color;
    private int spookiness;
    private int distance;

    public TweetCandidate(String name, String link, Color color) {
        this.name = name;
        this.link = link;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public Color getColor() {
        return color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getSpookiness() {
        return spookiness;
    }

    public void setSpookiness(int spookiness) {
        this.spookiness = spookiness;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
    
    @Override
    public String toString() {
        return name + ". ";
    }
    
}
