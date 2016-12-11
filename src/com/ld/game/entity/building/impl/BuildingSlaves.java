package com.ld.game.entity.building.impl;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.ld.game.entity.building.EntityBuilding;
import com.ld.game.graphics.map.Map;
import com.ld.game.item.Inventory;
import com.ld.game.item.InventoryItem;
import com.ld.game.item.ShopInventory;

public class BuildingSlaves extends EntityBuilding {

	public BuildingSlaves(String buildingName, String description, Map map, Vector2 position,
			Inventory playerInventory) {
		super(buildingName, description, map, position, playerInventory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<InventoryItem> setupCost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setupSprites() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Sprite setOutlinedSprite() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShopInventory setupInventory(Map map, Inventory playerInventory) {
		// TODO Auto-generated method stub
		return null;
	}

}
