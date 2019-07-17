package TileGame.Entity.Character;

import java.awt.Graphics;
import java.util.LinkedList;

import TileGame.Handler;

public class BulletController {
	public LinkedList<PlayerBullet> b = new LinkedList<PlayerBullet>();
	public LinkedList<PatrolEnemyBullet> p = new LinkedList<PatrolEnemyBullet>();
	private Handler handler;
	
	PlayerBullet tempBullet;
	PatrolEnemyBullet tempBullet2;
	
	public BulletController(Handler handler){
		
		this.handler = handler;
		
		
		//addBullet(new PlayerBullet(300, 300));
		
	}
	
	public void update() {
		for (int i = 0; i < b.size(); i++) {
			tempBullet = b.get(i);
			tempBullet.update();
		}
		for (int i = 0; i < p.size(); i++) {
			tempBullet2 = p.get(i);
			tempBullet2.update();
		}
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < b.size(); i++) {
			tempBullet = b.get(i);
			tempBullet.render(g);
		}
		for (int i = 0; i < p.size(); i++) {
			tempBullet2 = p.get(i);
			tempBullet2.render(g);
		}
	}
	
	public void addBullet(PlayerBullet object) {
		b.add(object);
		handler.getEntityManager().addObject(object);
	}
	
	public void addPatrolBullet(PatrolEnemyBullet object) {
		p.add(object);
		handler.getEntityManager().addObject(object);
	}
	
	public void removeBullet(PlayerBullet object) {
		b.remove(object);
	}
}
