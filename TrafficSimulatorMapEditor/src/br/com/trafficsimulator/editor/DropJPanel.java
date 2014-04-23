package br.com.trafficsimulator.editor;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

import br.com.trafficsimulator.commons.ImageType;

public class DropJPanel extends JPanel implements DropTargetListener {

	private ImageType[][] images = new ImageType[10][10];

	public DropJPanel() {
		new DropTarget(this, this);
		initImages();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();

		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				Image image;
				try {
					image = images[x][y].getImage();
					if (image != null) {
						g2d.drawImage(image, x * 80, y * 80, null);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
		g2d.dispose();

	}

	public void abrir() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Abrindo um mapa de simulação");
		fileChooser.setFileFilter(new FileFilter() {

			@Override
			public String getDescription() {
				return "Traffic Simulator Map (*.tsm)";
			}

			@Override
			public boolean accept(File file) {
				if (file.getName().contains(".tsm")) {
					return true;
				}
				return false;
			}
		});

		int userSelection = fileChooser.showOpenDialog(this);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			limpar();
			File file = fileChooser.getSelectedFile();
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line = br.readLine();
				String[] imageIds = line.split(",");
				int count = 0;
				for (int x = 0; x < 10; x++) {
					for (int y = 0; y < 10; y++) {
						images[x][y] = ImageType.getImageType(Integer.valueOf(imageIds[count]));
						count++;
					}
				}
				repaint();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void salvar() {

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Salvando um mapa de simulação");
		fileChooser.setFileFilter(new FileFilter() {

			@Override
			public String getDescription() {
				return "Traffic Simulator Map (*.tsm)";
			}

			@Override
			public boolean accept(File file) {
				if (file.getName().contains(".tsm")) {
					return true;
				}
				return false;
			}
		});
		int userSelection = fileChooser.showSaveDialog(this);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File file = new File(fileChooser.getSelectedFile() + ".tsm");
			StringBuilder sb = new StringBuilder();
			for (int x = 0; x < 10; x++) {
				for (int y = 0; y < 10; y++) {
					sb.append(images[x][y].getId()).append(",");
				}
			}
			try {
				FileWriter fw = new FileWriter(file);
				fw.append(sb.toString());
				fw.flush();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void limpar() {
		initImages();
		repaint();
	}

	private void initImages() {
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				images[x][y] = ImageType._11_GROSS;
			}
		}
	}

	@Override
	public void dragEnter(DropTargetDragEvent arg0) {

	}

	@Override
	public void dragExit(DropTargetEvent arg0) {

	}

	@Override
	public void dragOver(DropTargetDragEvent arg0) {

	}

	@Override
	public void drop(DropTargetDropEvent evt) {
		Transferable transferable = evt.getTransferable();
		String string = null;
		try {
			string = (String) transferable.getTransferData(DataFlavor.stringFlavor);
			Point point = evt.getLocation();
			images[(int) point.getX() / 80][(int) point.getY() / 80] = ImageType.getImageType(Integer.parseInt(string));
			repaint();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (string != null) {
			evt.getDropTargetContext().dropComplete(true);
		} else {
			evt.rejectDrop();
		}
	}

	@Override
	public void dropActionChanged(DropTargetDragEvent arg0) {

	}
}
