package com.ld.game.ai.mind.impl;


import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.ld.game.ai.mind.EntityAction;
import com.ld.game.entity.Direction;
import com.ld.game.entity.EntityLiving;
import com.ld.game.entity.impl.EntityNative;
import com.ld.game.entity.impl.EntitySoldier;
import com.ld.game.graphics.map.Map;

//they'll also get killed randomly
public class ActionAttackNearby extends EntityAction {

	private Class<EntityLiving> attackType;
	
	private Map map;
	
	private float elapsedSinceKilled;
	private float requiredTime;
	private boolean justKilled = false;
	
	public ActionAttackNearby(Map map, EntityLiving entity, Class<EntityLiving> attackType) {
		super(entity);
		this.attackType = attackType;
		this.map = map;
		
		this.requiredTime = (new Random().nextInt(5));
	}

	@Override
	public boolean update(OrthographicCamera camera) {
		for(EntityLiving entity : this.map.getEntities()){
			if(entity.getClass() == this.attackType){
				this.getEntity().directionChanges = 0;
				//EntityLiving collidingWith = this.collidesWithSameType();
				this.getEntity().moveTowardPosition(0.1f, entity.getPosition());
				
				if(entity.getRectangle().overlaps(this.getEntity().getRectangle()) && this.elapsedSinceKilled >= this.requiredTime || (entity.getRectangle().overlaps(this.getEntity().getRectangle()) && this.justKilled == false)){
					if(this.getEntity() instanceof EntityNative){
						if(map.soldiersKilled <3){
							//this.map.despawnEntity(entity);
							map.soldiersKilled++;
							this.justKilled = true;
							this.moveAwayFrom(this.getEntity(), entity.getPosition(), 16);
							this.moveAwayFrom(entity, this.getEntity().getPosition(), 16);
							entity.hits++;
						}
					}else{
						entity.hits++;
						this.moveAwayFrom(this.getEntity(), entity.getPosition(), 16);
						this.moveAwayFrom(entity, this.getEntity().getPosition(), 16);
						//this.map.despawnEntity(entity);
					}
					
					if(entity.hits > 4){
						this.map.despawnEntity(entity);
					}
				}
			}
		}
		
		if(justKilled){
			this.elapsedSinceKilled += (1 * Gdx.graphics.getDeltaTime());
			
			if(this.elapsedSinceKilled >= this.requiredTime){
				this.justKilled = false;
				this.requiredTime = (new Random().nextInt(5));
				this.elapsedSinceKilled = 0;
			}
		}
		
		if(this.getEntity() instanceof EntitySoldier){
			int nativesLeft = 0;
			
			for(EntityLiving entity : this.map.getEntities()){
				if(entity instanceof EntityNative){
					nativesLeft++;
				}
			}
			
			if(nativesLeft == 0){
				this.map.win();
				
				this.getEntity().move(1, Direction.values()[new Random().nextInt(Direction.values().length)], true, true);
			}
		}
		
		return true;
	}

	public EntityLiving collidesWithSameType(){
		for(EntityLiving entity : this.map.getEntities()){
			if(entity.getClass() == this.getEntity().getClass() && entity.getRectangle().overlaps(this.getEntity().getRectangle()) && entity != this.getEntity()){
				return entity;
			}
		}
		
		return null;
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}
	
	public void moveAwayFrom(EntityLiving moveEntity, Vector2 position, float force){
		if(moveEntity.getDirection() == Direction.UP){
			moveEntity.move(force, Direction.DOWN, true, true);
		}
		if(moveEntity.getDirection() == Direction.DOWN){
			moveEntity.move(force, Direction.UP, true, true);
		}
		if(moveEntity.getDirection() == Direction.LEFT){
			moveEntity.move(force, Direction.RIGHT, true, true);
		}
		if(moveEntity.getDirection() == Direction.RIGHT){
			moveEntity.move(force, Direction.LEFT, true, true);
		}
	}

}
