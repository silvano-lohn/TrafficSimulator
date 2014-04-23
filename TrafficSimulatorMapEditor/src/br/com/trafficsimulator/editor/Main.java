package br.com.trafficsimulator.editor;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.com.trafficsimulator.commons.ImageType;

public class Main extends JFrame {

	private static final String VERSION = "1.0";

	public Main() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(990, 830);
		setLayout(null);
		setTitle("Traffic Simulator Map Editor " + VERSION);
		initComponents();
		setResizable(false);
		setVisible(true);

	}

	private void initComponents() {

		final DropJPanel canvasPanel = new DropJPanel();
		canvasPanel.setBackground(Color.WHITE);
		canvasPanel.setBounds(0, 0, 800, 800);
		add(canvasPanel);

		JPanel imagesPanel = new JPanel();
		imagesPanel.setBackground(Color.WHITE);
		imagesPanel.setBounds(810, 0, 164, 500);
		imagesPanel.setLayout(new GridLayout(0, 2));
		loadImages(imagesPanel);
		add(imagesPanel);

		JButton abrir = new JButton("Abrir");
		abrir.setBounds(805, 510, 80, 40);
		abrir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				canvasPanel.abrir();
			}
		});
		
		JButton salvar = new JButton("Salvar");
		salvar.setBounds(900, 510, 80, 40);
		salvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				canvasPanel.salvar();
			}
		});

		JButton limparMapa = new JButton("Limpar");
		limparMapa.setBounds(805, 560, 80, 40);
		limparMapa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				canvasPanel.limpar();
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

	public static void main(String args[]) {
		new Main();
	}
}
