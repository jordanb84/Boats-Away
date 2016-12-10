package com.ld.game.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ld.game.graphics.map.Map;
import com.ld.game.tile.Tile;

public abstract class EntityLiving extends Entity {

	private float health;
	
	private Map map;
	
	public EntityLiving(Map map, Vector2 position) {
		super(position);
		this.map = map;
	}
	
	public void move(float speed, Direction direction, boolean updateAnimation, boolean checkCollision){
		Vector2 force = new Vector2();
		
		switch(direction){
		case UP:
			force.y = (speed);
			break;
		case DOWN:
			force.y = (-speed);
			break;
		case LEFT:
			force.x = (-speed);
			break;
		case RIGHT:
			force.x = (speed);
			break;
		}
		
		if(updateAnimation){
			this.setCurrentSprite(direction.name());
		}
		
		if(checkCollision){
			boolean colliding = false;
			
			for(Tile tile : this.map.getTiles()){
				if(tile.getRectangle().overlaps(new Rectangle(this.getPosition().x + force.x, this.getPosition().y + force.y, this.getCurrentSprite().getWidth(), this.getCurrentSprite().getHeight())) && tile.getTileType().solid){
					colliding = true;
				}
			}
			
			if(!colliding){
				this.getPosition().add(force);
			}
		}else{
			this.getPosition().add(force);
		}
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}
	
	public abstract EntityLiving getNewInstance(Vector2 position);

}
