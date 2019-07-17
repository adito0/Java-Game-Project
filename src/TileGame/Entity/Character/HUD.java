package TileGame.Entity.Character;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import TileGame.Entity.Entity;
import TileGame.GFX.Assets;

public class HUD {
	
	private Player player;
	
	public float HEALTH;
	public float stamina;
	public int coins, yOffset, npcMessage;
	private long lastTime, timer;

	private float redValue = 255;
	private float blueValue = 255;
	private int second=0;
	private int minute = 5;
	private boolean multiplayer;
	// private int level = 1;
	
	public HUD(Player player, int yOffset, boolean multiplayer) {
		this.player = player;
		this.multiplayer = multiplayer;
		HEALTH = player.getHealth();
		stamina = player.getStamina();
		lastTime = System.currentTimeMillis();
		this.yOffset = yOffset; 
	}
	
	public void update() {

		HEALTH = Entity.clamp(player.getHealth(), 10, 127);
		stamina = Entity.clamp(player.getStamina(), 10, 127);
		redValue = Entity.clamp(redValue, 0, 255);
		blueValue = Entity.clamp(blueValue, 0, 255);

		redValue = HEALTH * 2;
		blueValue = stamina * 2;

		coins = player.getInventory().coinAmount();
		
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis(); 
		
		if (timer > 1000) {
			second--;
			timer = 0;
		}
		
		if (second < 0) {
			second = 59;
			minute--;
		}
		
		if (stamina < 127) {
			stamina += 0.25;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(255, 255, 255, 160));
		g.fillRect(0, 0 + yOffset, 1024, 100);
//		g.setColor(Color.blue);
//		g.fillRect(500, 25 + yOffset, 50, 50);
		g.drawImage(Assets.npc, 500, 25 + yOffset, 65, 65, null);
		npcMessage = player.getMessage();
		if (npcMessage == 1) {
			if ((player.getWorldType()) == 0) {
				g.setColor(Color.gray);
				int xpoints[] = {540, 550, 550};
				int ypoints[] = {65, 65, 37};
				int npoints = 3;
				g.fillRect(550, 15, 250, 75);
				g.fillPolygon(xpoints, ypoints, npoints);
				g.setColor(Color.white);
				g.drawString("Hi! My name is Hilda if you", 580, 35);
				g.drawString("ever need help just press", 580, 50);
				g.drawString("the H key.", 580, 65);
			} else if ((player.getWorldType()) == 4) {
				g.setColor(Color.gray);
				int xpoints[] = {540, 550, 550};
				int ypoints[] = {65, 65, 37};
				int npoints = 3;
				g.fillRect(550, 15, 250, 75);
				g.fillPolygon(xpoints, ypoints, npoints);
				g.setColor(Color.white);
				g.drawString("Well done! You have met your final", 580, 35);
				g.drawString("nemesis. Press H again for more", 580, 50);
				g.drawString("info about his attacks & movements.", 580, 65);
			}
		} else if (npcMessage == 2) {
			if ((player.getWorldType()) == 0) {
				g.setColor(Color.gray);
				int xpoints[] = {540, 550, 550};
				int ypoints[] = {65, 65, 37};
				int npoints = 3;
				g.fillRect(550, 15, 250, 75);
				g.fillPolygon(xpoints, ypoints, npoints);
				g.setColor(Color.white);
				g.drawString("Make sure that you pick up", 580, 35);
				g.drawString("the key to be able to", 580, 50);
				g.drawString("advance to the next world.", 580, 65);
			} else if ((player.getWorldType()) == 4) {
				g.setColor(Color.gray);
				int xpoints[] = {540, 550, 550};
				int ypoints[] = {65, 65, 37};
				int npoints = 3;
				g.fillRect(550, 15, 250, 75);
				g.fillPolygon(xpoints, ypoints, npoints);
				g.setColor(Color.white);
				g.drawString("The Atronach is able to spit", 580, 35);
				g.drawString("fireballs that can track your", 580, 50);
				g.drawString("movement. So never stop moving!", 580, 65);
			} 
			
		} else if (npcMessage == 3) {
			if ((player.getWorldType()) == 0) {
				g.setColor(Color.gray);
				int xpoints[] = {540, 550, 550};
				int ypoints[] = {65, 65, 37};
				int npoints = 3;
				g.fillRect(550, 15, 250, 75);
				g.fillPolygon(xpoints, ypoints, npoints);
				g.setColor(Color.white);
				g.drawString("To earn more coins try", 580, 35);
				g.drawString("kill as many enemies as", 580, 50);
				g.drawString("possible.", 580, 65);
			} else if ((player.getWorldType()) == 4) {
				g.setColor(Color.gray);
				int xpoints[] = {540, 550, 550};
				int ypoints[] = {65, 65, 37};
				int npoints = 3;
				g.fillRect(550, 15, 250, 75);
				g.fillPolygon(xpoints, ypoints, npoints);
				g.setColor(Color.white);
				g.drawString("The Atronach can also teleport", 580, 35);
				g.drawString("to different spaces in the map.", 580, 50);
				g.drawString("So always be wary!!!", 580, 65);
			}
			
		} else if (npcMessage == 4) {
			if ((player.getWorldType()) == 0) {
				g.setColor(Color.gray);
				int xpoints[] = {540, 550, 550};
				int ypoints[] = {65, 65, 37};
				int npoints = 3;
				g.fillRect(550, 15, 250, 75);
				g.fillPolygon(xpoints, ypoints, npoints);
				g.setColor(Color.white);
				g.drawString("Don't forget that you are", 580, 35);
				g.drawString("are under a time limit!", 580, 50);
				g.drawString("FINISH ASAP!!!", 580, 65);
			}
		}
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.gray);
		g2.fillRoundRect(15, 15+ yOffset, 254, 32, 25, 25);
		g2.setColor(new Color((int) redValue, 0, 0, (int) redValue));
		g2.fillRoundRect(15, 15+ yOffset, (int) (HEALTH * 2), 32, 25, 25);
		g2.setColor(Color.white);
		g2.drawRoundRect(15, 15+ yOffset, 254, 32, 25, 25);

