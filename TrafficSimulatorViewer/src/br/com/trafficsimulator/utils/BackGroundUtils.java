package br.com.trafficsimulator.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.trafficsimulator.models.BackGround;
import br.com.trafficsimulator.models.BackGroundMap;

public class BackGroundUtils {

	public static BackGround importBackGround(String backgroundName) {
		File file = new File("maps/" + backgroundName);
		if (!file.exists()) {
			throw new RuntimeException("Background não encontrado: " + backgroundName);
		}

		BackGround backGround = new BackGround();

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				List<BackGroundMap> collumns = new ArrayList<BackGroundMap>();
				for (String value : values) {
					collumns.add(BackGroundMap.getBackGroundMapFromString(value));
				}
				backGround.addCollumn(collumns);
			}
			br.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return backGround;
	}

}
