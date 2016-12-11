package com.ld.game.entity.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.ld.game.entity.EntityLiving;
import com.ld.game.graphics.map.Map;

public class EntityBoat extends EntityLiving {

	public EntityBoat(Map map, Vector2 position) {
		super(map, position);
		this.getSprites().put("default", new Sprite(new Texture(Gdx.files.internal("assets/boat.png"))));
		this.setCurrentSprite("default");
	}

	@Override
	public EntityLiving getNewInstance(Vector2 position) {
		return null;
	}

}
