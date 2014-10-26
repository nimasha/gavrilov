package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Subscribers;

public class CreateSubscriber extends JPanel {

	private static final long serialVersionUID = 1L;
	private static Subscribers subscribers = Subscribers.getInstance();
	
	public CreateSubscriber() {
		// String[] demo = { " ", "1", "2", "3" };

		JTextField idT = new JTextField();
		JTextField pasT = new JTextField();
		JTextField fioT = new JTextField();
		JTextField addressT = new JTextField();
		JTextField birthdayT = new JTextField();
		JTextField phones = new JTextField();

		this.add(new JLabel("ID"));
		this.add(idT);
		this.add(new JLabel("Passport"));
		this.add(pasT);
		this.add(new JLabel("FIO"));
		this.add(fioT);
		this.add(new JLabel("Address"));
		this.add(addressT);
		this.add(new JLabel("Birthday"));
		this.add(birthdayT);
		this.add(new JLabel("Phones"));
		this.add(phones);
		JButton create = new JButton("Create");
		create.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		add(create);
		updateUI();
		setLayout(new GridLayout(5, 2));

	}
}
