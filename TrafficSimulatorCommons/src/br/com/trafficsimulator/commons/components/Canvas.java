package br.com.trafficsimulator.commons.components;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import br.com.trafficsimulator.commons.map.ImageType;
import br.com.trafficsimulator.commons.map.TrafficMap;
import br.com.trafficsimulator.commons.utils.ResultMessage;

public class Canvas extends JPanel {

    private static final int MAX_MAP_SIZE = 100; // 10x10
    private TrafficMap trafficMap = new TrafficMap(MAX_MAP_SIZE);
    private static final long serialVersionUID = 1L;

    public Canvas() {
	repaint();
    }

    public void setImageOnMap(int x, int y, ImageType image) {
	trafficMap.setImage(x, y, image);
    }

    public ImageType getImageFromMap(int x, int y) {
	return trafficMap.getImage(x, y);
    }

    public TrafficMap getMap() {
	return trafficMap;
    }

    @Override
    public void paint(Graphics g) {
	Graphics2D g2d = (Graphics2D) g.create();
	trafficMap.paint(g2d);
	g2d.dispose();
    }

    public void clearMap() {
	trafficMap.clearTrafficMap();
	repaint();
    }

    public ResultMessage checkMapIsOk() {
	return trafficMap.checkMapIsOk();
    }
}
