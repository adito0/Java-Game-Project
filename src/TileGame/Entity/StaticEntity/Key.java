package TileGame.Entity.StaticEntity;

import java.awt.Graphics;

import TileGame.Handler;
import TileGame.Entity.ID;
import TileGame.GFX.Assets;
import TileGame.Tile.Tile;

public class Key extends StaticEntity {
	public Key(Handler handler, float x, float y, ID id) {
		super(handler, x, y, (int) (Tile.TILEWIDTH / 2), (int) (Tile.TILEHEIGHT) / 2, id);
		bounds.x = 3;
		bounds.y = 10;
		bounds.width = 30;
		bounds.height = 10;
	}

	public void update() {

	}

	public void render(Graphics g) {
		g.drawImage(Assets.key, (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		//g.fillRect(
		// handler.getGame().getGameCamera().getxOffset()), (int)(y + bounds.y -
		// handler.getGame().getGameCamera().getyOffset()), bounds.width,
		// bounds.height);
	}
}
