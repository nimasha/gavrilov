package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Subscribers;

public class CreatePhone extends JPanel {

	private static final long serialVersionUID = 1L;
	private static Subscribers subscribers = Subscribers.getInstance();

	public CreatePhone() {

		// setLayout(new BorderLayout().);
		JLabel id = new JLabel("Phone Number");
		JTextField idT = new JTextField();
		JLabel balance = new JLabel("Balance");
		JTextField balanceT = new JTextField();
		JLabel subscriber = new JLabel("Subscriber");
		JComboBox<Object> subscriberT = new JComboBox<>(subscribers.getListOfSubscribers().toArray());
		add(id);
		add(idT);
		add(balance);
		this.add(balanceT);
		this.add(subscriber);
		this.add(subscriberT);
		JButton create = new JButton("Create");
		create.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		add(create);

		updateUI();
		setLayout(new GridLayout(4, 2));

	}
}
