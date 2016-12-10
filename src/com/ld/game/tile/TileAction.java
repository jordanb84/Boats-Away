package com.ld.game.tile;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class TileAction {

	public abstract  void render(SpriteBatch batch, Tile tile);
	
	public abstract void update(OrthographicCamera camera, Tile tile);
	
}
