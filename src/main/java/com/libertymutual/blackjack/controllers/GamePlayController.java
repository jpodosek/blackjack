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
import com.libertymutual.blackjack.models.GamePlay;
import com.libertymutual.blackjack.models.Hand;
import com.libertymutual.blackjack.models.Player;

@Controller // this is a controller that responds to http requests
@RequestMapping({ "/", "/gameplay" }) // all of the URL paths that this controller will handle will start with either
										// of these (added in start)
public class GamePlayController {

	private double betAmount;
	private double wallet;
	private Player user;
	private Player dealer;
	Deck deck;
	Hand userHand;
	Hand dealerHand;
	GamePlay round;
	String roundOutcome;

	public GamePlayController() {
		// create two players
		user = new Player();
		dealer = new Player();
		// Create new shuffled Deck; //moved this to constructor - should only happen
		// once
		deck = new Deck();
		deck.shuffle();
		roundOutcome = "";
		wallet = 100;
	}

	@GetMapping("") // in the URL; only about INCOMING URL request entered from browser
	public String showIndexPage() {
		System.out.println("Showing the home page.");
		return "gameplay/index"; // path to a file (under the templates folder) to send back to browser
	}

	@GetMapping("pregame")
	public String showPreGamePlayPage() {
		// maybe set betAmount=0 here
		System.out.println("Showing the pregame page.");
		return "gameplay/pregame";
	}

	@GetMapping("game")
	public String showGamePlayPage() {
		System.out.println("Showing the game page.");
		return "gameplay/game";
	}

	@GetMapping("rules")
	public String showGameRulesPage() {
		System.out.println("Showing the home page.");
		return "gameplay/rules";
	}

	@PostMapping("endofround")
	public String endofRound() {
		// maybe betAmount=0 here
		// display current wallet amouunt
		return "gameplay/endofround";
	}
	
	@PostMapping("outcome")
	public String outCome(Model model) {
		
		// maybe betAmount=0 here
		// display current wallet amouunt
		model.addAttribute("wallet", wallet);
		model.addAttribute("userHand", userHand);
		model.addAttribute("dealerHand", dealerHand);
		model.addAttribute("roundOutcome", roundOutcome);
		
		return "gameplay/outcome";
	}
	
	@PostMapping("game")
	public String gamePlayPage(Model model) {
		System.out.println("Showing the game page.");
		
		model.addAttribute("wallet", wallet);
		model.addAttribute("userHand", userHand);
		model.addAttribute("dealerHand", dealerHand);
		model.addAttribute("roundOutcome", roundOutcome);
		return "gameplay/game";
	}

	// @PostMapping("endofround")
	// public String endofRound() {
	// //maybe betAmount=0 here
	// //display current wallet amouunt
	// return "gameplay/endofround";
	// }

	// while(wallet > 0) {
	//
	// }

	// The bet amount on pregame posts to game; this post below picks up that value
	// and adds to model to render
	// Kicking off the Game
	
	@PostMapping("bet")
	public String startRound(@RequestParam(name = "betAmount") int betAmount, Model model) {
		roundOutcome = "";
		// get get amount and display
		this.betAmount = betAmount;
		// wallet = 100;
		model.addAttribute("wallet", wallet); // add to model, display on game.html
		model.addAttribute("betAmount", betAmount); // add to model, display on game.html

		// create new user hand
		userHand = new Hand();

		// assign hand to player object
		user.setHand(userHand);

		// add cards to hand
		userHand.addCard(deck.drawCard());
		userHand.addCard(deck.drawCard());

		// add userHand to model
		model.addAttribute("userHand", userHand);

		// create new dealer hand
		dealerHand = new Hand();

		// //draw cards from deck
		// Card dealerCard1 = deck.drawCard();
		// Card dealerCard2 = deck.drawCard();

		// add cards to hand
		dealerHand.addCard(deck.drawCard());
		dealerHand.addCard(deck.drawCard());

		// assign hand to player object
		dealer.setHand(dealerHand);

		// add userHand to model
		model.addAttribute("roundOutcome", roundOutcome);
		model.addAttribute("dealerHand", dealerHand);

		return "gameplay/game"; // take user to game page and display bet

	}

	@PostMapping("stay")
	public String stay(Model model) {

		int dealerHandScore = dealerHand.getHandScore();
		int userHandScore = userHand.getHandScore();

		while (dealerHand.getHandScore() < 17) {
			// deck.getCardsLeft();
			dealerHand.addCard(deck.drawCard());
			dealerHandScore = dealerHand.getHandScore();
		}

		if (dealerHandScore > 21) {
			// Dealer bust, user wins
			wallet += betAmount;
			roundOutcome = "You win!";

		} else if (dealerHandScore == 21) {
			if (userHandScore == 21) {
				// Tie, reset betAmount to zero
				roundOutcome = "Push. You keep your money.";
			} else {
				// user has less,
				// dealer wins
				wallet -= betAmount;
				roundOutcome = "You lose!";
			}
		} else if (dealerHandScore < 21) {
			// user has higher score than dealer
			if (userHandScore > dealerHandScore) {
				// user wins
				wallet += betAmount;
				roundOutcome = "You win!";
			} else if(userHandScore == dealerHandScore) {
				roundOutcome = "Push. You keep your money";
			} else {
				// dealer wins
				wallet -= betAmount;
				roundOutcome = "You lose!";
			}
		}

		// roundOutcome = "This should be an unreachable part of logic.";
		System.out.println("This should be an unreachable part of logic.");

		betAmount = 0; // reset bet amount to zero
		model.addAttribute("wallet", wallet);
		model.addAttribute("betAmount", betAmount);
		model.addAttribute("userHand", userHand);
		model.addAttribute("dealerHand", dealerHand);
		model.addAttribute("roundOutcome", roundOutcome);
		// System.out.println("This should be an unreachable part of logic.");
		return "gameplay/outcome";

	}

	@PostMapping("hit")
	public String hitMe(Model model) {
		int userHandScore;
		int dealerHandScore = dealerHand.getHandScore();
		//draw a card
		userHand.addCard(deck.drawCard());
		
		userHandScore = userHand.getHandScore();

		// Bust
		if (userHandScore > 21) {
			// user busted
			// account for ace method
			wallet -= betAmount;
			roundOutcome = "You lose!";
			model.addAttribute("roundOutcome", roundOutcome);
			model.addAttribute("wallet", wallet);
			return "gameplay/outcome";
		}
		
		
		model.addAttribute("betAmount", betAmount);
		model.addAttribute("wallet", wallet);
		model.addAttribute("userHand", userHand);
		model.addAttribute("dealerHand", dealerHand);
		model.addAttribute("roundOutcome", roundOutcome);
		return "gameplay/game"; // take user to game page and display bet
	}

}