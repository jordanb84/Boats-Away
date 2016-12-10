package com.ld.game.entity;

import java.util.HashMap;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

	private HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();

	private Vector2 position;
	
	private String currentSprite;
	
	public Entity(Vector2 position){
		this.setPosition(position);
	}

	public void render(SpriteBatch batch){
		this.getCurrentSprite().setPosition(this.getPosition().x, this.getPosition().y);
		this.getCurrentSprite().draw(batch);
	}
	
	public abstract void update(OrthographicCamera camera);
	
	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public HashMap<String, Sprite> getSprites() {
		return sprites;
	}

	public void setSprites(HashMap<String, Sprite> sprites) {
		this.sprites = sprites;
	}
	
	public Sprite getCurrentSprite(){
		return this.sprites.get(this.currentSprite);
	}
	
	public void setCurrentSprite(String currentSprite){
		this.currentSprite = (currentSprite);
	}
	
	public Rectangle getRectangle(){
		return new Rectangle(this.getPosition().x, this.getPosition().y, this.getCurrentSprite().getWidth(), this.getCurrentSprite().getHeight());
		//or just this.getCurrentSprite().bounds
	}
}
