public class App {
	public static void main(String[] args) {
		JREInfo jreInfo = new JREInfo();

		System.out.println("--- ABOUT YOUR JRE ---");

		new MainFrame(jreInfo);
	}
}
