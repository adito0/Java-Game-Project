package TileGame.Entity.Character;

import TileGame.Handler;
import TileGame.Entity.Entity;
import TileGame.GFX.Animations;
import TileGame.GFX.Assets;
import TileGame.Item.Item;
import TileGame.Tile.Tile;
import TileGame.Entity.ID;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

//import com.game.main.ID;



public class BasicEnemy extends Character {

	private Entity player;
	public static final int DEFAULT_WIDTH = 16;
	public static final int DEFAULT_HEIGHT = 16;
	public static final int DEFAULT_SPEED = 5;
	private int colour = 0;
	private Animations run;



	public BasicEnemy(Handler handler, float x, float y, ID id, int colour) {
		super(handler, x, y, BasicEnemy.DEFAULT_WIDTH, BasicEnemy.DEFAULT_HEIGHT, id);

		for (int i = 0; i < handler.getEntityManager().getAllEntities().size(); i++) {
			if (handler.getEntityManager().getAllEntities().get(i).getId() == ID.Player) {
				player = handler.getEntityManager().getAllEntities().get(i);
			}
		}
		this.colour = colour;
		velX = BasicEnemy.DEFAULT_SPEED;
		velY = BasicEnemy.DEFAULT_SPEED;
		
		if(colour == 0) {
			run = new Animations(75, Assets.blueSlime);
		}else if(colour == 1) {
			run = new Animations(75, Assets.greenSlime);
		}else {
			run = new Animations(75, Assets.redSlime);
		}
	}
	
	@Override
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		for (Entity e : handler.getEntityManager().getAllEntities()) {
			if (e.equals(this)) {
				continue;
			}
			if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
				if (e.getId() == ID.PlayerBullet) {
					this.setActive(false);
					e.setActive(false);
					handler.getWorld().getItemManager().addItem(Item.coinItem.createNew((int)x,(int)y));
				}
			}
		}
		return false;
	}

	
	public void update() {
		
		run.update();

		xMove = velX;
		yMove = velY;


		float diffX = x - player.getX() - 40;
		float diffY = y - player.getY() - 40;
		float distance = (float) Math.sqrt(((x - player.getX()) * (x - player.getX())) + ((y - player.getY()) * (y - player.getY())));

		velX = (float) ((-0.75 / distance) * diffX);
		velY = (float) ((-0.75 / distance) * diffY);

		move();
	}

	public void render(Graphics g) {
		g.drawImage(run.getCurrentFrame(), (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), 40, 40,null);

	}
	
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int)x, (int)y, bounds.width, bounds.height);
	}

}
