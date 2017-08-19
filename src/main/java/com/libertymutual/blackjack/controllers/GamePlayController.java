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
	private double wallet = 100;
	private Player user;
	private Player dealer;
	Deck deck;
	Hand userHand;
	Hand dealerHand;
	GamePlay round;

	public GamePlayController() {
		// create two players
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

	@GetMapping("rules")
	public String showGameRulesPage() {
		System.out.println("Showing the home page.");
		return "gameplay/rules";
	}

	// while(wallet > 0) {
	//
	// }

	// The bet amount on pregame posts to game; this post below picks up that value
	// and adds to model to render
	// Kicking off the Game
	@PostMapping("bet")
	public String startRound(@RequestParam(name = "betAmount") int betAmount, Model model) {
		// get get amount and display
		this.betAmount = betAmount;
		wallet = 100;
		// wallet -= betAmount;
		model.addAttribute("wallet", wallet); // add to model, display on game.html
		model.addAttribute("betAmount", betAmount); // add to model, display on game.html

		// Create new shuffled Deck;
		deck = new Deck();
		deck.shuffle();
		// create new user hand
		userHand = new Hand();

		// assign hand to player object
		user.setHand(userHand);

		// add cards to hand
		userHand.addCard(deck.drawCard());
		userHand.addCard(deck.drawCard());

		// add userHand to model
		model.addAttribute("userHand", userHand);

		if (userHand.getHandScore() == 21) {
			if (dealerHand.getHandScore() == 21) {
				wallet += betAmount;
			} else {
				wallet += betAmount * 1.5;
			}
			// payout
			// end round
			// take user to pregame page (for a new round)
		}

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
		model.addAttribute("dealerHand", dealerHand);

		return "gameplay/game"; // take user to game page and display bet

		// deal a hand
	}

	@PostMapping("hit")
	public String hitMe(Model model) {
		int userHandScore;
		// deal a card to user
		// if userHandTotal > 21 -- BUST
		// remaining money = remaining money - bet
		// redirect back to "gameplay/pregame" page
		// if userHand == 21, blackjack - dealer has chance to match

		// if
		userHand.addCard(deck.drawCard());

		userHandScore = userHand.getHandScore();
		// Bust
		if (userHand.getHandScore() > 21) {
			wallet -= betAmount;
		} else if (userHand.getHandScore() == 21) {
			if (dealerHand.getHandScore() == 21) {
				wallet += betAmount;
			} else {
				wallet += betAmount * 1.5;
			}
		}

		model.addAttribute("betAmount", betAmount);
		model.addAttribute("wallet", wallet);
		model.addAttribute("userHand", userHand);
		model.addAttribute("dealerHand", dealerHand);
		return "gameplay/game"; // take user to game page and display bet
	}

	@PostMapping("stay")
	public String stay(Model model) {
		if (true) {
			while (dealerHand.getHandScore() < 17) {
				dealerHand.addCard(deck.drawCard());
			}
		}
		
		if (dealerHand.getHandScore() < userHand.getHandScore()) {
			wallet += betAmount;
		} else if (dealerHand.getHandScore() > 21) {
			wallet += betAmount;
		} else {
			wallet -= betAmount;
		}

		model.addAttribute("betAmount", betAmount);
		model.addAttribute("wallet", wallet);
		model.addAttribute("userHand", userHand);
		model.addAttribute("dealerHand", dealerHand);
		return "gameplay/game"; // take user to game page and display bet
	}

}