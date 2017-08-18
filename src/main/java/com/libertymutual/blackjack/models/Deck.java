package com.libertymutual.blackjack.models;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private ArrayList<Card> deck;
	String[] suit = { "clubs", "spades", "diamonds", "hearts" };
	String[] rank = { "ACE", "2", "3", "4", "5", "6", "7", "8", "9", "10", "JACK", "QUEEN", "KING" };
	// private Card[] deck;
	private int cardsLeft;
	// Shuffle method
	// drawSingleCard
	// dealInitialcards

	// Constructor
	public Deck() {
		deck = new ArrayList<Card>();
		//cardsLeft = deck.size() - 1;
		cardsLeft = 52 - 1;

		createDeck();
		System.out.println(deck);
		shuffle();
		System.out.println(deck);
		drawCard();
		System.out.println(deck);
	}
	
	public void createDeck() {
		for (String s : suit)
			for (String r : rank)
				deck.add(new Card(r, s));
	}
	
	public void shuffle() {
		Random random = new Random();
		for (int i = deck.size() - 1; i >= 0; i--) {
		    int j = random.nextInt(i + 1);

		    /* swap cards i,j */
		    Card card = deck.get(i);
		    deck.set(i, deck.get(j));
		    deck.set(j, card);
		}
//		System.out.print(deck);
	}
	
	public Card drawCard() {	
		//draw new card and assign to drawnCard
		//remove card from deck
		Card drawnCard = deck.get(cardsLeft);
		deck.remove(cardsLeft);
		cardsLeft--;
		return drawnCard;
	}

	/*
	 * maybe a simpler way to do it (or understand) deck = new Card[52]; int index =
	 * 0; // How many cards have been created so far. for (int suit = 0; suit <= 3;
	 * suit+=1) { for (int rank = 1; rank <= 13; rank+=1) { deck[index+=1] = new
	 * Card(rank,suit);
	 * 
	 * } //System.out.println(deck[index]); } }
	 */
	// cardsUsed = 0;

	// }
	// interogate card - 2 methods - whats the value, what's the suit?

	// Methods

	// DealCard()

}
