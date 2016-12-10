package com.ld.game.entity.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ld.game.entity.Direction;
import com.ld.game.entity.EntityLiving;
import com.ld.game.graphics.map.Map;
import com.ld.game.item.Inventory;
import com.ld.game.item.ShopInventory;
import com.ld.game.item.ToolShackInventory;
import com.ld.game.item.impl.ItemLog;
import com.ld.game.tile.Tile;
import com.ld.game.tile.TileType;

public class EntityPlayer extends EntityLiving {

	private Map map;
	
	private Sprite dialogBreakTree;
	
	private ToolShackInventory toolShackInventory;
	
	private ShopInventory shopInventory;
	
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
		
		this.toolShackInventory = new ToolShackInventory(new Vector2(150, 230));
		
		this.shopInventory = new ShopInventory(map, this.toolShackInventory);
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
		this.shopInventory.update(camera);
	}
	
	@Override
	public void render(SpriteBatch batch){
		super.render(batch);
		this.checkForBreakableBlocks(batch, TileType.Tree);
		
		this.toolShackInventory.render(batch);
		this.shopInventory.render(batch);
	}
	
	public void checkForBreakableBlocks(SpriteBatch batch, TileType type){
		for(Tile tile : this.map.getTiles()){
			if(tile.getTileType() == type){
				Rectangle playerRectangle = new Rectangle(this.getPosition().x, this.getPosition().y, this.getCurrentSprite().getWidth() + 48 / 4, 1);
				playerRectangle.x -= playerRectangle.getWidth() / 2;
				/**ShapeRenderer shape = new ShapeRenderer();
				shape.setProjectionMatrix(batch.getProjectionMatrix());
				shape.setAutoShapeType(true);
				shape.begin();
				shape.rect(playerRectangle.getX(), playerRectangle.getY(), playerRectangle.getWidth(), playerRectangle.getHeight());
				shape.circle(this.getPosition().x, this.getPosition().y, 10);
				shape.end();**/
				if(playerRectangle.overlaps(tile.getRectangle())){
					tile.getCurrentSprite().setAlpha(0.9f);
					this.dialogBreakTree.setPosition(this.getPosition().x - this.dialogBreakTree.getWidth() / 4, this.getPosition().y + this.getCurrentSprite().getHeight() + 1.2f);
					this.dialogBreakTree.draw(batch);
					
					if(Gdx.input.isKeyJustPressed(Input.Keys.E)){
						tile.setNewType(TileType.Grass);
						//inventory.addItem(new ItemLog(1), new Random().nextInt(3));
						this.toolShackInventory.addItem(new ItemLog(1), MathUtils.random(1, 4));
					}
				}else{
					tile.getCurrentSprite().setAlpha(1f);
				}
			}
		}
	}

	@Override
	public EntityLiving getNewInstance(Vector2 position) {
		return null;
	}

}