		if(!this.multiplayer) {
			g.setColor(Color.gray);
			g.fillRoundRect(15, 50+ yOffset, 254, 32, 25, 25);
			g.setColor(new Color(0, 0, (int) blueValue, (int) blueValue));
			g.fillRoundRect(15, 50+ yOffset, (int) (stamina * 2), 32, 25, 25);
			g.setColor(new Color(240, 240, 240));
			g.drawRoundRect(15, 50+ yOffset, 254, 32, 25, 25);
			//g.setColor(new Color(60, 60, 200));
			g.fillRect(25, 50+ yOffset, 1, 32);
			g.fillRect(85, 50+ yOffset, 1, 32);
			g.fillRect(145, 50+ yOffset, 1, 32);
			g.fillRect(206, 50+ yOffset, 1, 32);

			g.setFont(new Font("Century Gothic", Font.BOLD, 24));
			g.setColor(Color.white/*new Color(0, 0, 0, 150)*/);

			g.drawString("STAMINA", 90, 75+ yOffset);
		}
		
		g.fillRect(25, 15+ yOffset, 1, 32);
		g.fillRect(85, 15+ yOffset, 1, 32);
		g.fillRect(145, 15+ yOffset, 1, 32);
		g.fillRect(206, 15+ yOffset, 1, 32);
		
		g.setFont(new Font("Century Gothic", Font.BOLD, 24));
		g.setColor(Color.white/*new Color(0, 0, 0, 150)*/);
		g.drawString("HEALTH", 100, 40+ yOffset);
		
		if (second < 10) {
			if (minute < 1) {
				g.setColor(new Color(255, 0+ yOffset, 0, 150));
			} else {
				g.setColor(new Color(0, 0+ yOffset, 0, 150));
			}
			g.drawString("Time: " + minute + ":0" + second, 850, 30+ yOffset);
			
		} else {
			if (minute < 1) {
				g.setColor(new Color(255, 0+ yOffset, 0, 150));
			} else {
				g.setColor(new Color(0, 0+ yOffset, 0, 150));
			}
			g.drawString("Time: " + minute + ":" + second, 850, 30+ yOffset);
		}
		g.setColor(new Color(0, 0+ yOffset, 0, 150));
		g.drawString("Coins: " + coins, 850, 55+ yOffset);
		
		
		g.drawString("X: " + (int) player.getX() + "  Y: " + (int) player.getY(), 750, 80+ yOffset);
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

}
