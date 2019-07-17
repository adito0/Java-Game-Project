package TileGame.Entity.Character;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import TileGame.Handler;
import TileGame.Entity.Entity;
import TileGame.Entity.EntityCollision;
import TileGame.Entity.ID;
import TileGame.GFX.Animations;
import TileGame.GFX.Assets;

public class PatrolEnemy extends Character{
	
//	private Handler handler;
	private Entity player;
	private BulletController c;
	private float startX, endX, startY, endY, radiusX, radiusY;
	private long timer, lastTime;
	public int count = 0;
	private int colour = 0;
	private int dir = 1;
	private Animations dinorun;

	public PatrolEnemy(Handler handler, float x, float y, float startX, float endX, float startY, float endY, ID id, int colour, int dir) {
		super(handler, x, y, 32, 32, id);
		//this.handler = handler;
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		this.dir = dir;
		this.colour = colour;
		if(colour == 0) {
			if(dir == -1) {
				dinorun = new Animations(50, Assets.r_dinorun_left);
			}else {
				dinorun = new Animations(50, Assets.r_dinorun_right);
			}
		}else if(colour == 1) {
			if(dir == -1) {
				dinorun = new Animations(50, Assets.g_dinorun_left);
			}else {
				dinorun = new Animations(50, Assets.g_dinorun_right);
			}
		}else if(colour == 2) {
			if(dir == -1) {
				dinorun = new Animations(50, Assets.b_dinorun_left);
			}else {
				dinorun = new Animations(50, Assets.b_dinorun_right);
			}
		}else {
			if(dir == -1) {
				dinorun = new Animations(50, Assets.y_dinorun_left);
			}else {
				dinorun = new Animations(50, Assets.y_dinorun_right);
			}
		}
//		bounds.x = 0;
//		bounds.y = 0;
//		bounds.width = 16;
//		bounds.height = 16;
		
		for (int i = 0; i < handler.getEntityManager().getAllEntities().size(); i++) {
			if (handler.getEntityManager().getAllEntities().get(i).getId() == ID.Player) {
				player = handler.getEntityManager().getAllEntities().get(i);
			}
		}
		velX = 0;
		velY = 0;
	}
	
	public void update() {
		
		dinorun.update();
		
		checkEntityCollisions(xMove, 0f);
		checkEntityCollisions(0f, yMove);
		
		xMove = velX;
		yMove = velY;
		x += velX;
		y += velY;
		float diffX = endX - startX;
		float diffY = endY - startY;
		if (diffX > 0) {
			x = Entity.clamp(x, startX, endX);
			if (velX == 0) {
				velX = 1;
			}
			if ((x == endX) || (x == startX)) {
				velX = velX * -1;
			}
		} else {
			x = Entity.clamp(x, startX, endX);
			if (velX == 0) {
				velX = 1;
			}
			if ((x == endX) || (x == startX)) {
				velX = velX * -1;
			}
		}
		if (diffY > 0) {
			y = Entity.clamp(y, startY, endY);
			if (velY == 0) {
				velY = 1;
			}
			if ((y == endY) || (y == startY)) {
				velY = velY * -1;
			}
		}
		
		radiusX = Math.abs(x - player.getX());
		radiusY = Math.abs(y - player.getY());
		
				
		if ((radiusX <= 50) || (radiusY <= 50)) {
			//System.out.print("Shoot");
			//handler.getEntityManager().addObject(new PatrolEnemyBullet(handler, (int)x, (int)y, ID.BasicEnemy));
			timer += System.currentTimeMillis() - lastTime;
			lastTime = System.currentTimeMillis();
			if (timer > 1000) {
				this.handler.getEntityManager().addObject(new PatrolEnemyBullet(this.handler, x, y , dir, ID.PatrolBullet)); //NEEDS TO BE FIXED
				timer = 0;
			}
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
					//handler.getGame().c.;
				}
			}
		}
		return false;
	}
	
	public BulletController getC() {
		return c;
	}

	public void setC(BulletController c) {
		this.c = c;
	}
	
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int)x, (int)y, bounds.width, bounds.height);
	}

	public void render(Graphics g) {
		g.drawImage(dinorun.getCurrentFrame(), (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height,null);
	}
}
