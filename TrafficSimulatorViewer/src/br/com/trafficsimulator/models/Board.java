package br.com.trafficsimulator.models;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JPanel;

import br.com.trafficsimulator.utils.BackGroundUtils;

public class Board extends JPanel implements Runnable {

	private List<GraphicModel> graphicModels = new ArrayList<GraphicModel>();
	private Thread thread;
	private AtomicBoolean running;
	private BackGround backGround;

	public Board(AtomicBoolean running) {
		this.running = running;
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		for (GraphicModel gm : graphicModels) {
			gm.paint(g2d);
		}
		g2d.dispose();
	}

	public void setBackGround(String backGroundName) {
		backGround = BackGroundUtils.importBackGround(backGroundName);
		setSize(backGround.getDimension());
		addGraphicModel(backGround);
	}

	public Dimension getBackGroundSize() {
		return backGround.getDimension();
	}

	public void startRepaint() {
		thread = new Thread(this);
		thread.start();
	}

	public void stopRepaint() {

	}

	public void addGraphicModel(GraphicModel gm) {
		graphicModels.add(gm);
	}

	@Override
	public void run() {
		while (running.get()) {
			long time = System.currentTimeMillis();
			repaint();
			try {
				Thread.sleep(16 - (System.currentTimeMillis() - time));
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
}
