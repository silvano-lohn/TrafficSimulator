package br.com.trafficsimulator.commons.components;

import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;

import br.com.trafficsimulator.commons.map.ImageType;

public class DropCanvas extends Canvas implements DropTargetListener {

    private static final long serialVersionUID = 1L;
    private static final int IMAGE_SIZE = 80; // 80/80px

    public DropCanvas() {
	super();
	new DropTarget(this, this);
    }

    @Override
    public void dragEnter(DropTargetDragEvent arg0) {

    }

    @Override
    public void dragExit(DropTargetEvent arg0) {

    }

    @Override
    public void dragOver(DropTargetDragEvent arg0) {

    }

    @Override
    public void drop(DropTargetDropEvent evt) {
	Transferable transferable = evt.getTransferable();
	String string = null;
	try {
	    string = (String) transferable.getTransferData(DataFlavor.stringFlavor);
	    Point point = evt.getLocation();
	    setImageOnMap((int) point.getX() / IMAGE_SIZE, (int) point.getY() / IMAGE_SIZE, ImageType.getImageType(Integer.parseInt(string)));
	    repaint();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	if (string != null) {
	    evt.getDropTargetContext().dropComplete(true);
	} else {
	    evt.rejectDrop();
	}
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent arg0) {

    }
}
