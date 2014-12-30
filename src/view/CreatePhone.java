package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.interfaces.SubscriberPanel;
import model.Phone;
import model.Subscriber;
import controller.Controller;

public class CreatePhone extends JPanel implements SubscriberPanel {
	private static Controller modelController;
	private static final long serialVersionUID = 1L;
	JTextField idT = new JTextField();
	JTextField balanceT = new JTextField();
	JComboBox<Object> subscriberT;
	JTextField phones = new JTextField();
	JLabel info = new JLabel("");
	Subscriber lockedSubscriber;
	public CreatePhone(Controller modelControllerExt) {
		modelController = modelControllerExt;
		// setLayout(new BorderLayout().);
		idT.addKeyListener(new DigitFormat());
		balanceT.addKeyListener(new DigitFormat());
		JLabel id = new JLabel("Phone Number");

		JLabel balance = new JLabel("Balance");

		JLabel subscriber = new JLabel("Subscriber");

		if (modelController.getSubscribers() != null) {
			subscriberT = new JComboBox<>(modelController.getSubscribers()
					.toArray());
			subscriberT.setSelectedIndex(-1);
		} else
			subscriberT = new JComboBox<>();
		add(id);
		add(idT);
		add(balance);
		this.add(balanceT);
		this.add(subscriber);
		this.add(subscriberT);
		JButton create = new JButton("Create");

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

		create.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!idT.getText().isEmpty()) {
					Double balance;
					if (balanceT.getText().isEmpty()) {
						balance = 0.0;
					} else {
						balance = new Double(balanceT.getText());
					}
					Phone p = new Phone(new Long(idT.getText()), balance,
							(Subscriber) subscriberT.getSelectedItem());
					modelController.addPhone(p);

					info.setText("Pone with number " + idT.getText()
							+ "  was successfully created");
				} else {
					info.setText("Please input at least the ID");
				}
			}
		});
		add(info);
		add(create);

		updateUI();
		setLayout(new GridLayout(4, 2));

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
