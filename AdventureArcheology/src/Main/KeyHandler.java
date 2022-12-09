// **********************************************************************************
// Title: AdventureArcheology
// Author: Benjamin C. Konczal
// Course Section: CMIS201-ONL1 (Seidel) Fall 2022
// File: KeyHandler.java
// Description: This file handles the keyboard input from the user and determines what actions to take.
// **********************************************************************************

package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	
	GamePanel gp;
	
	public boolean upPressed, downPressed, leftPressed, rightPressed, actionPressed;
	
	// Debugger
	boolean checkDrawTime = false;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	public void keyTyped(KeyEvent e) {
		
	}
	
	public void keyPressed(KeyEvent e) {
	
		int code = e.getKeyCode();
		
		// Title State
		if(gp.gameState == gp.titleState) {
			
			// 
			if(code == KeyEvent.VK_W) {
				gp.ui.commandNum --;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 2;
				}
			}
			
			//
			if(code == KeyEvent.VK_S) {
				gp.ui.commandNum ++;
				if(gp.ui.commandNum > 2) {
					gp.ui.commandNum = 0;
				}
			}
			
			// Title Menu Options
			if(code == KeyEvent.VK_E) {
				// New Game
				if(gp.ui.commandNum == 0) {
					gp.gameState = gp.playState;
//					gp.playMusic(0);
				}
				// Load Game
				if(gp.ui.commandNum == 1) {
					gp.saveLoad.load();
					gp.gameState = gp.playState;
//					gp.playMusic(0);
				}
				// Quit
				if(gp.ui.commandNum == 2) {
					System.exit(0);
				}	
			}
		}
		
		// Play State
		if(gp.gameState == gp.playState) {
			
			if(code == KeyEvent.VK_W) {
				upPressed = true;
			}
			if(code == KeyEvent.VK_S) {
				downPressed = true;
			}
			if(code == KeyEvent.VK_A) {
				leftPressed = true;
			}
			if(code == KeyEvent.VK_D) {
				rightPressed = true;
			}
			if(code == KeyEvent.VK_ESCAPE) {
				gp.gameState = gp.pauseState;
			}
			if(code == KeyEvent.VK_E) {
				actionPressed = true;
			}
		
			// Debugger
			if(code == KeyEvent.VK_BACK_SLASH) {
				if(checkDrawTime == false) {
					checkDrawTime = true;
				}
				else if(checkDrawTime == true) {
					checkDrawTime = false;
				}
			}
		}
		
		// Pause/Options State
		else if(gp.gameState == gp.pauseState) {
			if(code == KeyEvent.VK_ESCAPE) {
				gp.gameState = gp.playState;
			}


			// Up Selection Control
			if(code == KeyEvent.VK_W) {
				gp.ui.commandNum --;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 3;
				}
			}
			
			// Down Selection Control
			if(code == KeyEvent.VK_S) {
				gp.ui.commandNum ++;
				if(gp.ui.commandNum > 3) {
					gp.ui.commandNum = 0;
				}
			}
			
			// Choose Selection Control
			if(code == KeyEvent.VK_E) {
				
				// Save Game
				if(gp.ui.commandNum == 0) {					
					gp.saveLoad.save();
					//gp.ui.currentDialogue = "Game Data Saved Successfully";

				}
				
				// Load Game
				if(gp.ui.commandNum == 1) {
					gp.saveLoad.load();
					gp.gameState = gp.playState;
//					gp.playMusic(0);
				}
				
				// Options Menu
				if(gp.ui.commandNum == 2) {

				}
				
				// Quit
				if(gp.ui.commandNum == 3) {
					System.exit(0);
				}	
			}
		}
		
		// Dialogue State
		else if(gp.gameState == gp.dialogueState) {
			if(code == KeyEvent.VK_E) {
				gp.gameState = gp.playState;
			}
		}		
	}
		
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}	
	}
}
