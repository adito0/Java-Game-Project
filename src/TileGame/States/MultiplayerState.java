package TileGame.States;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import TileGame.Handler;
import TileGame.Entity.EntityManager;
import TileGame.Entity.ID;
import TileGame.Entity.Character.BasicEnemy;
import TileGame.Entity.Character.BossEnemy;
import TileGame.Entity.Character.BossEnemyBullet;
import TileGame.Entity.Character.BulletController;
import TileGame.Entity.Character.HUD;
import TileGame.Entity.Character.PatrolEnemy;
import TileGame.Entity.Character.Player;
import TileGame.Item.Item;
import TileGame.Utilities.Utilities;
import TileGame.Worlds.World;

public class MultiplayerState extends State{

private World world1;
private HUD hud;
private HUD hud2;
private boolean notPaused = true;
private BulletController c;
public boolean isNotPaused() {
	return notPaused;
}

public void setNotPaused(boolean notPaused) {
	this.notPaused = notPaused;
}

private Player player1, player2;
private EntityManager entityManager;
private PatrolEnemy patrol;

public MultiplayerState (Handler handler) {
	super(handler);
	world1 = new World(handler, "Resources/Worlds/world3.txt");
	handler.setWorldMultiplayer(world1);
	entityManager = new EntityManager(handler);
	handler.setEntityManager(entityManager);
	player1 = new Player(handler, world1.getSpawnX(), world1.getSpawnY(), ID.Player1);
	player1.setCharacter(2);
	player2 = new Player(handler, world1.getSpawnX() + 100, world1.getSpawnY(), ID.Player2);
	player2.setCharacter(1);
	handler.setPlayer1(player1);
	handler.setPlayer2(player2);
	hud = new HUD(player1, 0, true);
	hud2 = new HUD(player2, 100, true);
	this.enemyLoader("Resources/Worlds/World2Enemies.txt");
	this.entityManager.addObject(player1);
	this.entityManager.addObject(player2);
	handler.getEntityManager().addObject(player1);
	handler.getEntityManager().addObject(player1);
	this.enemyLoader("Resources/Worlds/World3Enemies.txt");
	c = new BulletController(handler);
	player1.setC(c);
	//patrol.setC(c);
	//this.entityManager.addObject(new PatrolEnemy(handler, 700 , 1016, 700, 700, 1016, 1200, ID.PatrolEnemy));
	//this.entityManager.addObject(new BossEnemy(handler, 700 , 1016, ID.BossEnemy));

}

public Player getPlayer() {
	return player1;
}

public void setPlayer(Player player) {
	this.player1 = player;
}

@Override
public void update() {
	if(handler.multiplayer) {
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_P)) {
			notPaused = !notPaused;
			player1.getInventory().setActive(!player1.getInventory().getActive());
		}
		if(notPaused) {
			handler.getEntityManager().update();
			world1.update();
			handler.update();
			hud.update();
			hud2.update();
			//c.update();
		}
		player1.getInventory().update();
	}
}

@Override
public void render(Graphics g) {
	if(handler.multiplayer) {
		world1.render(g);
		handler.render(g);
		hud.render(g);
		hud2.render(g);
		handler.getEntityManager().update();
		//c.render(g);
	}
}

public BulletController getC() {
	return c;
}

public void setC(BulletController c) {
	this.c = c;
}

private void enemyLoader(String path) {
	String file = Utilities.loadFileAsString(path);
	String[] tokens = file.split("\n");
	int k = Arrays.asList(tokens).indexOf("*");
	for(int i = 1; i < k; i++) {
		String[] p = tokens[i].split("\\s+");
		if(p[0].equals("1")) {
			//this.entityManager.addObject(new BossEnemy(handler,300 , 400, ID.BossEnemy));
			handler.getEntityManager().addObject(new BossEnemy(handler,300 , 400, ID.BossEnemy));
		}else if(p[0].equals("2")) {
			//this.entityManager.addObject(new BasicEnemy(handler,Integer.parseInt(p[1]) , Integer.parseInt(p[2]), ID.BasicEnemy, 0));
			handler.getEntityManager().addObject(new BasicEnemy(handler,Integer.parseInt(p[1]) , Integer.parseInt(p[2]), ID.BasicEnemy, 0));
		}
	}
	for(int i = k+2; i < tokens.length; i++) {
		String[] p = tokens[i].split("\\s+");
		if(p[0].equals("1")) {
			handler.getWorldMultiplayer().getItemManager().addItem(Item.coinItem.createNew(Integer.parseInt(p[1]), Integer.parseInt(p[2])));
		}else if(p[0].equals("2")) {
			handler.getWorldMultiplayer().getItemManager().addItem(Item.keyItem.createNew(Integer.parseInt(p[1]), Integer.parseInt(p[2])));
		}
	}
	

}

}

