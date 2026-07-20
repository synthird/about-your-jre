package ui;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mainpackage.JREInfo;

public class GUI extends JFrame implements MouseListener {
	// GUI variables
	JPanel buttonPanel,
			jreInfoPanel;

	JLabel vendorSiteLabel,
			reportBugsLabel;

	JButton copyButton,
			exitButton;

	StringSelection textToCopy;

	URI vendorUri,
			bugReportUri;

	// Non-GUI variables
	FlowLayout alignLeft = new FlowLayout(FlowLayout.LEFT);
	Desktop desktop = Desktop.getDesktop();
	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

	boolean linksAreParsable = true;

	String blueLinkColour = "blue",
			redLinkColour = "red",
			underlineLink = "underline",
			noUnderlineLink = "none";

	public GUI(JREInfo jreInfo) {
		// Prepare copying JRE info
		textToCopy = new StringSelection(String.format("""
				Version: %s
				Version date: %s
				Vendor: %s
				Device operating system: %s
				""",
				jreInfo.version,
				jreInfo.versionDate,
				jreInfo.vendor,
				jreInfo.deviceOS));

		// Extract links
		try {
			vendorUri = new URI(jreInfo.vendorSite);
			bugReportUri = new URI(jreInfo.bugReportLink);
		} catch (URISyntaxException e) {
			linksAreParsable = false;
		}

		// JLabel title
		JPanel titlePanel = new JPanel(alignLeft);
		titlePanel.add(new JLabel(String.format("%s JRE", jreInfo.vendor)));
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
		this.pack();
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

	// JLabel setup methods

	private JLabel setUpJLabel(String property, String value) {
		String text = String.format("<html><i>%s:</i> %s</html>", property, value);
		JLabel propertyLabel = new JLabel(text);
		jreInfoPanel.add(propertyLabel);
		return propertyLabel;
	}

	private JLabel setUpLinkLabel(String property, String link) {
		if (linksAreParsable) {
			String linkTag = String.format("<span style='text-decoration: underline' color=blue>%s</span>", link);
			JLabel linkLabel = setUpJLabel(property, linkTag);

			linkLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			linkLabel.addMouseListener(this);
			return linkLabel;
		} else {
			return setUpJLabel(property, link);
		}
	}

	// Link methods

	private void changeLinkAppearance(JLabel linkLabel, String originalValue, String newValue) {
		String labelText = linkLabel.getText();
		String newText = labelText.replaceFirst(originalValue, newValue);
		linkLabel.setText(newText);
	}

	private void showCannotOpenLinkMessage() {
		JOptionPane.showMessageDialog(this,
				"Your device refuses to open this link! :(",
				"Unable to open link!",
				JOptionPane.ERROR_MESSAGE);
	}

	private boolean checkIfChoseLinkLabel(MouseEvent e) {
		return e.getSource() == vendorSiteLabel || e.getSource() == reportBugsLabel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == exitButton) {
			System.exit(0);
		} else if (e.getSource() == copyButton) {
			// Copy JRE info
			clipboard.setContents(textToCopy, null);
			JOptionPane.showMessageDialog(this, "Copied JRE info to clipboard!");
		} else if (e.getSource() == vendorSiteLabel) {
			// Open vendor website
			try {
				desktop.browse(vendorUri);
			} catch (IOException e1) {
				showCannotOpenLinkMessage();
			}
		} else if (e.getSource() == reportBugsLabel) {
			// Open bug report link
			try {
				desktop.browse(bugReportUri);
			} catch (IOException e1) {
				showCannotOpenLinkMessage();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (checkIfChoseLinkLabel(e)) {
			changeLinkAppearance((JLabel) e.getSource(), underlineLink, noUnderlineLink);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (checkIfChoseLinkLabel(e)) {
			changeLinkAppearance((JLabel) e.getSource(), noUnderlineLink, underlineLink);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (checkIfChoseLinkLabel(e)) {
			changeLinkAppearance((JLabel) e.getSource(), blueLinkColour, redLinkColour);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (checkIfChoseLinkLabel(e)) {
			changeLinkAppearance((JLabel) e.getSource(), redLinkColour, blueLinkColour);
		}
	}
}
