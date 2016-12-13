package com.ld.game.tile;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.ld.game.entity.Entity;
import com.ld.game.entity.EntityLiving;
import com.ld.game.entity.impl.EntityPlayer;
import com.ld.game.graphics.map.Map;

public class Tile extends Entity {

	private TileType tileType;
	
	private float cropGrowthElapsed;
	
	private boolean hovered;
	
	private float requiredTime = 15;
	
	private Map map;
	
	public Tile(Map map, TileType type, Vector2 position) {
		super(position);
		this.setTileType(type);
		
		System.out.println(type);
		System.out.println("Loading tile " + "assets/" + type.mapFileName + ".png");
		this.getSprites().put("default", new Sprite(new Texture(Gdx.files.internal("assets/" + type.mapFileName + ".png"))));
		this.setCurrentSprite("default");
		
		this.requiredTime = (new Random().nextInt(60));
		
		this.map = map;
	}

	@Override
	public void render(SpriteBatch batch){
		if(this.getTileType().tileAction != null){
			this.getTileType().tileAction.render(batch, this);
		}
		
		if(this.getTileType().animation == null){
			this.getCurrentSprite().setPosition(this.getPosition().x, this.getPosition().y);
			this.getCurrentSprite().draw(batch);
		}else{
			this.getTileType().animation.update();
			this.getTileType().animation.render(batch, this.getPosition());
		}
	}
	
	@Override
	public void update(OrthographicCamera camera) {
		if(this.getTileType().tileAction != null){
			this.getTileType().tileAction.update(camera, this);
		}
		
		if(this.getTileType() == TileType.WheatPlanted){
			this.cropGrowthElapsed += (1 * Gdx.graphics.getDeltaTime());
			
			if(this.cropGrowthElapsed >= this.requiredTime){
				this.setNewType(TileType.WheatGrowing);
				this.cropGrowthElapsed = 0;
				this.requiredTime = (new Random().nextInt(60));
			}
		}
		
		if(this.getTileType() == TileType.Water){
			this.cropGrowthElapsed += (1 * Gdx.graphics.getDeltaTime());
			
			if(this.cropGrowthElapsed >= this.requiredTime){
				this.setNewType(TileType.WaterFish);
				this.cropGrowthElapsed = 0;
				this.requiredTime = (new Random().nextInt(60));
			}
		}
		
		if(this.getTileType() == TileType.Sapling){
			this.cropGrowthElapsed += (1 * Gdx.graphics.getDeltaTime());
			
			if(this.cropGrowthElapsed >= this.requiredTime){
				this.setNewType(TileType.Tree);
				this.cropGrowthElapsed = 0;
				this.requiredTime = (new Random().nextInt(60));
				
				for(EntityLiving entity : this.map.getEntities()){
					if(entity instanceof EntityPlayer && entity.getRectangle().overlaps(this.getRectangle())){
						this.setNewType(TileType.Sapling);
					}
				}
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
