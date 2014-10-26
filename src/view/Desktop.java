package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import controller.ModelController;
import controller.PhoneController;

public class Desktop extends JFrame {

	private static final long serialVersionUID = 1L;
	private static Desktop desktopInstance;
	private static PhoneController phoneController = PhoneController
			.getInstance();
	private static ModelController modelController = ModelController
			.getInstance();

	public Desktop() {
		super();
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
				desktopInstance.add(new CreatePhone());
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
				desktopInstance.getContentPane().removeAll();
				desktopInstance.add(new UpdatePhone());
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
				desktopInstance.getContentPane().removeAll();
				desktopInstance.add(new DeletePhone());
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
						desktopInstance.getContentPane().removeAll();
						desktopInstance.add(new CreateSubscriber());
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
						desktopInstance.getContentPane().removeAll();
						desktopInstance.add(new UpdateSubscriber());
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
						desktopInstance.getContentPane().removeAll();
						desktopInstance.add(new DeleteSubscriber());
						paintComponents(getGraphics());
					}
				});
		JMenuItem saveModel = new JMenuItem(new AbstractAction("Save") {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ModelController.getInstance().saveModel();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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

					try {
						modelController.setModel(
								chooseFile.getSelectedFile());
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});

		modelMenu.add(saveModel);
		modelMenu.add(uploadModel);
		phoneMenu.add(createPhone);
		phoneMenu.add(updatePhone);
		phoneMenu.add(deletePhone);
		subscriberMenu.add(createSubscriber);
		subscriberMenu.add(updateSubscriber);
		subscriberMenu.add(deleteSubscriber);
		fileMenuBar.add(modelMenu);
		fileMenuBar.add(phoneMenu);
		fileMenuBar.add(subscriberMenu);
		this.setJMenuBar(fileMenuBar);
		desktopInstance = this;

	}
}
