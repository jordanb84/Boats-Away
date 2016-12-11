package com.ld.game.ai.mind.impl;

import com.badlogic.gdx.math.Vector2;
import com.ld.game.ai.mind.EntityAction;
import com.ld.game.ai.mind.EntityMind;
import com.ld.game.entity.EntityLiving;

public class PeacefulWanderingMind extends EntityMind {

	private ActionWander wanderingAction;
	
	public PeacefulWanderingMind(float speed, EntityLiving entity, Vector2 range) {
		super(entity);
		this.wanderingAction = new ActionWander(speed, entity, range);
		this.setCurrentAction(this.wanderingAction);
	}

	@Override
	public EntityAction reevaluate() {
		return this.wanderingAction;
	}

}
