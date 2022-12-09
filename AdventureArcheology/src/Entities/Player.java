// **********************************************************************************
// Title: AdventureArcheology
// Author: Benjamin C. Konczal
// Course Section: CMIS201-ONL1 (Seidel) Fall 2022
// File: Player.java
// Description: This file handles the player's character
// **********************************************************************************

package Entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import Main.GamePanel;
import Main.KeyHandler;

public class Player extends Entities{

	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp);
		
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 12; 
		solidArea.y = 20; 
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 24; 
		solidArea.height = 24; 
		
		setDefaultValues();
		getPlayerImage();
	}
	
	// Player Character's Starting Position
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 4;
		direction = "down";
	}
	
	public void getPlayerImage() {
				
		up1 = setup("/player/boy_up_1");
		up2 = setup("/player/boy_up_2");
		down1 = setup("/player/boy_down_1");
		down2 = setup("/player/boy_down_2");
		left1 = setup("/player/boy_left_1");
		left2 = setup("/player/boy_left_2");
		right1 = setup("/player/boy_right_1");
		right2 = setup("/player/boy_right_2");
	}
		
	public void update() {
		
		if(keyH.upPressed == true || keyH.downPressed == true ||
				keyH.leftPressed == true || keyH.rightPressed == true) {
			
			if(keyH.upPressed == true) {
				direction = "up";
			}
			
			else if(keyH.downPressed == true) {
				direction = "down";
			}
			
			else if(keyH.leftPressed == true) {
				direction = "left";
			}
			
			else if(keyH.rightPressed == true) {
				direction = "right";
			}
			
			// Check Tile Collision
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			// Check Object Collision
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			// NPC Collision
			int npcIndex = gp.cChecker.checkEntities(this, gp.npc);
			NPCInteraction(npcIndex);
			
			// If Collision Is False, Player Can Move
			if(collisionOn == false) {
				
				switch(direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;		
					break;
				case "left":
					worldX -= speed;					
					break;
				case "right":
					worldX += speed;							
					break;
				}
			}
			
			spriteCounter++;
			if(spriteCounter > 13) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				
				else if(spriteNum == 2) {
					spriteNum = 1;	
				}
				
				spriteCounter = 0;
			}
		}
	}
	
	public void pickUpObject(int i) {
		
		if(i != 999) {
			
		}
	}
	
	public void NPCInteraction(int i) {
		
		if(i != 999) {

			if(gp.keyH.actionPressed == true) {
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();
			}
		}
		
		gp.keyH.actionPressed = false;
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
			image = up1;
			}
			if(spriteNum ==2) {
			image = up2;	
			}
			break;
		case "down":
			if(spriteNum == 1) {
			image = down1;
			}
			if(spriteNum ==2) {
			image = down2;	
			}
			break;
		case "left":
			if(spriteNum == 1) {
			image = left1;
			}
			if(spriteNum ==2) {
			image = left2;	
			}
			break;
		case "right":
			if(spriteNum == 1) {
			image = right1;
			}
			if(spriteNum ==2) {
			image = right2;	
			}
			break;
		}
		
		g2.drawImage(image, screenX, screenY, null);
	}
}
