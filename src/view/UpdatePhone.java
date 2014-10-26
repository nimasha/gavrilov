package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.PhoneController;

public class UpdatePhone extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField idT = new JTextField();
	JTextField hoursT = new JTextField();
	JTextField balanceT = new JTextField();
	String[] demo = { " ", "1", "2", "3" };
	JComboBox<String> subscriberT = new JComboBox<>(demo);
	private static Desktop desktopController = Desktop.getInstance();

	public UpdatePhone() {

		JLabel phoneChoose = new JLabel("Choose Phone Number To Update");
		JComboBox<String> phoneNumber = new JComboBox<>(demo);
		phoneNumber.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO set fields about chosen number
				idT.setText("sadas");
				hoursT.setText("asda");
				balanceT.setText("asdas");
				subscriberT.setSelectedItem("1");
			}
		});
		JLabel id = new JLabel("Phone Number");
		JButton update = new JButton("Update");
		
		
		JButton cancel = new JButton("Cancel");
		cancel.setVisible(false);
		
		id.setEnabled(true);
		JLabel hours = new JLabel("Hours");

		hoursT.setVisible(true);
		JLabel balance = new JLabel("Balance");

		balanceT.setVisible(true);
		JLabel subscriber = new JLabel("Subscriber");

		add(phoneChoose);
		add(phoneNumber);
		this.add(id);
		this.add(idT);
		this.add(hours);
		this.add(hoursT);
		this.add(balance);
		this.add(balanceT);
		this.add(subscriber);
		this.add(subscriberT);
		this.add(cancel);
		this.add(update);
		
		updateUI();
		setLayout(new GridLayout(6, 2));
	}
}
