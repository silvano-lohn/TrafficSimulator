package br.com.trafficsimulator.commons;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum ImageType {

	/** */
	_01_CURVE_UP_LEFT(1, "01curveUpLeft.jpg"),
	/** */
	_02_CURVE_UP_RIGHT(2, "02curveUpRight.jpg"),
	/** */
	_03_CURVE_DOWN_LEFT(3, "03curveDownLeft.jpg"),
	/** */
	_04_CURVE_DOWN_RIGHT(4, "04curveDownRight.jpg"),
	/** */
	_05_HORIZONTAL(5, "05horizontal.jpg"),
	/** */
	_06_VERTICAL(6, "06vertical.jpg"),
	/** */
	_07_X_UP(7, "07xUp.jpg"),
	/** */
	_08_X_DOWN(8, "08xDown.jpg"),
	/** */
	_09_X_RIGHT(9, "09xRight.jpg"),
	/** */
	_10_X_LEFT(10, "10xLeft.jpg"),
	/** */
	_11_GROSS(11, "11gross.jpg"),
	/** */
	_12_X_CENTER(12, "12xCenter.jpg");

	private int id;
	private String imageName;

	private ImageType(int id, String imageName) {
		this.id = id;
		this.imageName = imageName;
	}

	public int getId() {
		return id;
	}

	public String getImageName() {
		return imageName;
	}

	public static ImageType getImageType(int id) {
		for (ImageType imageTypte : values()) {
			if (imageTypte.getId() == id) {
				return imageTypte;
			}
		}
		throw new RuntimeException("ImageType não encontrado");
	}

	public Image getImage() throws IOException {
		return ImageIO.read(getClass().getResource("/" + getImageName()));
	}
}
