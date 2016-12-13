package com.ld.game.entity.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.ld.game.ai.mind.impl.PeacefulWanderingMind;
import com.ld.game.entity.Direction;
import com.ld.game.entity.EntityLiving;
import com.ld.game.entity.animation.Animation;
import com.ld.game.entity.animation.EntityAnimation;
import com.ld.game.entity.animation.Frame;
import com.ld.game.graphics.map.Map;
import com.ld.game.item.Text;

public class EntityNative extends EntityLiving {
	
	private Sprite dialogAttack;

	private Map map;
	
	Vector2 mousePosition = new Vector2();
	
	public EntityNative(Map map, Vector2 position) {
		super(map, position);
		
		//this.setMind(new PeacefulWanderingMind(0.5f, this, new Vector2(16, 16)));
		
		this.dialogAttack = new Sprite(new Texture(Gdx.files.internal("assets/dialog_breaktree.png")));
		
		this.map = map;
		
		EntityAnimation directionalAnimation = new EntityAnimation();
		
		Texture sheet = new Texture(Gdx.files.internal("assets/native.png"));
		float duration = 0.3f;
		
		Animation animationRight = new Animation();
		animationRight.addFrame(new Frame(sheet, 0, 0, 15, 16, duration));
		animationRight.addFrame(new Frame(sheet, 18, 0, 13, 16, duration));
		animationRight.addFrame(new Frame(sheet, 31, 0, 14, 17, duration));
		
		Animation animationLeft = new Animation();
		animationLeft.addFrame(new Frame(sheet, 1, 16, 18, 17, duration));
		animationLeft.addFrame(new Frame(sheet, 17, 16, 13, 16, duration));
		animationLeft.addFrame(new Frame(sheet, 33, 16, 13, 16, duration));
		
		Animation animationUp = new Animation();
		animationUp.addFrame(new Frame(sheet, 2, 32, 13, 16, duration));
		animationUp.addFrame(new Frame(sheet, 19, 32, 13, 16, duration));
		animationUp.addFrame(new Frame(sheet, 35, 32, 13, 16, duration));
		
		Animation animationDown = new Animation();
		animationDown.addFrame(new Frame(sheet, 3, 49, 10, 16, duration));
		animationDown.addFrame(new Frame(sheet, 19, 49, 10, 16, duration));
		animationDown.addFrame(new Frame(sheet, 35, 49, 10, 16, duration));
		
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
		Vector3 mouse = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(mouse);
		
		this.mousePosition.set(mouse.x, mouse.y);
	}
	
	@Override
	public void render(SpriteBatch batch){
		super.render(batch);
		for(EntityLiving entity : this.map.getEntities()){
			if(entity instanceof EntityPlayer){
				
			}
		}
		
		if(new Rectangle(this.mousePosition.x, this.mousePosition.y, 1, 1).overlaps(this.getRectangle())){
			this.map.drawText("Gr, I'm guarding this boat.", new Vector2(this.getPosition().x - 80, this.getPosition().y));
			this.map.drawText("Build up an army if you want", new Vector2(this.getPosition().x - 80, this.getPosition().y - 16));
			this.map.drawText("to kill us and get off the island", new Vector2(this.getPosition().x - 80, this.getPosition().y - 32));
		}
	}

}
