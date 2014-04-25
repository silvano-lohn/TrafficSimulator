package br.com.trafficsimulator.view;

import java.awt.Color;

import javax.swing.JFrame;

import br.com.trafficsimulator.commons.components.Canvas;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String VERSION = "1.0";
	private static final String NAME = "Traffic Simulator";
	private Canvas canvas;

	public Main() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(990, 830);
		setLayout(null);
		setTitle(NAME + " " + VERSION);
		initComponents();
		setResizable(false);
		setVisible(true);
	}

	private void initComponents() {
		canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		canvas.setBounds(0, 0, 800, 800);
		add(canvas);
	}

	public static void main(String args[]) {
		new Main();
	}

}
