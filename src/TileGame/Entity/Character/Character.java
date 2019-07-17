package TileGame.Entity.Character;

import TileGame.Entity.Entity;
import TileGame.Entity.EntityCollision;
import TileGame.Entity.ID;

import java.util.Random;

import TileGame.Handler;
import TileGame.Tile.Tile;

public abstract class Character extends Entity {


    public static final float DEFAULT_HEALTH = 127;
    public static final float DEFAULT_STAMINA = 127;
    public static final float DEFAULT_SPEED = 1.0f;
    public static final int DEFAULT_WIDTH = 64;
    public static final int DEFAULT_HEIGHT = 64;

    protected float health, bossHealth;
    protected float stamina;


	//protected float speed;
    protected float velX, velY;
    protected float xMove, yMove;
    
    Random r = new Random();

    public Character(Handler handler, float x, float y, int width, int height, ID id){
        super(handler, x, y, width, height, id);
        this.health = DEFAULT_HEALTH;
        this.stamina = DEFAULT_STAMINA;
        this.bossHealth = DEFAULT_HEALTH;
        //this.speed = DEFAULT_SPEED;
        this.xMove = 0;
        this.yMove = 0;
        //this.velX = DEFAULT_SPEED;
        //this.velY = DEFAULT_SPEED;
    }
    
    public void move(){ //Can we make it private?
    	checkEntityCollisions(xMove, 0f);
    	if(collisionType != EntityCollision.EntityCollision) {
    		moveX();
    	}
    	
    	checkEntityCollisions(0f, yMove);
    	if(collisionType != EntityCollision.EntityCollision) {
    		moveY();
    	}
    }
    
    public void hurt(int amt) {
    	health -= amt;
    	if(health <= 0) {
    		active = false;
    	}
    }

    public void moveX(){
        if(xMove > 0){ //Moving right
            int tx = (int)(x + xMove + bounds.x + bounds.width)/ Tile.TILEWIDTH;
            if(canWalkThrough(tx, (int)(y + bounds.y)/Tile.TILEHEIGHT) && //getting coordinates of upper right corner of collision box
                    canWalkThrough(tx, (int)(y + bounds.y + bounds.height)/Tile.TILEHEIGHT)){ //getting coordinates of upper left corner of collision box
                x += xMove;
            }else{
                x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
            }

        }else if(xMove < 0){ //Moving left
            int tx = (int)(x + xMove + bounds.x )/ Tile.TILEWIDTH;
            if(canWalkThrough(tx, (int)(y + bounds.y)/Tile.TILEHEIGHT) &&
                    canWalkThrough(tx, (int)(y + bounds.y + bounds.height)/Tile.TILEHEIGHT)){ //getting coordinates of upper right corner of collision box
                x += xMove;
            }else{
                x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
            }
        }
    }

    public void moveY(){
        if(yMove < 0){ //Moving up
            int ty = (int)(y + yMove + bounds.y)/ Tile.TILEWIDTH;
            if(canWalkThrough((int)(x + bounds.x)/Tile.TILEWIDTH, ty) && //getting coordinates of upper right corner of collision box
                    canWalkThrough((int)(x + bounds.x + bounds.width)/Tile.TILEWIDTH, ty)){ //getting coordinates of upper left corner of collision box
                y += yMove;
            }else{
                y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
            }

        }else if(yMove > 0){ //Moving down
            int ty = (int)(y + yMove + bounds.y + bounds.height)/ Tile.TILEWIDTH;
            if(canWalkThrough((int)(x + bounds.x)/Tile.TILEWIDTH, ty) && //getting coordinates of upper right corner of collision box
                    canWalkThrough((int)(x + bounds.x + bounds.width)/Tile.TILEWIDTH, ty)){ //getting coordinates of upper left corner of collision box
                y += yMove;
            }else{
                y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
            }
        }
    }

	public int[] teleport() {
		int[] arr = new int[2];
		int telX = r.nextInt(20);
		int telY = r.nextInt(20);
		arr[0] = telX * 50;
		arr[1] = telY * 50;
		return arr;
	}
	
	protected boolean canWalkThrough(int x, int y) {
		if(!handler.multiplayer) {
			return handler.getWorld().getTile(x, y).isWalkable();
		}else {
			return handler.getWorldMultiplayer().getTile(x, y).isWalkable();
		}
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public float getVelX() {
		return velX;
	}

	public float getVelY() {
		return velY;
	}

	public float getHealth() {
		return this.health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public float getBossHealth() {
		return bossHealth;
	}

	public void setBossHealth(float bossHealth) {
		this.bossHealth = bossHealth;
	}

	public float getxMove() {
		return this.xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return this.yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}
	
    public float getStamina() {
		return stamina;
	}

	public void setStamina(float stamina) {
		this.stamina = stamina;
	}
}
