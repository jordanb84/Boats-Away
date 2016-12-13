package com.ld.game.item.impl;

import java.util.ArrayList;
import java.util.List;

import com.ld.game.graphics.map.Map;
import com.ld.game.item.Inventory;
import com.ld.game.item.InventoryItem;
import com.ld.game.item.ShopBox;
import com.ld.game.item.ShopInventory;

public class ShopInventoryBarracks extends ShopInventory {

	public ShopInventoryBarracks(Map map, Inventory playerInventory) {
		super(map, playerInventory);
		
	}

	@Override
	public List<ShopBox> setupBoxes(Map map, Inventory playerInventory) {
		List<ShopBox> boxes = new ArrayList<ShopBox>();
		boxes.add(new ShopBoxSoldier(map, playerInventory, new InventoryItem[] {new ItemLog(5), new ItemBacon(6), new ItemBread(2)}, this, new ItemSoldier(1)));
		//boxes.add(new ShopBoxSoldier(map, playerInventory, new InventoryItem[] {new ItemLog(1)}, this, new ItemSoldier(1)));
		
		return boxes;
	}

}
