package br.com.trafficsimulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import br.com.trafficsimulator.commons.components.Canvas;
import br.com.trafficsimulator.commons.map.ImageType;
import br.com.trafficsimulator.commons.utils.Maybe;
import br.com.trafficsimulator.commons.utils.TrafficSimulatorFileUtil;

public class Main extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final String VERSION = "1.0";
    private static final String NAME = "Traffic Simulator";
    private Canvas canvas;

    public Main() {
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setSize(815, 860);
	setLayout(new BorderLayout());
	setTitle(NAME + " " + VERSION);
	initComponents();
	// setResizable(false);
	setVisible(true);
    }

    public void carregarArquivo() {
	canvas.clearMap();
	Maybe<File> maybeFile = TrafficSimulatorFileUtil.loadFile();
	if (maybeFile.hasElement()) {
	    File file = maybeFile.getElement();
	    try {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = br.readLine();
		String[] imageIds = line.split(",");
		int count = 0;
		for (int x = 0; x < 10; x++) {
		    for (int y = 0; y < 10; y++) {
			canvas.setImageOnMap(x, y, ImageType.getImageType(Integer.valueOf(imageIds[count])));
			count++;
		    }
		}
		canvas.repaint();
		br.close();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    private void initComponents() {
	JMenuBar menuBar = new JMenuBar();

	JMenu fileMenu = new JMenu("File");
	JMenu simulatorMenu = new JMenu("Simulator");
	JMenu helpMenu = new JMenu("Help");

	JMenuItem openItem = new JMenuItem("Open Map");
	openItem.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		carregarArquivo();
	    }
	});

	JSeparator separator = new JSeparator();
	JMenuItem exitItem = new JMenuItem("Exit");
	exitItem.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {

	    }
	});

	fileMenu.add(openItem);
	fileMenu.add(separator);
	fileMenu.add(exitItem);

	JMenuItem startSimulatorItem = new JMenuItem("Start");
	startSimulatorItem.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {

	    }
	});

	JMenuItem pauseSimulatorItem = new JMenuItem("Pause");
	pauseSimulatorItem.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {

	    }
	});

	JMenuItem stopSimulatorItem = new JMenuItem("Stop");
	stopSimulatorItem.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {

	    }
	});

	simulatorMenu.add(startSimulatorItem);
	simulatorMenu.add(pauseSimulatorItem);
	simulatorMenu.add(stopSimulatorItem);

	JMenuItem aboutItem = new JMenuItem("About");
	aboutItem.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {

	    }
	});

	helpMenu.add(aboutItem);

	menuBar.add(fileMenu);
	menuBar.add(simulatorMenu);
	menuBar.add(helpMenu);
	add(menuBar, BorderLayout.NORTH);

	canvas = new Canvas();
	canvas.setBackground(Color.WHITE);
	canvas.setSize(800, 800);
	add(canvas, BorderLayout.CENTER);
    }

    public static void main(String args[]) {
	new Main();
    }

}
