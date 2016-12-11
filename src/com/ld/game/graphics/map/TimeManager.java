package com.ld.game.graphics.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TimeManager {

	private float time;
	
	private Sprite nightSprite;
	
	private float nightTimeElapsed;
	
	private boolean goingDown;
	
	private final float maxDarkness;
	private final float minimumBrightness;
	
	public TimeManager(float maxDarkness, float minimumBrightness){
		this.nightSprite = new Sprite(new Texture(Gdx.files.internal("assets/night.png")));
		this.maxDarkness = maxDarkness;
		this.minimumBrightness = minimumBrightness;
		this.time = minimumBrightness;
	}
	
	public void render(SpriteBatch batch){
		this.nightSprite.setAlpha(this.time);
		this.nightSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.nightSprite.draw(batch);
	}
	
	public void update(){
		if(this.goingDown){
			this.time -= (1 * Gdx.graphics.getDeltaTime() / 30);
			
			if(this.time <= this.minimumBrightness){
				this.goingDown = false;
			}
		}else{
			this.time += (1 * Gdx.graphics.getDeltaTime() / 60);
			
			if(this.time >= this.maxDarkness){
				this.goingDown = true;
			}
		}
	}
	
}
