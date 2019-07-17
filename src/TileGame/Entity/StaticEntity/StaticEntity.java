package TileGame.Entity.StaticEntity;

import TileGame.Handler;
import TileGame.Entity.Entity;
import TileGame.Entity.ID;

public abstract class StaticEntity extends Entity {

	public StaticEntity(Handler handler, float x, float y, int width, int height, ID id) {
		super(handler, x, y, width, height, id);
	}
}
