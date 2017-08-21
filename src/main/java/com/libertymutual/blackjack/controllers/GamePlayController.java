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
		// deck = new Deck();
		// deck.shuffle();
		roundOutcome = "";
	}

	@GetMapping("") // in the URL; only about INCOMING URL request entered from browser
	public String showIndexPage() {
		System.out.println("Showing the home page.");
		return "gameplay/index"; // path to a file (under the templates folder) to send back to browser
	}

	// this mapping resets the initial state of the game.
	// setting these in the constructor won't allow replaying within the same
	// application session
	@GetMapping("pregame")
	public String showPreGamePlayPage() {
		deck = new Deck();
		deck.shuffle();
		wallet = 100; // setting wallet = 100 to allow clean slate for testing.
		//user.getPlayerWallet();
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
	public String endofRound(Model model) {

		if ((deck.getCardsLeft() > 0) && wallet > 0) {
			model.addAttribute("wallet", wallet);
			// maybe betAmount=0 here
			// display current wallet amouunt
			return "gameplay/endofround";
		} else
			return "gameplay/ranout";
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

		// only allow page to display if deck and wallet are NOT empty
		if ((deck.getCardsLeft() > 0) && wallet > 0) {
			model.addAttribute("wallet", wallet);
			model.addAttribute("userHand", userHand);
			model.addAttribute("dealerHand", dealerHand);
			model.addAttribute("roundOutcome", roundOutcome);
			return "gameplay/game";

		} else return "gameplay/ranout";
		
	}

	// @PostMapping("endofround")
	// public String endofRound() {
	// //maybe betAmount=0 here
	// //display current wallet amouunt
	// return "gameplay/endofround";
	// }

	// The bet amount on pregame posts to game; this post below picks up that value
	// and adds to model to render
	// Kicking off the Game

	@PostMapping("bet")
	public String startRound(@RequestParam(name = "betAmount") int betAmount, Model model) {

		// only allow page to display if deck and wallet are NOT empty
		if ((deck.getCardsLeft() > 0) && wallet > 0) {
			//change wallet to user.getPlayerWallet();

			roundOutcome = "";
			// get get amount and display
			this.betAmount = betAmount;
			model.addAttribute("wallet", wallet); // add to model, display on game.html
			model.addAttribute("betAmount", betAmount); // add to model, display on game.html

			// create new user & dealer hand
			userHand = new Hand();
			dealerHand = new Hand();

			// assign hand to player objects
			user.setHand(userHand);
			dealer.setHand(dealerHand);

			//deal cards
			userHand.addCard(deck.drawCard());
			dealerHand.addCard(deck.drawCard());
			userHand.addCard(deck.drawCard());
			dealerHand.addCard(deck.drawCard());
			
			
			model.addAttribute("userHand", userHand);
			model.addAttribute("dealerHand", dealerHand);
			model.addAttribute("roundOutcome", roundOutcome);
	
			return "gameplay/game"; // take user to game page and display bet
		}

		// deck or wallet has runout. direct to ranout page and end game
		// add userHand to model
		model.addAttribute("userHand", userHand);
		model.addAttribute("roundOutcome", roundOutcome);
		model.addAttribute("dealerHand", dealerHand);
		user.setPlayerWallet(0);// reset wallet
		return "gameplay/ranout";

		// //draw cards from deck
		// Card dealerCard1 = deck.drawCard();
		// Card dealerCard2 = deck.drawCard();
	}

	@PostMapping("stay")
	public String stay(Model model) {
		if((deck.getCardsLeft() > 0) && wallet > 0) {
			
			int dealerHandScore = dealerHand.getHandScore();
			int userHandScore = userHand.getHandScore();

			while (dealerHand.getHandScore() < 17) {
				// deck.getCardsLeft();
				dealerHand.addCard(deck.drawCard());
				dealerHandScore = dealerHand.getHandScore();
			}

			if (dealerHandScore > 21) {
				// Dealer bust, user wins
				wallet += betAmount * 2;
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
					wallet += betAmount * 2;
					roundOutcome = "You win!";
				} else if (userHandScore == dealerHandScore) {
					roundOutcome = "Push. You keep your money";
				} else {
					// dealer wins
					wallet -= betAmount;
					roundOutcome = "You lose!";
				}
			}

			betAmount = 0; // reset bet amount to zero
			model.addAttribute("wallet", wallet);
			model.addAttribute("betAmount", betAmount);
			model.addAttribute("userHand", userHand);
			model.addAttribute("dealerHand", dealerHand);
			model.addAttribute("roundOutcome", roundOutcome);
			//model.addAttribute("showDealerSecondCard", );
			// System.out.println("This should be an unreachable part of logic.");
			return "gameplay/outcome";
		}

		// deck or wallet has runout. direct to ranout page and end game
		user.setPlayerWallet(0);// reset wallet
		return "gameplay/ranout";
	}

	@PostMapping("hit")
	public String hitMe(Model model) {
		int userHandScore;

		// only allow page to display if deck and wallet are NOT empty
		if ((deck.getCardsLeft() > 0) && wallet > 0) {
			// draw a card
			userHand.addCard(deck.drawCard());
			userHandScore = userHand.getHandScore();

			// Bust
			if (userHandScore > 21) {
				// user busted
				// direct to outcome page and display round results
				// account for ace method
				wallet -= betAmount;
				roundOutcome = "You lose!";
				model.addAttribute("betAmount", betAmount);
				model.addAttribute("userHand", userHand);
				model.addAttribute("dealerHand", dealerHand);
				model.addAttribute("roundOutcome", roundOutcome);
				model.addAttribute("wallet", wallet);
				return "gameplay/outcome";
			}

			// user has not busted. display gameplay page again with options to hit or stay
			//Getting an error: NullContext for variable Rank Error (line 37)/ 
			//tried duplicating this outside while loop, still doesn't fix
			model.addAttribute("betAmount", betAmount);
			model.addAttribute("wallet", wallet);
			model.addAttribute("userHand", userHand);
			model.addAttribute("dealerHand", dealerHand);
			model.addAttribute("roundOutcome", roundOutcome);
			//model.addAttribute("showDealerSecondCard", );
			return "gameplay/game"; // take user to game page and display bet
		}
		// deck or wallet has runout. direct to ranout page and end game
		user.setPlayerWallet(0);// reset wallet
		return "gameplay/ranout";
	}
}