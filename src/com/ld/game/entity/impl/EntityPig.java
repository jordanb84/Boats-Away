package com.ld.game.entity.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.ld.game.ai.mind.impl.PeacefulWanderingMind;
import com.ld.game.entity.Direction;
import com.ld.game.entity.EntityLiving;
import com.ld.game.graphics.map.Map;

public class EntityPig extends EntityLiving {
	
	private Sprite dialogAttack;

	private Map map;
	
	public EntityPig(Map map, Vector2 position) {
		super(map, position);
		this.getSprites().put(Direction.UP.name(), new Sprite(new Texture(Gdx.files.internal("assets/pigup.png"))));
		this.getSprites().put(Direction.DOWN.name(), new Sprite(new Texture(Gdx.files.internal("assets/pigdown.png"))));
		this.getSprites().put(Direction.LEFT.name(), new Sprite(new Texture(Gdx.files.internal("assets/pigleft.png"))));
		this.getSprites().put(Direction.RIGHT.name(), new Sprite(new Texture(Gdx.files.internal("assets/pigright.png"))));
		
		this.setCurrentSprite(Direction.DOWN.name());
		
		this.setMind(new PeacefulWanderingMind(0.5f, this, new Vector2(128, 64)));
		
		this.dialogAttack = new Sprite(new Texture(Gdx.files.internal("assets/dialog_breaktree.png")));
		
		this.map = map;
	}

	@Override
	public EntityLiving getNewInstance(Vector2 position) {
		return null;
	}
	
	@Override
	public void update(OrthographicCamera camera){
		super.update(camera);
	}
	
	@Override
	public void render(SpriteBatch batch){
		super.render(batch);
		for(EntityLiving entity : this.map.getEntities()){
			if(entity instanceof EntityPlayer){
				
			}
		}
	}

}
