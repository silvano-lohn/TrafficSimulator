package br.com.trafficsimulator.models;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class BackGround implements GraphicModel {

	private static final int SIZE_MULTIPLICATOR = 20;
	List<List<BackGroundMap>> backGroundMapList = new ArrayList<List<BackGroundMap>>();

	public BackGround() {

	}

	public void addCollumn(List<BackGroundMap> collumn) {
		backGroundMapList.add(collumn);
	}

	public Dimension getDimension() {
		return new Dimension(backGroundMapList.get(0).size() * SIZE_MULTIPLICATOR, backGroundMapList.size() * SIZE_MULTIPLICATOR);
	}

	@Override
	public void paint(Graphics2D g2d) {
		int x = 0;
		int y = 0;

		for (List<BackGroundMap> lines : backGroundMapList) {
			x = 0;
			for (BackGroundMap bgMap : lines) {
				g2d.setColor(bgMap.getColor());
				g2d.fillRect(x, y, SIZE_MULTIPLICATOR, SIZE_MULTIPLICATOR);
				x += SIZE_MULTIPLICATOR;
			}
			y += SIZE_MULTIPLICATOR;
		}

	}

}
