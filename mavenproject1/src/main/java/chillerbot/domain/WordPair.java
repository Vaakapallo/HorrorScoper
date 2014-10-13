/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chillerbot.domain;

/**
 *
 * @author Lassi
 */
public class WordPair {
    private String first;
    private String second;
    private int spookiness;

    public WordPair(String first, String second) {
        this.first = first;
        this.second = second;
    }

    public WordPair(String first, String second, int spookiness) {
        this.first = first;
        this.second = second;
        this.spookiness = spookiness;
    }

    @Override
    public String toString() {
        return first + " " + second;
    }

    public String getFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }

    public int getSpookiness() {
        return spookiness;
    }
    
    public String printWithSpookiness(){
        return first + " " + second + ": " + spookiness;
    }
}
