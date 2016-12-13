package com.ld.game.item.impl;

import com.ld.game.graphics.map.Map;
import com.ld.game.item.Inventory;
import com.ld.game.item.InventoryItem;
import com.ld.game.item.ShopBox;

public class ShopBoxFish extends ShopBox {

	private Inventory playerInventory;
	
	public ShopBoxFish(Map map, Inventory playerInventory, InventoryItem[] cost, Inventory inventory,
			InventoryItem returnItem) {
		super(map, playerInventory, cost, inventory, returnItem);
		this.playerInventory = playerInventory;
	}

	@Override
	public void use() {
		this.playerInventory.addItem(new ItemBacon(1), 1);
	}

}
