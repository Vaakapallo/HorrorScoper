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

    public WordPair(String first, String second) {
        this.first = first;
        this.second = second;
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
}
