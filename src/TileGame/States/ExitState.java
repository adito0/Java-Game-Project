package TileGame.States;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import TileGame.Game;
import TileGame.Handler;
import TileGame.Entity.Character.HUD;
import TileGame.Entity.Character.Player;
import TileGame.GFX.Assets;

public class ExitState extends State {
	
	//private UIManager uiManager;
	private HUD hud;
	//private GameState gameState;
	//private Player player;
	
	private int minute, second, displayMinute, displaySeconds, totalTime, score, finalScore, coinMultiplier;
	
	public ExitState(Handler handler) {
		super(handler);
		hud = handler.getHud();		 
	}
	
	private void restart() {
		Game game = new Game("Legend of Zelda", 1024, 768);
		game.start();
	}
	
	private void calculateScore() {
		minute = hud.getMinute();
		second = hud.getSecond();
		totalTime = 300 - (minute * 60 + second);
		if (totalTime < 120){
			if ((handler.getPlayer().getWorldType() == 0) || (handler.getPlayer1().getWorldType() == 0) || (handler.getPlayer2().getWorldType() == 0)) {
				score = 11; //C-
			} else if ((handler.getPlayer().getWorldType() == 2) || (handler.getPlayer1().getWorldType() == 2) || (handler.getPlayer2().getWorldType() == 2)) {
				score = 22; //B-
			} else if ((handler.getPlayer().getWorldType() == 4) || (handler.getPlayer1().getWorldType() == 4) || (handler.getPlayer2().getWorldType() == 4)) {
				score = 41; //B+
			}
		} else {
			if ((handler.getPlayer().getWorldType() == 0) || (handler.getPlayer1().getWorldType() == 0) || (handler.getPlayer2().getWorldType() == 0)) {
				score = 18; //C
			} else if ((handler.getPlayer().getWorldType() == 2) || (handler.getPlayer1().getWorldType() == 2) || (handler.getPlayer2().getWorldType() == 2)) {
				score = 31; //C+
			} else if ((handler.getPlayer().getWorldType() == 4) || (handler.getPlayer1().getWorldType() == 4) || (handler.getPlayer2().getWorldType() == 4)) {
				score = 38; //B
			}
		}
	}

	@Override
	public void update() {
		if(handler.getMouseManager().isLeftPressed()) {
			handler.getGame().getDisplay().frame.dispose();
			restart();
			handler.getGame().stop();
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.exitscr, 0, 0, 1024, 768, null);
		calculateScore();
		displayMinute = 4 - minute;
		displaySeconds = 60 - second;
		
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);
		g2.fillRect(200, 500, 600, 200);
		g2.setColor(Color.white);
		if (displaySeconds < 10) {
			g2.drawString("Your time: " + displayMinute + ":0" + displaySeconds, 250, 550);
		} else {
			g2.drawString("Your time: " + displayMinute + ":" + displaySeconds, 250, 550);
		}
		if (hud.coins > 0) {
			coinMultiplier = hud.coins * 100;
		} else {
			coinMultiplier = 10;
		}
		finalScore = score * coinMultiplier;
		g2.drawString("Your score: " + finalScore, 250, 600);
		
		if (score == 41) {
			g.drawImage(Assets.bplus, 550, 500, 250, 150, null);
		} else if (score == 38) {
			g.drawImage(Assets.b, 550, 500, 250, 150, null);
		} else if (score == 31) {
			g.drawImage(Assets.bminus, 550, 500, 250, 150, null);
		} else if (score == 22) {
			g.drawImage(Assets.cplus, 550, 500, 250, 150, null);
		} else if (score == 18) {
			g.drawImage(Assets.c, 550, 500, 250, 150, null);
		} else if (score == 11) {
			g.drawImage(Assets.cminus, 550, 500, 250, 150, null);
		} 
	}
	
	
}
