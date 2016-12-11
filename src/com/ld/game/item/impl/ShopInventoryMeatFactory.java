package com.ld.game.item.impl;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	public void render(SpriteBatch batch){
		for(ShopBox shopBox : this.getShopBoxes()){
			shopBox.getSprite().setSize(16, 16);
			shopBox.render(batch, false);
			//System.out.println("box " + shopBox.sprite.getWidth() + "/" + shopBox.sprite.getHeight() + "/" + shopBox.sprite.getX() + "/" + shopBox.sprite.getY());
		}
		//System.out.println("Rendered " + this.shopBoxes.size() + " boxes");
	}
	
	@Override
	public List<ShopBox> setupBoxes(Map map, Inventory playerInventory) {
		List<ShopBox> shopBoxes = new ArrayList<ShopBox>();
		
		shopBoxes.add(new ShopBoxBacon(map, playerInventory, new InventoryItem[] {new ItemRawPig(5)}, this, new ItemBacon(1)));
		shopBoxes.add(new ShopBoxBread(map, playerInventory, new InventoryItem[] {new ItemWheat(8)}, this, new ItemBread(1)));
		
		return shopBoxes;
	}

}
