package TileGame.States;

import java.awt.Graphics;

import java.awt.event.KeyEvent;

import TileGame.Handler;
import TileGame.GFX.Assets;

public class CharacterSelectionState extends State{
	private int selected = 0; //0: Heather, 1: Eva, 2: Amber
	
	public CharacterSelectionState(Handler handler) {
		super(handler);
	}
	
	public void update() {
		if(handler.getMouseManager().isLeftPressed()) {
			this.handler.setCharacterType(selected);
			StateManager.setState(handler.getGame().gameState);
		}
		
		if(selected == 0) {
			if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT)) {
				selected = 1;
			}
		}else if(selected == 1) {
			if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT)) {
				selected = 0;
				
			}else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT)){
				selected = 2;
				
			}
		}else if(selected == 2) {
			if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT)) {
				selected = 1;
			}
		}	
		
	}


	@Override
	public void render(Graphics g) {
		if(selected == 0) {
			g.drawImage(Assets.heather, 0, 0, 1024, 768, null);
		}else if(selected == 1) {
			g.drawImage(Assets.eva, 0, 0, 1024, 768, null);
		}else {
			g.drawImage(Assets.amber, 0, 0, 1024, 768, null);
		}
	}
}
