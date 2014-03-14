package spies.framework;

import java.util.ArrayList;
import java.util.List;

import spies.framework.Card.Suit;
import spies.framework.Card.Value;

public class Mission
{
	private Card missionCard;
	private List<Card> playedCards;
	private int baseVP = 0;

	public Mission(Card card)
	{
		missionCard = card;
		playedCards = new ArrayList<Card>();
		baseVP = 0;
	}

	public int getCovertModifier()
	{
		if (missionCard.getSuit() == null)
			return 0;

		int modifier = 0;
		switch (missionCard.getSuit())
		{
		case CLUBS:
			modifier = -3;
			break;
		case DIAMONDS:
			break;
		case HEARTS:
			break;
		case SPADES:
			modifier = 1;
			break;
		default:
			break;

		}
		return modifier;
	}

	public int getPointsModifier()
	{
		if (missionCard.getSuit() == null)
			return 0;

		int modifier = 0;
		switch (missionCard.getSuit())
		{
		case CLUBS:
			modifier = 2;
			break;
		case DIAMONDS:
			break;
		case HEARTS:
			modifier = -1;
			break;
		case SPADES:
			break;
		default:
			break;

		}
		return modifier;
	}

	public int getPoints()
	{
		int cardVP = 0;

		switch (missionCard.getValue())
		{
		case TWO:
		case THREE:
		case FOUR:
		case FIVE:
			cardVP = 2;
			break;

		case SIX:
		case SEVEN:
		case EIGHT:
			cardVP = 3;
			break;

		case NINE:
			cardVP = 4;
			break;

		case TEN:
			cardVP = 5;
			break;

		default:
			break;

		}

		return baseVP + cardVP + getPointsModifier();
	}

	public int getCovertValue()
	{
		int base = 0;

		switch (missionCard.getValue())
		{
		case TWO:
		case THREE:
		case FOUR:
		case FIVE:
			base = 50;
			break;

		case SIX:
		case SEVEN:
		case EIGHT:
			base = 40;
			break;

		case NINE:
			base = 35;
			break;

		case TEN:
			base = 30;
			break;

		default:
			break;

		}

		return base + getCovertModifier();
	}

	public void playCard(Card card)
	{
		if (card == null)
			return;

		playedCards.add(card);
	}

	public boolean isSuccessful()
	{
		int total = getPlayedTotal();

		return total <= getCovertValue();
	}

	public int getPlayedTotal()
	{
		int total = 0;
		for (Card card : playedCards)
		{
			total += card.getValue().ordinal();
		}
		return total;
	}

	public Suit getSuit()
	{
		return missionCard.getSuit();
	}

	public Value getValue()
	{
		return missionCard.getValue();
	}

	@Override
	public String toString()
	{
		StringBuffer outString = new StringBuffer();

		outString.append("Card: ");
		outString.append(missionCard);
		outString.append("\n");

		outString.append("Covert Value: ");
		outString.append(getCovertValue());
		outString.append("\n");

		outString.append("Points: ");
		outString.append(getPoints());
		outString.append("\n");

		outString.append("Number of cards played: ");
		outString.append(playedCards.size());

		return outString.toString();
	}

	public List<Card> getPlayedCards()
	{
		return playedCards;
	}

	public void increaseBaseVP(int VP)
	{
		baseVP += VP;
	}
}
