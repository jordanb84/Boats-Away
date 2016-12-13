package com.ld.game.item.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ld.game.entity.Entity;
import com.ld.game.graphics.map.Map;
import com.ld.game.item.InventoryItem;

public class ItemSlave extends InventoryItem {

	public ItemSlave(int amount) {
		super("Slave", new Sprite(new Texture(Gdx.files.internal("assets/slaveitem.png"))), amount, "Click to place a slave");
		
	}

	@Override
	public void onUse(Map map, Entity playerEntity) {
		
	}
	

}
