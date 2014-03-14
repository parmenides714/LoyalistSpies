package spies.framework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import spies.framework.Card.Suit;

public class PlayArea
{
	private static final int TRAITOR_BONUS_POINTS = 10;
	private static final int ONE_POINT = 1;
	public static final int PLAYER_COUNT = 4;
	private static final int CARDS_PER_MISSION = 3;
	private static final int QUEEN_DRAW = 3;

	private Mission currentMission;

	private List<Mission> successfulMissions;
	private List<Mission> failedMissions;

	private Player[] players;
	private Deck deck;
	private int currentPlayer;

	public PlayArea()
	{
		successfulMissions = new ArrayList<Mission>();
		failedMissions = new ArrayList<Mission>();
		currentPlayer = 0;
	}

	public void startNewMission(Card card, Player activePlayer)
	{
		if (card.isFaceCard())
		{
			switch (card.getValue())
			{
			case KING:
				activePlayer.revealCard(card);
				activePlayer.showMessage("Starting mission with +2 VP Bonus");
				startNewMission(deck.drawCard(), activePlayer);
				if (currentMission != null)
				{
					currentMission.increaseBaseVP(2);
				}
				break;
			case QUEEN:
				activePlayer.revealCard(card);
				reorderTopThree(activePlayer);
				currentMission = null;
				break;
			case JACK:
				activePlayer.revealCard(card);
				peekAtSuit(activePlayer, card);
				currentMission = null;
				break;
			case JOKER:
				break;
			default:
				break;
			}
		} else
		{
			currentMission = new Mission(card);
		}
	}

	private void peekAtSuit(Player activePlayer, Card jack)
	{
		activePlayer.showMessage("Peek at Suit");
		int playerChoice = activePlayer.getPlayerChoice(getPlayerNames());

		activePlayer.showSuit(players[playerChoice]);

		if (players[playerChoice].getSuit() == jack.getSuit())
		{
			players[playerChoice].showSuit(activePlayer);
			// TODO reveal playerChoice's suit to the other two players.
		}
	}

	private void reorderTopThree(Player activePlayer)
	{
		activePlayer.showMessage("Reorder Top Three");
		List<Card> cardList = new ArrayList<Card>();

		for (int i = 0; i < QUEEN_DRAW; i++)
		{
			cardList.add(deck.drawCard());
		}

		while (!cardList.isEmpty())
		{
			int cardIndex = activePlayer.getTopOfDeckChoice(cardList);
			deck.replaceCard(cardList.remove(cardIndex));
		}

	}

	public void playCard(Card card)
	{
		currentMission.playCard(card);
	}

	public void finishMission()
	{
		if (currentMission == null)
			return;

		if (currentMission.isSuccessful())
		{
			System.out.println("The Mission was successful.");
			successfulMissions.add(currentMission);
			executeSuccessAction();
		} else
		{
			System.out.println("The Mission failed.");
			failedMissions.add(currentMission);
			executeFailedAction();
		}

		System.out.println(currentMission.getPlayedCards());
		System.out.println("Total: " + currentMission.getPlayedTotal());
		System.out.println();

		executeGeneralAction();

		currentMission = null;
	}

	private void executeGeneralAction()
	{
		switch (currentMission.getSuit())
		{
		case SPADES:
			deck.deal(players, 1);
			break;
		default:
			break;
		}

	}

	private void executeFailedAction()
	{
		switch (currentMission.getSuit())
		{
		case CLUBS:
			deck.deal(players, 1);
			break;
		case HEARTS:
			for (Player player : players)
			{
				discardACard(player);
			}
			break;
		default:
			break;
		}

	}

	private void discardACard(Player player)
	{
		int cardChoice = player.getDiscardChoice();
		player.playCard(cardChoice);
	}

	private void executeSuccessAction()
	{
		switch (currentMission.getSuit())
		{
		case DIAMONDS:
			deck.deal(players, 2);
			break;
		default:
			break;
		}

	}

	@Override
	public String toString()
	{
		StringBuffer outString = new StringBuffer();

		outString.append("Current Mission: ");
		outString.append("\n");

		String missionString = currentMission.toString();
		missionString = missionString.replaceAll("\n", "\n\t");

		outString.append("\t");
		outString.append(missionString);
		outString.append("\n");
		outString.append("Successful Missions: ");
		outString.append(getSuccessfulMissionCount());
		outString.append("\n");
		outString.append("Failed Missions: ");
		outString.append(getFailedMissionCount());

		return outString.toString();
	}

	public int getSuccessfulMissionCount()
	{
		return successfulMissions.size();
	}

	public int getTotalSuccessfulPoints()
	{
		int total = 0;
		for (Mission mission : successfulMissions)
		{
			total += mission.getPoints();
		}

		return total;
	}

	public int getFailedMissionCount()
	{
		return failedMissions.size();
	}

