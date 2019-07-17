package TileGame.Entity.Character;

import TileGame.Entity.Entity;
import TileGame.Entity.EntityCollision;
import TileGame.Entity.ID;
import TileGame.Entity.Character.Character;
import TileGame.GFX.Assets;
import TileGame.Inventory.Inventory;
import TileGame.GFX.*;
import TileGame.Handler;
import TileGame.Sound;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Player extends Character {

	private BulletController c;
	// Animations

	private Animations AanimDown;
	private Animations AanimUp;
	private Animations AanimLeft;
	private Animations AanimRight;
	private Animations AanimStill;
	private Animations EanimStill;
	private Animations EanimDown;
	private Animations EanimUp;
	private Animations EanimLeft;
	private Animations EanimRight;
	private Animations HanimStill;
	private Animations HanimDown;
	private Animations HanimUp;
	private Animations HanimLeft;
	private Animations HanimRight;
	
	private float damage = 0.1f;


	private float tired = 1;
	private long lastTime, timer;
	private int worldType = 6;
	private int dir = 1;
	private int message = 0;

	private boolean level1, level2, level3;
	private Inventory inventory;
	private int character;


	public Player(Handler handler, float x, float y, ID id) {
		super(handler, x, y, Character.DEFAULT_WIDTH, Character.DEFAULT_HEIGHT, id);
		this.character = handler.characterType;
		bounds.x = 24;
		bounds.y = 28;
		bounds.width = 20;
		bounds.height = 29;
		//bounds.width = 0;
		//bounds.height = 0;
		if(this.character == 0) {
			velX = DEFAULT_SPEED ;
			velY = DEFAULT_SPEED ;
		}else if(this.character == 1) {
			velX = DEFAULT_SPEED * 6;
			velY = DEFAULT_SPEED * 6;
		}else {
			velX = DEFAULT_SPEED * 3;
			velY = DEFAULT_SPEED * 3;
		}
		
		
		if(this.character == 0) {
			damage = 0.05f;
		}else if(this.character == 2) {
			damage = 1.5f;
		}

		AanimDown = new Animations(100, Assets.player_down);
		AanimUp = new Animations(100, Assets.player_up);
		AanimLeft = new Animations(100, Assets.player_down);
		AanimRight = new Animations(100, Assets.player_up);
		AanimStill = new Animations(100, Assets.player_still);
		
		EanimStill = new Animations(100, Assets.e_player_still);
		EanimDown = new Animations(100, Assets.e_player_down);
		EanimUp = new Animations(100, Assets.e_player_up);
		EanimLeft = new Animations(100, Assets.e_player_down);
		EanimRight = new Animations(100, Assets.e_player_up);
		
		HanimStill = new Animations(100, Assets.h_player_still);
		HanimDown = new Animations(100, Assets.h_player_down);
		HanimUp = new Animations(100, Assets.h_player_up);
		HanimLeft = new Animations(100, Assets.h_player_down);
		HanimRight = new Animations(100, Assets.h_player_up);
		
		inventory = new Inventory(handler);
		lastTime = System.currentTimeMillis();
	}

	public int getCharacter() {
		return character;
	}

	public void setCharacter(int character) {
		this.character = character;
	}

	public void die() {
	}
	
	public void setC(BulletController c) {
		this.c = c;
	}

	@Override
	public void update() {
		//Animations
		if(this.character == 2) {
			AanimStill.update();
			AanimDown.update();
			AanimUp.update();
			AanimLeft.update();
			AanimRight.update();
		}else if(this.character == 1) {
			EanimStill.update();
			EanimDown.update();
			EanimUp.update();
			EanimLeft.update();
			EanimRight.update();
		}else {
			HanimStill.update();
			HanimDown.update();
			HanimUp.update();
			HanimLeft.update();
			HanimRight.update();

		}
		
		//Movement
		getInput();
		move();
		handler.getGame().getGameCamera().centreOnEntity(this);
		
		if(health < 10) {
			handler.getGame().getStateManager().setState(handler.getGame().exitState);
		}
		if (stamina <= 127) {
			stamina += 0.2;

		}
		
		if(worldType == 0) {
			if(this.getX() >= 1235){
				if(inventory.find(1)) {
					worldType = 1;
					x = 0;
					level1 = true;	
				}else {
					x = 1235;
				}
			}else if((this.getX() <= -18)){
				x = -18;
			}
		} else if(worldType == 2) {
			if(this.getX() >= 1235){
				if(inventory.find(2)) {
					worldType = 3;
					x = 0;
					level2 = true;
				}else {
					x = 1235;
				}
			}else if((this.getX() <= -20)){
				worldType = 5;
				x = 1200;
			}
		}else if(worldType == 4) {
			if((this.getX() <= -20)){
				worldType = 1;
				x = 1200;
			}else if((this.getX() >= 1235)){
				x = 1235;
			}
			level3 = true; //How dos this work?
		}else if(worldType == 6) {
			if((this.getX() <= -18)){
				x = -18;
			}else if((this.getX() >= 1235)){
				if(inventory.find(3)) {
					worldType = 5;
					x = 1;
				}else {
					x = 1235;
				}
			}
			level3 = true; //How dos this work?
		}
		
		if(this.getY() <= 0) {
			y = 0;
		}else if(getY() >= 1155) {
			y = 1155;
		}
		
		inventory.update();
	}

	@Override
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		for (Entity e : handler.getEntityManager().getAllEntities()) {
			if (e.equals(this)) {
				continue;
			}
			if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
				if ((e.getId() == ID.BasicEnemy) || (e.getId() == ID.StaticEnemy) || (e.getId() == ID.PatrolEnemy)) {
					health-=damage;
				} else if(e.getId() == ID.PatrolBullet) {
					e.setActive(false);
					health-=1.5f;
				}
			}
		}
		return false;
	}
	
	private void getInput() {
		xMove = 0;
		yMove = 0;
		if((this.id == ID.Player)||(this.id == ID.Player1)) {
			if (handler.getKeyManager().up) {
				yMove = -velY;
				dir = -1;
			}
			if (handler.getKeyManager().down) {
				yMove = velY;
				dir = 1;
			}
			if (handler.getKeyManager().left) {
				xMove = -velX;
				dir = -1;
			}
			if (handler.getKeyManager().right) {
				xMove = velX;
				dir = 1;
			}
		} else if(this.id == ID.Player2) {
			if (handler.getKeyManager().up2) {
				yMove = -velY;
				dir = -1;
			}
			if (handler.getKeyManager().down2) {
				yMove = velY;
				dir = 1;
			}
			if (handler.getKeyManager().left2) {
				xMove = -velX;
				dir = -1;
			}
			if (handler.getKeyManager().right2) {
				xMove = velX;
				dir = 1;
			}
		}
		
		if(!handler.multiplayer) {
			if ((handler.getKeyManager().sprint)&& (handler.getKeyManager().right)) {
				if (stamina > 10) {
					stamina -= tired;
				}			
				if (stamina < 10) {
					xMove = velX;
				} else {
					xMove = velY * 2;
				}
			}
			if ((handler.getKeyManager().sprint)&& (handler.getKeyManager().left)) {
				if (stamina > 10) {
					stamina -= tired;
				}
				if (stamina < 10) {
					xMove = -velX;
				} else {
					xMove = -velY * 2;
				}
			}
			if ((handler.getKeyManager().sprint)&& (handler.getKeyManager().up)) {
				if (stamina > 10) {
					stamina -= tired;
				}
				if (stamina < 10) {
					yMove = -velY;
				} else {
					yMove = -velY * 2;
				}
			}
			if ((handler.getKeyManager().sprint)&& (handler.getKeyManager().down)) {
				if (stamina > 10) {
					stamina -= tired;
				}
				if (stamina < 10) {
					yMove = velY;
				} else {
					yMove = velY * 2;
				}
			}
		}
		
		if ((handler.getKeyManager().attack))  {
			String filepath = "shoot.wav";
			Sound sound = new Sound();
			sound.playMusic(filepath);
			if ((handler.getKeyManager().left) || (handler.getKeyManager().up)) {
				c.addBullet(new PlayerBullet(handler, this.getX()+ 52, this.getY() + 32, -1, ID.PlayerBullet));	
				handler.getKeyManager().attack = false;
			} else if ((handler.getKeyManager().right) || (handler.getKeyManager().down)) {
				c.addBullet(new PlayerBullet(handler, this.getX()+ 52, this.getY() + 32, 1, ID.PlayerBullet));
				handler.getKeyManager().attack = false;
			} else {
				handler.getEntityManager().addObject(new PlayerBullet(handler,this.getX()+ 52, this.getY() + 32, 1, ID.PlayerBullet));
				//c.addBullet(new PlayerBullet(handler,this.getX()+ 52, this.getY() + 32, 1, ID.PlayerBullet));
				handler.getKeyManager().attack = false;
			}
		}
		
		if ((handler.getKeyManager().keyJustPressed(KeyEvent.VK_H))) {
			if (message >= 4) {
				message = 0;
			} else {
				message++;
			}
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentFrame(), (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height,null);
//		g.setColor(Color.red);
//		g.fillRect((int)(x + bounds.x - handler.getGame().getGameCamera().getxOffset()), (int)(y + bounds.y - handler.getGame().getGameCamera().getyOffset()), bounds.width, bounds.height);
		if(worldType == 2) {
			if(inventory.find(4)) {
				g.drawImage(Assets.blackCircle, (int)(x - handler.getGameCamera().getxOffset() - 2350), (int)(y - handler.getGameCamera().getyOffset())-1330, 4980, 2700,null);
				g.drawImage(Assets.lantern, (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset())+15, 16, 26,null);
			}else {
				g.drawImage(Assets.darkScr, 0, 0, 1024, 768,null);
			}
		}

		inventory.render(g);
	}
	
	public void playerDamage(int amount) {
		health-= amount;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	public int getWorldType() {
		return worldType;
	}

	public void setWorldType(int worldType) {
		this.worldType = worldType;
	}

	private BufferedImage getCurrentFrame() {
		if(xMove < 0) {
			if(this.character == 2) {
				return AanimLeft.getCurrentFrame();
			}else if(this.character == 1) {
				return EanimLeft.getCurrentFrame();
			}else {
				return HanimLeft.getCurrentFrame();
			}
		}else if (xMove > 0) {
			if(this.character == 2) {
				return AanimRight.getCurrentFrame();
			}else if(this.character == 1) {
				return EanimRight.getCurrentFrame();
			}else {
				return HanimRight.getCurrentFrame();
			}
		}else if (yMove < 0) {
			if(this.character == 2) {
				return AanimDown.getCurrentFrame();
			}else if(this.character == 1) {
				return EanimDown.getCurrentFrame();
			}else {
				return HanimDown.getCurrentFrame();
			}
		}else if(yMove > 0) {
			if(this.character == 2) {
				return AanimUp.getCurrentFrame();
			}else if(this.character == 1) {
				return EanimUp.getCurrentFrame();
			}else {
				return HanimUp.getCurrentFrame();
			}
		}else {
			if(this.character == 2) {
				return AanimStill.getCurrentFrame();
			}else if(this.character == 1) {
				return EanimStill.getCurrentFrame();
			}else {
				return HanimStill.getCurrentFrame();
			}
		}
	}
	
	public boolean isLevel1() {
		return level1;
	}

	public void setLevel1(boolean level1) {
		this.level1 = level1;
	}

	public boolean isLevel2() {
		return level2;
	}

	public void setLevel2(boolean level2) {
		this.level2 = level2;
	}

	public boolean isLevel3() {
		return level3;
	}

	public void setLevel3(boolean level3) {
		this.level3 = level3;
	}
	
	public int getMessage() {
		return message;
	}

	public void setMessage(int message) {
		this.message = message;
	}

}
