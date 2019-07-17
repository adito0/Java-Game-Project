package TileGame.Entity.Character;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import TileGame.Handler;
import TileGame.Entity.Entity;
import TileGame.Entity.ID;
public class PatrolEnemyBullet extends Entity{
	
	
//	private float x, y, velX;
	private float velX;
//	private Handler handler;	
	private static final int DEFAULT_WIDTH = 10;
	private static final int DEFAULT_HEIGHT = 10;

	public PatrolEnemyBullet(Handler handler, float x, float y, float velX, ID id) {
		super(handler, x, y + 3, DEFAULT_WIDTH, DEFAULT_HEIGHT, id);
		this.velX = velX;
	}
 
	@Override
	public void update() {
		this.x += velX;
	}
	
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int)x, (int)y, bounds.width, bounds.height);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), DEFAULT_WIDTH, DEFAULT_HEIGHT);
		g.setColor(Color.black);
		g.fillRect((int)(x + bounds.x - handler.getGame().getGameCamera().getxOffset()), (int)(y + bounds.y - handler.getGame().getGameCamera().getyOffset()), bounds.width, bounds.height);
	}
}
