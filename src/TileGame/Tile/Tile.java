package TileGame.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

	// STATICS

	public static Tile[] tiles = new Tile[5];
	public static Tile darkGrass = new DarkGrassTile(0);
	public static Tile lightGrass = new LightGrassTile(1);
	public static Tile sand = new SandTile(2);
	public static Tile water = new WaterTile(3);
	public static Tile rock = new RockTile(4);

	public static final int TILEWIDTH = 64, TILEHEIGHT = 64;

	// CLASSES
	protected BufferedImage texture;
	protected final int id;

	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;

		this.tiles[id] = this;
	}

	public void update() {

	}

	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}

	public boolean isWalkable() {
		return true;
	}

	public int getId() {
		return this.id;
	}
}
