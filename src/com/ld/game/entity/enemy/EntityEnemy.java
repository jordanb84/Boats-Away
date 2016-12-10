package com.ld.game.entity.enemy;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.ld.game.entity.EntityLiving;
import com.ld.game.graphics.map.Map;

public abstract class EntityEnemy extends EntityLiving {

	public EntityEnemy(Map map, Vector2 position) {
		super(map, position);
	}

	@Override
	public void update(OrthographicCamera camera) {
		// TODO Auto-generated method stub
		
	}

}
