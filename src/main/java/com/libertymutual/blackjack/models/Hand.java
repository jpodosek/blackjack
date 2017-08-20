package com.libertymutual.blackjack.models;

import java.util.ArrayList;

public class Hand {

	ArrayList<Card> hand;
	Hand userHand;
	Hand dealerHand;
	Card card;
	private int cardRank;

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
			//a way to handle the ace????
//			if(handScore > 21) {
//				aceCheck();
//			}
		} return handScore;
	}
	
	
	public int aceCheck() {
		for(Card card : hand) {
			if(card.getCardValue() == 11) {
				card.setCardRank(getHandScore() - 10);
			}
		} 
		return getHandScore();
	}
//	public int aceCheck() {
//			//check to see if hand has an ace, if yes then equals 1, else bust
//			ArrayList<Card> cardsInHand = userHand.getCards();
//			int handLength = cardsInHand.size();
//			for(int i = 0; i < handLength; i+= 1) {
//				//traverse the hand, extract the card values and check to see if there's an ace
//				Card card = cardsInHand.remove(i);
//				if(card.getCardRank() == 11) {
//					setCardRank(1);
//					return cardRank;
//				}
//			} return cardRank;
//	}
	//in game, create who instances of Hand object - one for player, one for dealer
	//handTotalScore
		//method add card;

	public void setCardValue(int cardRank) {
		this.cardRank = cardRank;
	}
}