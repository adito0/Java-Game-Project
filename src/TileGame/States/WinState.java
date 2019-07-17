package TileGame.States;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import TileGame.Game;
import TileGame.Handler;
import TileGame.Entity.Character.HUD;
import TileGame.GFX.Assets;

public class WinState extends State{
	
	private HUD hud;
	
	private int minute, second, displayMinute, displaySeconds, totalTime, score, coins, finalScore;

	public WinState(Handler handler) {
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
			score = 51; //A+
		} else if (totalTime < 240 ) {
			score = 42; //A
		} else {
			score = 33; //A-
		}
	}

	@Override
	public void update() {
		//uiManager.update();
		if(handler.getMouseManager().isLeftPressed()) {
			handler.getGame().getDisplay().frame.dispose();
			restart();
			handler.getGame().stop();
			//StateManager.setState(handler.getGame().menuState);
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.winscr, 0, 0, 1024, 768, null);
		calculateScore();
		displayMinute = 4 - minute;
		displaySeconds = 60 - second;
		
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);
		g2.fillRect(200, 500, 600, 200);
		g2.setColor(Color.white);
		if (displaySeconds < 10) {
			//g2.drawString("Your time: " + totalTime, 250, 550);
			g2.drawString("Your time: " + displayMinute + ":0" + displaySeconds, 250, 550);
		} else {
			//g2.drawString("Your time: " + totalTime, 250, 550);
			g2.drawString("Your time: " + displayMinute + ":" + displaySeconds, 250, 550);
		}
		//g2.drawString("Your time: ", 250, 550);
		coins = hud.coins * 100;
		finalScore = score * coins;
		g2.drawString("Your score: " + finalScore, 250, 600);
		
		if (score == 51) {
			g.drawImage(Assets.aplus, 550, 500, 250, 150, null);
		} else if (score == 42) {
			g.drawImage(Assets.a, 550, 500, 250, 150, null);
		} else if (score == 33) {
			g.drawImage(Assets.aminus, 550, 500, 250, 150, null);
		}
		
		//uiManager.render(g);
	}
}
