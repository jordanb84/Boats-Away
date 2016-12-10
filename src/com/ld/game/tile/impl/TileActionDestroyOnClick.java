package com.ld.game.tile.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.ld.game.tile.Tile;
import com.ld.game.tile.TileAction;

public class TileActionDestroyOnClick extends TileAction {

	@Override
	public void render(SpriteBatch batch, Tile tile) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(OrthographicCamera camera, Tile tile) {
		Vector3 mousePosition = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(mousePosition);
		
		//System.out.println("Pos XY " + mousePosition.x + "/" + mousePosition.y);
		
		
		
		/**if(new Rectangle(mousePosition.x, mousePosition.y, 0, 0).overlaps(tile.getRectangle())){
			tile.getCurrentSprite().setAlpha(0.9f);
			if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
				tile.getCurrentSprite().setAlpha(0.5f);
			}
		}else{
			tile.getCurrentSprite().setAlpha(1f);
		}**/
	}

}
