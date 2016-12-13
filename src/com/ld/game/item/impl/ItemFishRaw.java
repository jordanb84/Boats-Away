package com.ld.game.item.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ld.game.entity.Entity;
import com.ld.game.graphics.map.Map;
import com.ld.game.item.InventoryItem;

public class ItemFishRaw extends InventoryItem {

	public ItemFishRaw(int amount) {
		super("Raw Fish", new Sprite(new Texture(Gdx.files.internal("assets/fishraw.png"))), amount, "Used to make cooked fish with a fire");
	}

	@Override
	public void onUse(Map map, Entity playerEntity) {
		
	}

}
