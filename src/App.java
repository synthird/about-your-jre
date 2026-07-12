public class App {
	public static void main(String[] args) {
		JREInfo jreInfo = new JREInfo();

		if (args.length == 0) {
			new MainFrame(jreInfo);
		} else {
			String mainCLIContent = String.format(
					"Version: %s\nVersion date: %s\nVendor: %s\nVendor website: %s\nDevice operating system: %s\nReport JRE bugs: %s",
					jreInfo.version,
					jreInfo.versionDate,
					jreInfo.vendor,
					jreInfo.vendorSite,
					jreInfo.deviceOS,
					jreInfo.bugReportLink);

			System.out.println("----- ABOUT YOUR JRE -----");
			System.out.println(mainCLIContent);
		}
	}
}
