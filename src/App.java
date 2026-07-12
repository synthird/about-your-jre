public class App {
	public static void main(String[] args) {
		JREInfo jreInfo = new JREInfo();
		String jreInfoDisplay = String.format(
				"Version: %s\nVersion date: %s\nVendor: %s\nVendor website: %s\nDevice operating system: %s\nReport JRE bugs: %s",
				jreInfo.version,
				jreInfo.versionDate,
				jreInfo.vendor,
				jreInfo.vendorSite,
				jreInfo.deviceOS,
				jreInfo.bugReportLink);

		System.out.println("----- ABOUT YOUR JRE -----");
		System.out.println(jreInfoDisplay);

		new MainFrame(jreInfo);
	}
}
