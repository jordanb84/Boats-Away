package com.ld.game.entity.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.ld.game.ai.mind.impl.FollowRockMind;
import com.ld.game.entity.Direction;
import com.ld.game.entity.EntityLiving;
import com.ld.game.entity.animation.Animation;
import com.ld.game.entity.animation.EntityAnimation;
import com.ld.game.entity.animation.Frame;
import com.ld.game.graphics.map.Map;
import com.ld.game.item.Text;

public class EntitySlave extends EntityLiving {

	private Vector2 mousePosition = new Vector2();
	
	private Map map;
	
	private final String name;
	
	public EntitySlave(int slaveNumber, Map map, Vector2 position) {
		super(map, position);
		Texture sheet = new Texture(Gdx.files.internal("assets/slave.png"));
		float duration = 0.3f;
		
		EntityAnimation directionalAnimation = new EntityAnimation();
		
		Animation animationRight = new Animation();
		animationRight.addFrame(new Frame(sheet, 3, 0, 10, 15, duration));
		animationRight.addFrame(new Frame(sheet, 19, 0, 10, 16, duration));
		animationRight.addFrame(new Frame(sheet, 34, 0, 11, 16, duration));
		
		Animation animationLeft = new Animation();
		animationLeft.addFrame(new Frame(sheet, 3, 16, 11, 16, duration));
		animationLeft.addFrame(new Frame(sheet, 19, 16, 10, 16, duration));
		animationLeft.addFrame(new Frame(sheet, 35, 16, 10, 16, duration));
		
		Animation animationUp = new Animation();
		animationUp.addFrame(new Frame(sheet, 3, 32, 10, 16, duration));
		animationUp.addFrame(new Frame(sheet, 19, 32, 10, 16, duration));
		animationUp.addFrame(new Frame(sheet, 35, 32, 10, 16, duration));
		
		Animation animationDown = new Animation();
		animationDown.addFrame(new Frame(sheet, 3, 48, 10, 16, duration));
		animationDown.addFrame(new Frame(sheet, 19, 48, 10, 16, duration));
		animationDown.addFrame(new Frame(sheet, 35, 48, 10, 17, duration));
		
		directionalAnimation.addAnimation(Direction.RIGHT, animationRight);
		directionalAnimation.addAnimation(Direction.LEFT, animationLeft);
		directionalAnimation.addAnimation(Direction.UP, animationUp);
		directionalAnimation.addAnimation(Direction.DOWN, animationDown);
		
		this.setDirectionalAnimation(directionalAnimation);
		
		this.setMind(new FollowRockMind(slaveNumber, map, this));
		
		this.map = map;
		
		this.name = ("Ieyfo " + this.map.getRandomName());
	}
	
	@Override
	public EntityLiving getNewInstance(Vector2 position) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void render(SpriteBatch batch){
		super.render(batch);
			if(new Rectangle(this.mousePosition.x, this.mousePosition.y, 1, 1).overlaps(this.getRectangle())){
				Text.Small.FONT.draw(batch, "I'm a slave named " + this.name + ". " + "Mining rocks...", this.getPosition().x, this.getPosition().y);
			}
	}
	
	@Override
	public void update(OrthographicCamera camera) {
		super.update(camera);
		Vector3 mouse = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(mouse);
		
		this.mousePosition.set(mouse.x, mouse.y);
	}
}
