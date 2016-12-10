package com.ld.game.item;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class InventoryBox {

	private InventoryItem item;
	
	protected Sprite sprite;
	
	private Rectangle mouseRectangle;
	
	private Rectangle rectangle;
	
	private Vector2 position;
	
	private boolean hovered;
	
	private Inventory inventory;
	
	private boolean unclickable;
	
	public InventoryBox(Inventory inventory, Sprite sprite, InventoryItem item, Vector2 position){
		this.setItem(item);
		this.sprite = sprite;
		this.position = position;
		this.inventory = inventory;
	}
	
	public void render(SpriteBatch batch, boolean selected){
		this.sprite.setPosition(position.x, position.y);
		if(!this.hovered){
			this.sprite.setAlpha(.6f);
		}else{
			this.sprite.setAlpha(.7f);
		}
		if(selected){
			this.sprite.setAlpha(.9f);
		}
		this.sprite.draw(batch);
		
		if(this.getItem() != null){
			this.getItem().getIcon().setSize(24/2, 24/2);
			this.getItem().getIcon().setPosition(this.position.x + this.getItem().getIcon().getWidth() / 2, this.position.y + this.getItem().getIcon().getHeight() / 2);
			this.getItem().getIcon().draw(batch);
		}
		
		if(this.getItem() != null){
			//Text.Default.FONT.draw(batch, "" + this.getItem().getAmount(), this.position.x + this.getSprite().getWidth() / 8, this.position.y + this.getItem().getIcon().getHeight() * 1.5f);
		}
		
		if(this.isHovered() && this.getItem() != null){
			Text.Default.FONT.draw(batch, "" + this.getItem().getDescription(), this.position.x + this.getSprite().getWidth() / 2, this.position.y);
		}
	}
	
	public void update(OrthographicCamera camera){
		Vector3 mousePosition = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(mousePosition);
		
		if(this.mouseRectangle == null){
			this.mouseRectangle = new Rectangle(mousePosition.x, mousePosition.y, 0, 0);
		}
		
		if(this.rectangle == null){
			this.rectangle = new Rectangle(this.position.x, this.position.y, this.getSprite().getWidth(), this.getSprite().getHeight());
		}
		
		this.mouseRectangle.set(mousePosition.x, mousePosition.y, 0, 0);
		this.rectangle.set(this.position.x, this.position.y, this.getSprite().getWidth(), this.getSprite().getHeight());
		
		if(mouseRectangle.overlaps(rectangle)){
			this.setHovered(true);
			
			if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
				if(!this.unclickable){
					this.onUse();
					this.unclickable = true;
				}
			}else{
				this.unclickable = false;
			}
		}else{
			this.setHovered(false);
		}
		
		//System.out.println(hovered);
	}

	public InventoryItem getItem() {
		return item;
	}

	public void setItem(InventoryItem item) {
		this.item = item;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public boolean isHovered() {
		return hovered;
	}

	public void setHovered(boolean selected) {
		this.hovered = selected;
	}
	
	public void addAmount(int amount){
		this.getItem().addAmount(amount);
	}
	
	public Vector2 getPosition(){
		return this.position;
	}
	
	public void setPosition(Vector2 position){
		this.position = position;
	}
	
	public void onUse(){
		this.inventory.select(this);
	}
	
	public void setAmount(int amount){
		this.getItem().setAmount(amount);
	}
	
	public int getAmount(){
		return this.getItem().getAmount();
	}
}
