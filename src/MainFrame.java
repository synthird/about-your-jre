import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends JFrame implements MouseListener {
	JPanel buttonPanel,
		jreInfoPanel;

	JLabel vendorSiteLabel,
		reportBugsLabel;

	JButton copyButton,
			exitButton;

	FlowLayout alignLeft = new FlowLayout(FlowLayout.LEFT);

	URI vendorUri,
		bugReportUri;
	
	Desktop desktop = Desktop.getDesktop();

	public MainFrame(JREInfo jreInfo) {
		// Extract links
		try {
			vendorUri = new URI(jreInfo.vendorSite);
			bugReportUri = new URI(jreInfo.bugReportLink);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		// JLabel title
		JPanel titlePanel = new JPanel(alignLeft);
		JLabel titleLabel = new JLabel("About your JRE");

		titlePanel.add(titleLabel);
		this.add(titlePanel, BorderLayout.NORTH);

		// JRE info
		JPanel alignMainContent = new JPanel(alignLeft);

		jreInfoPanel = new JPanel(alignLeft);
		jreInfoPanel.setLayout(new BoxLayout(jreInfoPanel, BoxLayout.Y_AXIS));

		setUpJLabel("Version", jreInfo.version);
		setUpJLabel("Version date", jreInfo.versionDate);
		setUpJLabel("Vendor", jreInfo.vendor);

		vendorSiteLabel = setUpJLabel("Vendor website", jreInfo.vendorSite);
		vendorSiteLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		vendorSiteLabel.addMouseListener(this);

		setUpJLabel("Device operating system", jreInfo.deviceOS);

		reportBugsLabel = setUpJLabel("Report JRE bugs", jreInfo.bugReportLink);
		reportBugsLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		reportBugsLabel.addMouseListener(this);

		alignMainContent.add(jreInfoPanel);

		this.add(alignMainContent);

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
		button.addMouseListener(this);
		button.setFocusable(false);
		buttonPanel.add(button);
		return button;
	}

	private JLabel setUpJLabel(String property, String value) {
		String text = String.format("%s: %s", property, value);
		JLabel propertyLabel = new JLabel(text);
		jreInfoPanel.add(propertyLabel);
		return propertyLabel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == exitButton) {
			System.exit(0);
		} else if (e.getSource() == copyButton) {
			System.out.println("Copy JRE info");
		} else if (e.getSource() == vendorSiteLabel) {
			// Open vendor website
			try {
				desktop.browse(vendorUri);
			} catch (IOException e1) {
				System.out.println("Unable to open vendor site");
			}
		} else if (e.getSource() == reportBugsLabel) {
			// Open bug report link
			try {
				desktop.browse(bugReportUri);
			} catch (IOException e1) {
				System.out.println("Unable to open bug report link");
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
