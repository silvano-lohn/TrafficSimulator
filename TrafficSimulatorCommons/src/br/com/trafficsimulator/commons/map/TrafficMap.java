package br.com.trafficsimulator.commons.map;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.trafficsimulator.commons.utils.ResultMessage;

public class TrafficMap {

    private List<ImageType> imageList;
    private int mapSize;
    private int sqrt;

    public TrafficMap(int mapSize) {
	this.mapSize = mapSize;
	sqrt = (int) Math.sqrt(mapSize);
	initTrafficMap();
    }

    public void setImage(int x, int y, ImageType imageType) {
	imageList.set(convertCoordinateToIndex(x, y), imageType);
    }

    public ImageType getImage(int x, int y) {
	return imageList.get(convertCoordinateToIndex(x, y));
    }

    private int convertCoordinateToIndex(int x, int y) {
	return x * sqrt + y;
    }

    public void clearTrafficMap() {
	for (int index = 0; index < mapSize; index++) {
	    imageList.set(index, ImageType._11_GROSS);
	}
    }

    private void initTrafficMap() {
	imageList = new ArrayList<ImageType>(mapSize);
	for (int index = 0; index < mapSize; index++) {
	    imageList.add(ImageType._11_GROSS);
	}
    }

    public ResultMessage checkMapIsOk() {
	if (!imageList.contains(ImageType._14_HORIZONTAL_START)) {
	    return ResultMessage.ERROR("The map must have a start point!");
	}
	if (!imageList.contains(ImageType._13_HORIZONTAL_FINISH)) {
	    return ResultMessage.ERROR("The map must have a finish point!");
	}
	return ResultMessage.OK();
    }

    public void paint(Graphics2D g2d) {

	for (int index = 0; index < mapSize; index++) {
	    Image image;
	    try {
		image = imageList.get(index).getImage();
		g2d.drawImage(image, (index / sqrt) * 80, (index % sqrt) * 80, null);
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }
}
