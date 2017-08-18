package com.libertymutual.blackjack.models;

public class Card {
	
	private String rank;
	private String suit; 
	
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
 
 

}
