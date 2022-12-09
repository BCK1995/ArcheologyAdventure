// **********************************************************************************
// Title: AdventureArcheology
// Author: Benjamin C. Konczal
// Course Section: CMIS201-ONL1 (Seidel) Fall 2022
// File: CollisionChecker.java
// Description: This file handles the game's collision mechanics
// **********************************************************************************

package Main;

import Entities.Entities;

public class CollisionChecker {
	
	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entities entities) {
		
		int entityLeftWorldX = entities.worldX + entities.solidArea.x;
		int entityRightWorldX = entities.worldX + entities.solidArea.x + entities.solidArea.width;
		int entityTopWorldY = entities.worldY + entities.solidArea.y;
		int entityBottomWorldY = entities.worldY + entities.solidArea.y + entities.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		int tileNum1, tileNum2;
		
		switch(entities.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entities.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entities.collisionOn = true;
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entities.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entities.collisionOn = true;
			}			
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entities.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entities.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entities.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entities.collisionOn = true;
			}
			break;
		}
	}
	
	public int checkObject(Entities entities, boolean player) {
		
		int index = 999;
		
		for(int i = 0; i < gp.obj.length; i++) {
			
			if(gp.obj[i] != null) {
				
				// Get Entity's Solid Area Position
				entities.solidArea.x = entities.worldX + entities.solidArea.x;
				entities.solidArea.y = entities.worldY + entities.solidArea.y;
		
				// Get The Object's Solid Area Position
				gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
				
				switch(entities.direction) {
				case "up":
					entities.solidArea.y -= entities.speed;
					if(entities.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision == true) {
							entities.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "down":
					entities.solidArea.y += entities.speed;
					if(entities.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision == true) {
							entities.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "left":
					entities.solidArea.x -= entities.speed;
					if(entities.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision == true) {
							entities.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "right":
					entities.solidArea.x += entities.speed;
					if(entities.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision == true) {
							entities.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					break;
					}
				}

				entities.solidArea.x = entities.solidAreaDefaultX;
				entities.solidArea.y = entities.solidAreaDefaultY;
				gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;			
			}			
		}		
	
		return index;
	}
	
	// NPC and Monster
	public int checkEntities(Entities entities, Entities[] target) {
		
		int index = 999;
		
		for(int i = 0; i < target.length; i++) {
			
			if(target[i] != null) {
				
				// Get Entity's Solid Area Position
				entities.solidArea.x = entities.worldX + entities.solidArea.x;
				entities.solidArea.y = entities.worldY + entities.solidArea.y;
		
				// Get The Object's Solid Area Position
				target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
				target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;
				
				switch(entities.direction) {
				case "up":
					entities.solidArea.y -= entities.speed;
					if(entities.solidArea.intersects(target[i].solidArea)) {
							entities.collisionOn = true;
							index = i;
							}
					break;
				case "down":
					entities.solidArea.y += entities.speed;
					if(entities.solidArea.intersects(target[i].solidArea)) {
							entities.collisionOn = true;
							index = i;
							}
					break;
				case "left":
					entities.solidArea.x -= entities.speed;
					if(entities.solidArea.intersects(target[i].solidArea)) {
							entities.collisionOn = true;
							index = i;
							}
					break;
				case "right":
					entities.solidArea.x += entities.speed;
					if(entities.solidArea.intersects(target[i].solidArea)) {
							entities.collisionOn = true;
							index = i;
							}
					break;
					}
				
				entities.solidArea.x = entities.solidAreaDefaultX;
				entities.solidArea.y = entities.solidAreaDefaultY;
				target[i].solidArea.x = target[i].solidAreaDefaultX;
				target[i].solidArea.y = target[i].solidAreaDefaultY;					
			}
		}
		
		return index;
	}
	
	public void checkPlayer(Entities entities) {
		
	// Get Entity's Solid Area Position
	entities.solidArea.x = entities.worldX + entities.solidArea.x;
	entities.solidArea.y = entities.worldY + entities.solidArea.y;

	// Get The Object's Solid Area Position
	gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
	gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
	
	switch(entities.direction) {
	case "up":
		entities.solidArea.y -= entities.speed;
		if(entities.solidArea.intersects(gp.player.solidArea)) {
				entities.collisionOn = true;
				}
		break;
	case "down":
		entities.solidArea.y += entities.speed;
		if(entities.solidArea.intersects(gp.player.solidArea)) {
				entities.collisionOn = true;
				}
		break;
	case "left":
		entities.solidArea.x -= entities.speed;
		if(entities.solidArea.intersects(gp.player.solidArea)) {
				entities.collisionOn = true;
				}
		break;
	case "right":
		entities.solidArea.x += entities.speed;
		if(entities.solidArea.intersects(gp.player.solidArea)) {
				entities.collisionOn = true;
				}
		break;
		}
	
	entities.solidArea.x = entities.solidAreaDefaultX;
	entities.solidArea.y = entities.solidAreaDefaultY;
	gp.player.solidArea.x = gp.player.solidAreaDefaultX;
	gp.player.solidArea.y = gp.player.solidAreaDefaultY;				

	}
}
