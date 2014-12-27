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
		lockedObject=null;
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
		    }
		});
	}

	@Override
	public void subscriberChanged(Subscriber subscriber) {
		if (currentModelView != null
				&& currentModelView instanceof DeleteSubscriber) {

		}

	}

	@Override
	public void subscriberAdded(Subscriber subscriber) {
		// TODO Auto-generated method stub

	}

	@Override
	public void subscriberRemoved(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void phoneChanged(Phone phone) {
		// TODO Auto-generated method stub

	}

	@Override
	public void phoneAdded(Phone phone) {
	 //JOptionPane.showInternalMessageDialog(this,"Phone "+ phone.toString() +" was added" );
System.out.println("Phone "+ phone.toString() +" was added" );
	}

	@Override
	public void phoneRemoved(Long id) {
		//JOptionPane.showInternalMessageDialog(this,"Phone "+ controller.getPhone(id).toString() +" was deleted" );
	}
}
