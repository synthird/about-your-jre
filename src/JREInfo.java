public class JREInfo {
	public String version = System.getProperty("java.version"),
			versionDate = System.getProperty("java.version.date"),
			vendor = System.getProperty("java.vendor"),
			vendorSite = System.getProperty("java.vendor.url"),
			deviceOS = System.getProperty("os.name"),
			bugReportLink = System.getProperty("java.vendor.url.bug");
}