	public int getFailedMissionCount(Suit suit)
	{
		int count = 0;

		for (Mission mission : failedMissions)
		{
			if (mission.getSuit() == suit)
			{
				count++;
			}
		}

		return count;
	}

	public int getSuccessfulMissionCount(Suit suit)
	{
		int count = 0;

		for (Mission mission : successfulMissions)
		{
			if (mission.getSuit() == suit)
			{
				count++;
			}
		}

		return count;
	}

	public int getMissionCount(Suit suit)
	{
		return getFailedMissionCount(suit) + getSuccessfulMissionCount(suit);
	}

	public boolean moreMissionsFailed()
	{
		return getFailedMissionCount() > getSuccessfulMissionCount();
	}

	public Suit getCompromisedSuit()
	{
		int[] suitCount = new int[Suit.values().length];
		Arrays.fill(suitCount, 0);

		for (Mission mission : failedMissions)
		{
			suitCount[mission.getSuit().ordinal()]++;
		}

		for (Mission mission : successfulMissions)
		{
			suitCount[mission.getSuit().ordinal()]++;
		}

		Suit compromised = null;
		int max = -1;

		for (int i = 0; i < suitCount.length; i++)
		{
			if (suitCount[i] > max)
			{
				compromised = Suit.values()[i];
				max = suitCount[i];
			}
		}

		return compromised;
	}

	public void initializePlayers(IInputDevice[] devices)
	{
		players = new Player[PLAYER_COUNT];

		for (int i = 0; i < PLAYER_COUNT; i++)
		{
			String name = devices[i].getPlayerName();
			players[i] = new Player(name);
			players[i].setInputDevice(devices[i]);
		}

	}

	public void dealAces()
	{
		Deck aces = Deck.getAces();

		aces.deal(players);

		for (Player player : players)
		{
			player.setSuit();
		}
	}

	public void initializeDeck()
	{
		deck = Deck.getDeckNoAces();

		deck.deal(players, 5);
	}

	public void startPlayLoop()
	{
		currentPlayer = 0;

		while (deck.hasCards())
		{
			startNewMission();

			playersPlayMission();

			finishMission();
		}
	}

	private void playersPlayMission()
	{
		if (currentMission == null)
			return;

		int playerIndex = currentPlayer;
		do
		{
			players[playerIndex].displayPlayArea(this);

			int cardIndex = players[playerIndex].getCardChoice();

			playCard(players[playerIndex].playCard(cardIndex));

			playerIndex = (playerIndex + 1) % PLAYER_COUNT;
		} while (playerIndex != currentPlayer);

	}

	private void startNewMission()
	{
		currentPlayer = (currentPlayer + 1) % PLAYER_COUNT;

		Card nextMission = deck.drawCard();
		startNewMission(nextMission, players[currentPlayer]);

		if (currentMission == null)
			return;

		for (int i = 0; i < CARDS_PER_MISSION; i++)
		{
			playCard(deck.drawCard());
		}
	}

	public Player[] getPlayers()
	{
		return players;
	}

	public List<String> getPlayerNames()
	{
		List<String> names = new ArrayList<String>();
		for (Player player : players)
		{
			names.add(player.getName());
		}
		return names;
	}

	public void calculateScores()
	{
		// Find the traitors and compromised players
		Suit suit = getCompromisedSuit();
		for (Player player : players)
		{
			player.checkForTraitor();
			player.checkForCompromised(suit);
		}

		// Process all Successful Missions
		for (Mission mission : successfulMissions)
		{
			for (Player player : players)
			{
				// If the player is a Loyalist, or if they are compromised and
				// of the same suit as the mission
				// then they gets points equal to the mission's VP.
				if (player.isLoyalist() || (player.isCompromised() && player.getSuit() == mission.getSuit()))
				{
					player.increaseScore(mission.getPoints());
				}

				// If the player is of the same suit as the mission, but not
				// compromised, they get 1 bonus point
				if (!player.isCompromised() && player.getSuit() == mission.getSuit())
				{
					player.increaseScore(ONE_POINT);
				}
			}
		}

		for (Mission mission : failedMissions)
		{
			for (Player player : players)
			{
				// Traitors get 1 point for every failed mission
				if (player.isTraitor())
				{
					player.increaseScore(ONE_POINT);
				}

				// Compromised players get the VP of all missions of their suit
				if (player.isCompromised() && player.getSuit() == mission.getSuit())
				{
					player.increaseScore(mission.getPoints());
				} else if (player.getSuit() == mission.getSuit())
				// All non-compromised players get 1 bonus point for every
				// mission of their suit.
				{
					player.increaseScore(ONE_POINT);
				}
			}
		}

		// If more missions are failed, the traitors get bonues points
		if (moreMissionsFailed())
		{
			for (Player player : players)
			{
				if (player.isTraitor())
				{
					player.increaseScore(TRAITOR_BONUS_POINTS);
				}
			}
		}

		for (Player player : players)
		{
			player.displayScore();

		}
	}
}
