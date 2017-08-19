package com.libertymutual.blackjack.models;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	
	private ArrayList<Card> deck;
	String[] suit = { "clubs", "spades", "diamonds", "hearts" };
	String[] rank = { "ACE", "2", "3", "4", "5", "6", "7", "8", "9", "10", "JACK", "QUEEN", "KING" };
	private int cardsLeft;

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
	}
	
	public Card drawCard() {	
		//draw new card and assign to drawnCard
		//remove card from deck
		Card drawnCard = deck.get(cardsLeft);
		deck.remove(cardsLeft);
		cardsLeft--;
		return drawnCard;
	}
	
	//New method - added 936AM 8/19
	public int getCardsLeft()
	{
		return cardsLeft;
	}

}
