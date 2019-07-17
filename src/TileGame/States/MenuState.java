package TileGame.States;

import TileGame.Handler;
import TileGame.GFX.Assets;
import TileGame.UI.ClickListener;
import TileGame.UI.UIImageButton;
import TileGame.UI.UIManager;

import java.awt.*;

public class MenuState extends State {

	public static UIManager uiManager;
	public MultiplayerState multiplayerState;
	
	public MenuState(Handler handler) {
		super(handler);
		this.multiplayerState = new MultiplayerState(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		uiManager.addObject(new UIImageButton(490, 400, 128, 64, Assets.btn_start, new ClickListener(){

			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				handler.multiplayer = false;
				StateManager.setState(handler.getGame().controlState);
			}
		}));
		
		uiManager.addObject(new UIImageButton(465, 500, 180, 64, Assets.mbtn, new ClickListener(){

			@Override
			public void onClick() {
				handler.multiplayer = true;
				multiplayerState = new MultiplayerState(handler);
				StateManager.setState(multiplayerState);
			}
		}));
		
	}

	@Override
	public void update() {
		uiManager.update();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.menuScr, 0, 0, 1024, 768, null);
		uiManager.render(g);
	}
}
