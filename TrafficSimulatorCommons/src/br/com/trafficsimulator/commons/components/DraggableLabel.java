package br.com.trafficsimulator.commons.components;

import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import br.com.trafficsimulator.commons.ImageType;

public class DraggableLabel extends JLabel implements DragGestureListener,
		DragSourceListener {

	private static final long serialVersionUID = 1L;
	private DragSource dragSource;
	private ImageType imageType;

	public DraggableLabel(ImageType imageType) throws IOException {
		super(new ImageIcon(imageType.getImage()));
		this.imageType = imageType;
		dragSource = new DragSource();
		dragSource.createDefaultDragGestureRecognizer(this,
				DnDConstants.ACTION_COPY_OR_MOVE, this);
	}

	public void dragGestureRecognized(DragGestureEvent evt) {
		Transferable transferable = new StringTransferable(
				String.valueOf(imageType.getId()));
		dragSource.startDrag(evt, DragSource.DefaultCopyDrop, transferable,
				this);

	}

	public void dragDropEnd(DragSourceDropEvent evt) {

	}

	@Override
	public void dragEnter(DragSourceDragEvent dsde) {

	}

	@Override
	public void dragExit(DragSourceEvent dse) {

	}

	@Override
	public void dragOver(DragSourceDragEvent dsde) {

	}

	@Override
	public void dropActionChanged(DragSourceDragEvent dsde) {

	}
}