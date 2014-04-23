package br.com.trafficsimulator.models;

import java.awt.Color;

public enum BackGroundMap {

	/**
	 * 
	 */
	NOTHING(0, Color.BLACK),
	/**
	 * 
	 */
	STREET_LEFT(1, Color.YELLOW),
	/**
	 * 
	 */
	STREET_RIGHT(2, Color.RED),
	/**
	 * 
	 */
	STREET_UP(3, Color.GREEN),
	/**
	 * 
	 */
	STREET_DOWN(4, Color.BLUE),
	/**
	 * 
	 */
	STREET(5, Color.WHITE);

	private int id;
	private Color color;

	private BackGroundMap(int id, Color color) {
		this.id = id;
		this.color = color;
	}

	public int getId() {
		return id;
	}

	public Color getColor() {
		return color;
	}

	public static BackGroundMap getBackGroundMapFromString(String value) {
		int v = Integer.parseInt(value, 16);
		for (BackGroundMap bgMap : values()) {
			if (bgMap.getId() == v) {
				return bgMap;
			}
		}
		return NOTHING;
	}

}
