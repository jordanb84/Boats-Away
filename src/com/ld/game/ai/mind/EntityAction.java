package com.ld.game.ai.mind;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ld.game.entity.EntityLiving;

public abstract class EntityAction {

	private EntityLiving entity;
	
	public EntityAction(EntityLiving entity){
		this.setEntity(entity);
	}
	
	public abstract boolean update(OrthographicCamera camera);

	public abstract void draw(SpriteBatch batch);
	
	public EntityLiving getEntity() {
		return entity;
	}

	public void setEntity(EntityLiving entity) {
		this.entity = entity;
	}
	
}
