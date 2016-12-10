package com.ld.game.item;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ld.game.entity.Entity;
import com.ld.game.graphics.map.Map;

public abstract class InventoryItem {
	
	private String name;
	
	private Sprite icon;
	
	private int amount;
	
	private String description;
	
	public InventoryItem(String name, Sprite icon, int amount, String description){
		this.setName(name);
		this.setIcon(icon);
		this.amount = amount;
		this.setDescription(description);
	}
	
	public void use(Map map, Entity playerEntity){
		if(this.amount > 0){
			this.amount--;
			this.onUse(map, playerEntity);
		}
	}
	
	public abstract void onUse(Map map, Entity playerEntity);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Sprite getIcon() {
		return icon;
	}

	public void setIcon(Sprite icon) {
		this.icon = icon;
	}
	
	public int getAmount(){
		return this.amount;
	}
	
	public void addAmount(int amount){
		this.amount += amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setAmount(int amount){
		this.amount = amount;
	}
	
}
