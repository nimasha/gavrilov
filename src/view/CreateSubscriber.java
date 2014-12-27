package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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

	public CreateSubscriber(Controller modelControllerExt) {
		// String[] demo = { " ", "1", "2", "3" };
		modelController = modelControllerExt;
		// this.add(new JLabel("ID"));
		// this.add(idT);
		phones.addKeyListener(new DigitFomatWithComma());
		pasT.addKeyListener(new DigitFormat());
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
				if (!fioT.getText().isEmpty()) {
					Subscriber s = new Subscriber(new Random().nextLong(), pasT
							.getText(), fioT.getText(), addressT.getText(),
							birthdayT.getText());
					List<Phone> list = new ArrayList<>();
					Phone p;
					if (!phones.getText().isEmpty()) {
						for (String phone : phones.getText().split(",")) {
							p = new Phone();
							p.setId(new Long(phone));
							p.setSubscriber(s);
							modelController.addPhone(p);
							list.add(p);
						}
						s.setPhoneList(list);
					}
					modelController.addSubscriber(s);
					info.setText("Subscriber " + fioT.getText()
							+ " was successfully created");
				} else {
					info.setText("Please input fio");
				}
			}
		});
		add(info);
		add(create);
		updateUI();
		setLayout(new GridLayout(6, 2));

	}
}
