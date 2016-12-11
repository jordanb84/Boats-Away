package com.ld.game.entity;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ld.game.ai.mind.EntityMind;
import com.ld.game.entity.building.EntityBuilding;
import com.ld.game.graphics.map.Map;
import com.ld.game.tile.Tile;

public abstract class EntityLiving extends Entity {

	private float health;
	
	private Map map;
	
	private EntityMind mind;
	
	public EntityLiving(Map map, Vector2 position) {
		super(position);
		this.map = map;
	}
	
	@Override
	public void render(SpriteBatch batch){
		super.render(batch);
		if(this.getMind() != null){
			this.getMind().draw(batch);
		}
	}
	
	@Override
	public void update(OrthographicCamera camera){
		if(this.getMind() != null){
			this.mind.update(camera);
		}
	}
	
	public boolean moveTowardPosition(float speed, Vector2 position){
		//float forceX = 0;
		//float forceY = 0;

		Vector2 force = new Vector2();
		
		if(this.getPosition().x > position.x){
			//forceX = -speed;
			force.set(-speed, force.y);
			//System.out.println("Setting x to " + forceX);
		}
		if(this.getPosition().x < position.y){
			//forceX = speed;
			//System.out.println("Setting x to " + forceX);
			force.set(speed, force.y);
		}
		
		if(this.getPosition().y > position.y){
			//forceY = -speed;
			//System.out.println("Setting y to " + forceY);
			force.set(force.x, -speed);
		}
		if(this.getPosition().y < position.y){
			//forceY = speed;
			//System.out.println("Setting y to " + forceY);
			force.set(force.x, speed);
		}
		
		//System.out.println("Force: " + forceX + "/" + forceY + " position: " + this.getPosition().x + "/" + this.getPosition().y + " destination: " + position.x + "/" + position.y);
		
		Direction direction = (this.directionForForce(force));
		//System.out.println("X: " + this.getPosition().x + " Y: " + this.getPosition().y);
		
		if(direction != null){
			this.setCurrentSprite(this.directionForForce(force).name());
		}
		
		if(force.x == 0 || force.y == 0){
			return true;
		}
		
		if(!this.tileAt(new Vector2(this.getPosition().x + force.x, this.getPosition().y + force.y))){
			this.getPosition().add(force);
			return false;
		}else{
			return true;
		}
	}
	
	public Direction directionForForce(Vector2 force){
		if(force.x < 0){
			return Direction.LEFT;
		}
		if(force.x > 0){
			return Direction.RIGHT;
		}
		if(force.y < 0){
			return Direction.DOWN;
		}
		if(force.y > 0){
			return Direction.UP;
		}
		
		return null;
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
			/**boolean colliding = false;
			
			for(Tile tile : this.map.getTiles()){
				if(tile.getRectangle().overlaps(new Rectangle(this.getPosition().x + force.x, this.getPosition().y + force.y, this.getCurrentSprite().getWidth(), this.getCurrentSprite().getHeight())) && tile.getTileType().solid){
					colliding = true;
				}
			}**/
			
			if(!this.tileAt(new Vector2(this.getPosition().x + force.x, this.getPosition().y + force.y))){
				this.getPosition().add(force);
			}
		}else{
			this.getPosition().add(force);
		}
	}
	
	public boolean tileAt(Vector2 position){
		for(Tile tile : this.map.getTiles()){
			if(tile.getRectangle().overlaps(new Rectangle(position.x, position.y, this.getCurrentSprite().getWidth(), this.getCurrentSprite().getHeight())) && tile.getTileType().solid){
				return true;
			}
		}
		
		for(EntityLiving entity : this.map.getEntities()){
			if(entity instanceof EntityBuilding){
				if(entity.getRectangle().overlaps(new Rectangle(position.x, position.y, this.getCurrentSprite().getWidth(), this.getCurrentSprite().getHeight()))){
					return true;
				}
			}
		}
		return false;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}
	
	public abstract EntityLiving getNewInstance(Vector2 position);

	public EntityMind getMind() {
		return mind;
	}

	public void setMind(EntityMind mind) {
		this.mind = mind;
	}

}
