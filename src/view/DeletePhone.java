package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Phone;
import controller.Controller;

public class DeletePhone extends JPanel {
	private static Controller modelController;
	private static final long serialVersionUID = 1L;
	private JLabel info = new JLabel();
	JComboBox<Object> phoneNumber;
	Object removeObject = null;
	Phone lockedPhone;

	public DeletePhone(Controller modelControllerExt) {
		modelController = modelControllerExt;
		JLabel phoneChoose = new JLabel("Choose Phone Number To Delete");
		phoneNumber = new JComboBox<>(modelController.getPhones().toArray());
		phoneNumber.setSelectedIndex(-1);
		JButton delete = new JButton("Delete");
		phoneNumber.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!modelController.tryLockPhone(((Phone) phoneNumber
						.getSelectedItem()).getId())
						&& lockedPhone != (Phone) phoneNumber.getSelectedItem()) {
					JOptionPane.showMessageDialog(null,
							"Phone is currently locked by another user");
					phoneNumber.setSelectedIndex(-1);
				} else {
					lockUnlockPhone();
				}
			}
		});

		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//
				removeObject = phoneNumber.getSelectedItem();
				if (removeObject != null
						&& modelController.tryLockPhone(((Phone) removeObject)
								.getId())) {

					modelController.deletePhone(((Phone) removeObject).getId());
					info.setText("Phone " + removeObject + " was deleted");
					phoneNumber.removeItem(removeObject);
					phoneNumber.updateUI();
				} else {
					JOptionPane.showMessageDialog(null,
							"Phone is currently locked by another user");
				}
			}
		});
		add(phoneChoose);
		add(phoneNumber);
		add(info);
		add(delete);

		setLayout(new GridLayout(2, 2));

	}
	private void lockUnlockPhone() {
		if (lockedPhone != (Phone) phoneNumber.getSelectedItem()) {
			if (lockedPhone != null){
				modelController.unlockPhone(lockedPhone.getId());
		}
				lockedPhone = (Phone) phoneNumber.getSelectedItem();
				Desktop.getInstance().lockObject(lockedPhone);
		
		}
	}

}
