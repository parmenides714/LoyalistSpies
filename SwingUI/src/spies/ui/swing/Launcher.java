package spies.ui.swing;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Launcher extends JFrame
{

	private static final long serialVersionUID = -377931050958705081L;

	private JTextField playerName;
	private JComboBox<String> robot1Chooser;
	private JComboBox<String> robot2Chooser;
	private JComboBox<String> robot3Chooser;

	public Launcher()
	{
		// TODO Auto-generated constructor stub
		super("Loyalist Spies");

		Container pane = getContentPane();

		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

		robot1Chooser = createRobotChooser();
		robot2Chooser = createRobotChooser();
		robot3Chooser = createRobotChooser();

		pane.add(createNameField());
		pane.add(createRobotField(robot1Chooser));
		pane.add(createRobotField(robot2Chooser));
		pane.add(createRobotField(robot3Chooser));

		pane.add(createButtons());

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	private Component createButtons()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		JButton start = new JButton();
		start.setText("Start Game");
		start.addActionListener(getStartAction());
		panel.add(start);

		JButton quit = new JButton();
		quit.setText("Quit");
		quit.addActionListener(getQuitAction());
		panel.add(quit);

		return panel;
	}

	private ActionListener getQuitAction()
	{
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				// TODO Auto-generated method stub

			}
		};
	}

	private ActionListener getStartAction()
	{
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub

			}
		};
	}

	private Component createRobotField(JComboBox<String> chooser)
	{
		JPanel panel = new JPanel();

		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(new JLabel("Robot Difficulty:"));
		panel.add(chooser);

		return panel;
	}

	private Component createNameField()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(new JLabel("Name:"));
		playerName = new JTextField();
		panel.add(playerName);
		return panel;
	}

	private JComboBox<String> createRobotChooser()
	{
		JComboBox<String> combo = new JComboBox<String>();

		combo.addItem("Easy");
		combo.addItem("Medium");
		combo.addItem("Hard");

		return combo;
	}

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run()
			{
				new Launcher();
			}
		});
	}
}
