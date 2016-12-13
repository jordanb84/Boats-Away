package com.ld.game.item;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ToolShackInventory extends Inventory {

	public ToolShackInventory(Vector2 position) {
		super(15);
		this.position = position;
	}
	
	@Override
	public void render(SpriteBatch batch){
		//you can add normal items, but they render differently here
		int boxIndex = 0;
		for(InventoryBox box : this.slots){
			if(box.getItem() != null){
				box.getItem().getIcon().setPosition(this.position.x, this.position.y + boxIndex * box.getItem().getIcon().getHeight() * 2f);
				box.getItem().getIcon().draw(batch);
				Text.Small.FONT.draw(batch, box.getItem().getName() + " x" + box.getItem().getAmount(), box.getItem().getIcon().getX(), box.getItem().getIcon().getY());
				boxIndex++;
				
				//float pos = this.position.y + boxIndex * box.getItem().getIcon().getHeight();
				//System.out.println("Drawing #" + boxIndex + " at " + pos + " with height " + box.getItem().getIcon().getHeight());
			}
		}
	}
	
	@Override
	public void update(OrthographicCamera camera){
		
	}

}
