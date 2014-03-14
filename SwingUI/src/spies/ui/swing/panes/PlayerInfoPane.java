package spies.ui.swing.panes;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlayerInfoPane extends JPanel
{

	private static final long serialVersionUID = -1064379576638778325L;

	public PlayerInfoPane()
	{
		JLabel label = new JLabel("Player Info Pane");
		add(label);
	}
}
