package br.com.trafficsimulator.commons.components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.swing.JPanel;

import br.com.trafficsimulator.commons.ImageType;

public class Canvas extends JPanel {

	private static final long serialVersionUID = 1L;

	private ImageType[][] images = new ImageType[10][10];

	public Canvas() {
		initImages();
	}

	public ImageType[][] getImages() {
		return images;
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
}
