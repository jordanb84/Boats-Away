package com.ld.game.entity.building.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.ld.game.ai.mind.impl.AttackNearbyMind;
import com.ld.game.entity.EntityLiving;
import com.ld.game.entity.building.EntityBuilding;
import com.ld.game.entity.impl.EntityNative;
import com.ld.game.entity.impl.EntitySoldier;
import com.ld.game.graphics.map.Map;
import com.ld.game.item.Inventory;
import com.ld.game.item.InventoryItem;
import com.ld.game.item.ShopInventory;
import com.ld.game.item.impl.ItemLog;
import com.ld.game.item.impl.ItemRock;
import com.ld.game.item.impl.ShopInventoryBridge;
import com.ld.game.sound.SoundPlayer;

public class BuildingBridge extends EntityBuilding {

	private Map map;
	
	private boolean hovered;
	
	public BuildingBridge(Map map, Vector2 position, Inventory playerInventory) {
		super(map, "Bridge", "Used to cross the river to get to the boat", position, playerInventory);
		this.map = map;
	}

	@Override
	public List<InventoryItem> setupCost() {
		List<InventoryItem> cost = new ArrayList<InventoryItem>();
		cost.add(new ItemLog(50));
		cost.add(new ItemRock(15));
		//cost.add(new ItemLog(1));
		
		return cost;
	}

	@Override
	public void render(SpriteBatch batch){
		super.render(batch);
		if(!this.canPurchase() && this.hovered){
			this.map.drawText("You need " + this.calculateSoldiers() + " more soldiers to build this", new Vector2(this.getPosition().x + this.textOffsetX(), this.getPosition().y - 32));
		}else{
			if(this.hovered){
				this.map.drawText("You have enough soldiers", new Vector2(this.getPosition().x, this.getPosition().y - 32));
			}
		}
	}
	
	@Override
	public void update(OrthographicCamera camera){
		super.update(camera);
		
		Vector3 mouse = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(mouse);
		
		this.hovered = (new Rectangle(mouse.x, mouse.y, 1, 1).overlaps(this.getRectangle()));
	}
	
	@Override
	public void setupSprites() {
		this.getSprites().put("default", new Sprite(new Texture(Gdx.files.internal("assets/bridge.png"))));
		this.setCurrentSprite("default");
	}

	@Override
	public Sprite setOutlinedSprite() {
		return new Sprite(new Texture(Gdx.files.internal("assets/bridgeoutlined.png")));
	}

	@Override
	public ShopInventory setupInventory(Map map, Inventory playerInventory) {
		return new ShopInventoryBridge(map, playerInventory);
	}

	@Override
	public void onPurchase() {
		for(EntityLiving entity : this.map.getEntities()){
			if(entity instanceof EntitySoldier){
				Random random = new Random();
				
				float randomX = 0;
				float randomY = 0;
				
				if(random.nextBoolean()){
					randomX = (random.nextInt(16));
					randomY = (random.nextInt(16));
				}else{
					randomX = (-random.nextInt(16));
					randomY = (-random.nextInt(16));
				}
				
				entity.setPosition(new Vector2(this.getPosition().x + 48 + randomX, this.getPosition().y + randomY - 50));
				
				entity.setMind(new AttackNearbyMind(this.map, entity, EntityNative.class));
			}
			
			if(entity instanceof EntityNative){
				entity.setMind(new AttackNearbyMind(this.map, entity, EntitySoldier.class));
			}
		}
		
		SoundPlayer.play(SoundPlayer.Intense.MUSIC);
	}
	
	@Override
	public boolean canPurchase(){
		
		return this.calculateSoldiers() <= 0;
	}
	
	public int calculateSoldiers(){
		int totalNeededSoldiers = (5);
		int soldiers = (0);
		
		for(EntityLiving entity : this.map.getEntities()){
			if(entity instanceof EntitySoldier){
				soldiers++;
			}
		}
		
		int neededSoldiers = (totalNeededSoldiers - soldiers);
		
		if(neededSoldiers <0){
			neededSoldiers = 0;
		}
		
		return neededSoldiers;
	}

	public int textOffsetX(){
		return -48;
	}
	
	public int textOffsetY(){
		return 0;
	}
	
}
