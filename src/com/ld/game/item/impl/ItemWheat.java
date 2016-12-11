package com.ld.game.item.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ld.game.entity.Entity;
import com.ld.game.graphics.map.Map;
import com.ld.game.item.InventoryItem;

public class ItemWheat extends InventoryItem {

	public ItemWheat(int amount) {
		super("Wheat", new Sprite(new Texture(Gdx.files.internal("assets/wheat.png"))), amount, "Used for feeding slaves");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onUse(Map map, Entity playerEntity) {
		// TODO Auto-generated method stub
		
	}

}
