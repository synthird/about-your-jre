package ui;

import mainpackage.JREInfo;

public class CLI {
	public CLI(JREInfo jreInfo) {
		String mainCLIContent = String.format(
				"""
						----- %s JRE -----
						Version: %s
						Version date: %s
						Vendor: %s
						Vendor website: %s
						Device operating system: %s
						Report JRE bugs: %s
						""",
				jreInfo.vendor,
				jreInfo.version,
				jreInfo.versionDate,
				jreInfo.vendor,
				jreInfo.vendorSite,
				jreInfo.deviceOS,
				jreInfo.bugReportLink);

		System.out.println(mainCLIContent);
	}
}
