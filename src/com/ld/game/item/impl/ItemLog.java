package com.ld.game.item.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ld.game.entity.Entity;
import com.ld.game.graphics.map.Map;
import com.ld.game.item.InventoryItem;

public class ItemLog extends InventoryItem {

	public ItemLog(int amount) {
		super("Log", new Sprite(new Texture(Gdx.files.internal("assets/wood.png"))), amount, "Used for hiring soldiers");
	}

	@Override
	public void onUse(Map map, Entity playerEntity) {
		
	}

}
