package TileGame.Entity.Character;

import TileGame.Handler;
import TileGame.Entity.Entity;
import TileGame.GFX.Assets;
import TileGame.Tile.Tile;
import TileGame.Entity.ID;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BossEnemyBullet extends Character {

	private Entity player;
	//private Handler handler;
	Random r = new Random();
	private int timer = 80;
	private int timer2 = 50;
	private int timer3 = 750;

	public BossEnemyBullet(Handler handler, float x, float y, ID id) {
		super(handler, x, y, Character.DEFAULT_WIDTH, Character.DEFAULT_HEIGHT, id);

		for (int i = 0; i < handler.getEntityManager().getAllEntities().size(); i++) {
			if ((handler.getEntityManager().getAllEntities().get(i).getId() == ID.Player)||(handler.getEntityManager().getAllEntities().get(i).getId() == ID.Player1)) {
				player = handler.getEntityManager().getAllEntities().get(i);
			}
		}
		
		velX = 5;
		velY = 5;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x,(int) y,16,16);		
	}

	public void update() {		
		move();
		
		x += velX;
		y += velY;

		if (timer > 0) {
			timer--;
		} else {
			timer2--;
		}
		
		if (timer2 <= 0) {
			timer3--;
		}

		float diffX = x-player.getX() - 32;
		float diffY = y-player.getY() - 32;
		float distance = (float) Math.sqrt(((x-player.getX())*(x-player.getX())) + ((y-player.getY()) * (y-player.getY())));
		
		velX = (float) ((-0.9 / distance) * diffX);
		velY = (float) ((-0.9 / distance) * diffY);
		
		if (timer3 <= 0) {
			handler.getEntityManager().removeObject(this);
		}

	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.drawImage(Assets.fireball,(int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), 40, 40, null);

	}
}

