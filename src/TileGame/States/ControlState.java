package TileGame.States;

import java.awt.Graphics;

import TileGame.Handler;
import TileGame.GFX.Assets;

public class ControlState extends State{

	public ControlState(Handler handler) {
		super(handler);
	}

	@Override
	public void update() {
		if(handler.getKeyManager().attack) {
				StateManager.setState(handler.getGame().charselState);
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.ctrlscr, 0, 0, 1024, 768, null);
	}

}
