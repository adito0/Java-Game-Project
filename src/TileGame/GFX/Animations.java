package TileGame.GFX;

import java.awt.image.BufferedImage;

public class Animations {

	private int speed, index;
	private long lastTime, timer;
	private BufferedImage[] frames;

	public Animations(int speed, BufferedImage[] frames) {
		this.frames = frames;
		this.speed = speed;
		timer = 0;
		index = 0;
		lastTime = System.currentTimeMillis();
	}

	public BufferedImage getCurrentFrame() {
		return frames[index];
	}
	public int getindex() {
		return index;
	}
	
	public BufferedImage getImageAtIndex(int num) {
		return frames[num];
	}


	public void update() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();

		if (timer > speed) {
			index++;
			timer = 0;

			if (index >= frames.length) {
				index = 0;
			}
		}

	}

}
