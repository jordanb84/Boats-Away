package com.ld.game.tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.ld.game.entity.Entity;

public class Tile extends Entity {

	private TileType tileType;
	
	public Tile(TileType type, Vector2 position) {
		super(position);
		this.setTileType(type);
		
		System.out.println(type);
		System.out.println("Loading tile " + "assets/" + type.mapFileName + ".png");
		this.getSprites().put("default", new Sprite(new Texture(Gdx.files.internal("assets/" + type.mapFileName + ".png"))));
		this.setCurrentSprite("default");
	}

	@Override
	public void render(SpriteBatch batch){
		super.render(batch);
		if(this.getTileType().tileAction != null){
			this.getTileType().tileAction.render(batch, this);
		}
	}
	
	@Override
	public void update(OrthographicCamera camera) {
		if(this.getTileType().tileAction != null){
			this.getTileType().tileAction.update(camera, this);
		}
	}

	public TileType getTileType() {
		return tileType;
	}

	public void setTileType(TileType tileType) {
		this.tileType = tileType;
	}

}
