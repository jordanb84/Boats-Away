package com.ld.game.entity.animation;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ld.game.entity.Direction;

public class EntityAnimation {

	private HashMap<String, Animation> animations = new HashMap<String, Animation>();
	
	private String currentAnimation;
	
	public void render(SpriteBatch batch, Vector2 position){
		this.getAnimation(this.currentAnimation).render(batch, position);
	}
	
	public void render(SpriteBatch batch, Vector2 position, float alpha){
		this.getAnimation(this.currentAnimation).render(batch, position, alpha);
	}
	
	public void update(){
		this.getAnimation(this.currentAnimation).update();
	}
	
	public Animation getAnimation(String key){
		return this.animations.get(key);
	}
	
	public Animation getAnimation(Direction direction){
		return this.animations.get(direction.name());
	}
	
	public void addAnimation(String key, Animation animation){
		this.animations.put(key, animation);
	}
	
	public void addAnimation(Direction direction, Animation animation){
		this.animations.put(direction.name(), animation);
	}
	
	public void setCurrentAnimation(String animationKey){
		this.currentAnimation = animationKey;
		assert this.animations.get(animationKey) == null;
	}
	
	public void setCurrentAnimation(Direction direction){
		this.currentAnimation = direction.name();
		assert this.animations.get(direction.name()) == null;
	}
	
	public Rectangle getBoundingRectangle(Direction direction){
		return this.getAnimation(direction).getCurrentFrame().getBoundingRectangle();
	}
}
