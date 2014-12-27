package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Subscriber;
import controller.Controller;

public class DeleteSubscriber extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel info = new JLabel();
	private static Controller modelController;
	JComboBox<Object> subscriberID;
	Object removeObject = null;
	Subscriber lockedSubscriber;
	public DeleteSubscriber(Controller modelControllerExt) {
		modelController = modelControllerExt;
		JLabel subscriberChoose = new JLabel("Choose Subscriber To Delete");
		subscriberID = new JComboBox<>(modelController.getSubscribers()
				.toArray());
		subscriberID.setSelectedIndex(-1);
		subscriberID.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!modelController
						.tryLockSubscriber(((Subscriber) subscriberID
								.getSelectedItem()).getId())&& lockedSubscriber != (Subscriber) subscriberID.getSelectedItem()) {
					JOptionPane.showMessageDialog(null,
							"Subscriber is currently locked by another user");
					subscriberID.setSelectedIndex(-1);
				}
				else{
					lockUnlockSubscriber();
				}
			}
		});
		JButton delete = new JButton("Delete");
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				removeObject = subscriberID.getSelectedItem();
				if (removeObject != null) {
					modelController
							.deleteSubscriber(((Subscriber) removeObject)
									.getId());
					info.setText("Subscriber "
							+ ((Subscriber) removeObject).toString()
							+ " was deleted");
					subscriberID.removeItem(removeObject);
					subscriberID.updateUI();
				}
			}
		});
		add(subscriberChoose);
		add(subscriberID);
		add(info);
		add(delete);

		setLayout(new GridLayout(2, 2));

	}
	private void lockUnlockSubscriber() {
		if (lockedSubscriber!= (Subscriber) subscriberID.getSelectedItem()) {
			if (lockedSubscriber != null){
				modelController.unlockSubscriber(lockedSubscriber.getId());}
			
				lockedSubscriber = (Subscriber) subscriberID.getSelectedItem();
				Desktop.getInstance().lockObject(lockedSubscriber);
			
		}
	}
}
