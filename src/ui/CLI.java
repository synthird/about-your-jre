package ui;

import mainpackage.JREInfo;

public class CLI {
	public CLI(JREInfo jreInfo) {
		String mainCLIContent = String.format(
				"Version: %s\nVersion date: %s\nVendor: %s\nVendor website: %s\nDevice operating system: %s\nReport JRE bugs: %s",
				jreInfo.version,
				jreInfo.versionDate,
				jreInfo.vendor,
				jreInfo.vendorSite,
				jreInfo.deviceOS,
				jreInfo.bugReportLink);

		System.out.println(String.format("----- %s JRE -----\n%s", jreInfo.vendor, mainCLIContent));
	}
}
