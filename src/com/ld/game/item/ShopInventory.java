package com.ld.game.item;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.ld.game.graphics.map.Map;

public abstract class ShopInventory extends Inventory {

	private List<ShopBox> shopBoxes;
	
	public ShopInventory(Map map, Inventory playerInventory) {
		super(0);
		Sprite boxSprite = new Sprite(new Texture(Gdx.files.internal("assets/slot.png")));
		
		//int slots = 1;
		//this.position = new Vector2(Gdx.graphics.getWidth() / 4 - boxSprite.getWidth() * slots / 2, 280);
		
		this.shopBoxes = (this.setupBoxes(map, playerInventory));
		//shopBoxes.add(new ShopBoxSoldier(map, playerInventory, new InventoryItem[] {new ItemLog(1)}, this, new ItemSoldier(1)));
		
		int boxIndex = 0;
		for(ShopBox box : shopBoxes){
			box.setPosition(new Vector2(this.position.x + boxIndex * box.getSprite().getWidth(), this.position.y));
		}
		
		//this.selectedSlot = this.slots.get(0);
	}
	
	@Override
	public void render(SpriteBatch batch){
		for(ShopBox shopBox : this.shopBoxes){
			shopBox.render(batch, false);
			//System.out.println("box " + shopBox.sprite.getWidth() + "/" + shopBox.sprite.getHeight() + "/" + shopBox.sprite.getX() + "/" + shopBox.sprite.getY());
		}
		//System.out.println("Rendered " + this.shopBoxes.size() + " boxes");
	}
	
	@Override
	public void update(OrthographicCamera camera){
		for(ShopBox shopBox : this.shopBoxes){
			shopBox.update(camera);
		}
	}
	
	public List<ShopBox> getShopBoxes(){
		return this.shopBoxes;
	}

	public abstract List<ShopBox> setupBoxes(Map map, Inventory playerInventory);
}
