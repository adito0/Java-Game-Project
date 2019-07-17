package TileGame.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

	private boolean[] keys, justPressed, canPress;
	public boolean up, down, up2, down2, right2, left2, left, right, esc, pickup, pause, attack, boss, sprint, help; 

	public KeyManager() {
		keys = new boolean[256];
		justPressed = new boolean[keys.length];
		canPress = new boolean[keys.length];
	}

	public void update() {
		for(int i = 0; i < keys.length; i++) { //
			if(!canPress[i] && !keys[i]) {
				canPress[i] = true;
 			}else if (justPressed[i]) {
 				canPress[i] = false;
 				justPressed[i] = false;
 			}
			
			if(canPress[i] && keys[i]) {
				justPressed[i] = true;
			}
		}
		
		if(keyJustPressed(KeyEvent.VK_SPACE)) {
			attack = true;
		}
				
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
		esc = keys[KeyEvent.VK_ESCAPE];
		help = keys[KeyEvent.VK_H];
		
		up2 = keys[KeyEvent.VK_W];
		down2 = keys[KeyEvent.VK_S];
		left2 = keys[KeyEvent.VK_A];
		right2 = keys[KeyEvent.VK_D];
		
		pause = keys[KeyEvent.VK_P];
		//attack = keys[KeyEvent.VK_SPACE];
		
		pickup = keys[KeyEvent.VK_M];
		sprint = keys[KeyEvent.VK_Z];
		boss = keys[KeyEvent.VK_PAGE_DOWN];
		
	}

	public boolean keyJustPressed(int keyCode){
		if(keyCode < 0 || keyCode >= keys.length)
			return false;
		return justPressed[keyCode];
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length) {
			return;
		}
		keys[e.getKeyCode()] = true;
		attack = false;
		//help = false;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}
