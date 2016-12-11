package com.ld.game.entity.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.ld.game.ai.mind.impl.PeacefulWanderingMind;
import com.ld.game.entity.Direction;
import com.ld.game.entity.EntityLiving;
import com.ld.game.graphics.map.Map;

public class EntitySoldier extends EntityLiving {

	Vector2 mousePosition = new Vector2();
	
	private Map map;
	
	public EntitySoldier(Map map, Vector2 position) {
		super(map, position);
		this.getSprites().put(Direction.UP.name(), new Sprite(new Texture(Gdx.files.internal("assets/soldierup.png"))));
		this.getSprites().put(Direction.DOWN.name(), new Sprite(new Texture(Gdx.files.internal("assets/soldierdown.png"))));
		this.getSprites().put(Direction.LEFT.name(), new Sprite(new Texture(Gdx.files.internal("assets/soldierright.png"))));
		this.getSprites().put(Direction.RIGHT.name(), new Sprite(new Texture(Gdx.files.internal("assets/soldierleft.png"))));
		
		this.setCurrentSprite(Direction.DOWN.name());
		
		this.setMind(new PeacefulWanderingMind(0.5f, this, new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())));
		
		this.map = map;
	}

	@Override
	public EntityLiving getNewInstance(Vector2 position) {
		return null;
	}

	@Override
	public void render(SpriteBatch batch){
		super.render(batch);
			/**if(new Rectangle(this.mousePosition.x, this.mousePosition.y, 1, 1).overlaps(this.getRectangle())){
				this.getCurrentSprite().setAlpha(0.8f);
				Text.Small.FONT.draw(batch, "Click to teleport soldier", this.getPosition().x, this.getPosition().y);
				
				if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
					for(EntityLiving entity : this.map.getEntities()){
						if(entity instanceof EntityPlayer){
							this.getPosition().set(entity.getPosition().x, entity.getPosition().y);
						}
					}
				}
			}**/
	}
	
	@Override
	public void update(OrthographicCamera camera) {
		super.update(camera);
		Vector3 mouse = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(mouse);
		
		this.mousePosition.set(mouse.x, mouse.y);
	}

}
