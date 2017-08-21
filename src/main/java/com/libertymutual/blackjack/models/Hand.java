package com.libertymutual.blackjack.models;

import java.util.ArrayList;

public class Hand {

	ArrayList<Card> hand;
	Hand userHand;
	Hand dealerHand;
	Card card;
	int handScore;

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
		handScore = 0;
		int aceCount = 0;
		for (Card c: hand) {
			handScore += c.getCardValue();
			if(c.getCardValue() == 11) {
				aceCount++;
			}
			if(handScore > 21 && aceCount > 0) {
				for(int i = 1; i <= aceCount; i++) {
					if(handScore > 21) {
						handScore -= 10;
						aceCount--;
					}
				}
			}
		} return handScore;
	}
}