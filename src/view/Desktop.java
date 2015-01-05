package view;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import view.interfaces.PhonePanel;
import view.interfaces.SubscriberPanel;
import model.Phone;
import model.Subscriber;
import controller.Controller;
import controller.NotificationListener;

public class Desktop extends JFrame implements NotificationListener {

	private static final long serialVersionUID = 1L;
	private static Desktop desktopInstance;
	private static Controller controller;
	private JPanel currentModelView = null;
	private Object lockedObject;

	public static synchronized Desktop getInstance() {

		return desktopInstance;

	}

	public void lockObject(Object object) {
		lockedObject = object;
	}

	private void unlockSomething() {
		if (lockedObject != null) {
			if (lockedObject instanceof Phone) {
				controller.unlockPhone(((Phone) lockedObject).getId());
			} else if (lockedObject instanceof Subscriber) {
				controller
						.unlockSubscriber(((Subscriber) lockedObject).getId());
			}
			lockedObject = null;
		}
	}

	public Desktop(Controller ControllerExt) {

		super();
		controller = ControllerExt;
		JMenuBar fileMenuBar = new JMenuBar();
		JMenu modelMenu = new JMenu("Model");
		JMenu phoneMenu = new JMenu("Phone");
		JMenu subscriberMenu = new JMenu("Subscriber");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JMenuItem createPhone = new JMenuItem(new AbstractAction("Create") {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				desktopInstance.getContentPane().removeAll();
				unlockSomething();
				currentModelView = new CreatePhone(controller);
				desktopInstance.add(currentModelView);

				paintComponents(getGraphics());
			}
		});
		JMenuItem updatePhone = new JMenuItem(new AbstractAction("Update") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				unlockSomething();
				desktopInstance.getContentPane().removeAll();
				currentModelView = new UpdatePhone(controller);
				desktopInstance.add(currentModelView);
				paintComponents(getGraphics());

			}
		});

		JMenuItem deletePhone = new JMenuItem(new AbstractAction("Delete") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				unlockSomething();
				desktopInstance.getContentPane().removeAll();
				currentModelView = new DeletePhone(controller);
				desktopInstance.add(currentModelView);
				paintComponents(getGraphics());
			}
		});

		JMenuItem createSubscriber = new JMenuItem(
				new AbstractAction("Create") {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void actionPerformed(ActionEvent e) {
						unlockSomething();
						desktopInstance.getContentPane().removeAll();
						currentModelView = new CreateSubscriber(controller);
						desktopInstance.add(currentModelView);
						paintComponents(getGraphics());

					}
				});
		JMenuItem updateSubscriber = new JMenuItem(
				new AbstractAction("Update") {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void actionPerformed(ActionEvent e) {
						unlockSomething();
						desktopInstance.getContentPane().removeAll();
						currentModelView = new UpdateSubscriber(controller);
						desktopInstance.add(currentModelView);
						paintComponents(getGraphics());
					}
				});
		JMenuItem deleteSubscriber = new JMenuItem(
				new AbstractAction("Delete") {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void actionPerformed(ActionEvent e) {
						unlockSomething();
						desktopInstance.getContentPane().removeAll();
						currentModelView = new DeleteSubscriber(controller);
						desktopInstance.add(currentModelView);
						paintComponents(getGraphics());
					}
				});
		JMenuItem saveModel = new JMenuItem(new AbstractAction("Save") {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooseFile = new JFileChooser("");
				int ret = chooseFile.showDialog(null, "Choose File");
				if (ret == JFileChooser.APPROVE_OPTION) {

					/*
					 * try {
					 * //controller.saveModel(chooseFile.getSelectedFile()); }
					 * catch (FileNotFoundException e1) { e1.printStackTrace();
					 * } catch (IOException e1) { e1.printStackTrace(); }
					 */
				}

			}
		});
		JMenuItem uploadModel = new JMenuItem(new AbstractAction("Upload") {

			/**
					 * 
					 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooseFile = new JFileChooser("");
				int ret = chooseFile.showDialog(null, "Choose File");
				if (ret == JFileChooser.APPROVE_OPTION) {

					/*
					 * try { Controller.setModel(chooseFile.getSelectedFile());
					 * } catch (FileNotFoundException e1) { // TODO
					 * Auto-generated catch block e1.printStackTrace(); } catch
					 * (IOException e1) { // TODO Auto-generated catch block
					 * e1.printStackTrace(); } catch (ClassNotFoundException e1)
					 * { // TODO Auto-generated catch block
					 * e1.printStackTrace(); }
					 */
				}

			}
		});

		// modelMenu.add(saveModel);
		// modelMenu.add(uploadModel);
		phoneMenu.add(createPhone);
		phoneMenu.add(updatePhone);
		phoneMenu.add(deletePhone);
		subscriberMenu.add(createSubscriber);
		subscriberMenu.add(updateSubscriber);
		subscriberMenu.add(deleteSubscriber);
		// fileMenuBar.add(modelMenu);
		fileMenuBar.add(phoneMenu);
		fileMenuBar.add(subscriberMenu);
		this.setJMenuBar(fileMenuBar);
		desktopInstance = this;
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				unlockSomething();
				System.exit(0);
			}
		});
	}

	@Override
	public void subscriberChanged(final Subscriber subscriber) {
		if (currentModelView != null) {
			if (currentModelView instanceof UpdateSubscriber
					&& ((Subscriber) ((UpdateSubscriber) currentModelView)
							.getSubscriberComboBox().getSelectedItem())
							.getId() == subscriber.getId()) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						((UpdateSubscriber) currentModelView)
								.getSubscriberComboBox().removeItem(
										((UpdateSubscriber) currentModelView)
												.getSubscriberComboBox()
												.getSelectedItem());
						((UpdateSubscriber) currentModelView)
								.getSubscriberComboBox().addItem(subscriber);
						((UpdateSubscriber) currentModelView)
								.getSubscriberComboBox()
								.setSelectedItem(subscriber);
						((UpdatePhone) currentModelView).updateFields();
					}
				});

			}
		}
		System.out.println("Subscriber " + subscriber.toString() + " was changed");

	}

	@Override
	public void subscriberAdded(final Subscriber subscriber) {
		if (currentModelView != null) {
			if (currentModelView instanceof UpdateSubscriber
					|| currentModelView instanceof DeleteSubscriber
					|| currentModelView instanceof CreatePhone
					|| currentModelView instanceof UpdatePhone) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						((SubscriberPanel) currentModelView)
								.getSubscriberComboBox().addItem(subscriber);
					}
				});

			}
		}

		System.out
				.println("Subscriber " + subscriber.toString() + " was added");

	}

	@Override
	public void subscriberRemoved(final Subscriber subscriber) {
		if (currentModelView != null) {
			if (currentModelView instanceof UpdateSubscriber
					|| currentModelView instanceof DeleteSubscriber
					|| currentModelView instanceof CreatePhone
					|| currentModelView instanceof UpdatePhone) {

				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						((SubscriberPanel) currentModelView)
								.getSubscriberComboBox().removeItem(subscriber);
					}
				});
			}
		}

		System.out.println("Subscriber " + subscriber.toString()
				+ " was removed");
	}

	@Override
	public void phoneChanged(final Phone phone) {
		if (currentModelView != null) {
			if (currentModelView instanceof UpdatePhone
					&& ((Phone) ((UpdatePhone) currentModelView)
							.getPhoneNumberComboBox().getSelectedItem())
							.getId() == phone.getId()) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						((UpdatePhone) currentModelView)
								.getPhoneNumberComboBox().removeItem(
										((UpdatePhone) currentModelView)
												.getPhoneNumberComboBox()
												.getSelectedItem());
						((UpdatePhone) currentModelView)
								.getPhoneNumberComboBox().addItem(phone);
						((UpdatePhone) currentModelView)
								.getPhoneNumberComboBox()
								.setSelectedItem(phone);
						JOptionPane.showMessageDialog(currentModelView,
								"Phone " + phone + " was updated");
						((UpdatePhone) currentModelView).updateFields();
					}
				});

			}
		}
		System.out.println("Phone " + phone.toString() + " was changed");

	}

	@Override
	public void phoneAdded(final Phone phone) {
		if (currentModelView != null) {
			if (currentModelView instanceof DeletePhone
					|| currentModelView instanceof UpdatePhone) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						((PhonePanel) currentModelView)
								.getPhoneNumberComboBox().addItem(phone);
					}
				});
			}
		}

		System.out.println("Phone " + phone.toString() + " was added");
	}

	@Override
	public void phoneRemoved(final Phone phone) {
		if (currentModelView != null) {
			if (currentModelView instanceof DeletePhone
					|| currentModelView instanceof UpdatePhone) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						
						((PhonePanel) currentModelView)
								.getPhoneNumberComboBox().removeItem(phone);
					}
				});

			}
		}

		System.out.println("Phone " + phone.toString() + " was removed");
	}
}
