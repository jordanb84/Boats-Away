package com.ld.game.entity.building.impl;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.ld.game.entity.building.EntityBuilding;
import com.ld.game.graphics.map.Map;
import com.ld.game.item.Inventory;
import com.ld.game.item.InventoryItem;
import com.ld.game.item.ShopInventory;
import com.ld.game.item.impl.ItemLog;
import com.ld.game.item.impl.ShopInventoryFirepit;

public class BuildingFirePit extends EntityBuilding {

	public BuildingFirePit(Map map, Vector2 position, Inventory playerInventory) {
		super(map, "Fire Pit", "Cooks fish", position, playerInventory);
		
		//this.unlock();
	}

	@Override
	public ShopInventory setupInventory(Map map, Inventory playerInventory) {
		return new ShopInventoryFirepit(map, playerInventory);
	}

	@Override
	public void setupSprites() {
		this.getSprites().put("default", new Sprite(new Texture(Gdx.files.internal("assets/fire0.png"))));
		
		this.setCurrentSprite("default");
	}

	@Override
	public Sprite setOutlinedSprite() {
		return new Sprite(new Texture(Gdx.files.internal("assets/fire0outlined.png")));
	}

	@Override
	public List<InventoryItem> setupCost() {
		List<InventoryItem> cost = new ArrayList<InventoryItem>();
		
		cost.add(new ItemLog(25));
		
		return cost;
	}

	@Override
	public void onPurchase() {
		// TODO Auto-generated method stub
		
	}

}
