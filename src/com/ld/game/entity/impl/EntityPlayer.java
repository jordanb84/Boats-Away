package com.ld.game.entity.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.ld.game.Game;
import com.ld.game.entity.Direction;
import com.ld.game.entity.EntityLiving;
import com.ld.game.entity.animation.Animation;
import com.ld.game.entity.animation.EntityAnimation;
import com.ld.game.entity.animation.Frame;
import com.ld.game.graphics.map.Map;
import com.ld.game.item.InventoryItem;
import com.ld.game.item.Text;
import com.ld.game.item.ToolShackInventory;
import com.ld.game.item.impl.ItemLog;
import com.ld.game.item.impl.ItemRawPig;
import com.ld.game.item.impl.ItemSeeds;
import com.ld.game.item.impl.ItemWheat;
import com.ld.game.tile.Tile;
import com.ld.game.tile.TileType;

public class EntityPlayer extends EntityLiving {

	private Map map;
	
	private Sprite dialogBreakTree;
	
	protected ToolShackInventory toolShackInventory;
	
	private OrthographicCamera camera;
	
	//private ShopInventory shopInventory;
	
	public EntityPlayer(Map map, Vector2 position) {
		super(map, position);
		this.getSprites().put(Direction.UP.name(), new Sprite(new Texture(Gdx.files.internal("assets/playerup.png"))));
		this.getSprites().put(Direction.DOWN.name(), new Sprite(new Texture(Gdx.files.internal("assets/playerdown.png"))));
		this.getSprites().put(Direction.LEFT.name(), new Sprite(new Texture(Gdx.files.internal("assets/playerleft.png"))));
		this.getSprites().put(Direction.RIGHT.name(), new Sprite(new Texture(Gdx.files.internal("assets/playerright.png"))));
		
		this.setCurrentSprite(Direction.DOWN.name());
		
		this.map = map;
		
		this.dialogBreakTree = new Sprite(new Texture(Gdx.files.internal("assets/dialog_breaktree.png")));
		this.dialogBreakTree.setAlpha(0.7f);
		
		this.toolShackInventory = new ToolShackInventory(new Vector2(150, 180));
		
		//this.shopInventory = new ShopInventory(map, this.toolShackInventory);
		
		EntityAnimation directionalAnimation = new EntityAnimation();
		
		Texture sheet = new Texture(Gdx.files.internal("assets/playersheet.png"));
		float duration = 0.3f;
		
		Animation animationRight = new Animation();
		animationRight.addFrame(new Frame(sheet, 3, 0, 10, 16, duration));
		animationRight.addFrame(new Frame(sheet, 19, 0, 10, 16, duration));
		animationRight.addFrame(new Frame(sheet, 34, 0, 11, 16, duration));
		
		Animation animationLeft = new Animation();
		animationLeft.addFrame(new Frame(sheet, 3, 16, 11, 16, duration));
		animationLeft.addFrame(new Frame(sheet, 19, 16, 10, 16, duration));
		animationLeft.addFrame(new Frame(sheet, 35, 16, 10, 16, duration));
		
		Animation animationUp = new Animation();
		animationUp.addFrame(new Frame(sheet, 3, 32, 10, 16, duration));
		animationUp.addFrame(new Frame(sheet, 19, 32, 10, 16, duration));
		animationUp.addFrame(new Frame(sheet, 35, 32, 10, 16, duration));
		
		Animation animationDown = new Animation();
		animationDown.addFrame(new Frame(sheet, 3, 48, 10, 16, duration));
		animationDown.addFrame(new Frame(sheet, 19, 48, 10, 16, duration));
		animationDown.addFrame(new Frame(sheet, 35, 48, 10, 16, duration));
		
		directionalAnimation.addAnimation(Direction.RIGHT, animationRight);
		directionalAnimation.addAnimation(Direction.LEFT, animationLeft);
		directionalAnimation.addAnimation(Direction.UP, animationUp);
		directionalAnimation.addAnimation(Direction.DOWN, animationDown);
		
		this.setDirectionalAnimation(directionalAnimation);
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
		
		//this.checkForBreakableBlocks(batch, TileType.Tree);
		
		this.toolShackInventory.update(camera);
		//this.shopInventory.update(camera);
		
		this.camera = camera;
	}
	
