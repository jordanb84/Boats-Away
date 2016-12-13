package com.ld.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ld.game.ai.mind.EntityMind;
import com.ld.game.entity.animation.EntityAnimation;
import com.ld.game.entity.building.EntityBuilding;
import com.ld.game.entity.impl.EntityRock;
import com.ld.game.graphics.map.Map;
import com.ld.game.tile.Tile;

public abstract class EntityLiving extends Entity {

	private float health;
	
	private Map map;
	
	private EntityMind mind;
	
	private EntityAnimation directionalAnimation;
	
	private Direction direction;
	
	public int hits;
	
	public int directionChanges;
	private float directionChangesElapsed;
	
	public EntityLiving(Map map, Vector2 position) {
		super(position);
		this.map = map;
		this.setDirection(Direction.DOWN);
	}
	
	@Override
	public void render(SpriteBatch batch){
		if(this.getDirectionalAnimation() == null){
			this.getCurrentSprite().setPosition(this.getPosition().x, this.getPosition().y);
			this.getCurrentSprite().draw(batch);
		}else{
			//this.getDirectionalAnimation().getAnimation(this.getDirection()).update();
			this.getDirectionalAnimation().getAnimation(this.getDirection()).render(batch, this.getPosition());;
			//this.getDirectionalAnimation().render(batch, this.getPosition());
		}
		
		if(this.getMind() != null){
			this.getMind().draw(batch);
		}
	}
	
	@Override
	public void update(OrthographicCamera camera){
		if(this.getMind() != null){
			this.mind.update(camera);
		}
		
		this.directionChangesElapsed += (1 * Gdx.graphics.getDeltaTime());
		if(this.directionChangesElapsed >= 5){
			this.directionChangesElapsed = 0;
			this.directionChanges = 0;
		}
	}
	
	public void moveTowardPosition(float speed, Vector2 position, boolean noCollision){
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
		
		this.getPosition().add(force);
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
		
		System.out.println("Direction changes: " + this.directionChanges);
		if(this.directionChanges <= 0){
		if(direction != null){
			this.setCurrentSprite(this.directionForForce(force).name());
			if(!direction.name().equals(this.direction.name())){
				this.directionChanges++;
			}
			this.setDirection(direction);
			
			if(this.getDirectionalAnimation() != null){
				this.getDirectionalAnimation().getAnimation(this.getDirection()).update();
			}
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
		
		this.setDirection(direction);
		
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
			if(this.getDirectionalAnimation() != null){
				this.getDirectionalAnimation().getAnimation(this.getDirection()).update();
			}
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
			}else{
				//this.getPosition().add(-force.x, force.y);
			}
		}else{
			this.getPosition().add(force);
			
			if(this.getDirectionalAnimation() != null){
				this.getDirectionalAnimation().getAnimation(this.getDirection()).update();
				System.out.println("Updated animation");
			}
		}
	}
	
	public boolean tileAt(Vector2 position){
		for(Tile tile : this.map.getTiles()){
			if(tile.getRectangle().overlaps(new  Rectangle(position.x, position.y, tile.getCurrentSprite().getWidth(), tile.getCurrentSprite().getHeight())) && tile.getTileType().solid){
				/**Game.globalDebug = true;
				Game.shape.setAutoShapeType(true);
				Game.shape.begin();
				Game.shape.rect(tile.getRectangle().x, tile.getRectangle().y, 3, 3);
				Game.shape.end();
				System.out.println("overlapping");**/
				//so you're overlapping that position, and somehow, that position has a solid tile on it. draw those things to get an idea
				return true;
			}
		}
		
		for(EntityLiving entity : this.map.getEntities()){
			if(entity instanceof EntityBuilding || entity instanceof EntityRock){
				if(entity.getRectangle().overlaps(new Rectangle(position.x, position.y, entity.getCurrentSprite().getWidth(), entity.getCurrentSprite().getHeight()))){
					return true;
				}
			}
		}
		
		boolean noTile = true;
		for(Tile tile : this.map.getTiles()){
			if(tile.getRectangle().overlaps(new Rectangle(position.x, position.y, tile.getCurrentSprite().getWidth(), tile.getCurrentSprite().getHeight()))){
				noTile = false;
			}
		}
		
		
		
		return noTile;
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

	public EntityAnimation getDirectionalAnimation() {
		return directionalAnimation;
	}

	public void setDirectionalAnimation(EntityAnimation directionalAnimation) {
		this.directionalAnimation = directionalAnimation;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	@Override
	public Rectangle getRectangle(){
		if(this.getDirectionalAnimation() == null){
			return new Rectangle(this.getPosition().x, this.getPosition().y, this.getCurrentSprite().getWidth(), this.getCurrentSprite().getHeight());
		}else{
			return new Rectangle(this.getPosition().x, this.getPosition().y, this.getDirectionalAnimation().getAnimation(this.getDirection()).getWidth(), this.getDirectionalAnimation().getAnimation(this.getDirection()).getHeight());
		}
		//or just this.getCurrentSprite().bounds
	}
	
}
