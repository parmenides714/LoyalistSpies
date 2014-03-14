package spies.framework;

import java.util.ArrayList;
import java.util.List;

import spies.framework.Card.Suit;
import spies.framework.Card.Value;

public class Player
{
	private static final int LOYALIST = 1;
	private static final int TRAITOR = 2;
	private static final int COMPROMISED = 4;

	private String name;
	private Suit suit;
	private List<Card> hand;
	private int status;
	private int currentScore;

	private IOutputDevice outputDevice;
	private IInputDevice inputDevice;

	public Player(String name)
	{
		this.name = name;
		hand = new ArrayList<Card>();
		status = LOYALIST;
		currentScore = 0;
	}

	public String getName()
	{
		return name;
	}

	@Override
	public String toString()
	{
		return name + ":" + suit.toString() + ":" + hand.toString();
	}

	public void setSuit(Card ace)
	{
		suit = ace.getSuit();
	}

	public void setSuit()
	{
		suit = hand.remove(0).getSuit();
	}

	public Suit getSuit()
	{
		return suit;
	}

	public void dealCard(Card card)
	{
		if (card.getValue() == Value.JOKER)
		{
			status = TRAITOR;
		}

		hand.add(card);
	}

	public Card playCard(int index)
	{
		if (index >= hand.size())
			return null;

		return hand.remove(index);
	}

	public boolean isLoyalist()
	{
		return (status & LOYALIST) == LOYALIST;
	}

	public boolean isTraitor()
	{
		return (status & TRAITOR) == TRAITOR;
	}

	public boolean isCompromised()
	{
		return (status & COMPROMISED) == COMPROMISED;
	}

	public void increaseScore(int points)
	{
		currentScore += points;
	}

	public int getScore()
	{
		return currentScore;
	}

	public void showSuit(Player player)
	{
		outputDevice.showSuit(player);
	}

	public void checkForTraitor()
	{
		for (Card card : hand)
		{
			if (card.getValue() == Value.JOKER)
			{
				status = TRAITOR;
			}
		}

	}

	public void checkForCompromised(Suit compromisedSuit)
	{
		if (suit == compromisedSuit && status != TRAITOR)
		{
			status = COMPROMISED;
		}

	}

	public String getStatus()
	{
		if (isLoyalist())
			return "Loyalist";

		if (isCompromised())
			return "Compromised";

		if (isTraitor())
			return "Traitor";

		return null;
	}

	public void setOutputDevice(IOutputDevice device)
	{
		outputDevice = device;
	}

	public void setInputDevice(IInputDevice iInputDevice)
	{
		// iInputDevice.setPlayer(this);
		inputDevice = iInputDevice;
	}

	public int getPlayerChoice(List<String> names)
	{
		outputDevice.showMessage("Choose a player:");
		outputDevice.showPlayers(names);

		return inputDevice.getPlayerChoice();
	}

	public int getCardChoice()
	{
		outputDevice.showMessage("Choose a card to play.");
		outputDevice.showCards(hand);

		return inputDevice.getCardChoice();
	}

	public int getTopOfDeckChoice(List<Card> cards)
	{
		outputDevice.showMessage("Choose a card to put on top of the deck.");
		outputDevice.showCards(cards);

		return inputDevice.getCardChoice();
	}

	public int getDiscardChoice()
	{
		outputDevice.showMessage("Choose a card to discard.");
		outputDevice.showCards(hand);

		return inputDevice.getCardChoice();
	}

	public void displayScore()
	{
		outputDevice.showMessage("Score after the round:");
		outputDevice.showScore();
	}

	public void displayPlayArea(PlayArea playArea)
	{
		outputDevice.showPlayArea(playArea);
	}

	public void revealCard(Card card)
	{
		outputDevice.revealCard(card);
	}

	public void showMessage(String msg)
	{
		outputDevice.showMessage(msg);
	}
}
