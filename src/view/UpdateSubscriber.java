package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UpdateSubscriber extends JPanel {

	private static final long serialVersionUID = 1L;
	JTextField idT = new JTextField();
	JTextField passportT = new JTextField();
	JTextField fioT = new JTextField();
	String[] demo = { " ", "1", "2", "3" };
	JComboBox<String> subscriberT = new JComboBox<>(demo);

	public UpdateSubscriber() {
		JLabel phoneChoose = new JLabel("Choose Phone Number To Delete");
		JComboBox<String> phoneNumber = new JComboBox<>(demo);
		phoneNumber.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO set fields about chosen number
				idT.setText("sadas");
				passportT.setText("asda");
				fioT.setText("asdas");
				subscriberT.setSelectedItem("1");
			}
		});
		
		JButton update = new JButton("Update");
		
		JLabel id = new JLabel("Phone Number");

		idT.setEnabled(false);
		JLabel hours = new JLabel("Hours");

		passportT.setVisible(false);
		JLabel balance = new JLabel("Balance");

		fioT.setVisible(false);
		JLabel subscriber = new JLabel("Subscriber");

		
		JButton cancel = new JButton("Cancel");
		cancel.setVisible(false);
		
		add(phoneChoose);
		add(phoneNumber);
		this.add(id);
		this.add(idT);
		this.add(hours);
		this.add(passportT);
		this.add(balance);
		this.add(fioT);
		this.add(subscriber);
		this.add(subscriberT);
		this.add(cancel);
		this.add(update);
		updateUI();
		setLayout(new GridLayout(6, 2));
	}

}
