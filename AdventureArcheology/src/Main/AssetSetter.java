// **********************************************************************************
// Title: AdventureArcheology
// Author: Benjamin C. Konczal
// Course Section: CMIS201-ONL1 (Seidel) Fall 2022
// File: AssetSetter.java
// Description: This file handles in game assets.
// **********************************************************************************

package Main;

import Entities.NPC_OldMan;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {

	}
	public void setNPC() {
		
		gp.npc[0] = new NPC_OldMan(gp);
		gp.npc[0].worldX = gp.tileSize*21;
		gp.npc[0].worldY = gp.tileSize*21;

	}
}
