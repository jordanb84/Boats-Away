package com.ld.game.item;

import java.util.Arrays;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.ld.game.graphics.map.Map;

public abstract class ShopBox extends InventoryBox {

	private List<InventoryItem> cost;
	
	private InventoryItem returnItem;
	
	protected Inventory playerInventory;
	
	private Map map;
	
	private Sprite deniedSprite;
	
	private Sprite boxSprite;
	
	public ShopBox(Map map, Inventory playerInventory, InventoryItem[] cost, Inventory inventory, InventoryItem returnItem) {
		super(inventory, new Sprite(new Texture(Gdx.files.internal("assets/slot.png"))), null, new Vector2());
		this.setCost(Arrays.asList(cost));
		this.setReturnItem(returnItem);
		
		this.playerInventory = playerInventory;
		
		this.setMap(map);
		
		this.deniedSprite = new Sprite(new Texture(Gdx.files.internal("assets/slotdenied.png")));
		
		this.getSprite().setSize(this.getSprite().getWidth() / 2, this.getSprite().getHeight() / 2);
		this.deniedSprite.setSize(this.deniedSprite.getWidth() / 2, this.deniedSprite.getHeight() / 2);
		
		this.getReturnItem().getIcon().setSize(this.getReturnItem().getIcon().getWidth() / 2, this.getReturnItem().getIcon().getHeight() / 2);
		
		this.boxSprite = new Sprite(new Texture(Gdx.files.internal("assets/slot.png")));
	}
	
	@Override
	public void render(SpriteBatch batch, boolean selected){
		super.render(batch, selected);
		
		if(this.isHovered()){
			String cost = ("");
			for(InventoryItem itemPrice : this.cost){
				cost += (itemPrice.getName() + " (" + itemPrice.getAmount() + ") ");
			}
			Text.Small.FONT.draw(batch, "Cost: " + cost, this.getSprite().getX(), this.getSprite().getY());
		}
		
		this.returnItem.getIcon().setPosition(this.getPosition().x + this.returnItem.getIcon().getWidth() / 2, this.getPosition().y + this.returnItem.getIcon().getHeight() / 2);
		this.returnItem.getIcon().draw(batch);
		
		if(this.playerInventory.hasResources(this.cost)){
			this.setSprite(this.boxSprite);
		}else{
			this.setSprite(this.deniedSprite);
		}
		
		this.getSprite().setSize(16, 16);
		//System.out.println("Rendered box at xy " + this.getPosition().x + "/" + this.getPosition().y);
	}

	public List<InventoryItem> getCost() {
		return cost;
	}

	public void setCost(List<InventoryItem> cost) {
		this.cost = cost;
	}

	public InventoryItem getReturnItem() {
		return returnItem;
	}

	public void setReturnItem(InventoryItem returnItem) {
		this.returnItem = returnItem;
	}

	@Override
	public void onUse(){
		super.onUse();
		
		/**boolean playerHasSufficientFunds = true;
		
		for(InventoryItem item : this.cost){
			int itemsOfTypePlayerHas = 0;
			
			for(InventoryBox itemBox : this.playerInventory.slots){
				if(itemBox.getItem() != null){
					if(itemBox.getItem().getName().equals(item.getName())){
						itemsOfTypePlayerHas += (itemBox.getItem().getAmount());
					}
				}
			}
			
			if(!(itemsOfTypePlayerHas >= item.getAmount())){
				playerHasSufficientFunds = false;
			}
		}**/
		
		if(this.playerInventory.hasResources(this.cost)){
			for(InventoryItem item : this.cost){
				this.playerInventory.removeItem(item, item.getAmount());
			}
			
			this.use();
		}
	}
	
	public abstract void use();

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}
}
