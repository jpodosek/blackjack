package com.libertymutual.blackjack.models;

public class Player {
	Hand currentHand;
	private int wallet;
	private Hand hand;
	
	public Player() {
		wallet = 100;
		hand = new Hand();
	}
	
	public void setHand(Hand currentHand) {
		this.currentHand = currentHand;
	}
	
//	public void playerWallet() {
//		//incremented when you win
//		//reduce when you lose
//		//as long as you have money you can hit, you can stay
//		}
	
	public int getPlayerWallet() {
		return wallet;
	}
	
	public int setPlayerWallet(int betAmount) {
		//if you lost, wallet - losingAmount;
		//if you won, wallet + winningAmount;
		return wallet;
	}
	
	
	
	//getHand() {}
}
