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
import com.ld.game.item.impl.ItemRawPig;
import com.ld.game.item.impl.ShopInventoryMeatFactory;

public class BuildingMeatFactory extends EntityBuilding {

	public BuildingMeatFactory(Map map, Vector2 position, Inventory playerInventory) {
		super("Meat Factory", "Processes meat for making soldiers", map, position, playerInventory);
	}

	@Override
	public List<InventoryItem> setupCost() {
		List<InventoryItem> cost = new ArrayList<InventoryItem>();
		cost.add(new ItemRawPig(3));
		cost.add(new ItemLog(30));
		
		return cost;
	}

	@Override
	public void setupSprites() {
		this.getSprites().put("default", new Sprite(new Texture(Gdx.files.internal("assets/meatfactory.png"))));
		
		this.setCurrentSprite("default");
	}

	@Override
	public Sprite setOutlinedSprite() {
		return new Sprite(new Texture(Gdx.files.internal("assets/meatfactoryselected.png")));
	}

	@Override
	public ShopInventory setupInventory(Map map, Inventory playerInventory) {
		ShopInventory shopInventory = new ShopInventoryMeatFactory(map, playerInventory);
		return shopInventory;
	}

}
