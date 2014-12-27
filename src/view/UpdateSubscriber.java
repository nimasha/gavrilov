package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Phone;
import model.Subscriber;
import controller.Controller;

public class UpdateSubscriber extends JPanel {

	private static final long serialVersionUID = 1L;
	JTextField passportT = new JTextField();
	JTextField fioT = new JTextField();
	JTextField phoneNumber = new JTextField();
	JTextField addressT = new JTextField();
	JTextField birthdayT = new JTextField();
	JLabel info = new JLabel();
	JComboBox<Object> subscriberT;
	private static Controller modelController;
	Subscriber chosenSubscrier;
	Subscriber lockedSubscriber;
	public UpdateSubscriber(Controller modelControllerExt) {
		modelController = modelControllerExt;
		subscriberT = new JComboBox<>(modelController.getSubscribers()
				.toArray());
		subscriberT.setSelectedIndex(-1);
		phoneNumber.addKeyListener(new DigitFomatWithComma());
		passportT.addKeyListener(new DigitFormat());
		JButton update = new JButton("Update");
		subscriberT.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!modelController
						.tryLockSubscriber(((Subscriber) subscriberT
								.getSelectedItem()).getId())) {
					JOptionPane.showMessageDialog(null,
							"Subscriber is currently locked by another user all fields will be uneditable");
				setAllEditable(false);
				} else {
					setAllEditable(true);
					lockUnlockSubscriber();
					updateFields();
				}

			}
		});

		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (chosenSubscrier != null) {
					Subscriber s = new Subscriber();
					s.setId(chosenSubscrier.getId());
					s.setBirthday(birthdayT.getText());
					s.setPassport(passportT.getText());
					s.setFio(fioT.getText());
					s.setAddress(addressT.getText());
					List<Phone> list = new ArrayList<>();
					Phone p;
					for (String phone : phoneNumber.getText().split(",")) {
						p = new Phone();
						p.setId(new Long(phone));
						p.setSubscriber(s);
						modelController.addPhone(p);
						list.add(p);
					}
					s.setPhoneList(list);
					modelController.replaceSubscriber(s);
					subscriberT.removeAllItems();
					subscriberT.setModel(new DefaultComboBoxModel<Object>(
							modelController.getSubscribers().toArray()));
					subscriberT.setSelectedItem(s);
					updateUIOfSubscriber();
					updateFields();
					info.setText("Subscriber " + s.getFio() + " was updated");
				} else {
					info.setText("Please select a subscriber");
				}
			}
		});

		add(new JLabel("Subscriber"));
		add(subscriberT);
		add(new JLabel("Phone Number Of Subscriber"));
		add(phoneNumber);
		add(new JLabel("Passport"));
		add(passportT);
		add(new JLabel("FIO"));
		add(fioT);
		add(new JLabel("Address"));
		add(addressT);
		add(new JLabel("Birthday"));
		add(birthdayT);
		info.setText("Input phones separating by comma");
		add(info);
		add(update);
		updateFields();
		updateUI();
		setLayout(new GridLayout(7, 2));
	}

	public void updateUIOfSubscriber() {
		paintComponents(getGraphics());
	}

	public void updateFields() {
		if ((Subscriber) subscriberT.getSelectedItem() != null
				&& subscriberT.getItemCount() > 0) {
			chosenSubscrier = (Subscriber) subscriberT.getSelectedItem();
			birthdayT.setText(chosenSubscrier.getBirthday());
			addressT.setText(chosenSubscrier.getAddress());
			if (chosenSubscrier.getPhoneList() != null)
				phoneNumber.setText(chosenSubscrier.getPhoneListAsString());
			else
				phoneNumber.setText("");
			fioT.setText(chosenSubscrier.getFio());
			passportT.setText(chosenSubscrier.getPassport());
		}
	}
	private void lockUnlockSubscriber() {
		if (lockedSubscriber != (Subscriber) subscriberT.getSelectedItem()) {
			if (lockedSubscriber != null) {
				modelController.unlockSubscriber(lockedSubscriber.getId());
			}

			lockedSubscriber = (Subscriber) subscriberT.getSelectedItem();
			Desktop.getInstance().lockObject(lockedSubscriber);

		}
	}

	public void setAllEditable(Boolean flag) {
		birthdayT.setEditable(flag);
		addressT.setEditable(flag);
		phoneNumber.setEditable(flag);
		fioT.setEditable(flag);
		passportT.setEditable(flag);
	}
}
