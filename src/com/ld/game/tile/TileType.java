package com.ld.game.tile;

import com.ld.game.tile.impl.TileActionDestroyOnClick;

public enum TileType {
	Grass("grass"), Grass1("grass1"), Grass2("grass2"), Grass3("grass3"),
	GrassDark("grassdark"), Tree("tree", true, new TileActionDestroyOnClick()), Water("water"), Water1("water1"), House("house", true), Brick("brick", true)
	;
	
	TileType(String mapFileName){
		this.mapFileName = mapFileName;
		this.solid = false;
	}
	
	TileType(String mapFileName, boolean solid){
		this.mapFileName = mapFileName;
		this.solid = solid;
	}
	
	TileType(String mapFileName, boolean solid, TileAction tileAction){
		this.mapFileName = mapFileName;
		this.solid = solid;
		this.tileAction = tileAction;
	}
	
	public final String mapFileName;
	public final boolean solid;
	public TileAction tileAction;
	
	public static TileType forName(String mapFileName){
		TileType type = null;
		
		for(TileType potentialMatchType : TileType.values()){
			if(potentialMatchType.mapFileName.equals(mapFileName)){
				type = potentialMatchType;
				break;
			}
		}
		
		return type;
	}
}
