package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DeletePhone extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel info = new JLabel();

	public DeletePhone() {
		String[] demo = { " ", "1", "2", "3" };
		JLabel phoneChoose = new JLabel("Choose Phone Number To Delete");
		JComboBox<String> phoneNumber = new JComboBox<>(demo);

		JButton delete = new JButton("Delete");
		phoneNumber.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO set info about chosen number
				info.setText("afdvadfvadfv");
			}
		});
		add(phoneChoose);
		add(phoneNumber);
		add(info);
		add(delete);

		setLayout(new GridLayout(2, 2));
	}
}
