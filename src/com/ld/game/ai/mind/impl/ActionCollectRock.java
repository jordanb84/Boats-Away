package com.ld.game.ai.mind.impl;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.ld.game.ai.mind.EntityAction;
import com.ld.game.entity.EntityLiving;
import com.ld.game.entity.impl.EntityPlayer;
import com.ld.game.graphics.map.Map;
import com.ld.game.item.impl.ItemRock;
import com.ld.game.tile.Tile;
import com.ld.game.tile.TileType;

public class ActionCollectRock extends EntityAction {
	
	private Tile followingRock;
	
	private Map map;
	
	private int rockNumber;
	
	private float collectingTimeElapsed;
	
	public ActionCollectRock(int rockNumber, Map map, EntityLiving entity) {
		super(entity);
		this.map = map;
		this.rockNumber = rockNumber;
		this.repath();
	}

	@Override
	public boolean update(OrthographicCamera camera) {
		//this.getEntity().moveTowardPosition(1, this.followingRock.getPosition());
		this.collectingTimeElapsed += (1 * Gdx.graphics.getDeltaTime());
		
		if(this.collectingTimeElapsed >= 15){
			for(EntityLiving entity : this.map.getEntities()){
				if(entity instanceof EntityPlayer){
					((EntityPlayer) entity).getInventory().addItem(new ItemRock(1), 1);
					this.collectingTimeElapsed = 0;
					break;
				}
			}
		}
		return false;
	}
	
	public void repath(){
		List<Tile> rocks = new ArrayList<Tile>();
		for(Tile tile : this.map.getTiles()){
			if(tile.getTileType() == TileType.Rock){
				rocks.add(tile);
				}
		}
		
		Tile tile = rocks.get(this.rockNumber);
		this.followingRock = tile;
		this.getEntity().setPosition(new Vector2(this.followingRock.getPosition().x, this.followingRock.getPosition().y + this.followingRock.getCurrentSprite().getHeight() * 1.5f));
	}

	@Override
	public void draw(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}

}
