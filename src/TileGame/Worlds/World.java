package TileGame.Worlds;

import java.awt.Graphics;

import java.util.Arrays;
import java.lang.*;

import TileGame.Game;
import TileGame.Tile.Tile;
import TileGame.Utilities.Utilities;
import TileGame.Handler;
import TileGame.Entity.EntityManager;
import TileGame.Entity.ID;
import TileGame.Entity.Character.BasicEnemy;
import TileGame.Entity.Character.BossEnemy;
import TileGame.Item.ItemManager;

public class World {

	private Handler handler;
	private int width, height, spawnX, spawnY; // eg 5x5 map
	private int[][] tiles;
	private EntityManager entityManager;
	private ItemManager itemManager;

	public World(Handler handler, String path) {
		this.handler = handler;
		entityManager = handler.getEntityManager();
		itemManager = new ItemManager(handler);
		loadWorld(path);
	}

	public void loadWorld(String path) {
		String file = Utilities.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utilities.parseInt(tokens[0]);
		height = Integer.parseInt(tokens[1]);
		spawnX = Utilities.parseInt(tokens[2]) * Tile.TILEWIDTH;
		spawnY = Utilities.parseInt(tokens[3]) * Tile.TILEHEIGHT;	
		
		String[] newArray = Arrays.copyOfRange(tokens, 4, tokens.length);
		tiles = new int[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				tiles[i][j] = Integer.parseInt(newArray[(i * width) + j]);
			}
		}
		
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Tile getTile(int y, int x) {

		if (y < 0 || x < -18 || y >= width || x >= height) {
			return Tile.lightGrass;
		}
		Tile t = Tile.tiles[tiles[x][y]];
		if (t == null) {
			return Tile.lightGrass;
		}
		return t;
	}

	public void update() {
		itemManager.update();
	}

	public void render(Graphics g) {
		// his stuff
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width,
				(handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height,
				(handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

		for (int y = yStart; y < yEnd; y++) {
			for (int x = xStart; x < xEnd; x++) {
				if(getTile(x, y) == null) {
					return;
				}
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		// his stuff ends

		/*
		 * <<<<<<< HEAD for(int y = 0; y < height; y++) { for (int x = 0; x < width;
		 * x++) { getTile(x, y).render(g, (int)(x * Tile.TILEWIDTH -
		 * handler.getGame().getGameCamera().getxOffset()), y * (int)(Tile.TILEHEIGHT -
		 * handler.getGame().getGameCamera().getyOffset())); } }
		 */

		/*
		 * for(int y = 0; y < height; y++) { for (int x = 0; x < width; x++) {
		 * getTile(x, y).render(g, (int)(x * Tile.TILEWIDTH -
		 * handler.getGame().getGameCamera().getxOffset()), y * (int)(Tile.TILEHEIGHT -
		 * handler.getGame().getGameCamera().getyOffset())); } }
		 */
		
		itemManager.render(g);
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}

	public int getSpawnX() {
		return spawnX;
	}

	public void setSpawnX(int spawnX) {
		this.spawnX = spawnX;
	}

	public int getSpawnY() {
		return spawnY;
	}

	public void setSpawnY(int spawnY) {
		this.spawnY = spawnY;
	}
}
