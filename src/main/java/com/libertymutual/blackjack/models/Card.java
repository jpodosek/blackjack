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
			setCardRank(11);
		} else if (getRank().equals("JACK") || getRank().equals("QUEEN") || getRank().equals("KING")) {
			setCardRank(10);
		} else {
			setCardRank(Integer.parseInt(getRank()));
		} 
		return getCardRank();
	}

	public int getCardRank() {
		return cardRank;
	}


	public void setCardRank(int cardRank) {
		this.cardRank = cardRank;
	}
	
} 
