package spies.ui.swing.panes;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import spies.framework.Mission;

public class PlayAreaPane extends JPanel
{

	private static final long serialVersionUID = 5815414632783896101L;

	private Mission mission;

	private JLabel cardLabel;
	private JLabel covertLabel;
	private JLabel vpLabel;
	private JLabel effectLabel;
	private JLabel messageLabel;

	public PlayAreaPane()
	{
		setLayout(new GridBagLayout());

		GridBagConstraints cons = getDefaultGridBagConstraints();
		cardLabel = new JLabel();
		covertLabel = new JLabel();
		vpLabel = new JLabel();
		effectLabel = new JLabel();
		messageLabel = new JLabel();

		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridheight = 3;
		cons.gridwidth = 1;
		add(cardLabel, cons);
		cardLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

		cons.gridx = 1;
		cons.gridy = 0;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		add(covertLabel, cons);

		cons.gridx = 1;
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		add(vpLabel, cons);

		cons.gridx = 1;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		add(effectLabel, cons);

		cons.gridx = 0;
		cons.gridy = 3;
		cons.gridheight = 1;
		cons.gridwidth = 2;
		add(messageLabel, cons);

		updateDisplay();
	}

	private GridBagConstraints getDefaultGridBagConstraints()
	{
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.weighty = 0.0;

		return c;
	}

	private void updateDisplay()
	{
		if (mission == null)
		{
			cardLabel.setText("No Card");
			covertLabel.setText("Covert Value:");
			vpLabel.setText("VP Value:");
			effectLabel.setText("Card Effect");
			messageLabel.setText("Game Message: ");
		} else
		{
			cardLabel.setText(mission.getMissionCard().toString());
			covertLabel.setText("Covert Value: " + mission.getCovertValue());
			vpLabel.setText("VP Value: " + mission.getPoints());
			effectLabel.setText("Card Effect: ");
			messageLabel.setText("Game Message: ");
		}
	}
}
