// **********************************************************************************
// Title: AdventureArcheology
// Author: Benjamin C. Konczal
// Course Section: CMIS201-ONL1 (Seidel) Fall 2022
// File: NPC_OldMan.java
// Description: This file handles the specifics for a particular NPC
// **********************************************************************************

package Entities;

import java.util.Random;

import Main.GamePanel;

public class NPC_OldMan extends Entities {

	
	public NPC_OldMan(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		
		getImage();
		setDialogue();
	}

	public void getImage() {
		
		up1 = setup("/npc/oldman_up_1");
		up2 = setup("/npc/oldman_up_2");
		down1 = setup("/npc/oldman_down_1");
		down2 = setup("/npc/oldman_down_2");
		left1 = setup("/npc/oldman_left_1");
		left2 = setup("/npc/oldman_left_2");
		right1 = setup("/npc/oldman_right_1");
		right2 = setup("/npc/oldman_right_2");
	}
	
	public void setDialogue() {
		
		dialogues[0] = "What, where am I? ...";
		dialogues[1] = "What, who am I? What am I supposed to be doing? \n I don't remember...";
		dialogues[2] = "What, what am I? ...";
		dialogues[3] = "What, year is it? ...";

	}
	
	public void setAction() {
		
		actionCountDown++;
		
		if(actionCountDown == 120) {
			
			Random random = new Random();
			int r = random.nextInt(4); // Picks a number to determine NPC action
			
			switch(r) {
			case 0:
				direction = "up";
				break;
			case 1:
				direction = "down";
				break;
			case 2:
				direction = "left";
				break;
			case 3:
				direction = "right";
				break;
			}
						
			actionCountDown = 0;
		}
	}	
	
	public void speak() {
			
		super.speak();
	}
}
