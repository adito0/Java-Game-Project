package TileGame.Entity.Character;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.Random;

import TileGame.Handler;
import TileGame.Entity.Entity;
import TileGame.Entity.ID;
import TileGame.GFX.Animations;
import TileGame.GFX.Assets;

public class BossEnemy extends Character{
	
	Random r = new Random();
	
	private Animations bossFly, bossAttack, bossDie;
	
	private float bHealth;
	
	private int[] arr = new int[2];
	private int mode = 0;
	private long lastTime, timer, lastTime2, timer2, timer3, lastTime3;
	private float damage = 1f;

	public BossEnemy(Handler handler, float x, float y, ID id) {
		super(handler, x, y, 140, 155, id);
		bossFly = new Animations(100, Assets.bossIdle);
		bossAttack = new Animations(100, Assets.bossAttack);
		bossDie = new Animations(140, Assets.bossDie);
		bHealth = this.getBossHealth();
		
		this.bounds.x = 150;
		this.bounds.y = 90;
		bounds.width = 140;
		bounds.height = 155;

		
		lastTime = System.currentTimeMillis();
		lastTime2 = System.currentTimeMillis();
		lastTime3 = System.currentTimeMillis();
		
		//this.handler = handler;
	}
	
//	public Rectangle getBounds() {
//		return new Rectangle((int) x,(int) y,96,96);		
//	}
	
	@Override
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		for (Entity e : handler.getEntityManager().getAllEntities()) {
			if (e.equals(this)) {
				continue;
			}
			if (e.getCollisionBounds(0f, 0f).intersects(/*getCollisionBounds(xOffset, yOffset)*/new Rectangle((int)(x+bounds.x), (int)(y + bounds.y), bounds.width, bounds.height))) {
				if ((e.getId() == ID.PlayerBullet)) {
					bHealth-=damage;
					e.setActive(false);
				}
			}
		}
		return false;
	}

	public void update() {
		
		checkEntityCollisions(xMove, 0f);
		checkEntityCollisions(0f, yMove);
		bossFly.update();
		bossAttack.update();
		bossDie.update();
		
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis(); 
		
		if(timer > 500) {
			mode = 0;
		}
		if (timer > 1000) {
			int spawn = r.nextInt(2);
			if (spawn == 0) {
				if (timer2 < 10000) {
					mode = 1;
					handler.getEntityManager().addObject(new BossEnemyBullet(handler, (int)x + 250, (int)y + 150, ID.BasicEnemy));
				}
			}
			timer = 0;
		}
		
		timer2 += System.currentTimeMillis() - lastTime2;
		lastTime2 = System.currentTimeMillis();

		if (timer2 > 10000) {
			arr = teleport();
			x = arr[0];
			y = arr[1];
		}
		
		if (timer2 > 11000) {
			timer2 = 0;
			arr = teleport();
			x = arr[0];
			if (arr[1] > 50) {
				y = arr[1];
			} else {
				y = 200;
			}
		}
				
		if (bHealth < 5) {
			mode = 2;
		}
	}

	public void render(Graphics g) {
		if(mode == 0) {
			g.drawImage(bossFly.getCurrentFrame(),(int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), 400, 267, null);
		}else if(mode == 1) {
			g.drawImage(bossAttack.getCurrentFrame(),(int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), 400, 267, null);	
		}else {
			if(bossDie.getindex() != 15) {
				g.drawImage(bossDie.getCurrentFrame(),(int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), 400, 267, null);
			}else {
				handler.getGame().getStateManager().setState(handler.getGame().winState);
			}
		}
		if (timer2 < 10000) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.gray);
			g2.fillRoundRect((int) (x - handler.getGameCamera().getxOffset() + 150) , (int)(y - handler.getGameCamera().getyOffset() - 20), 127, 15, 25, 25);
			g2.setColor(Color.red);
			g2.fillRoundRect((int) (x - handler.getGameCamera().getxOffset() + 150) , (int)(y - handler.getGameCamera().getyOffset() - 20), (int) (bHealth), 15, 25, 25);
			g2.setColor(Color.white);
			g2.drawRoundRect((int) (x - handler.getGameCamera().getxOffset() + 150) , (int)(y - handler.getGameCamera().getyOffset() - 20), 127, 15, 25, 25);
		}	
	}
	
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int)x, (int)y, bounds.width, bounds.height);
	}
}
