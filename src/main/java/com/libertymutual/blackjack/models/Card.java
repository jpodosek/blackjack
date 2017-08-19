package com.libertymutual.blackjack.models;

import java.util.ArrayList;

public class Card {
	
	private String rank;
	private String suit; 
	private int cardRank;
	
	Hand userHand = new Hand();
	Hand dealerHand = new Hand();

	 public Card(String rank, String suit) {
       
	     this.rank = rank;
	     this.suit = suit;
	 }
	 
	 
	 public String getSuit() {
         // Return the int that codes for this card's suit.
		 return suit;
	 }
 
	 public String getRank() {
	         // Return the int that codes for this card's value.
	     return rank;
	 }
 
 	@Override 
 	public String toString() { 
		return this.getRank() + " of " + this.getSuit(); 
 	} 
 
	public int getCardValue() {
		if (getRank().equals("ACE")) {
			cardRank = 11;
		} else if (getRank().equals("JACK") || getRank().equals("QUEEN") || getRank().equals("KING")) {
			cardRank = 10;
		} else {
			cardRank = Integer.parseInt(getRank());
		} 
		return cardRank;
	}
	
	public int aceCheck() {
		if(userHand.getHandScore() > 21 || dealerHand.getHandScore() > 21) {
			//will eliminate the above if statement b/c we will invoke this method if user/dealer are over 21 in controller
			//check to see if hand has an ace, if yes then equals 1, else bust
			ArrayList<Card> cardsInHand = userHand.getCards();
			int handLength = cardsInHand.size();
			for(int i = 0; i < handLength; i+= 1) {
				//traverse the hand, extract the card values and check to see if there's an ace
				Card card = cardsInHand.remove(i);
				if(card.cardRank == 11) {
					cardRank = 1;
					return cardRank;
				}
			}
		} return cardRank;
	}
	
} 
