import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
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

	String textToCopy;
	StringSelection copySelection;

	URI vendorUri,
			bugReportUri;

	Desktop desktop = Desktop.getDesktop();
	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

	public MainFrame(JREInfo jreInfo) {
		// Add text to the textToCopy variable
		textToCopy = String.format("Version: %s\nVersion date: %s\nVendor: %s\nDevice operating system: %s",
				jreInfo.version,
				jreInfo.versionDate,
				jreInfo.vendor,
				jreInfo.deviceOS);

		copySelection = new StringSelection(textToCopy);

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

		// Show JRE info
		JPanel alignMainContent = new JPanel(alignLeft);

		jreInfoPanel = new JPanel(alignLeft);
		jreInfoPanel.setLayout(new BoxLayout(jreInfoPanel, BoxLayout.Y_AXIS));

		setUpJLabel("Version", jreInfo.version);
		setUpJLabel("Version date", jreInfo.versionDate);
		setUpJLabel("Vendor", jreInfo.vendor);
		vendorSiteLabel = setUpLinkLabel("Vendor website", jreInfo.vendorSite);
		setUpJLabel("Device operating system", jreInfo.deviceOS);
		reportBugsLabel = setUpLinkLabel("Report JRE bugs", jreInfo.bugReportLink);

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
		String text = String.format("<html><i>%s</i>: %s", property, value);
		JLabel propertyLabel = new JLabel(text);
		jreInfoPanel.add(propertyLabel);
		return propertyLabel;
	}

	private JLabel setUpLinkLabel(String property, String link) {
		String linkTag = String.format("<font color=blue>%s</font>", link);
		JLabel linkLabel = setUpJLabel(property, linkTag);

		linkLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		linkLabel.addMouseListener(this);
		return linkLabel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == exitButton) {
			System.exit(0);
		} else if (e.getSource() == copyButton) {
			// Copy JRE info
			clipboard.setContents(copySelection, null);
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
