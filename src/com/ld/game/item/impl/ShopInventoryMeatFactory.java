package com.ld.game.item.impl;

import java.util.ArrayList;
import java.util.List;

import com.ld.game.graphics.map.Map;
import com.ld.game.item.Inventory;
import com.ld.game.item.InventoryItem;
import com.ld.game.item.ShopBox;
import com.ld.game.item.ShopInventory;

public class ShopInventoryMeatFactory extends ShopInventory {

	public ShopInventoryMeatFactory(Map map, Inventory playerInventory) {
		super(map, playerInventory);
	}

	@Override
	public List<ShopBox> setupBoxes(Map map, Inventory playerInventory) {
		List<ShopBox> shopBoxes = new ArrayList<ShopBox>();
		
		shopBoxes.add(new ShopBoxBacon(map, playerInventory, new InventoryItem[] {new ItemRawPig(5)}, this, new ItemBacon(1)));
		
		return shopBoxes;
	}

}
