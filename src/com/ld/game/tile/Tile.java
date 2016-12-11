package com.ld.game.tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.ld.game.entity.Entity;
import com.ld.game.item.Text;

public class Tile extends Entity {

	private TileType tileType;
	
	private float cropGrowthElapsed;
	
	private boolean hovered;
	
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
		
		if(this.tileType.tooltip != null && this.hovered){
			Text.Small.FONT.draw(batch, this.tileType.tooltip, this.getPosition().x, this.getPosition().y);
		}
	}
	
	@Override
	public void update(OrthographicCamera camera) {
		if(this.getTileType().tileAction != null){
			this.getTileType().tileAction.update(camera, this);
		}
		
		if(this.getTileType() == TileType.WheatPlanted){
			this.cropGrowthElapsed += (1 * Gdx.graphics.getDeltaTime());
			
			if(this.cropGrowthElapsed >= 15){
				this.setNewType(TileType.WheatGrowing);
			}
		}
		
		Vector3 mouse = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(mouse);
		
		this.hovered = (this.getRectangle().overlaps(new Rectangle(mouse.x, mouse.y, 1, 1)));
	}
	
	public void setNewType(TileType newType){
		this.setTileType(newType);
		this.getSprites().remove("default");
		this.getSprites().put("default", new Sprite(new Texture(Gdx.files.internal("assets/" + newType.mapFileName + ".png"))));
		this.setCurrentSprite("default");
	}

	public TileType getTileType() {
		return tileType;
	}

	public void setTileType(TileType tileType) {
		this.tileType = tileType;
	}

}
