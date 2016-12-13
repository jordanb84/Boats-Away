package com.ld.game.graphics.map;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.ld.game.entity.EntityLiving;
import com.ld.game.entity.enemy.impl.EnemyGoblin;
import com.ld.game.entity.impl.EntityPig;
import com.ld.game.entity.impl.EntityPlayer;
import com.ld.game.graphics.map.io.LoadedTileData;
import com.ld.game.graphics.map.io.XmlMapReader;
import com.ld.game.item.Text;
import com.ld.game.sound.SoundPlayer;
import com.ld.game.state.StateManager;
import com.ld.game.state.impl.StateIntro;
import com.ld.game.tile.Tile;
import com.ld.game.tile.TileType;

public class Map {

	private List<EntityLiving> entities = new ArrayList<EntityLiving>();
	
	private List<EntityLiving> entitySpawnQueue = new ArrayList<EntityLiving>();
	
	private List<EntityLiving> entityDespawnQueue = new ArrayList<EntityLiving>();
	
	private List<Tile> tiles;
	
	private float elapsedSinceLastSpawn;
	
	private TimeManager clock;
	
	private String currentTooltip = ("");
	private Vector2 tooltipPosition = new Vector2();
	
	private HashMap<String, Vector2> text = new HashMap<String, Vector2>();
	
	public int soldiersKilled;
	
	private StateManager manager;
	
	private OrthographicCamera camera;
	
	private String names;
	
	private Sprite grassBackground;
	
	public Map(FileHandle mapFile, StateManager manager) throws SAXException, IOException, ParserConfigurationException{
		this.setTiles(this.generateTileListFromXml(mapFile));
		
		System.out.println("Loaded " + this.tiles.size() + " tiles");
		
		this.clock = new TimeManager(0.7f, 0.3f);
		
		this.manager = manager;
		
		try{
			this.names = (Gdx.files.internal("assets/nameinput.txt").readString());
		}catch(Exception e){
			this.names = ("Smith\nHarris\nDavis\nJohnson\nJones\nMiller\nWilson\nMoore");
		}
		
		this.grassBackground = new Sprite(new Texture(Gdx.files.internal("assets/sand3.png")));
	}

	public void render(SpriteBatch batch){
		for(int row = 0; row < 30; row++){
			for(int tile = 0; tile < 58; tile++){
				this.grassBackground.setPosition(tile * 16, row * 16);
				this.grassBackground.draw(batch);
			}
		}
		
		for(Tile tile : this.getTiles()){
			tile.render(batch);
		}
		
		for(EntityLiving entity : this.entities){
			entity.render(batch);
		}
		
		//this.clock.render(batch);
		
		if(this.currentTooltip.length() > 0){
			Text.Small.FONT.draw(batch, this.currentTooltip, this.tooltipPosition.x, this.tooltipPosition.y);
		}
		
		for(java.util.Map.Entry<String, Vector2> text : this.text.entrySet()){
			Text.Small.FONT.draw(batch, text.getKey(), text.getValue().x, text.getValue().y);
		}
		
		this.text.clear();
		
		//this.win();
	}
	
	boolean setPosition;
	
	boolean goofed;
	
	boolean goingBackward;
	
	boolean finishedGoof;
	
