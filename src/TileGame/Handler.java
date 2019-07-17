package TileGame;

import java.awt.Graphics;

import TileGame.Entity.EntityManager;
import TileGame.Entity.Character.HUD;
import TileGame.Entity.Character.Player;
import TileGame.Input.KeyManager;
import TileGame.Input.MouseManager;
import TileGame.Worlds.World;
import TileGame.GFX.*;

public class Handler {

	private Game game;
	private World world;
	private World worldMultiplayer;
	public World getWorldMultiplayer() {
		return worldMultiplayer;
	}



	public void setWorldMultiplayer(World worldMultiplayer) {
		this.worldMultiplayer = worldMultiplayer;
	}

	private EntityManager entityManager;
	private Player player;
	private HUD hud;
	private Player player1;
	private Player player2;
	public int characterType = 0;
	public Player getPlayer1() {
		return player1;
	}



	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}



	public Player getPlayer2() {
		return player2;
	}



	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public boolean multiplayer = false;
	public int getCharacterType() {
		return characterType;
	}



	public void setCharacterType(int characterType) {
		this.characterType = characterType;
	}

	public Handler(Game game) {
		this.game = game;
		//entityManager = new EntityManager();
	}
	
	public void update() {
		//entityManager.update();
		player.setCharacter(this.characterType);
	}

	public void render(Graphics g) {
		//entityManager.render(g);
	}
	
 
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
    public GameCamera getGameCamera() {
    	return game.getGameCamera();
    }

    public int getWidth(){
        return game.getWidth();
    }

    public int getHeight(){
        return game.getHeight();
    }

    public KeyManager getKeyManager(){
        return game.getKeyManager();
    }
    
    public MouseManager getMouseManager() {
    	return game.getMouseManager();
    }



    //GETTER SETTER
    public Game getGame() {
        return game;
    }

    public World getWorld() {
        return world;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setWorld(World world) {
        this.world = world;
    }
    
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}   
	
	public HUD getHud() {
		return hud;
	}

	public void setHud(HUD hud) {
		this.hud = hud;
	}
}
