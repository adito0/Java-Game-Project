package TileGame.States;

import TileGame.Handler;

import java.awt.Graphics;

public abstract class State {

	// CLASS

	protected Handler handler;
	protected boolean active;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public State(Handler handler) {

		this.handler = handler;
	}

	public abstract void update();

	public abstract void render(Graphics g);
}
