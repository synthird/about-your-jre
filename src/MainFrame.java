import javax.swing.JFrame;

public class MainFrame extends JFrame {
	public MainFrame(String version, String versionDate, String vendor, String vendorWebsite, String deviceOS, String bugReportLink) {
		System.out.println(version);
		System.out.println(versionDate);
		System.out.println(vendor);
		System.out.println(vendorWebsite);
		System.out.println(deviceOS);
		System.out.println(bugReportLink);

		this.setSize(333, 290);
		this.setTitle("About your JRE");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
