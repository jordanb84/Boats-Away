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
import com.ld.game.entity.animation.Animation;
import com.ld.game.entity.animation.EntityAnimation;
import com.ld.game.entity.animation.Frame;
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
		
		EntityAnimation directionalAnimation = new EntityAnimation();
		
		Texture sheet = new Texture(Gdx.files.internal("assets/pig.png"));
		float duration = 0.3f;
		
		Animation animationRight = new Animation();
		animationRight.addFrame(new Frame(sheet, 1, 3, 13, 13, duration));
		animationRight.addFrame(new Frame(sheet, 17, 3, 13, 13, duration));
		animationRight.addFrame(new Frame(sheet, 34, 3, 13, 13, duration));
		
		Animation animationLeft = new Animation();
		animationLeft.addFrame(new Frame(sheet, 1, 19, 13, 13, duration));
		animationLeft.addFrame(new Frame(sheet, 18, 19, 13, 13, duration));
		animationLeft.addFrame(new Frame(sheet, 34, 19, 13, 13, duration));
		
		Animation animationUp = new Animation();
		animationUp.addFrame(new Frame(sheet, 4, 35, 8, 13, duration));
		animationUp.addFrame(new Frame(sheet, 20, 35, 8, 13, duration));
		animationUp.addFrame(new Frame(sheet, 36, 35, 8, 13, duration));
		
		Animation animationDown = new Animation();
		animationDown.addFrame(new Frame(sheet, 4, 49, 8, 13, duration));
		animationDown.addFrame(new Frame(sheet, 20, 49, 8, 13, duration));
		animationDown.addFrame(new Frame(sheet, 36, 49, 9, 13, duration));
		
		directionalAnimation.addAnimation(Direction.RIGHT, animationRight);
		directionalAnimation.addAnimation(Direction.LEFT, animationLeft);
		directionalAnimation.addAnimation(Direction.UP, animationUp);
		directionalAnimation.addAnimation(Direction.DOWN, animationDown);
		
		this.setDirectionalAnimation(directionalAnimation);
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
