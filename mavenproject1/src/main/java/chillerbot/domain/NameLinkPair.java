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
public class NameLinkPair {
    
    private String name;
    private String link;
    private Color color;

    public NameLinkPair(String name, String link, Color color) {
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

    @Override
    public String toString() {
        return name + ": " + link;
    }
    
}
