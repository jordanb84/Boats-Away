package com.ld.game.entity.building;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.ld.game.entity.EntityLiving;
import com.ld.game.graphics.map.Map;
import com.ld.game.item.Inventory;
import com.ld.game.item.InventoryItem;
import com.ld.game.item.ShopBox;
import com.ld.game.item.ShopInventory;
import com.ld.game.item.Text;

public abstract class EntityBuilding extends EntityLiving {

	private ShopInventory shopInventory;
	
	public boolean unlocked;
	
	private boolean hovered;
	
	private String buildingName;
	
	private Sprite outlinedSprite;
	
	private boolean selected;
	
	private String description;
	
	private List<InventoryItem> cost;
	
	private Inventory playerInventory;
	
	private Sprite pointerArrowSprite;
	
	private boolean movingFlashingDown = true;
	private float transparency = 1f;
	
	private Map map;
	
	public EntityBuilding(Map map, String buildingName, String description, Vector2 position, Inventory playerInventory) {
		super(map, position);
		this.setShopInventory(this.setupInventory(map, playerInventory));
		this.setBuildingName(buildingName);
		
		this.setupSprites();
		this.setupBoxes();
		
		this.setOutlinedSprite((this.setOutlinedSprite()));
		
		this.setDescription(description);
		
		this.setCost(this.setupCost());
		
		this.playerInventory = playerInventory;
		
		this.pointerArrowSprite = new Sprite(new Texture(Gdx.files.internal("assets/dialog_pointer.png")));
		
		this.map = map;
	}
	
	public abstract List<InventoryItem> setupCost(); 
	
	public abstract void setupSprites();
	
	public abstract Sprite setOutlinedSprite();
	
	public abstract void onPurchase();
	
	public void setupBoxes(){
		int shopBoxIndex = 0;
		for(ShopBox shopBox : this.getShopInventory().getShopBoxes()){
			shopBox.setPosition(new Vector2(this.getPosition().x + shopBox.getSprite().getWidth() * shopBoxIndex + this.getCurrentSprite().getWidth(), this.getPosition().y));
			
			shopBoxIndex++;
		}
	}
	
	@Override
	public void render(SpriteBatch batch){
		if(!this.unlocked){
			if(this.isHovered()){
				this.getCurrentSprite().setAlpha(0.5f);
				this.map.drawText(this.buildingName, new Vector2(this.getPosition().x + this.textOffsetX(), this.getPosition().y + this.textOffsetY() + this.getCurrentSprite().getHeight() * 1.5f));
				this.map.drawText(this.description, new Vector2(this.getPosition().x + this.textOffsetX(), this.getPosition().y + this.textOffsetY()));
				this.map.drawText("Click to purchase for: " + this.getPrice(), new Vector2(this.getPosition().x + this.textOffsetX(), this.getPosition().y - 15 + this.textOffsetY()));
				
				if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
					if(this.playerInventory.hasResources(this.getCost())){
						this.onPurchase();
						if(this.canPurchase()){
							for(InventoryItem item : this.getCost()){
								this.playerInventory.removeItem(item, item.getAmount());
							}
							
							this.unlock();
						}
					}
				}
			}else{
				this.getCurrentSprite().setAlpha(0.4f);
			}
			
			if(this.playerInventory.hasResources(this.getCost())){
				this.getCurrentSprite().setAlpha(0.6f);
				this.getCurrentSprite().setAlpha(this.transparency);
				if(this.movingFlashingDown){
					this.transparency -= (1 * Gdx.graphics.getDeltaTime() / 3);
					if(this.transparency <= 0.2f){
						this.movingFlashingDown = false;
					}
				}else{
					this.transparency += (1 * Gdx.graphics.getDeltaTime() / 3);
					if(this.transparency >= 0.95f){
						this.movingFlashingDown = true;
					}
				}
				//this.pointerArrowSprite.setAlpha(this.transparency);
				//this.pointerArrowSprite.setPosition(this.getPosition().x + this.getCurrentSprite().getWidth() / 2 - this.pointerArrowSprite.getWidth() / 2, this.getPosition().y + this.getCurrentSprite().getHeight() * 1.3f);
				//this.pointerArrowSprite.draw(batch);
			}
		}else{
			if(this.isSelected()){
				this.getShopInventory().render(batch);
			}
			
			if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
				this.setSelected(this.isHovered());
			}
		}
		
		
		if(this.isSelected()){
			this.getOutlinedSprite().setPosition(this.getPosition().x, this.getPosition().y);
			this.getOutlinedSprite().draw(batch);
		}else{
			this.getSprites().get("default").setPosition(this.getPosition().x, this.getPosition().y);
			this.getSprites().get("default").draw(batch);
		}
		
		//System.out.println("Selected: " + this.isSelected());
	}

	public boolean canPurchase(){
		return true;
	}
	
	@Override
	public void update(OrthographicCamera camera){
		Vector3 mouse = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(mouse);
		
		if(new Rectangle(mouse.x, mouse.y, 1, 1).overlaps(this.getRectangle())){
			this.setHovered(true);
		}else{
			this.setHovered(false);
		}
		
		if(this.unlocked){
			this.getShopInventory().update(camera);
		}
	}
	
	@Override
	public EntityLiving getNewInstance(Vector2 position) {
		return null;
	}

	public abstract ShopInventory setupInventory(Map map, Inventory playerInventory);
	
	public ShopInventory getShopInventory() {
		return shopInventory;
	}

	public void setShopInventory(ShopInventory shopInventory) {
		this.shopInventory = shopInventory;
	}
	
	public void unlock(){
		this.unlocked = true;
		this.getCurrentSprite().setAlpha(1f);
	}
	
	public boolean isUnlocked(){
		return this.unlocked;
	}

	public boolean isHovered() {
		return hovered;
	}

	public void setHovered(boolean hovered) {
		this.hovered = hovered;
	}
	
	public String getPrice(){
		return this.getCostText();
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public Sprite getOutlinedSprite() {
		return outlinedSprite;
	}

	public void setOutlinedSprite(Sprite outlinedSprite) {
		this.outlinedSprite = outlinedSprite;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<InventoryItem> getCost() {
		return cost;
	}

	public void setCost(List<InventoryItem> cost) {
		this.cost = cost;
	}
	
	public String getCostText(){
		String costText = ("");
		
		for(InventoryItem item : this.cost){
			costText += (item.getAmount() + " " + item.getName() + "s ");
		}
		
		return costText;
	}
	
	public int textOffsetX(){
		return 0;
	}
	
	public int textOffsetY(){
		return 0;
	}

}
