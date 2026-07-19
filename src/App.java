public class App {
	public static void main(String[] args) {
		JREInfo jreInfo = new JREInfo();

		if (args.length == 0) {
			new GUI(jreInfo);
		} else {
			new CLI(jreInfo);
		}
	}
}
