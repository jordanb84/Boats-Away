package com.ld.game.ai.mind.impl;

import com.badlogic.gdx.math.Vector2;
import com.ld.game.ai.mind.EntityAction;
import com.ld.game.ai.mind.EntityMind;
import com.ld.game.entity.EntityLiving;
import com.ld.game.graphics.map.Map;

public class FollowRockMind extends EntityMind {

	private ActionCollectRock collectRockAction;
	
	public FollowRockMind(int rockNumber, Map map, EntityLiving entity) {
		super(entity);
		this.collectRockAction = new ActionCollectRock(rockNumber, map, entity);
		this.setCurrentAction(this.collectRockAction);
	}

	@Override
	public EntityAction reevaluate() {
		return this.collectRockAction;
	}

}
