package com.ld.game.item.impl;

import java.util.ArrayList;
import java.util.List;

import com.ld.game.graphics.map.Map;
import com.ld.game.item.Inventory;
import com.ld.game.item.InventoryItem;
import com.ld.game.item.ShopBox;
import com.ld.game.item.ShopInventory;

public class ShopInventoryFire extends ShopInventory {

	public ShopInventoryFire(Map map, Inventory playerInventory) {
		super(map, playerInventory);
	}

	@Override
	public List<ShopBox> setupBoxes(Map map, Inventory playerInventory) {
		List<ShopBox> shopBoxes = new ArrayList<ShopBox>();
		shopBoxes.add(new ShopBoxCookedFish(map, playerInventory, new InventoryItem[] {new ItemFishRaw(2)}, this, new ItemFishCooked(2)));
		
		return shopBoxes;
	}

}
