package com.ld.game.entity.animation;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Animation {

	private List<Frame> frames = new ArrayList<Frame>();
	
	private float frameElapsed;
	
	private int frame;
	
	public void render(SpriteBatch batch, Vector2 position){
		this.getCurrentFrame().render(batch, position);
	}
	
	public void render(SpriteBatch batch, Vector2 position, float alpha){
		this.getCurrentFrame().render(batch, position, alpha);
	}
	
	public void update(){
		this.frameElapsed += 1 * Gdx.graphics.getDeltaTime();
		
		if(this.frameElapsed > frames.get(this.frame).getDuration()){
			this.frameElapsed = 0;
			if(this.frames.size() > this.frame + 1){
				this.frame++;
			}else{
				this.frame = 0;
			}
		}
	}
	
	public void addFrame(Frame frame){
		this.frames.add(frame);
	}
	
	public Frame getCurrentFrame(){
		return this.frames.get(this.frame);
	}
	
	public float getWidth(){
		return this.getCurrentFrame().getWidth();
	}
	
	public float getHeight(){
		return this.getCurrentFrame().getHeight();
	}
}
