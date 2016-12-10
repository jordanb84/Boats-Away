package com.ld.game.tile;

import java.util.HashMap;

public class TileManager {
	
	/** Standard size which can be assumed for all tiles **/
	public static final int TILE_WIDTH = 16;
	public static final int TILE_HEIGHT = 16;

	/** Used to map each tile type to a tile instance **/
	private HashMap<TileType, Tile> tiles = new HashMap<TileType, Tile>();
	
	public TileManager(){
		//this.tiles.put(TileType.Grass, new Tile())
	}
	
	public Tile getTileForType(TileType type){
		return this.tiles.get(type);
	}
}
