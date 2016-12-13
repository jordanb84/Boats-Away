package com.ld.game.item.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ld.game.entity.Entity;
import com.ld.game.graphics.map.Map;
import com.ld.game.item.InventoryItem;

public class ItemRock extends InventoryItem {

	public ItemRock(int amount) {
		super("Rock", new Sprite(new Texture(Gdx.files.internal("assets/rockitem.png"))), amount, "Rocks! Used to build the bridge");
	}

	@Override
	public void onUse(Map map, Entity playerEntity) {
	}

}
