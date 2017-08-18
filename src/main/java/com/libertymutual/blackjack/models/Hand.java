package com.libertymutual.blackjack.models;

import java.util.ArrayList;

public class Hand {

	ArrayList<Card> hand;
	//Card card;

	public Hand() {
		hand = new ArrayList<Card>();
	}

	public void addCard(Card card) {
		hand.add(card);
	}

	public void removeCard(Card card) {
		hand.remove(card);
	}

	public ArrayList<Card> getCards() {
		return hand;
	}


	public int getHandScore() {
		//this.hand = hand;
		int handScore = 0;
		for (Card c: hand) {
			handScore += c.getCardValue();
		}
	return handScore;
	}
	//in game, create who instances of Hand object - one for player, one for dealer
	//handTotalScore
		//method add card;
}