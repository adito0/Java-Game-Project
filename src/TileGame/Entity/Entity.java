package TileGame.Entity;

import TileGame.Game;
import TileGame.Entity.Character.*;
import TileGame.Handler;
import java.awt.*;

public abstract class Entity {

    protected Handler handler;
    private EntityManager entityManager;
    protected ID id;
    protected float x, y;
    protected int width, height;
    protected Rectangle bounds;
    protected boolean active; 

	protected EntityCollision collisionType = EntityCollision.NoCollision;

	public Entity(Handler handler, float x, float y, int width, int height, ID id) {

		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
		active = true;
		entityManager = handler.getEntityManager();

		bounds = new Rectangle(0, 0, width, height);
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public abstract void update();

	public abstract void render(Graphics g);
	

//	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
//		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width,
//				bounds.height);
//	}
	
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		return false;
	}
	
	public static float clamp(float var, float min, float max) {
		if (var >= max) {
			return var = max;
		} else if (var <= min) {
			return var = min;
		} else {
			return var;
		}
	}

	// GETTER AND SETTER

	public float getX() {
		return this.x;
	}

	public void setX(float newX) {
		this.x = newX;
	}

	public float getY() {
		return this.y;
	}

	public void setY(float newY) {
		this.y = newY;
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public ID getId() {
		return id;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
    public boolean isActive() {
		return active;
	}
    
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int) x + bounds.x, (int) y + bounds.y, bounds.width, bounds.height);
	}

}
