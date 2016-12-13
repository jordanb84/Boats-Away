package com.ld.game.item.impl;

import java.util.ArrayList;
import java.util.List;

import com.ld.game.graphics.map.Map;
import com.ld.game.item.Inventory;
import com.ld.game.item.ShopBox;
import com.ld.game.item.ShopInventory;

public class ShopInventoryBridge extends ShopInventory {

	public ShopInventoryBridge(Map map, Inventory playerInventory) {
		super(map, playerInventory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<ShopBox> setupBoxes(Map map, Inventory playerInventory) {
		// TODO Auto-generated method stub
		return new ArrayList<ShopBox>();
	}

}
