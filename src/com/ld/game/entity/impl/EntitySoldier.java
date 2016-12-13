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

public class EntitySoldier extends EntityLiving {

	Vector2 mousePosition = new Vector2();
	
	private Map map;
	
	private final String name;
	
	public EntitySoldier(Map map, Vector2 position) {
		super(map, position);
		this.getSprites().put(Direction.UP.name(), new Sprite(new Texture(Gdx.files.internal("assets/soldierup.png"))));
		this.getSprites().put(Direction.DOWN.name(), new Sprite(new Texture(Gdx.files.internal("assets/soldierdown.png"))));
		this.getSprites().put(Direction.LEFT.name(), new Sprite(new Texture(Gdx.files.internal("assets/soldierright.png"))));
		this.getSprites().put(Direction.RIGHT.name(), new Sprite(new Texture(Gdx.files.internal("assets/soldierleft.png"))));
		
		this.setCurrentSprite(Direction.DOWN.name());
		
		this.setMind(new PeacefulWanderingMind(0.2f, this, new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())));
		
		this.map = map;
		
		EntityAnimation directionalAnimation = new EntityAnimation();
		
		Texture sheet = new Texture(Gdx.files.internal("assets/soldier.png"));
		float duration = 0.3f;
		
		Animation animationRight = new Animation();
		animationRight.addFrame(new Frame(sheet, 3, 0, 10, 15, duration));
		animationRight.addFrame(new Frame(sheet, 19, 0, 10, 16, duration));
		animationRight.addFrame(new Frame(sheet, 34, 0, 11, 15, duration));
		
		Animation animationLeft = new Animation();
		animationLeft.addFrame(new Frame(sheet, 0, 16, 14, 16, duration));
		animationLeft.addFrame(new Frame(sheet, 17, 16, 12, 16, duration));
		animationLeft.addFrame(new Frame(sheet, 32, 16, 13, 16, duration));
		
		Animation animationUp = new Animation();
		animationUp.addFrame(new Frame(sheet, 3, 32, 11, 16, duration));
		animationUp.addFrame(new Frame(sheet, 19, 32, 11, 16, duration));
		animationUp.addFrame(new Frame(sheet, 35, 32, 11, 16, duration));
		
		Animation animationDown = new Animation();
		animationDown.addFrame(new Frame(sheet, 1, 48, 12, 16, duration));
		animationDown.addFrame(new Frame(sheet, 17, 48, 12, 16, duration));
		animationDown.addFrame(new Frame(sheet, 33, 48, 12, 16, duration));
		
		directionalAnimation.addAnimation(Direction.RIGHT, animationRight);
		directionalAnimation.addAnimation(Direction.LEFT, animationLeft);
		directionalAnimation.addAnimation(Direction.UP, animationUp);
		directionalAnimation.addAnimation(Direction.DOWN, animationDown);
		
		this.setDirectionalAnimation(directionalAnimation);
		
		this.name = ("Mr. " + this.map.getRandomName());
	}

	@Override
	public EntityLiving getNewInstance(Vector2 position) {
		return null;
	}

	@Override
	public void render(SpriteBatch batch){
		super.render(batch);
			if(new Rectangle(this.mousePosition.x, this.mousePosition.y, 1, 1).overlaps(this.getRectangle())){
				this.getCurrentSprite().setAlpha(0.8f);
				
				int totalNeededSoldiers = (5);
				int soldiers = (0);
				
				for(EntityLiving entity : this.map.getEntities()){
					if(entity instanceof EntitySoldier){
						soldiers++;
					}
				}
				
				int neededSoldiers = (totalNeededSoldiers - soldiers);
				
				if(neededSoldiers <0){
					neededSoldiers = 0;
				}
				
				Text.Small.FONT.draw(batch, this.name, this.getPosition().x, this.getPosition().y);
				
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
