package spies.ui.swing;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import spies.ui.swing.panes.PlayAreaPane;
import spies.ui.swing.panes.PlayerHandPane;
import spies.ui.swing.panes.PlayerInfoPane;
import spies.ui.swing.panes.StatusPane;

public class GameView extends JFrame
{
	private static final long serialVersionUID = -5478478422519545132L;

	private StatusPane status;
	private PlayAreaPane playArea;
	private PlayerHandPane playerHand;
	private PlayerInfoPane playerInfo;

	public GameView()
	{
		super("Loyalist Spies");

		status = new StatusPane();
		playArea = new PlayAreaPane();
		playerHand = new PlayerHandPane();
		playerInfo = new PlayerInfoPane();

		Container pane = getContentPane();
		pane.setLayout(new GridBagLayout());

		// Status in top left, 1x3
		GridBagConstraints cons = getDefaultGridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridheight = 1;
		cons.gridwidth = 3;
		pane.add(status, cons);

		// Play Area middle left, 2x3
		cons = getDefaultGridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridheight = 2;
		cons.gridwidth = 3;
		pane.add(playArea, cons);

		// Player Info far right, 3x2
		cons = getDefaultGridBagConstraints();
		cons.gridx = 3;
		cons.gridy = 0;
		cons.gridheight = 3;
		cons.gridwidth = 2;
		pane.add(playerInfo, cons);

		// Hand area bottom, 2x5
		cons = getDefaultGridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 3;
		cons.gridheight = 2;
		cons.gridwidth = 5;
		pane.add(playerHand, cons);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	private GridBagConstraints getDefaultGridBagConstraints()
	{
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.weighty = 0.0;

		return c;
	}

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run()
			{
				new GameView();
			}
		});
	}
}
