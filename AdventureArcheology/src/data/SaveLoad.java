// **********************************************************************************
// Title: AdventureArcheology
// Author: Benjamin C. Konczal
// Course Section: CMIS201-ONL1 (Seidel) Fall 2022
// File: SaveLoad.java
// Description: This file handles the saving and loading methods to read/write data in a file.
// **********************************************************************************

package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Main.GamePanel;

public class SaveLoad {
	
	GamePanel gp;
	
	public SaveLoad(GamePanel gp) {
		this.gp = gp;
		
	}
	
	public void save() {
	
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
			
			DataStorage ds = new DataStorage();
			
			ds.worldX = gp.player.worldX;
			ds.worldY = gp.player.worldY;
			
			// Write The DataStorage Object
			oos.writeObject(ds);
			
		} catch(Exception e) {
			
			System.out.println("Save Exception!");
		}
	}
	
	public void load() {
		
		try {
			
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));
			
			// Read The DataStorage Object
			DataStorage ds = (DataStorage)ois.readObject();
			
			gp.player.worldX = ds.worldX;
			gp.player.worldY = ds.worldY;
			
		} catch(Exception e) {
			System.out.println("Load Exception!");
		}
	}
}
