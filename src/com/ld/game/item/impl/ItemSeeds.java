package com.ld.game.item.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ld.game.entity.Entity;
import com.ld.game.graphics.map.Map;
import com.ld.game.item.InventoryItem;

public class ItemSeeds extends InventoryItem {

	public ItemSeeds(int amount) {
		super("Seeds", new Sprite(new Texture(Gdx.files.internal("assets/seeds.png"))), amount, "Used for growing wheat on soil");
	}

	@Override
	public void onUse(Map map, Entity playerEntity) {
		
	}

}
