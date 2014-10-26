package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.Model;

public class ModelController {
	private static Model model = Model.getInstance();
	private static ModelController instance;

	public static ModelController getInstance() {
		if (instance == null) {
			instance = new ModelController();
		}
		return instance;

	}

	public void setModel(File file) throws FileNotFoundException, IOException,
			ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
		model = (Model) in.readObject();
		in.close();
	}

	public void saveModel() throws FileNotFoundException, IOException {
		File f = new File("D:\\afsd");
		f.getAbsolutePath();
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
		out.writeObject(model);
		out.flush();
		out.close();
	}

}
