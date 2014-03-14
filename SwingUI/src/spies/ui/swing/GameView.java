package spies.ui.swing;

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

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
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
