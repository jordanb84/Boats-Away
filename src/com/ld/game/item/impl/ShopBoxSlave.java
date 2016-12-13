package com.ld.game.item.impl;

import com.badlogic.gdx.math.Vector2;
import com.ld.game.entity.impl.EntitySlave;
import com.ld.game.graphics.map.Map;
import com.ld.game.item.Inventory;
import com.ld.game.item.InventoryItem;
import com.ld.game.item.ShopBox;

public class ShopBoxSlave extends ShopBox {

	int uses = 0;
	
	public ShopBoxSlave(Map map, Inventory playerInventory, InventoryItem[] cost, Inventory inventory, InventoryItem returnItem) {
		super(map, playerInventory, cost, inventory, returnItem);
	}

	@Override
	public void onUse(){
		super.onUse();
	}

	@Override
	public void use() {
		this.getMap().spawnEntity(new EntitySlave(this.uses, this.getMap(), new Vector2(100, 100)));
		this.uses++;
	}
	
}
