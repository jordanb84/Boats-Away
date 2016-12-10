package com.ld.game.entity.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ld.game.entity.Direction;
import com.ld.game.entity.EntityLiving;
import com.ld.game.graphics.map.Map;
import com.ld.game.tile.Tile;
import com.ld.game.tile.TileType;

public class EntityPlayer extends EntityLiving {

	private Map map;
	
	public EntityPlayer(Map map, Vector2 position) {
		super(map, position);
		this.getSprites().put(Direction.UP.name(), new Sprite(new Texture(Gdx.files.internal("assets/playerup.png"))));
		this.getSprites().put(Direction.DOWN.name(), new Sprite(new Texture(Gdx.files.internal("assets/playerdown.png"))));
		this.getSprites().put(Direction.LEFT.name(), new Sprite(new Texture(Gdx.files.internal("assets/playerleft.png"))));
		this.getSprites().put(Direction.RIGHT.name(), new Sprite(new Texture(Gdx.files.internal("assets/playerright.png"))));
		
		this.setCurrentSprite(Direction.DOWN.name());
		
		this.map = map;
	}

	@Override
	public void update(OrthographicCamera camera) {
		float movementSpeed = 0.5f;
		
		if(Gdx.input.isKeyPressed(Input.Keys.W)){
			this.move(movementSpeed, Direction.UP, true, true);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.S)){
			this.move(movementSpeed, Direction.DOWN, true, true);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.D)){
			this.move(movementSpeed, Direction.RIGHT, true, true);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.A)){
			this.move(movementSpeed, Direction.LEFT, true, true);
		}
		
		this.checkForBreakableBlocks(TileType.Tree);
	}
	
	public void checkForBreakableBlocks(TileType type){
		for(Tile tile : this.map.getTiles()){
			if(tile.getTileType() == type){
				Rectangle playerRectangle = new Rectangle(this.getPosition().x - 16 * 2, this.getPosition().y, this.getCurrentSprite().getWidth() + 16 * 2, this.getCurrentSprite().getHeight());
				if(playerRectangle.overlaps(tile.getRectangle())){
					//tile.getCurrentSprite().setAlpha(0.9f);
				}else{
					//tile.getCurrentSprite().setAlpha(1f);
				}
			}
		}
	}

	@Override
	public EntityLiving getNewInstance(Vector2 position) {
		return null;
	}

}
