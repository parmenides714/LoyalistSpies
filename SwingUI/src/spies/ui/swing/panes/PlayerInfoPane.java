package spies.ui.swing.panes;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import spies.framework.Player;

public class PlayerInfoPane extends JPanel
{

	private static final long serialVersionUID = -1064379576638778325L;

	private Player[] players;
	private PlayerInfo[] playerInfo;

	public PlayerInfoPane(Player[] players)
	{
		this.players = players;
		playerInfo = new PlayerInfo[players.length];

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		initializePlayerInfo();

		for (PlayerInfo info : playerInfo)
		{
			add(info);
		}

		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	}

	private void initializePlayerInfo()
	{
		for (int i = 0; i < players.length; i++)
		{
			playerInfo[i] = new PlayerInfo(players[i]);
		}

	}

	private class PlayerInfo extends JPanel
	{

		private static final long serialVersionUID = 2030585862598354251L;

		private Player player;

		private JLabel nameLabel;
		private JLabel handLabel;
		private JLabel suitLabel;
		private JLabel statusLabel;

		private PlayerInfo(Player player)
		{
			this.player = player;

			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

			nameLabel = new JLabel();
			handLabel = new JLabel();
			suitLabel = new JLabel();
			statusLabel = new JLabel();

			add(nameLabel);
			add(handLabel);
			add(suitLabel);
			add(statusLabel);

			updateText();

			setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		}

		private void updateText()
		{
			nameLabel.setText(getNameText());
			handLabel.setText(getHandText());
			suitLabel.setText(getSuitText());
			statusLabel.setText(getStatusText());

		}

		private String getStatusText()
		{
			if (isComprised())
			{
				return "Compromised";
			} else if (isStatusKnown())
			{
				return player.getStatus().toString();
			} else
			{
				return "Status Unknown";
			}
		}

		private boolean isStatusKnown()
		{
			// TODO Auto-generated method stub
			return false;
		}

		private boolean isComprised()
		{
			// TODO Auto-generated method stub
			return false;
		}

		private String getSuitText()
		{
			if (knowsSuit())
			{
				return player.getSuit().toString();
			} else
			{
				return "Suit Unknown";
			}
		}

		private boolean knowsSuit()
		{
			// TODO Auto-generated method stub
			return false;
		}

		private String getHandText()
		{
			StringBuffer sbHand = new StringBuffer();

			int handSize = player.getHandSize();
			sbHand.append(handSize);
			sbHand.append(handSize == 1 ? " card" : " cards");

			return sbHand.toString();
		}

		private String getNameText()
		{
			StringBuffer sbName = new StringBuffer();

			sbName.append(player.getName());
			sbName.append(": ");
			sbName.append(player.getScore());

			return sbName.toString();
		}
	}
}
