package TileGame.Entity;

import java.awt.Graphics;
import java.util.Comparator;
import java.util.LinkedList;

import TileGame.Handler;

public class EntityManager {
	
	private Handler handler;
	private LinkedList<Entity> object = new LinkedList<Entity>();
			
	public EntityManager(Handler handler){
			this.handler = handler;
	}
	
	public void update() {
		for (int i= 0; i < object.size(); i++) {
			Entity tempObject = object.get(i); //get id of the object
			if(!tempObject.isActive()){ 
				object.remove(tempObject); 
			}
			tempObject.update();
		}
	}

	public void render(Graphics g) {
		int playerIndex = 0;
		int player1Index = 0;
		for (int i = 0; i < object.size(); i++) {
			Entity tempObject = object.get(i); // get id of the object
			if((tempObject.getId()!=ID.Player)||(tempObject.getId()!=ID.Player1)){
				tempObject.render(g);
			}else if(tempObject.getId() == ID.Player) {
				playerIndex = i;
			}else {
				player1Index = i;
			}
		}
		object.get(playerIndex).render(g);
		object.get(player1Index).render(g);
	}
	
	public void pickUpKey(Entity player, Entity e) {
			object.remove(e);
	}
	
	public void addObject(Entity object) {
		this.object.add(object);
	}

	public void removeObject(Entity object) {
		this.object.remove(object);
	}
	
	 public LinkedList<Entity> getAllEntities(){
	    	return object;
	 }	
		
}
