package TileGame.Tile;

import TileGame.GFX.Assets;

public class DarkGrassTile extends Tile {
	public DarkGrassTile(int id) {
		super(Assets.grassTileDark, id);
	}

	@Override
	public boolean isWalkable() {
		return false;
	}
}
