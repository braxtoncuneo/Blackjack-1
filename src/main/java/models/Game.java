package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by michaelhilton on 1/25/16.
 */
public class Game {

    public java.util.List<Card> deck = new ArrayList<>();

    public java.util.List<String> ownership;
    public java.util.List<java.util.List<Card>> cols = new ArrayList<>();

    public int pot;
    public int ante;
    public int totalCash;



    public String errorCode;

    public Game(){
        cols.add(new ArrayList<Card>());
        cols.add(new ArrayList<Card>());
        cols.add(new ArrayList<Card>());
        cols.add(new ArrayList<Card>());
        ante=2;
        errorCode=" ";
    }


    public void buildDeck() {
        for (int i = 1; i < 14; i++) {
            deck.add(new Card(i, Suit.Clubs, true));
            deck.add(new Card(i, Suit.Hearts, true));
            deck.add(new Card(i, Suit.Diamonds, true));
            deck.add(new Card(i, Suit.Spades, true));
        }
    }

    public void shuffle() {
        long seed = System.nanoTime();
        Collections.shuffle(deck, new Random(seed));
    }

    //customDeal to setup game for testing purposes

    private boolean colHasCards(int colNumber) {
        return this.cols.get(colNumber).size()>0;
    }

    public void dealCardToCol(int colTo, Card cardToDeal) {
        cols.get(colTo).add(cardToDeal);
    }

    public Card drawCard(){
        if(deck.isEmpty()){
            buildDeck();
            shuffle();
        }
        Card result=deck.get(deck.size()-1);
        deck.remove(deck.size()-1);
        return result;
    }

    public int colScore(int col){
        int pos=0;
        int theScore=0;
        int aces=0;
        while(pos<cols.get(col).size()){
            if(cols.get(col).get(pos).getValue()>10){
                theScore+=10;
            }
            else{
                theScore+=cols.get(col).get(pos).getValue();
            }
            if(cols.get(col).get(pos).getValue()==1){
                aces++;
            }
            pos++;
        }
        while(theScore<=11 && aces>0){
            aces--;
            theScore+=10;
        }
        return 0;
    }
    
    public String getCardFromURL(Card card){
	    String URL =  https://raw.githubusercontent.com/cs361-W16/Blackjack-1/master/src/main/java/assets/images/”;
	    int tempV = card.getValue().toString();
        String =tempS = card.getSuit().toString();
        URL = URL + tempV + “of” + tempS + “.png”;
	    Return URL;
    }



    public void newGame(){

    }

}