	@Override
	public void render(SpriteBatch batch){
		super.render(batch);
		this.checkForBreakableBlocks(batch, TileType.Tree);
		
		this.toolShackInventory.render(batch);
		//this.shopInventory.render(batch);
		
		for(Tile tile : this.map.getTiles()){
			Vector3 mouse = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			this.camera.unproject(mouse);
			boolean overlaps = new Rectangle(mouse.x, mouse.y, 1, 1).overlaps(tile.getRectangle());
			if(tile.getTileType() == TileType.Soil){
				
				if(overlaps){
					List<InventoryItem> items = new ArrayList<InventoryItem>();
					items.add(new ItemSeeds(1));
					if(this.toolShackInventory.hasResources(items)){
						Text.Small.FONT.draw(batch, "Click to plant seeds", tile.getPosition().x, tile.getPosition().y);
					}else{
						Text.Small.FONT.draw(batch, "Click to plant seeds (you have none)", tile.getPosition().x, tile.getPosition().y);
					}
					
					if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
						if(this.toolShackInventory.hasResources(items)){
							tile.setNewType(TileType.WheatPlanted);
							this.toolShackInventory.removeItem(new ItemSeeds(1), 1);
						}
					}
				}
			}
			
			if(tile.getTileType() == TileType.WheatGrowing){
				if(overlaps){
					Text.Small.FONT.draw(batch, "Hey, it's done! Click to harvest wheat", tile.getPosition().x, tile.getPosition().y);
					
					if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
						tile.setNewType(TileType.Soil);
						this.toolShackInventory.addItem(new ItemWheat(1), 1);
					}
				}
			}
		}
		
		for(EntityLiving entity : this.map.getEntities()){
			Vector3 mouse = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			this.camera.unproject(mouse);
			boolean overlaps = new Rectangle(mouse.x, mouse.y, 1, 1).overlaps(entity.getRectangle());
			if(entity instanceof EntityBoat){
				
				if(overlaps){
					Text.Small.FONT.draw(batch, "It's a boat - a way", entity.getPosition().x-5, entity.getPosition().y);
					Text.Small.FONT.draw(batch, "to escape the island!", entity.getPosition().x-5, entity.getPosition().y - 16);
				}
			}
		}
	}
	
	public void checkForBreakableBlocks(SpriteBatch batch, TileType type){
		for(Tile tile : this.map.getTiles()){
			if(tile.getTileType() == type){
				Rectangle playerRectangle = new Rectangle(this.getPosition().x, this.getPosition().y, this.getCurrentSprite().getWidth() + 48 / 4, 1);
				playerRectangle.x -= playerRectangle.getWidth() / 2;
				
				Rectangle playerRectangle2 = new Rectangle(this.getPosition().x, this.getPosition().y - this.getCurrentSprite().getHeight() / 2 - 6, 1, this.getCurrentSprite().getHeight() * 2.2f);
				/**ShapeRenderer shape = new ShapeRenderer();
				shape.setProjectionMatrix(batch.getProjectionMatrix());
				shape.setAutoShapeType(true);
				shape.begin();
				shape.rect(playerRectangle.getX(), playerRectangle.getY(), playerRectangle.getWidth(), playerRectangle.getHeight());
				shape.circle(this.getPosition().x, this.getPosition().y, 10);
				shape.end();**/
				/**Game.globalDebug = true;
				Game.shape.setAutoShapeType(true);
				Game.shape.begin();
				Game.shape.setProjectionMatrix(batch.getProjectionMatrix());
				Game.shape.rect(playerRectangle.getX(), playerRectangle.getY(), playerRectangle.getWidth(), playerRectangle.getHeight());
				Game.shape.circle(this.getPosition().x, this.getPosition().y, 10);
				Game.shape.rect(playerRectangle2.getX(), playerRectangle2.getY(), playerRectangle2.getWidth(), playerRectangle2.getHeight());
				Game.shape.end();**/
				System.out.println(Gdx.graphics.getFramesPerSecond());
				if(playerRectangle.overlaps(tile.getRectangle()) || playerRectangle2.overlaps(tile.getRectangle())){
					tile.getCurrentSprite().setAlpha(0.9f);
					this.dialogBreakTree.setPosition(this.getPosition().x - this.dialogBreakTree.getWidth() / 4, this.getPosition().y + this.getCurrentSprite().getHeight() * 1.2f);
					this.dialogBreakTree.draw(batch);
					
					if(Gdx.input.isKeyJustPressed(Input.Keys.E)){
						tile.setNewType(TileType.Grass);
						//inventory.addItem(new ItemLog(1), new Random().nextInt(3));
						this.toolShackInventory.addItem(new ItemLog(1), MathUtils.random(1, 4));
						if(new Random().nextInt(3) == 2){
							this.toolShackInventory.addItem(new ItemSeeds(1), 1);
						}
						
						//tile.setNewType(TileType.Rock);
						//this.map.spawnEntity(new EntityRock(this.map, new Vector2(tile.getPosition().x, tile.getPosition().y)));
					}
				}else{
					tile.getCurrentSprite().setAlpha(1f);
				}
			}
		}
		
		for(EntityLiving entity : this.map.getEntities()){
			if(entity instanceof EntityPig){
				Rectangle playerRectangle = new Rectangle(this.getPosition().x, this.getPosition().y, this.getCurrentSprite().getWidth() + 48 / 4, 1);
				playerRectangle.x -= playerRectangle.getWidth() / 2;
				
				if(entity.getRectangle().overlaps(this.getRectangle())){
					entity.getCurrentSprite().setAlpha(0.9f);
					this.dialogBreakTree.setPosition(this.getPosition().x - this.dialogBreakTree.getWidth() / 3, this.getPosition().y + this.getCurrentSprite().getHeight() * 1.3f);
					this.dialogBreakTree.draw(batch);
					
					if(Gdx.input.isKeyJustPressed(Input.Keys.E)){
						
						this.toolShackInventory.addItem(new ItemRawPig(1), MathUtils.random(1, 4));
						this.map.despawnEntity(entity);
					}
				}
			}
		}
	}

	@Override
	public EntityLiving getNewInstance(Vector2 position) {
		return null;
	}

	public ToolShackInventory getInventory(){
		return this.toolShackInventory;
	}
}
