package com.ld.game.item.impl;

import java.util.ArrayList;
import java.util.List;

import com.ld.game.graphics.map.Map;
import com.ld.game.item.Inventory;
import com.ld.game.item.InventoryItem;
import com.ld.game.item.ShopBox;
import com.ld.game.item.ShopInventory;

public class ShopInventorySlaves extends ShopInventory {

	public ShopInventorySlaves(Map map, Inventory playerInventory) {
		super(map, playerInventory);
		
	}

	@Override
	public List<ShopBox> setupBoxes(Map map, Inventory playerInventory) {
		List<ShopBox> boxes = new ArrayList<ShopBox>();
		boxes.add(new ShopBoxSlave(map, playerInventory, new InventoryItem[] {new ItemBread(5), new ItemFishCooked(10), new ItemBacon(3)}, this, new ItemSlave(1)));
		//boxes.add(new ShopBoxSlave(map, playerInventory, new InventoryItem[] {new ItemLog(1)}, this, new ItemSlave(1)));
		
		return boxes;
	}

}
