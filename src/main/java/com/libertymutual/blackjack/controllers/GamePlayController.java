package com.libertymutual.blackjack.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.libertymutual.blackjack.models.Card;
import com.libertymutual.blackjack.models.Deck;
import com.libertymutual.blackjack.models.Hand;
import com.libertymutual.blackjack.models.Player;

@Controller // this is a controller that responds to http requests
@RequestMapping({ "/", "/gameplay" }) // all of the URL paths that this controller will handle will start with either
										// of these (added in start)
public class GamePlayController {
	
	private int betAmount;
	private Player user;
	private Player dealer;
	
	
	public GamePlayController() {	
		//create two players
		user = new Player();
		dealer = new Player();
	}

	@GetMapping("") // in the URL; only about INCOMING URL request entered from browser
	public String showIndexPage() {
		System.out.println("Showing the home page.");
		return "gameplay/index"; // path to a file (under the templates folder) to send back to browser
	}

	@GetMapping("pregame")
	public String showPreGamePlayPage() {
		System.out.println("Showing the pregame page.");
		return "gameplay/pregame";
	}

	@GetMapping("game")
	public String showGamePlayPage() {
		System.out.println("Showing the game page.");
		return "gameplay/game";
	}

	@GetMapping("rules") //
	public String showGameRulesPage() {
		System.out.println("Showing the home page.");
		return "gameplay/rules";
	}

	/*
	 * @PostMapping("pregame") public int getBetAmount(int theNumber) { // Spring
	 * puts the value of 'theNumber' from the form into this return theNumber; }
	 */
	// The bet amount on pregame posts to game; this post below picks up that value
	// and adds to model to render
	@PostMapping("bet")
	public String startRound(@RequestParam(name = "betAmount") int betAmount, Model model) {
		this.betAmount = betAmount; //set bet amount;
		model.addAttribute("betAmount", betAmount); // add to model, display on game.html
		
		Deck deck = new Deck(); //Create new shuffled Deck;		
		Hand userHand = new Hand();	 //create new hand
		Card userCard1 = deck.drawCard(); 
		Card userCard2 = deck.drawCard();
		
		userHand.addCard(userCard1); //add card to hand
		userHand.addCard(userCard2);//add card to hand
		user.setHand(userHand); //assign hand to player object
		
		model.addAttribute("userHand", userHand);
		
		return "gameplay/game"; // take user to game page and display bet	
		
	//deal a hand
	}
	
	

//	@PostMapping("hit")
//	public String hitMe() {
//		// deal a card to user
//		// if userHandTotal > 21 -- BUST
//		// remaining money = remaining money - bet
//		// redirect back to "gameplay/pregame" page
//		// if userHand == 21, blackjack - dealer has chance to match
//
//		// if
//		return "gameplay/game"; // take user to game page and display bet
//	}

	// *-----------------------------------------------------logic

	
}


