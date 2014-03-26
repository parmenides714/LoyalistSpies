package spies.ui.swing.panes;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import spies.framework.Card;
import spies.framework.Player;

public class PlayerHandPane extends JPanel
{
	private static final long serialVersionUID = 8091471602836095874L;

	private Player player;

	// private JLabel nameLabel;
	private JLabel suitLabel;
	private JLabel loyaltyLabel;
	private JLabel scoreLabel;

	private CardPane cardPane;

	public PlayerHandPane(Player player)
	{
		this.player = player;

		setLayout(new GridBagLayout());

		GridBagConstraints cons = getDefaultGridBagConstraints();

		suitLabel = new JLabel();
		suitLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		loyaltyLabel = new JLabel();
		loyaltyLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		scoreLabel = new JLabel();
		scoreLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		cardPane = new CardPane();
		cardPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		setBorder(BorderFactory.createTitledBorder(player.getName()));

		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		add(suitLabel, cons);

		cons.gridx = 1;
		cons.gridy = 0;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		add(loyaltyLabel, cons);

		cons.gridx = 2;
		cons.gridy = 0;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		add(scoreLabel, cons);

		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 3;
		add(cardPane, cons);

		updateDisplay();
	}

	private void updateDisplay()
	{
		suitLabel.setText("Suit: " + player.getSuit());
		loyaltyLabel.setText("Loyalty: " + player.getStatus());
		scoreLabel.setText("Score: " + player.getScore());

		cardPane.updateDisplay();
	}

	private GridBagConstraints getDefaultGridBagConstraints()
	{
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.weighty = 0.0;

		return c;
	}

	private class CardPane extends JPanel
	{
		private static final long serialVersionUID = -2443797858160867526L;

		// private List<JButton> cardButtons;

		public CardPane()
		{
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			// cardButtons = new ArrayList<JButton>();
			setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}

		private void updateDisplay()
		{
			removeAll();
			// cardButtons.clear();

			for (Card card : player.getHand())
			{
				add(createButton(card));

			}
		}

		private JButton createButton(Card card)
		{
			JButton button = new JButton();

			button.setText(card.toString());
			// TODO define action listener

			return button;
		}
	}
}
