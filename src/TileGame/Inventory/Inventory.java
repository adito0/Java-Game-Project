package TileGame.Inventory;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import TileGame.Handler;
import TileGame.GFX.Assets;
import TileGame.Item.Item;

public class Inventory {
	private Handler handler;
	private boolean active = false;
	private ArrayList<Item> inventoryItems;
	
	private int invWidth = 660, invHeight = 400,
			invX = (int)512 - (invWidth / 2), invY = (int)358 - (invHeight / 2),
			invListCenterX = invX + 171,
			invListCenterY = invY + invHeight / 2 + 5,
			invListSpacing = 30, offsetX = 10, offsetY = 110;
	
	private int invImageX = 452, invImageY = 82,
			invImageWidth = 80, invImageHeight = 80;
	
	private int invCountX = 484, invCountY = 172;
	
	private int selectedItem = 0;
	
	public Inventory(Handler handler){
		this.handler = handler;
		inventoryItems = new ArrayList<Item>();
	}
	
	public boolean find(int id) {
		for(int i = 0; i < inventoryItems.size(); i++) {
			Item item = inventoryItems.get(i);
			if(item.getId() == id) {
				return true;
			}
		}
		return false;
	}
	
	public void update(){

	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public boolean getActive() {
		return active;
	}

	public void render(Graphics g){
		if(!active)
			return;
		
		g.drawImage(Assets.inventoryscr, invX, invY, invWidth, invHeight, null);
		
		int len = inventoryItems.size();
		int Xrend, Yrend;
		if(len == 0)
			return;
		
		ArrayList<int[]> positions = new ArrayList<int[]>();
		
		for(int i = 0; i < len; i++) {
			if(i % 3 == 0) {
				Xrend = 90;
			}else if(i % 3 == 1) {
				Xrend = 300;
			}else {
				Xrend = 510;
			}
			
			if(i < 3) {
				Yrend = 200;
			}else {
				Yrend = 360;
			}
			g.setFont(new Font("Century Gothic", Font.BOLD, 12));
			g.setColor(Color.ORANGE);
			g.drawString(">  " + inventoryItems.get(i).getName() + " ( " + (int)(inventoryItems.get(i).getCount()) + " )" + "  <", invX + Xrend, invY + Yrend);
			g.drawImage(inventoryItems.get(i).getTexture(), invX + Xrend - offsetX, invY + Yrend -offsetY, invImageWidth, invImageHeight, null);
		
		}
	}
	
	// Inventory methods
	public int coinAmount() {
		for(Item i : inventoryItems) {
			if(i.getId() == 0) {
				return i.getCount();
			}
		}
		return 0;
	}
	
	public void addItem(Item item){
		for(Item i : inventoryItems){
			if(i.getId() == item.getId()){
				i.setCount(i.getCount() + item.getCount());
				return;
			}
			
		}
		if(inventoryItems.size() >= 6) {
			return;
		}
		inventoryItems.add(item);
	}
	
	// GETTERS SETTERS

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public boolean isActive() {
		return active;
	}
	
}