	public void win(){
		for(EntityLiving entity : this.getEntities()){
			if(entity instanceof EntityPlayer){
				int y = 30;
				int x = 30;
				if(!setPosition){
					SoundPlayer.play(SoundPlayer.Happy.MUSIC);
					entity.setPosition(new Vector2(416 - x, 208 - y));
					setPosition = true;
					
					entity.setDirectionalAnimation(null);
					entity.getSprites().put("boatright", new Sprite(new Texture(Gdx.files.internal("assets/boatright.png"))));
					entity.getSprites().put("boatleft", new Sprite(new Texture(Gdx.files.internal("assets/boatleft.png"))));
					entity.setCurrentSprite("boatright");
				}
				
				if(!goofed && entity.getPosition().x >= 420-x){
					goofed = true;
					goingBackward = true;
					entity.setCurrentSprite("boatleft");
				}
				
				
				if(!goingBackward){
					//entity.moveTowardPosition(0.5f, new Vector2(416 + 32, 208), true);
					entity.getPosition().add(0.02f, 0);
				}else{
					entity.getPosition().add(-0.03f, 0);
					
					if(entity.getPosition().x <= 410-x){
						goingBackward = false;
						entity.setCurrentSprite("boatright");
						finishedGoof = true;
					}
				}
				//System.out.println("moved " + entity.getPosition().x + "/" + entity.getPosition().y);
				
				if(finishedGoof){
					this.drawText("Oops! Away...", new Vector2(entity.getPosition().x-2, entity.getPosition().y));
				}
				
				if(!goofed){
					this.drawText("Bye!", new Vector2(entity.getPosition().x, entity.getPosition().y));
				}
				
				if(entity.getPosition().x >= 445){
					this.manager.setState(new StateIntro(this.manager, this.manager.getCamera()));
				}
			}
		}
	}
	
	public void drawText(String text, Vector2 position){
		this.text.put(text, position);
	}
	
	public void update(OrthographicCamera camera){
		for(EntityLiving entity : this.entitySpawnQueue){
			this.entities.add(entity);
		}
		this.entitySpawnQueue.clear();
		
		for(EntityLiving entity : this.entityDespawnQueue){
			this.entities.remove(entity);
		}
		this.entityDespawnQueue.clear();
		
		boolean newTooltip = false;
		
		for(Tile tile : this.getTiles()){
			tile.update(camera);
			
			
			
			Vector3 mouse = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(mouse);
			
			if(new Rectangle(mouse.x, mouse.y, 1, 1).overlaps(tile.getRectangle())){
				if(tile.getTileType().tooltip != null){
					this.currentTooltip = (tile.getTileType().tooltip);
					this.tooltipPosition.set(tile.getPosition().x, tile.getPosition().y + 16);
					newTooltip = true;
					//System.out.println(tile.getPosition().x + "/" + tile.getPosition().y);
				}
				System.out.println(tile.getPosition().x + "/" + tile.getPosition().y + "/" + tile.getTileType().name());
			}
		}
		
		if(!newTooltip){
			this.currentTooltip = ("");
		}
		
		for(EntityLiving entity : this.entities){
			entity.update(camera);
		}
		
		this.elapsedSinceLastSpawn += (1 * Gdx.graphics.getDeltaTime());
		
		if(this.elapsedSinceLastSpawn > 30f){
			int pigCount = 0;
			for(EntityLiving potentialPig : this.getEntities()){
				if(potentialPig instanceof EntityPig){
					pigCount++;
				}
			}
			
			if(pigCount <= 3){
				this.spawnEntity(new EntityPig(this, new Vector2(150, 250)));
				this.elapsedSinceLastSpawn = 0;
			}
		}

		System.out.println(this.currentTooltip);
		
		this.clock.update();
	}
	
	public void spawnEntity(EntityLiving entity){
		this.entitySpawnQueue.add(entity);
	}
	
	public void despawnEntity(EntityLiving entity){
		this.entityDespawnQueue.add(entity);
	}
	
	public List<Tile> generateTileListFromXml(FileHandle file) throws SAXException, IOException, ParserConfigurationException{
		XmlMapReader mapReader = new XmlMapReader();
		
		List<LoadedTileData> loadedTileData = (mapReader.getTilesFromFile(file));
		
		System.out.println("Tiles read from file: " + loadedTileData.size());
		
		List<Tile> loadedTiles = new ArrayList<Tile>();
		
		for(LoadedTileData tileData : loadedTileData){
			System.out.println("Name attribute: " + tileData.getAttributes().get("name"));
			TileType type = (TileType.forName(tileData.getAttributes().get("name")));
			int positionX = (Integer.parseInt(tileData.getAttributes().get("x")));
			int positionY = (Integer.parseInt(tileData.getAttributes().get("y")));
			
			loadedTiles.add(new Tile(this, type, new Vector2(positionX-16, positionY-16)));
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
	
	public String getRandomName(){
		String[] names = (this.names.split("\n"));
		return names[new Random().nextInt(names.length)];
	}
}
