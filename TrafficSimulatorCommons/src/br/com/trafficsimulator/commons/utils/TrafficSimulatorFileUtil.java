package br.com.trafficsimulator.commons.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class TrafficSimulatorFileUtil {

    public static Maybe<File> loadFile() {
	JFileChooser fileChooser = new JFileChooser();
	fileChooser.setDialogTitle("Opening simulation map");
	fileChooser.setFileFilter(new TrafficSimulatorFileFilter());
	int userSelection = fileChooser.showOpenDialog(null);
	if (userSelection == JFileChooser.APPROVE_OPTION) {
	    return Maybe.just(fileChooser.getSelectedFile());
	} else {
	    return Maybe.nothing();
	}
    }

    public static void saveFile(StringBuilder sb) {
	JFileChooser fileChooser = new JFileChooser();
	fileChooser.setDialogTitle("Saving simulation map");
	fileChooser.setFileFilter(new TrafficSimulatorFileFilter());
	int userSelection = fileChooser.showSaveDialog(null);
	if (userSelection == JFileChooser.APPROVE_OPTION) {
	    File file = new File(fileChooser.getSelectedFile() + ".tsm");
	    try {
		FileWriter fw = new FileWriter(file);
		fw.append(sb.toString());
		fw.flush();
		fw.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }
}

class TrafficSimulatorFileFilter extends FileFilter {

    @Override
    public String getDescription() {
	return "Traffic Simulator Map (*.tsm)";
    }

    @Override
    public boolean accept(File file) {
	if (file.getName().contains(".tsm")) {
	    return true;
	}
	return false;
    }
}
