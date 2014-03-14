package spies.framework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import spies.framework.Card.Suit;
import spies.framework.Card.Value;

public class Deck
{
	private Random random;
	private List<Card> cards;

	private Deck(int size)
	{
		random = new Random(System.nanoTime());
		cards = new ArrayList<Card>(size);
	}

	public void shuffle()
	{
		Collections.shuffle(cards);
		Collections.shuffle(cards);
		Collections.shuffle(cards);

	}

	public static Deck getAces()
	{
		Deck deck = new Deck(4);

		deck.cards.add(new Card(Value.ACE, Suit.HEARTS));
		deck.cards.add(new Card(Value.ACE, Suit.CLUBS));
		deck.cards.add(new Card(Value.ACE, Suit.SPADES));
		deck.cards.add(new Card(Value.ACE, Suit.DIAMONDS));

		deck.shuffle();

		return deck;
	}

	public static Deck getFullDeck()
	{
		Deck deck = new Deck(54);

		for (Suit suit : Suit.values())
		{
			for (Value value : Value.values())
			{
				if (value != Value.JOKER)
				{
					deck.cards.add(new Card(value, suit));
				}
			}
		}

		deck.cards.add(new Card(Value.JOKER, null));
		deck.cards.add(new Card(Value.JOKER, null));

		deck.shuffle();

		return deck;
	}

	public static Deck getDeckNoAces()
	{
		Deck deck = new Deck(50);

		for (Suit suit : Suit.values())
		{
			for (Value value : Value.values())
			{
				if (value != Value.ACE && value != Value.JOKER)
				{
					deck.cards.add(new Card(value, suit));
				}
			}
		}

		deck.cards.add(new Card(Value.JOKER, null));
		deck.cards.add(new Card(Value.JOKER, null));

		deck.shuffle();

		return deck;
	}

	public void deal(Player[] players)
	{
		deal(players, cards.size());
	}

	public void deal(Player[] players, int numCards)
	{
		int currentPlayer = random.nextInt(players.length);
		int pivotPlayer = currentPlayer;

		for (int i = 0; i < numCards; i++)
		{
			if (cards.isEmpty())
				break;

			do
			{
				if (cards.isEmpty())
					break;

				players[currentPlayer].dealCard(cards.remove(cards.size() - 1));
				currentPlayer = (currentPlayer + 1) % players.length;
			} while (currentPlayer != pivotPlayer);
		}

		shuffle();
	}

	@Override
	public String toString()
	{
		return cards.toString();
	}

	public boolean hasCards()
	{
		return !cards.isEmpty();
	}

	public Card drawCard()
	{
		if (hasCards())
		{
			return cards.remove(cards.size() - 1);
		}

		return null;
	}

	public void replaceCard(Card card)
	{
		cards.add(card);
	}
}
