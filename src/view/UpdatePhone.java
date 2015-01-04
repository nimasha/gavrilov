package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.interfaces.PhonePanel;
import view.interfaces.SubscriberPanel;
import view.validator.ValidatorImpl;
import model.Phone;
import model.Subscriber;
import controller.Controller;

public class UpdatePhone extends JPanel implements PhonePanel, SubscriberPanel {

	/**
	 * 
	 */
	private static Controller modelController;
	private static final long serialVersionUID = 1L;
	// JTextField idT = new JTextField();
	JTextField hoursT = new JTextField();
	JTextField balanceT = new JTextField();
	private static JComboBox<Object> phoneNumber;
	JComboBox<Object> subscriberT;
	Phone chosenPhone;
	JLabel info = new JLabel("");
	Phone lockedPhone;
	Subscriber lockedSubscriber;
	JButton update;
	public UpdatePhone(Controller modelControllerExt) {
		modelController = modelControllerExt;
		subscriberT = new JComboBox<>(modelController.getSubscribers()
				.toArray());
		subscriberT.setSelectedIndex(-1);
		subscriberT.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (subscriberT.getSelectedItem() != null
						&& !modelController
								.tryLockSubscriber(((Subscriber) subscriberT
										.getSelectedItem()).getId())
						&& lockedSubscriber != (Subscriber) subscriberT
								.getSelectedItem()) {
					JOptionPane.showMessageDialog(null,
							"Subscriber is currently locked by another user");
					subscriberT.setSelectedIndex(-1);
				} else {
					lockUnlockSubscriber();
				}
			}
		});
		hoursT.addKeyListener(new DigitFormat());
		balanceT.addKeyListener(new NegativeDigitFormat());
		JLabel phoneChoose = new JLabel("Choose Phone Number To Update");
		phoneNumber = new JComboBox<>(modelController.getPhones().toArray());
		phoneNumber.setSelectedIndex(-1);
		phoneNumber.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!modelController.tryLockPhone(((Phone) phoneNumber
						.getSelectedItem()).getId())) {
					JOptionPane
							.showMessageDialog(null,
									"Phone is currently locked by another user all fiels will be uneditable");
					setAllEditable(false);
					updateFields();
				} else {
					lockUnlockSubscriber();
					lockUnlockPhone();
					setAllEditable(true);
					updateFields();
				}
			}
		});
		JLabel id = new JLabel("Phone Number");
		update = new JButton("Update");
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (chosenPhone != null) {
					if (ValidatorImpl.getInstance().validateBalance(
							balanceT.getText())) {
						Phone p = new Phone();
						p.setId(chosenPhone.getId());
						Double balance;
						Double hours;

						if (balanceT.getText().isEmpty()) {
							p.setBalance(0.0);
						} else {
							p.setBalance(new Double(balanceT.getText()));
						}
						if (hoursT.getText().isEmpty()) {
							p.setHours(0.0);
						} else {
							p.setHours(new Double(hoursT.getText()));
						}

						p.setSubscriber((Subscriber) subscriberT
								.getSelectedItem());
						modelController.replacePhone(p,
								chosenPhone.getSubscriber());

						phoneNumber.removeAllItems();
						phoneNumber.setModel(new DefaultComboBoxModel<Object>(
								modelController.getPhones().toArray()));
						phoneNumber.setSelectedItem(p);
						updateUIOfSubscriber();
						updateFields();
						info.setText("Phone " + p.getId() + " was updated");
					} else {
						JOptionPane.showMessageDialog(null,
								Constants.BALANCE_ERROR_MESSAGE);
					}
				}
			}
		});
		// JButton cancel = new JButton("Cancel");
		// cancel.setVisible(false);

		id.setEnabled(true);
		JLabel hours = new JLabel("Hours");

		hoursT.setVisible(true);
		JLabel balance = new JLabel("Balance");

		balanceT.setVisible(true);
		JLabel subscriber = new JLabel("Subscriber");

		add(phoneChoose);
		add(phoneNumber);
		// this.add(id);
		// this.add(idT);
		this.add(hours);
		this.add(hoursT);
		this.add(balance);
		this.add(balanceT);
		this.add(subscriber);
		this.add(subscriberT);
		this.add(info);
		this.add(update);
		updateFields();
		updateUI();
		setLayout(new GridLayout(5, 2));
	}

	public void updateUIOfSubscriber() {
		paintComponents(getGraphics());
	}
	public JComboBox<Object> getPhoneNumberComboBox() {
		return phoneNumber;
	}
	public void updateFields() {
		if ((Phone) phoneNumber.getSelectedItem() != null
				&& phoneNumber.getItemCount() > 0) {
			chosenPhone = (Phone) phoneNumber.getSelectedItem();
			hoursT.setText(String.valueOf(chosenPhone.getHours()));
			balanceT.setText(String.valueOf(chosenPhone.getBalance()));
			subscriberT.setSelectedItem(chosenPhone.getSubscriber());
		}
	}

	public void setAllEditable(Boolean flag) {
		hoursT.setEditable(flag);
		balanceT.setEditable(flag);
		subscriberT.setEnabled(flag);
		update.setEnabled(flag);
	}

	private void lockUnlockPhone() {
		if (lockedPhone != (Phone) phoneNumber.getSelectedItem()) {
			if (lockedPhone != null) {
				modelController.unlockPhone(lockedPhone.getId());
			}
			lockedPhone = (Phone) phoneNumber.getSelectedItem();
			Desktop.getInstance().lockObject(lockedPhone);
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

	@Override
	public JComboBox<Object> getSubscriberComboBox() {
		return subscriberT;
	}
}
