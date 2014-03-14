package spies.framework;

public class Card
{
	enum Suit {
		CLUBS, DIAMONDS, SPADES, HEARTS
	};

	enum Value {
		JOKER, ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
	};

	private Suit suit;
	private Value value;

	public Suit getSuit()
	{
		return suit;
	}

	public Value getValue()
	{
		return value;
	}

	public Card(Value value, Suit suit)
	{
		this.value = value;
		this.suit = suit;

		if (value == Value.JOKER)
		{
			suit = null;
		}
	}

	@Override
	public String toString()
	{
		if (value == Value.JOKER)
		{
			return "JOKER";
		}

		return value.toString() + " OF " + suit.toString();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null || !(obj instanceof Card))
		{
			return false;
		}

		Card card = (Card) obj;

		if (this.value == Value.JOKER && card.value == Value.JOKER)
		{
			return true;
		}

		return this.value == card.value && this.suit == card.suit;
	}

	public boolean isFaceCard()
	{
		return (value == Value.JACK || value == Value.QUEEN || value == Value.KING || value == Value.JOKER);
	}
}
