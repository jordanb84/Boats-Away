package com.ld.game.ai.mind;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ld.game.entity.EntityLiving;

public abstract class EntityMind {

	private EntityLiving entity;
	
	private EntityAction currentAction;
	
	public EntityMind(EntityLiving entity){
		this.setEntity(entity);
	}
	
	public void update(OrthographicCamera camera){
		boolean shouldReevaluate = (this.getCurrentAction().update(camera));
		
		if(shouldReevaluate){
			this.setCurrentAction(this.reevaluate());
		}
	}
	
	public void draw(SpriteBatch batch){
		this.getCurrentAction().draw(batch);
	}
	
	public abstract EntityAction reevaluate();

	public EntityLiving getEntity() {
		return entity;
	}

	public void setEntity(EntityLiving entity) {
		this.entity = entity;
	}

	public EntityAction getCurrentAction() {
		return currentAction;
	}

	public void setCurrentAction(EntityAction currentAction) {
		this.currentAction = currentAction;
	}
	
	/**
	 * Mind classes hold action instances.
	 * 
	 * Each frame, we call action.update(). If it returns true, we reevaluate the current action.
	 * Simple as that.
	 */
	
}
