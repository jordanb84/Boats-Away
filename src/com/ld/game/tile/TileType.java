package com.ld.game.tile;

import com.ld.game.tile.impl.TileActionDestroyOnClick;

public enum TileType {
	Grass("grass"), Grass1("grass1"), Grass2("grass2"), Grass3("grass3"),
	GrassDark("grassdark"), Tree("tree", true, new TileActionDestroyOnClick(), "Press E when nearby to break. Drops: Log, Seeds"), Water("water", true, "You'll need a bridge to cross it"), Water1("water1", true, "You'll need a bridge to cross it"), House("house", true, "It's a house, but the door doesn't work"), Brick("brick", true, "Part of your one room storage hut. Cool."),
	Soil("soil"), WheatPlanted("wheatplanted", false, "It's growing! Not done yet, though"), WheatGrowing("wheatgrowing"), Rock("rock", true, "Huh. It's a rock. You'll need slaves to collect it.")
	;
	
	TileType(String mapFileName){
		this.mapFileName = mapFileName;
		this.solid = false;
	}
	
	TileType(String mapFileName, boolean solid, String tooltip){
		this.mapFileName = mapFileName;
		this.solid = solid;
		this.tooltip = tooltip;
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
	
	TileType(String mapFileName, boolean solid, TileAction tileAction, String tooltip){
		this.mapFileName = mapFileName;
		this.solid = solid;
		this.tileAction = tileAction;
		this.tooltip = tooltip;
	}
	
	public final String mapFileName;
	public final boolean solid;
	public TileAction tileAction;
	public String tooltip;
	
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
