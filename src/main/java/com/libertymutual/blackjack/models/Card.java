package com.libertymutual.blackjack.models;

public class Card {
	
	private String rank;
	private String suit; 
	private int cardRank;
	
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
		} else if (getRank().equals("2")) {
			cardRank = 2;
		} else if (getRank().equals("3")) {
			cardRank = 3;
		} else if (getRank().equals("4")) {
			cardRank = 4;
		} else if (getRank().equals("5")) {
			cardRank = 5;
		} else if (getRank().equals("6")) {
			cardRank = 6;
		} else if (getRank().equals("7")) {
			cardRank = 7;
		} else if (getRank().equals("8")) {
			cardRank = 8;
		} else if (getRank().equals("9")) {
			cardRank = 9;
		} else if (getRank().equals("10")) {
			cardRank = 10;
		}
		return cardRank;
	}

}
