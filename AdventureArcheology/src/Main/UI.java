// **********************************************************************************
// Title: AdventureArcheology
// Author: Benjamin C. Konczal
// Course Section: CMIS201-ONL1 (Seidel) Fall 2022
// File: UI.java
// Description: This file handles the game's user interface.
// **********************************************************************************

package Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI {
	
	GamePanel gp;
	Graphics2D g2;
	Font arial_40, arial_80B;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	public String currentDialogue = "";
	public int commandNum = 0;
		
	public UI(GamePanel gp) {
		this.gp = gp;
		
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 80);
		
	}
	
	public void showMessage(String text) {
		
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		
		g2.setFont(arial_40);
		g2.setColor(Color.white);
	
		// Title State
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		// Play State
		if(gp.gameState == gp.playState) {
			// Fill In Later
		}
		
		// Pause State
		if(gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
		
		// Dialog State
		if(gp.gameState == gp.dialogueState) {
			drawDialogueScreen();
		}
	}
	
	public void drawTitleScreen() {
		
		g2.setColor(new Color(100, 75, 50));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		//Title Name
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
		String text = "ARCHEOLOGY ADVENTURE";
		int x = getXForCenteredText(text);
		int y = gp.tileSize*3;
		
		// Shadows For Title Text
		g2.setColor(Color.black);
		g2.drawString(text, x + 7, y + 7);
		
		// Main Color
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		// Title Character Image
		x = gp.screenWidth/2 - (gp.tileSize);
		y += gp.tileSize*3;
		g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);
		
		// Title Menu Options
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
		
		text = "NEW GAME";
		x = getXForCenteredText(text);
		y += gp.tileSize*4;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x - gp.tileSize, y);
		}
		
		text = "LOAD GAME";
		x = getXForCenteredText(text);
		y += gp.tileSize*2;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString(">", x - gp.tileSize, y);
		}

		text = "QUIT";
		x = getXForCenteredText(text);
		y += gp.tileSize*2;
		g2.drawString(text, x, y);
		if(commandNum == 2) {
			g2.drawString(">", x - gp.tileSize, y);
		}		
	}
	
	public void drawPauseScreen() {
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
		String text = "PAUSED";
		int x = getXForCenteredText(text);
		int y = gp.tileSize*4;
		
		g2.drawString(text, x, y);
		
		// Pause Menu Options
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
		
		text = "SAVE GAME";
		x = getXForCenteredText(text);
		y += gp.tileSize*4;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x - gp.tileSize, y);
		}
		
		text = "LOAD GAME";
		x = getXForCenteredText(text);
		y += gp.tileSize*2;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString(">", x - gp.tileSize, y);
		}

		text = "OPTIONS";
		x = getXForCenteredText(text);
		y += gp.tileSize*2;
		g2.drawString(text, x, y);
		if(commandNum == 2) {
			g2.drawString(">", x - gp.tileSize, y);
		}

		text = "QUIT";
		x = getXForCenteredText(text);
		y += gp.tileSize*2;
		g2.drawString(text, x, y);
		if(commandNum == 3) {
			g2.drawString(">", x - gp.tileSize, y);
		}		

	}
	
	public void drawDialogueScreen() {
	
		// Window
		int x = gp.tileSize*4; 
		int y = gp.tileSize*15; 
		int width = gp.screenWidth - (gp.tileSize*8);
		int height = gp.tileSize*5;
		drawSubWindow(x, y, width, height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 24));
		x += gp.tileSize;
		y += gp.tileSize;
		
		for(String line : currentDialogue.split("\n")) {
		g2.drawString(line, x, y);
		y += 40;
		}
	}
	
	public void drawSubWindow(int x, int y, int width, int height) {
		
		Color c = new Color (0, 0, 0, 100);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255, 255, 255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
		
	}
	
	public int getXForCenteredText(String text) {		
		
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
}
