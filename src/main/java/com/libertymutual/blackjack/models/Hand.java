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
		for (Card c: hand) {
			if(handScore <= 21) {
				handScore += c.getCardValue();
			} else if(handScore > 21) {
				//checking to see if there's an ace
				if(aceCheck()) {
					changeAceValue();
					handScore += c.getCardValue();
				}
			}
		} return handScore;
	}
	
	public void changeAceValue() {
		int sizeOfHand = hand.size();
		for(int i = 0; i < sizeOfHand; i += 1) {
			card = hand.remove(i); //removing the card w/ value of 11
			Card temp = card; //storing the card into temp 
//			int newAceValue = 1;
			temp.setCardRank(1); //replacing 11 with value of 1
			hand.add(temp); //adding temp card w/ value 1 back to hand
		}
	}
//		for(Card card : hand) {
//			//traverse the length of the hand, find a card w/ initial value of 11
//			if(card.getCardValue() == 11) {
//				//card.setCardRank(getHandScore() - 10);
//			}
//		} 
//		return handScore;
	//}
	
	public boolean aceCheck() {
		boolean ace = false;
		int currentHand = hand.size();
		for(int i = 0; i < currentHand; i += 1) {
			card = hand.get(i);
			if(card.getCardValue() == 11) {
				ace = true;
			}
		} return ace;
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
}