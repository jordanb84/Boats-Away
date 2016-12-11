package com.ld.game.item.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ld.game.entity.Entity;
import com.ld.game.graphics.map.Map;
import com.ld.game.item.InventoryItem;

public class ItemRawPig extends InventoryItem {

	public ItemRawPig(int amount) {
		super("Raw Meat", new Sprite(new Texture(Gdx.files.internal("assets/pigitem.png"))), amount, "Can be processed to make soldiers");
	}

	@Override
	public void onUse(Map map, Entity playerEntity) {
		
	}

}
