package com.libertymutual.blackjack.models;

import java.util.ArrayList;

public class Deck {
	private ArrayList<Card> deck;
	String[] suit = { "clubs", "spades", "diamonds", "hearts" };
	String[] rank = { "ACE", "2", "3", "4", "5", "6", "7", "8", "9", "10", "JACK", "QUEEN", "KING" };
	// private Card[] deck;
	// private int cardsLeft;
	// Shuffle method
	// drawSingleCard
	// dealInitialcards

	// Constructor
	public Deck() {
		// use a list instead - do list.add
		deck = new ArrayList<Card>();
		for (String s : suit)
			for (String r : rank)
				deck.add(new Card(r, s));
		//System.out.println(Arrays.toString(deck));
		System.out.println(deck);
		
		//printThis();
	}
	
//	public Card drawCard() {
//		
//	}


//	public void printThis() {
//		for (Card c : deck) {
//			System.out.println(c);
//		}
//	}

	/*
	 * maybe a simpler way to do it (or understand) deck = new Card[52]; int index =
	 * 0; // How many cards have been created so far. for (int suit = 0; suit <= 3;
	 * suit+=1) { for (int rank = 1; rank <= 13; rank+=1) { deck[index+=1] = new
	 * Card(rank,suit);
	 * 
	 * } //System.out.println(deck[index]); } }
	 */
	// cardsUsed = 0;

	// Create an unshuffled deck of cards.

	// create a stack of card objects
	// loop to create four iterations of loop below for suits
	// loop to create 13 cards 1-13 value

	// }
	// interogate card - 2 methods - whats the value, what's the suit?
	// create the deck
	// Draw a card

	// Methods

	// DealCard()

}
