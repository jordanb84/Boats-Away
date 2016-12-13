package com.ld.game.item.impl;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.ld.game.entity.impl.EntitySoldier;
import com.ld.game.graphics.map.Map;
import com.ld.game.item.Inventory;
import com.ld.game.item.InventoryItem;
import com.ld.game.item.ShopBox;

public class ShopBoxSoldier extends ShopBox {

	public ShopBoxSoldier(Map map, Inventory playerInventory, InventoryItem[] cost, Inventory inventory, InventoryItem returnItem) {
		super(map, playerInventory, cost, inventory, returnItem);
	}

	@Override
	public void onUse(){
		super.onUse();
	}

	@Override
	public void use() {
		
		float randomX = (0);
		float randomY = (0);
		
		Random random = new Random();
		
		if(random.nextBoolean()){
			randomX = (random.nextInt(16));
			randomY = (random.nextInt(16));
		}else{
			randomX = (-random.nextInt(16));
			randomY = (-random.nextInt(16));
		}
		
		this.getMap().spawnEntity(new EntitySoldier(this.getMap(), new Vector2(32 + randomX, 144 + randomY)));
	}
	
}
