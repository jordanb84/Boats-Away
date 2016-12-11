package com.ld.game.entity.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Frame {

	private float duration;
	
	private TextureRegion texture;
	
	private Sprite sprite;
	
	public Frame(TextureRegion texture, float duration){
		this.texture = texture;
		this.setDuration(duration);
		this.sprite = new Sprite(texture);
	}
	
	public Frame(Texture sheet, int x, int y, int width, int height, float duration){
		this.texture = new TextureRegion(sheet, x, y, width, height);
		this.setDuration(duration);
		this.sprite = new Sprite(this.texture);
	}
	
	public void render(SpriteBatch batch, Vector2 position){
		batch.draw(this.texture, position.x, position.y);
	}

	public void render(SpriteBatch batch, Vector2 position, float alpha){
		this.sprite.setPosition(position.x, position.y);
		this.sprite.setAlpha(alpha);
		this.sprite.draw(batch);
	}
	
	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}
	
	public float getWidth(){
		//System.out.println("Width: " + this.texture.getRegionWidth());
		return this.texture.getRegionWidth();
	}
	
	public float getHeight(){
		//System.out.println("Height: " + this.texture.getRegionHeight());
		return this.texture.getRegionHeight();
	}
	
	public Rectangle getBoundingRectangle(){
		return this.sprite.getBoundingRectangle();
	}
	
	
	
}
