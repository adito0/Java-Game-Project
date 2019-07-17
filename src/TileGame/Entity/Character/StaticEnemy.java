package TileGame.Entity.Character;

import java.awt.Color;
import java.awt.Graphics;

import TileGame.Handler;
import TileGame.Entity.Entity;
import TileGame.Entity.ID;

public class StaticEnemy extends BasicEnemy{
	
	private Handler handler;

	public StaticEnemy(Handler handler, float x, float y, int width, int height, ID id) {
		super(handler, x, y, id, 0);
		this.handler = handler;
	}
	
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		for (Entity e : handler.getEntityManager().getAllEntities()) {
			if (e.equals(this)) {
				continue;
			}
			if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
				if (e.getId() == ID.PlayerBullet) {
					this.setActive(false);
					e.setActive(false);
				}
			}
		}
		return false;
	}

	@Override
	public void update() {
		checkEntityCollisions(xMove, 0f);
		checkEntityCollisions(0f, yMove);		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.pink);
		g.fillRect((int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()),
				16, 16);
	}

}
