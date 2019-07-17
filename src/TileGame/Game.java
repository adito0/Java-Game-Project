package TileGame;

import TileGame.Display.Display;
import TileGame.Entity.EntityManager;
import TileGame.Entity.ID;
import TileGame.Entity.Character.BulletController;
import TileGame.Entity.Character.HUD;
import TileGame.Entity.Character.Player;
import TileGame.States.*;
import TileGame.Input.KeyManager;
import TileGame.Input.MouseManager;
import TileGame.GFX.*;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

	private Display display;
	private int width, height;
	public String title;
	private Thread thread;
	private boolean running = false;
	private BufferStrategy bs;
	private Graphics g;
	private Player player;
	private HUD hud;
	private boolean multiplayer = false;
	

	// States
	public State gameState, charselState, multiplayerState, winState, controlState;
	public State menuState;
	private State highScoreState;
	public State exitState;
	public StateManager stateManager;

	// Input
	private KeyManager keyManager;
	private MouseManager mouseManager;

	// Camera
	private GameCamera gameCamera;

	// Handler
	private Handler handler;
	
	//private EntityManager entityManager;
	public BulletController c;

	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		stateManager = new StateManager();
		mouseManager = new MouseManager();
		//entityManager = new EntityManager(handler);
		c = new BulletController(handler);	
		String filepath = "BGM.wav";
		
		Sound sound = new Sound();
		sound.playMusic(filepath);
	}

	private void init() {

		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		
		Assets.init();

		handler = new Handler(this);
		//handler.setEntityManager(entityManager);

		gameCamera = new GameCamera(handler, 0, 0);

		gameState = new GameState(handler/*, entityManager*/);
		//multiplayerState = new MultiplayerState(handler);
//		player = new Player(handler, 100, 100, ID.Player);
//		hud = new HUD(player);
//		handler.setPlayer(player);
//		entityManager.addObject(player);
		menuState = new MenuState(handler);
		highScoreState = new HighScoreState(handler);
		exitState = new ExitState(handler);
		charselState = new CharacterSelectionState(handler);
		winState = new WinState(handler);
		controlState = new ControlState(handler);

		stateManager.setState(menuState);
	}

	private void update() {
		if (StateManager.getState() != null) {
			keyManager.update();
			StateManager.getState().update();
		}
		if (handler.getKeyManager().esc) {
			System.exit(0);
		}
		if (handler.getKeyManager().boss) {
			this.handler.getPlayer().setWorldType(3);
		}
		//hud.update();
	}

	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}

		g = bs.getDrawGraphics();
		// Clear Screen!

		g.clearRect(0, 0, width, height);

		// DRAW HERE!

		if (StateManager.getState() != null) {
			StateManager.getState().render(g);
		}

		// END DRAWING!
		bs.show();
		g.dispose();
		
	}

	public void run() {
		init();
		int fps = 120;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();

		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;

			if (delta >= 1) {
				update();
				render();
				delta--;
			}
		}
		stop();
	}

	public synchronized void start() {
		if (this.running) {
			return;
		} else {
			thread = new Thread(this);
			thread.start();
			this.running = true;
		}
	}

	public synchronized void stop() {
		if (!this.running) {
			return;
		} else {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	//GETTERS AND SETTERS
	
	public StateManager getStateManager() {
		return stateManager;
	}

	public void setStateManager(StateManager stateManager) {
		this.stateManager = stateManager;
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}

	public GameCamera getGameCamera() {
		return gameCamera;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public Display getDisplay() {
		return display;
	}

	public void setDisplay(Display display) {
		this.display = display;
	}
	
	public void getInput() {
		if (handler.getKeyManager().esc) {
			System.exit(0); //fix this so esc works from all screens and not just in the gameplay screen
		}
	}
}
