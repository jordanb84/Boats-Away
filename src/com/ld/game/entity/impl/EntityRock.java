package com.ld.game.entity.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.ld.game.entity.EntityLiving;
import com.ld.game.graphics.map.Map;

//When you break a tree, a rock is put in it's place! There's also rocks down by the river side
public class EntityRock extends EntityLiving {

	public EntityRock(Map map, Vector2 position) {
		super(map, position);
		this.getSprites().put("default", new Sprite(new Texture(Gdx.files.internal("assets/rock.png"))));
		this.setCurrentSprite("default");
	}

	@Override
	public EntityLiving getNewInstance(Vector2 position) {
		// TODO Auto-generated method stub
		return null;
	}

}
