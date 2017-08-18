package com.libertymutual.blackjack.models;

import java.util.ArrayList;

public class Hand {
	
	ArrayList<Card> hand;
	
	public Hand() {
		hand = new ArrayList<Card>();
	}
	
	public void addCard(Card card) {
		hand.add(card);
		
	}
	
	public ArrayList<Card> getCards(){
		return hand;
	}
	//in game, create who instances of Hand object - one for player, one for dealer
	//handTotalScore
		//method add card;
}
