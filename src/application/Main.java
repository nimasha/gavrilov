package application;

import view.Desktop;

public class Main {
	public static void main(String[] args) throws Exception {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					Desktop desktop = new Desktop();
					desktop.pack();
					desktop.setSize(640, 480);
					desktop.setVisible(true);
				} catch (Exception ex) {

				}
			}
		});

	}

}
