package com.ld.game.graphics.map;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.ld.game.entity.Entity;
import com.ld.game.entity.EntityLiving;
import com.ld.game.entity.enemy.impl.EnemyGoblin;
import com.ld.game.graphics.map.io.LoadedTileData;
import com.ld.game.graphics.map.io.XmlMapReader;
import com.ld.game.tile.Tile;
import com.ld.game.tile.TileType;

public class Map {

	private List<EntityLiving> entities = new ArrayList<EntityLiving>();
	
	private List<EntityLiving> entitySpawnQueue = new ArrayList<EntityLiving>();
	
	private List<Tile> tiles;
	
	public Map(File mapFile) throws SAXException, IOException, ParserConfigurationException{
		this.setTiles(this.generateTileListFromXml(mapFile));
		
		System.out.println("Loaded " + this.tiles.size() + " tiles");
	}

	public void render(SpriteBatch batch){
		for(Tile tile : this.getTiles()){
			tile.render(batch);
		}
		
		for(EntityLiving entity : this.entities){
			entity.render(batch);
		}
	}
	
	public void update(OrthographicCamera camera){
		for(EntityLiving entity : this.entitySpawnQueue){
			this.entities.add(entity);
		}
		this.entitySpawnQueue.clear();
		
		for(Tile tile : this.getTiles()){
			tile.update(camera);
		}
		
		for(EntityLiving entity : this.entities){
			entity.update(camera);
		}
	}
	
	public void spawnEntity(EntityLiving entity){
		this.entitySpawnQueue.add(entity);
	}
	
	public List<Tile> generateTileListFromXml(File file) throws SAXException, IOException, ParserConfigurationException{
		XmlMapReader mapReader = new XmlMapReader();
		
		List<LoadedTileData> loadedTileData = (mapReader.getTilesFromFile(file));
		
		System.out.println("Tiles read from file: " + loadedTileData.size());
		
		List<Tile> loadedTiles = new ArrayList<Tile>();
		
		for(LoadedTileData tileData : loadedTileData){
			System.out.println("Name attribute: " + tileData.getAttributes().get("name"));
			TileType type = (TileType.forName(tileData.getAttributes().get("name")));
			int positionX = (Integer.parseInt(tileData.getAttributes().get("x")));
			int positionY = (Integer.parseInt(tileData.getAttributes().get("y")));
			
			loadedTiles.add(new Tile(type, new Vector2(positionX, positionY)));
		}
		
		return loadedTiles;
	}

	public List<Tile> getTiles() {
		return tiles;
	}

	public void setTiles(List<Tile> tiles) {
		this.tiles = tiles;
	}
	
	public List<EntityLiving> getEntities(){
		return this.entities;
	}
	
	public void spawnRandomEnemy(){
		Class<EnemyGoblin> goblin = EnemyGoblin.class;
		try {
			goblin.newInstance().getNewInstance(new Vector2(200, 200));
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
