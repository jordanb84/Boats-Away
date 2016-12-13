package com.ld.game.ai.mind.impl;

import com.ld.game.ai.mind.EntityAction;
import com.ld.game.ai.mind.EntityMind;
import com.ld.game.entity.EntityLiving;
import com.ld.game.graphics.map.Map;

public class AttackNearbyMind extends EntityMind {

	private ActionAttackNearby attackNearby;
	
	public AttackNearbyMind(Map map, EntityLiving entity, Class attackType) {
		super(entity);
		this.attackNearby = new ActionAttackNearby(map, entity, attackType);
		this.setCurrentAction(this.attackNearby);
	}

	@Override
	public EntityAction reevaluate() {
		return this.attackNearby;
	}

}
