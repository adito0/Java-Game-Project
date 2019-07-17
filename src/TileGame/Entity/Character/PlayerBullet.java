package TileGame.Entity.Character;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import TileGame.Handler;
import TileGame.Entity.Entity;
import TileGame.Entity.ID;

public class PlayerBullet extends Entity{
	
//	private float x, y, velX;
	private float velX;
	private float multiplier;
//	private Handler handler;
	private static final int DEFAULT_WIDTH = 10;
	private static final int DEFAULT_HEIGHT = 10;
	
	public PlayerBullet (Handler handler, float x, float y, float velX, ID id) {
		super(handler, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, id);
		this.velX = velX;
		if(handler.characterType == 0) {
			this.multiplier = 1.5f;
		}else if(handler.characterType == 1) {
			this.multiplier = 0.75f;
		}else {
			this.multiplier = 3f;
		}
	}
	
	@Override
	public void update() {
		this.x += (velX * this.multiplier);
	}
	
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int)x, (int)y, bounds.width, bounds.height);
	}
	
//	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
//		return new Rectangle((int) x + bounds.x, (int) y + bounds.y, bounds.width, bounds.height);
//	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), DEFAULT_WIDTH, DEFAULT_HEIGHT);
		g.setColor(Color.yellow);
		g.fillRect((int)(x + bounds.x - handler.getGame().getGameCamera().getxOffset()), (int)(y + bounds.y - handler.getGame().getGameCamera().getyOffset()), bounds.width, bounds.height);
	}
}
