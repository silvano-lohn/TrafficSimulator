package br.com.trafficsimulator.editor;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import br.com.trafficsimulator.commons.ImageType;
import br.com.trafficsimulator.commons.components.DraggableLabel;
import br.com.trafficsimulator.commons.components.DropCanvas;
import br.com.trafficsimulator.commons.utils.Maybe;
import br.com.trafficsimulator.commons.utils.TrafficSimulatorFileUtil;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String VERSION = "1.0";
	private static final String NAME = "Traffic Simulator Map Editor";
	private DropCanvas dropCanvas;

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
		dropCanvas = new DropCanvas();
		dropCanvas.setBackground(Color.WHITE);
		dropCanvas.setBounds(0, 0, 800, 800);
		add(dropCanvas);

		JPanel imagesPanel = new JPanel();
		imagesPanel.setBackground(Color.WHITE);
		imagesPanel.setBounds(810, 0, 170, 570);
		imagesPanel.setLayout(new GridLayout(0, 2));
		loadImages(imagesPanel);
		add(imagesPanel);

		JButton abrir = new JButton("Abrir");
		abrir.setBounds(805, 570, 80, 40);
		abrir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				carregarArquivo();
			}
		});

		JButton salvar = new JButton("Salvar");
		salvar.setBounds(900, 570, 80, 40);
		salvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				salvarArquivo();
			}
		});

		JButton limparMapa = new JButton("Limpar");
		limparMapa.setBounds(805, 620, 80, 40);
		limparMapa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dropCanvas.limpar();
			}
		});

		add(abrir);
		add(salvar);
		add(limparMapa);
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

	public void carregarArquivo() {
		dropCanvas.limpar();
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
						dropCanvas.getImages()[x][y] = ImageType
								.getImageType(Integer.valueOf(imageIds[count]));
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

	public void salvarArquivo() {
		StringBuilder sb = new StringBuilder();
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				sb.append(dropCanvas.getImages()[x][y].getId()).append(",");
			}
		}

		TrafficSimulatorFileUtil.saveFile(sb);
	}

	public static void main(String args[]) {
		new Main();
	}
}
