package TileGame.States;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import TileGame.Handler;
import TileGame.Entity.EntityManager;
import TileGame.Entity.ID;
import TileGame.Entity.Character.BasicEnemy;
import TileGame.Entity.Character.BossEnemy;
import TileGame.Entity.Character.BulletController;
import TileGame.Entity.Character.HUD;
import TileGame.Entity.Character.PatrolEnemy;

import TileGame.Entity.Character.Player;
import TileGame.Entity.Character.StaticEnemy;
import TileGame.Item.Item;
import TileGame.Utilities.Utilities;
import TileGame.Worlds.MakeTextFile;
import TileGame.Worlds.World;

public class GameState extends State {

	private World world;
	private HUD hud;
	private boolean notPaused = true;
	private BulletController c;

	private Player player;
	private EntityManager entityManager;
	private PatrolEnemy patrol;

	public GameState(Handler handler/*, EntityManager entityManager*/) {
		super(handler);
		world = new World(handler, "Resources/Worlds/tutorialworld.txt");
		player = new Player(handler, world.getSpawnX(), world.getSpawnY(), ID.Player);
		hud = new HUD(player, 0, handler.multiplayer);
		handler.setHud(hud);
		handler.setWorld(world);
		handler.setPlayer(player);
		this.entityManager = new EntityManager(handler);
		handler.setEntityManager(entityManager);
		this.entityManager.addObject(player);
		player.setWorldType(6);
		handler.getWorld().getItemManager().addItem(Item.key3Item.createNew(600, 600));
		c = new BulletController(handler);
		player.setC(c);
	}

	@Override
	public void update() {
		if(!handler.multiplayer) {
			if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_P)) {
				notPaused = !notPaused;
				player.getInventory().setActive(!player.getInventory().getActive());
			}
			if(notPaused) {
				world.update();
				handler.update();
				hud.update();
				entityManager.update();
				//c.update();

				if(player.getWorldType() == 1) {
					world = new World(handler, "Resources/Worlds/world2.txt");
					handler.setWorld(world);
					entityManager = new EntityManager(handler);
					handler.setEntityManager(entityManager);
					entityManager.addObject(player);
					player.setWorldType(2);
					this.enemyLoader("Resources/Worlds/World2Enemies.txt");
				} else if(player.getWorldType() == 3) {
					world = new World(handler, "Resources/Worlds/world3.txt");
					handler.setWorld(world);
					entityManager = new EntityManager(handler);
					handler.setEntityManager(entityManager);
					entityManager.addObject(player);
					player.setWorldType(4);
					this.enemyLoader("Resources/Worlds/World3Enemies.txt");
				} else if(player.getWorldType() == 5) {
					world = new World(handler, "Resources/Worlds/world1.txt");
					handler.setWorld(world);
					entityManager = new EntityManager(handler);
					handler.setEntityManager(entityManager);
					entityManager.addObject(player);
					player.setWorldType(0);
					this.enemyLoader("Resources/Worlds/World1Enemies.txt");
				}
			}
			player.getInventory().update();
		}
	}

	@Override
	public void render(Graphics g) {
		if(!handler.multiplayer) {
			world.render(g);
			handler.render(g);
			entityManager.render(g);
			hud.render(g);
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
				this.entityManager.addObject(new BossEnemy(handler,Integer.parseInt(p[1]) , Integer.parseInt(p[2]), ID.BossEnemy));
			}else if(p[0].equals("2")) {
				this.entityManager.addObject(new BasicEnemy(handler,Integer.parseInt(p[1]) , Integer.parseInt(p[2]), ID.BasicEnemy, Integer.parseInt(p[3])));
			}else if(p[0].equals("3")) {
				this.entityManager.addObject(new PatrolEnemy(handler,Integer.parseInt(p[1]) , Integer.parseInt(p[2]), Integer.parseInt(p[1]), Integer.parseInt(p[1]), Integer.parseInt(p[2]), Integer.parseInt(p[3]), ID.PatrolEnemy, Integer.parseInt(p[4]), Integer.parseInt(p[5])));
			}
		}
		for(int i = k+2; i < tokens.length; i++) {
			String[] p = tokens[i].split("\\s+");
			if(p[0].equals("1")) {
				handler.getWorld().getItemManager().addItem(Item.coinItem.createNew(Integer.parseInt(p[1]), Integer.parseInt(p[2])));
			}else if(p[0].equals("2")) {
				handler.getWorld().getItemManager().addItem(Item.keyItem.createNew(Integer.parseInt(p[1]), Integer.parseInt(p[2])));
			}else if(p[0].equals("3")) {
				handler.getWorld().getItemManager().addItem(Item.key2Item.createNew(Integer.parseInt(p[1]), Integer.parseInt(p[2])));
			}else if(p[0].equals("4")) {
				handler.getWorld().getItemManager().addItem(Item.lantern.createNew(Integer.parseInt(p[1]), Integer.parseInt(p[2])));
			}
		}
	}
		
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public boolean isNotPaused() {
		return notPaused;
	}

	public void setNotPaused(boolean notPaused) {
		this.notPaused = notPaused;
	}

}
