package spies.ui.swing.panes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import spies.framework.Card.Suit;

public class StatusPane extends JPanel
{
	private static final long serialVersionUID = -4595070513407486690L;

	private SuitInfo clubsInfo;
	private SuitInfo diamondsInfo;
	private SuitInfo heartsInfo;
	private SuitInfo spadesInfo;

	private SuitInfo totalInfo;

	public StatusPane()
	{
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		add(getRowLabels());

		diamondsInfo = new SuitInfo(Suit.DIAMONDS);
		add(diamondsInfo);

		clubsInfo = new SuitInfo(Suit.CLUBS);
		add(clubsInfo);

		heartsInfo = new SuitInfo(Suit.HEARTS);
		add(heartsInfo);

		spadesInfo = new SuitInfo(Suit.SPADES);
		add(spadesInfo);

		totalInfo = new SuitInfo(null);
		add(totalInfo);

		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	}

	private Component getRowLabels()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		panel.add(Box.createRigidArea(new Dimension(0, 15)));
		panel.add(new JLabel("Successful"));
		panel.add(new JLabel("Failed"));
		panel.add(new JLabel("Total"));

		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		return panel;
	}

	private class SuitInfo extends JPanel
	{
		private static final long serialVersionUID = 2468410898945489307L;

		private Suit suit;

		private int successCount;
		private int successVP;

		private int failedCount;
		private int failedVP;

		private JLabel successLabel;
		private JLabel failedLabel;
		private JLabel totalLabel;

		public SuitInfo(Suit suit)
		{
			this.suit = suit;

			successCount = 0;
			successVP = 0;

			failedCount = 0;
			failedVP = 0;

			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			add(getSuitLabel());
			add(getSuccessLabel());
			add(getFailedLabel());
			add(getTotalLabel());

			setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		}

		private Component getTotalLabel()
		{
			totalLabel = new JLabel();

			StringBuffer sbText = new StringBuffer();
			sbText.append(successCount + failedCount);
			sbText.append(" (");
			sbText.append(successVP + failedVP);
			sbText.append(")");

			totalLabel.setText(sbText.toString());
			totalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

			return totalLabel;
		}

		private Component getFailedLabel()
		{
			failedLabel = new JLabel();
			failedLabel.setForeground(Color.RED);

			StringBuffer sbText = new StringBuffer();
			sbText.append(failedCount);
			sbText.append(" (");
			sbText.append(failedVP);
			sbText.append(")");

			failedLabel.setText(sbText.toString());
			failedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

			return failedLabel;
		}

		private Component getSuccessLabel()
		{
			successLabel = new JLabel();
			successLabel.setForeground(Color.GREEN);

			StringBuffer sbText = new StringBuffer();
			sbText.append(successCount);
			sbText.append(" (");
			sbText.append(successVP);
			sbText.append(")");

			successLabel.setText(sbText.toString());
			successLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

			return successLabel;
		}

		private Component getSuitLabel()
		{
			JLabel label = new JLabel(suit == null ? "Total" : suit.toString());

			label.setAlignmentX(Component.CENTER_ALIGNMENT);

			return label;
		}
	}

}
