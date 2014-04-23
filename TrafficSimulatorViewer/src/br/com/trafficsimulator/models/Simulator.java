package br.com.trafficsimulator.models;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JFrame;

public abstract class Simulator extends JFrame implements Runnable {

	private Board board;
	private Thread thread;
	private AtomicBoolean running = new AtomicBoolean(false);

	public Simulator() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		board = new Board(running);
		Container contents = getContentPane();
		contents.add(board, BorderLayout.CENTER);		
	}

	@Override
	public void run() {
		while (running.get()) {

		}
	}

	public void start(String backgroundName) {
		board.setBackGround(backgroundName);
		setPreferredSize(new Dimension(1000,1000));
		pack();
		setVisible(true);
		if (running.compareAndSet(false, true)) {
			thread = new Thread(this);
			thread.start();
			board.startRepaint();
		}
	}

	public void stop() {
		if (running.compareAndSet(true, false)) {
			System.exit(0);
		}
	}
}
