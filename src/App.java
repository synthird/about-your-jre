public class App {
	public static void main(String[] args) {
		String version = System.getProperty("java.version"),
				versionDate = System.getProperty("java.version.date"),
				vendor = System.getProperty("java.vendor"),
				vendorWebsite = System.getProperty("java.vendor.url"),
				deviceOS = System.getProperty("os.name"),
				bugReportLink = System.getProperty("java.vendor.url.bug");

		new MainFrame(version, versionDate, vendor, vendorWebsite, deviceOS, bugReportLink);
	}
}
