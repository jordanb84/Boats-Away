package com.ld.game.ai.mind.impl;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.ld.game.Game;
import com.ld.game.ai.mind.EntityAction;
import com.ld.game.entity.EntityLiving;
import com.ld.game.entity.impl.EntitySoldier;

public class ActionWander extends EntityAction {

	private Vector2 destination;
	
	private Vector2 range;
	
	private float speed;
	
	private Vector2 originalPosition;
	
	public ActionWander(float speed, EntityLiving entity, Vector2 range) {
		super(entity);
		this.destination = new Vector2(entity.getPosition().x, entity.getPosition().y);
		
		this.range = range;
		
		this.speed = speed;
		
		this.originalPosition = new Vector2(entity.getPosition().x, entity.getPosition().y);
	}

	@Override
	public boolean update(OrthographicCamera camera) {
		if(new Rectangle(this.destination.x, this.destination.y, 5, 5).overlaps(this.getEntity().getRectangle())){
			this.newDestination();
		}
		
		boolean colliding = this.getEntity().moveTowardPosition(this.speed, this.destination);
		
		if(colliding){
			this.newDestination();
		}
		
		if(this.getEntity().tileAt(this.destination)){
			this.newDestination();
		}
		
		if(this.getEntity().getPosition().x - this.originalPosition.x >= this.range.x || this.getEntity().getPosition().y - this.originalPosition.y >= this.range.y){
			this.destination = (new Vector2(this.originalPosition.x, this.originalPosition.y));
		}
		
		return true;
	}
	
	public void newDestination(){
		if(new Random().nextBoolean()){
			this.destination = new Vector2(this.getEntity().getPosition().x + new Random().nextInt((int) this.range.x), this.getEntity().getPosition().y + new Random().nextInt((int) this.range.y));
		}else{
			this.destination = new Vector2(this.getEntity().getPosition().x - new Random().nextInt((int) this.range.x), this.getEntity().getPosition().y - new Random().nextInt((int) this.range.y));
		}
	}

	@Override
	public void draw(SpriteBatch batch) {
		/**Game.globalDebug = true;
		Game.shape.setAutoShapeType(true);
		Game.shape.begin();
		Game.shape.rect(this.destination.x, this.destination.y, 5, 5);
		Game.shape.circle(this.getEntity().getPosition().x, this.getEntity().getPosition().y, 5);
		Game.shape.end();**/
	}

}
