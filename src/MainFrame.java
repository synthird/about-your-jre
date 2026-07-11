import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame implements ActionListener {
	JPanel buttonPanel;

	JButton copyButton,
			exitButton;

	public MainFrame(JREInfo jreInfo) {
		System.out.println(jreInfo.version);
		System.out.println(jreInfo.versionDate);
		System.out.println(jreInfo.vendor);
		System.out.println(jreInfo.vendorSite);
		System.out.println(jreInfo.deviceOS);
		System.out.println(jreInfo.bugReportLink);

		// Buttons
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		copyButton = setupButton("Copy JRE info");
		exitButton = setupButton("Exit");
		this.add(buttonPanel, BorderLayout.SOUTH);

		// Set up JFrame window
		this.setSize(333, 290);
		this.setTitle("About your JRE");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private JButton setupButton(String text) {
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
		}
	}
}
