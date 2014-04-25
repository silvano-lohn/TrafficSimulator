package br.com.trafficsimulator.commons.components;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class StringTransferable implements Transferable {
	private String string;
	final private DataFlavor[] flavors;

	public StringTransferable(String string) {
		this.string = string;
		this.flavors = new DataFlavor[] { DataFlavor.stringFlavor };
	}

	public String getString() {
		return string;
	}

	@Override
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		if (isDataFlavorSupported(flavor))
			return this.getString();
		else
			return null;
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		return this.flavors;
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return DataFlavor.stringFlavor.equals(flavor);
	}
}
