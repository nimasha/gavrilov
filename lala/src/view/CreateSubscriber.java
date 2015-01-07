package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.validator.ValidatorImpl;
import model.Phone;
import model.Subscriber;
import controller.Controller;

public class CreateSubscriber extends JPanel {
	private static Controller modelController;
	private static final long serialVersionUID = 1L;
	JTextField idT = new JTextField();
	JTextField pasT = new JTextField();
	JTextField fioT = new JTextField();
	JTextField addressT = new JTextField();
	JTextField birthdayT = new JTextField();
	JTextField phones = new JTextField();
	JLabel info = new JLabel();
	ValidatorImpl validator = ValidatorImpl.getInstance();

	public CreateSubscriber(Controller modelControllerExt) {
		// String[] demo = { " ", "1", "2", "3" };
		modelController = modelControllerExt;
		// this.add(new JLabel("ID"));
		// this.add(idT);
		phones.addKeyListener(new DigitFomatWithComma());
		pasT.addKeyListener(new DigitFormatWithSpace());
		this.add(new JLabel("Passport"));
		this.add(pasT);
		this.add(new JLabel("FIO"));
		this.add(fioT);
		this.add(new JLabel("Address"));
		this.add(addressT);
		this.add(new JLabel("Birthday"));
		this.add(birthdayT);
		this.add(new JLabel("Phones"));
		info.setText("Input phones separating by comma");
		this.add(phones);
		JButton create = new JButton("Create");
		create.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (validator.validateFIO(fioT.getText())) {
					if (validator.validateDate(birthdayT.getText())) {
						if (validator.validatePassport(pasT.getText())) {
							Subscriber s = new Subscriber(new Random()
									.nextLong(), pasT.getText(),
									fioT.getText(), addressT.getText(),
									birthdayT.getText());
							modelController.addSubscriber(s);
							// List<Long> list = new ArrayList<>();
							Phone p;
							if (!phones.getText().isEmpty()) {
								for (String phone : phones.getText().split(",")) {
									if (!phone.isEmpty()) {
										p = modelController.getPhone(new Long(
												phone));
										if (p == null) {
											p = new Phone(new Long(phone));
											p.setSubscriber(s.getId());
											modelController.addPhone(p);
										} else {
											p.setSubscriber(s.getId());
											modelController.replacePhone(p,
													null);
										}
									}
								}
								/* s.setPhoneList(list); */
							}

							info.setText("Subscriber " + fioT.getText()
									+ " was successfully created");
						} else {
							JOptionPane.showMessageDialog(null,
									Constants.PASSPORT_ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null,
								Constants.DATE_ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null,
							Constants.NAME_ERROR_MESSAGE);
				}
			}
		});
		add(info);
		add(create);
		updateUI();
		setLayout(new GridLayout(6, 2));

	}
}
