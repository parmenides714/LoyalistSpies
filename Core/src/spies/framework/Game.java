package spies.framework;

import java.util.List;
import java.util.Scanner;

public class Game
{
	private Scanner scan;

	private PlayArea playArea;

	public Game()
	{
		scan = new Scanner(System.in);
		playArea = new PlayArea();
	}

	public static void main(String[] args)
	{
		new Game().launch();
	}

	public void launch()
	{
		PlayerInputDevice[] devices = setupInputDevices();
		playArea.initializePlayers(devices);

		setupOutputDevices();

		playArea.dealAces();
		playArea.initializeDeck();

		playArea.startPlayLoop();

		playArea.calculateScores();
	}

	private PlayerInputDevice[] setupInputDevices()
	{
		PlayerInputDevice[] devices = new PlayerInputDevice[PlayArea.PLAYER_COUNT];

		for (int i = 0; i < devices.length; i++)
		{
			devices[i] = new PlayerInputDevice();
		}

		return devices;
	}

	private void setupOutputDevices()
	{
		for (Player player : playArea.getPlayers())
		{
			PlayerOutputDevice device = new PlayerOutputDevice(player);
			player.setOutputDevice(device);
		}
	}

	private class PlayerOutputDevice implements IOutputDevice
	{
		private Player player;

		private PlayerOutputDevice(Player player)
		{
			this.player = player;
		}

		@Override
		public void showSuit(Player targetPlayer)
		{
			System.out.println(player.getName() + ":: " + targetPlayer.getName() + " is showing you their suit: "
					+ targetPlayer.getSuit());
		}

		@Override
		public void showMessage(String msg)
		{
			StringBuffer outBuf = new StringBuffer();
			if (player != null)
			{
				outBuf.append(player.getName());
				outBuf.append(":");
			} else
			{
				outBuf.append("New Player:");
			}
			outBuf.append(msg);

			System.out.println(outBuf.toString());

		}

		@Override
		public void showCards(List<Card> cards)
		{
			StringBuffer outBuf = new StringBuffer();
			if (player != null)
			{
				outBuf.append(player.getName());
				outBuf.append(":");
			} else
			{
				outBuf.append("New Player:");
			}
			outBuf.append(cards.toString());

			System.out.println(outBuf.toString());
		}

		@Override
		public void showPlayers(List<String> names)
		{
			StringBuffer outBuf = new StringBuffer();
			if (player != null)
			{
				outBuf.append(player.getName());
				outBuf.append(":");
			} else
			{
				outBuf.append("New Player:");
			}
			outBuf.append(names.toString());

			System.out.println(outBuf.toString());
		}

		@Override
		public void showScore()
		{
			StringBuffer outBuf = new StringBuffer();
			if (player != null)
			{
				outBuf.append(player.getName());
				outBuf.append(":");
				outBuf.append(player.getStatus());
				outBuf.append(":");
				outBuf.append(player.getScore());
			} else
			{
				outBuf.append("No Player defined...");
			}

			System.out.println(outBuf.toString());
		}

		@Override
		public void showPlayArea(PlayArea playArea)
		{
			System.out.println(player.getName() + "'s turn.");
			System.out.println(playArea);
		}

		@Override
		public void revealCard(Card card)
		{
			StringBuffer outBuf = new StringBuffer();
			if (player != null)
			{
				outBuf.append(player.getName());
				outBuf.append(":");
			} else
			{
				outBuf.append("New Player:");
			}

			outBuf.append("You revealed the ");
			outBuf.append(card);
			System.out.println(outBuf.toString());
		}
	}

	private class PlayerInputDevice implements IInputDevice
	{
		// private Player player;

		@Override
		public String getPlayerName()
		{
			System.out.println("Enter Player's name: ");
			String name = scan.nextLine();
			return name;
		}

		@Override
		public int getCardChoice()
		{
			String choice = scan.nextLine();
			int cardIndex = Integer.parseInt(choice);

			return cardIndex;
		}

		@Override
		public int getPlayerChoice()
		{
			String choice = scan.nextLine();
			int playerIndex = Integer.parseInt(choice);

			return playerIndex;
		}

		// @Override
		// public void setPlayer(Player player)
		// {
		// this.player = player;
		// }

	}
}
