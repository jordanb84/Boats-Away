package com.ld.game.item.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ld.game.entity.Entity;
import com.ld.game.graphics.map.Map;
import com.ld.game.item.InventoryItem;

public class ItemSoldier extends InventoryItem {

	public ItemSoldier(int amount) {
		super("Soldier Troll", new Sprite(new Texture(Gdx.files.internal("assets/soldierdown.png"))), amount, "Click to place a soldier");
		
	}

	@Override
	public void onUse(Map map, Entity playerEntity) {
		
	}
	

}
