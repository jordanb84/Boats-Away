package com.ld.game.item.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ld.game.entity.Entity;
import com.ld.game.graphics.map.Map;
import com.ld.game.item.InventoryItem;

public class ItemBread extends InventoryItem {

	public ItemBread(int amount) {
		super("Bread", new Sprite(new Texture(Gdx.files.internal("assets/bread.png"))), amount, "Used by slaves and soldiers");
	}

	@Override
	public void onUse(Map map, Entity playerEntity) {
		
	}

}
