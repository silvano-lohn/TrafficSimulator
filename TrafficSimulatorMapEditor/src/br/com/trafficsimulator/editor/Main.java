package br.com.trafficsimulator.editor;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.com.trafficsimulator.commons.components.DraggableLabel;
import br.com.trafficsimulator.commons.components.DropCanvas;
import br.com.trafficsimulator.commons.map.ImageType;
import br.com.trafficsimulator.commons.utils.Maybe;
import br.com.trafficsimulator.commons.utils.ResultMessage;
import br.com.trafficsimulator.commons.utils.TrafficSimulatorFileUtil;

public class Main extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final String VERSION = "1.0";
    private static final String NAME = "Traffic Simulator Map Editor";
    private DropCanvas dropCanvas;

    public Main() {
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setSize(996, 860);
	setLayout(null);
	setTitle(NAME + " " + VERSION);
	initComponents();
	setResizable(false);
	repaint();
	setVisible(true);
    }

    private void initComponents() {

	JMenuBar menuBar = new JMenuBar();
	JMenu fileMenu = new JMenu("File");
	JMenu mapMenu = new JMenu("Map");
	JMenu helpMenu = new JMenu("Help");

	JMenuItem exitItem = new JMenuItem("Exit");
	exitItem.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		System.exit(0);
	    }
	});

	fileMenu.add(exitItem);

	JMenuItem openItem = new JMenuItem("Open Map");
	openItem.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		loadFile();
	    }
	});

	JMenuItem saveItem = new JMenuItem("Save Map");
	saveItem.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		saveFile();
	    }
	});

	JMenuItem clearItem = new JMenuItem("Clear Map");
	clearItem.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		dropCanvas.clearMap();
	    }
	});

	mapMenu.add(openItem);
	mapMenu.add(saveItem);
	mapMenu.add(clearItem);

	JMenuItem aboutItem = new JMenuItem("About");
	aboutItem.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {

	    }
	});

	helpMenu.add(aboutItem);

	menuBar.add(fileMenu);
	menuBar.add(mapMenu);
	menuBar.add(helpMenu);
	menuBar.setBounds(0, 0, 996, 25);
	add(menuBar);

	dropCanvas = new DropCanvas();
	dropCanvas.setBounds(0, 25, 800, 800);
	add(dropCanvas);

	JPanel toolBox = new JPanel();
	toolBox.setLayout(new FlowLayout());
	toolBox.setBounds(801, 25, 180, 860);
	loadImages(toolBox);
	add(toolBox);
    }

    private void loadImages(JPanel panel) {

	for (ImageType imageType : ImageType.values()) {
	    DraggableLabel label;
	    try {
		label = new DraggableLabel(imageType);
		label.setSize(80, 80);
		panel.add(label);
	    } catch (IOException e) {
		e.printStackTrace();
	    }

	}
    }

    private void loadFile() {
	dropCanvas.clearMap();
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
			dropCanvas.setImageOnMap(x, y, ImageType.getImageType(Integer.valueOf(imageIds[count])));
			count++;
		    }
		}
		dropCanvas.repaint();
		br.close();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    private void saveFile() {
	ResultMessage resultMessage = dropCanvas.checkMapIsOk();
	if (!resultMessage.isOk()) {
	    JOptionPane.showMessageDialog(this, resultMessage.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	} else {
	    StringBuilder sb = new StringBuilder();
	    for (int x = 0; x < 10; x++) {
		for (int y = 0; y < 10; y++) {
		    sb.append(dropCanvas.getImageFromMap(x, y).getId()).append(",");
		}
	    }
	    TrafficSimulatorFileUtil.saveFile(sb);
	}
    }

    public static void main(String args[]) {
	new Main();
    }
}
