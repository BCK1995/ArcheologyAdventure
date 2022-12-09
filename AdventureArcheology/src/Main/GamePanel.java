// **********************************************************************************
// Title: AdventureArcheology
// Author: Benjamin C. Konczal
// Course Section: CMIS201-ONL1 (Seidel) Fall 2022
// File: GamePanel.java
// Description: This file handles the game's mechanics.
// **********************************************************************************

package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Entities.Entities;
import Entities.Player;
import data.SaveLoad;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//screen settings
	final int originalTileSize = 16; // 16x16 tile size
	final int scale = 3; 
	
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 30; 
	public final int maxScreenRow = 20; 
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
	//World Settings
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
		
	// FPS
	int FPS = 60;
	long secondsConversion = 1000000000;
	int actualFPS = 0;

	TileManager tileM = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);
	Sound music  = new Sound();
	Sound gameSE = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	SaveLoad saveLoad = new SaveLoad(this);
	Thread gameThread;
	
	// Entities And ObjectS
	public Player player = new Player(this, keyH);
	public SuperObject obj[] = new SuperObject[10];
	public Entities npc[] = new Entities[10];
	
	// Game State
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		
		aSetter.setObject();
		aSetter.setNPC();
//		playMusic(0);
//		stopMusic();
		gameState = titleState;
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void run() {
		
		double drawInterval = secondsConversion/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if(timer >= secondsConversion) {
				System.out.println("FPS: " + drawCount);
				actualFPS = drawCount;
				drawCount = 0;
				timer = 0;
			}
		}
	}
	
	public void update() {
		
		if(gameState == playState) {	
			
			// Player Character
			player.update();
			
			// NPC
			for(int i = 0; i < npc.length; i++) {
				if(npc[i] != null) {
					npc[i].update();
				}
			}
		}
		
		if(gameState == pauseState) {
			// Nothing ATM
		}
		
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		// Debugging
		long drawStart = 0;
		if(keyH.checkDrawTime == true) {
			
			drawStart = System.nanoTime();
		}
		
		// Title Screen
		if(gameState == titleState) {
			ui.draw(g2);
		}
		// Others
		else {
					
			// Tile
			tileM.draw(g2);
			
			// Object
			for(int i = 0; i < obj.length; i++) {
				if(obj[i] != null) {
					obj[i].draw(g2, this);
				}
			}
			
			// NPC
			for(int i = 0; i < npc.length; i++) {
				if(npc[i] != null) {
					npc[i].draw(g2);
				}
			}
			
			// Player
			player.draw(g2);
			
			//UI
			ui.draw(g2);	
		}
		
		// Debugging
		if(keyH.checkDrawTime == true) {
		
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			g2.setColor(Color.white);
			g2.drawString("Draw Time: " + passed, 10, 800);
			g2.drawString("FPS: " + actualFPS, 10, 900);
			System.out.println("Draw Time: " + passed);
		}
		
		g2.dispose();		
	}
	
	public void playMusic(int i) {
		
//		music.setFile(i);
//		music.play();
//		music.loop();
	}
	
	public void stopMusic() {
		
//		music.stop();
	}
	
	public void playSE(int i) {
		
//		gameSE.setFile(i);
//		gameSE.play();
	}
}
