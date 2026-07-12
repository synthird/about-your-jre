import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends JFrame implements ActionListener {
	JPanel buttonPanel;

	JButton copyButton,
			exitButton;
	
	FlowLayout alignLeft = new FlowLayout(FlowLayout.LEFT);

	public MainFrame(JREInfo jreInfo) {
		System.out.println(jreInfo.version);
		System.out.println(jreInfo.versionDate);
		System.out.println(jreInfo.vendor);
		System.out.println(jreInfo.vendorSite);
		System.out.println(jreInfo.deviceOS);
		System.out.println(jreInfo.bugReportLink);

		// JLabel title
		JPanel titlePanel = new JPanel(alignLeft);
		JLabel titleLabel = new JLabel("About your JRE");

		titlePanel.add(titleLabel);
		this.add(titlePanel, BorderLayout.NORTH);

		// Buttons
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		copyButton = setUpButton("Copy JRE info");
		exitButton = setUpButton("Exit");
		this.add(buttonPanel, BorderLayout.SOUTH);

		// Set up JFrame window
		this.setSize(333, 290);
		this.setTitle("About your JRE");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private JButton setUpButton(String text) {
		JButton button = new JButton(text);
		button.addActionListener(this);
		button.setFocusable(false);
		buttonPanel.add(button);
		return button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exitButton) {
			System.exit(0);
		} else if (e.getSource() == copyButton) {
			System.out.println("Copy JRE info");
		}
	}
}
