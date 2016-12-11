package com.ld.game.item;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Inventory {

	protected List<InventoryBox> slots = new ArrayList<InventoryBox>();
	
	protected Vector2 position;
	
	protected InventoryBox selectedSlot;
	
	public Inventory(int slotsWidth){
		Sprite boxSprite = new Sprite(new Texture("assets/slot.png"));
		
		this.position = new Vector2(Gdx.graphics.getWidth() / 4 - boxSprite.getWidth() * slotsWidth / 2, 280);
		
		for(int slotsAdded = 0; slotsAdded < slotsWidth; slotsAdded++){
			this.slots.add(new InventoryBox(this, boxSprite, null, new Vector2(this.position.x + slotsAdded * boxSprite.getWidth(), this.position.y)));
		}
		
		//this.selectedSlot = this.slots.get(0);
	}
	
	//draw "Tap a slot to select an item!"
	
	public void render(SpriteBatch batch){
		int slotIndex = 0;
		for(InventoryBox box : this.slots){
			box.render(batch, box == this.selectedSlot);
			slotIndex++;
		}
	
		try{
			Text.Default.FONT.draw(batch, "Inventory | Click an item", this.position.x + this.slots.size() * this.getSelectedItem().getIcon().getWidth() / 2 - 16, this.position.y + this.getSelectedItem().getIcon().getHeight() * 2.8f);
		}
			catch(NullPointerException e){
			
		}
		}
	
	public void update(OrthographicCamera camera){
		for(InventoryBox box : this.slots){
			box.update(camera);
		}
	}
	
	public void select(InventoryBox box){
		this.selectedSlot = box;
	}
	
	public InventoryItem getSelectedItem(){
		return this.selectedSlot.getItem();
	}
	
	public void addItem(InventoryItem item, int amount){
		boolean added = false;
		try{
		for(InventoryBox box : this.slots){
			if(!added){
				if(box.getItem().getClass() == item.getClass()){
					box.addAmount(amount);
					added = true;
				}
			}
		}
		}catch(NullPointerException e){
			
		}
		
		if(!added){
			for(InventoryBox box : this.slots){
				if(box.getItem() == null){
					box.setItem(item);
					item.addAmount(amount);
					break;
				}
			}
		}
		
	}
	
	public void removeItem(InventoryItem item, int amount){
		int itemsRemoved = 0;
		
		for(InventoryBox box : this.slots){
			if(box.getItem() != null){
				if(box.getItem().getClass() == item.getClass()){
					itemsRemoved += (box.getAmount());
					box.setAmount(box.getAmount() - amount);
					if(box.getAmount() < 1){
						box.setItem(null);
					}
				}
			}
		}
	}
	
	public boolean hasResources(List<InventoryItem> items){
		boolean hasResources = true;
		for(InventoryItem item : items){
			int itemsOfType = 0;
			for(InventoryBox box : this.slots){
				if(box.getItem() != null){
					if(box.getItem().getClass() == item.getClass()){
						itemsOfType += box.getItem().getAmount();
					}
				}
			}
			
			if(itemsOfType < item.getAmount()){
				hasResources = false;
			}
		}
		
		return hasResources;
	}
	
	public List<InventoryBox> getSlots(){
		return this.slots;
	}
	
}
