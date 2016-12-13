package com.ld.game.tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ld.game.entity.animation.Animation;
import com.ld.game.entity.animation.Frame;
import com.ld.game.tile.impl.TileActionDestroyOnClick;

public enum TileType {
	Grass("grass"), Grass1("grass1"), Grass2("grass2"), Grass3("grass3"),
	GrassDark("grassdark"), Tree("tree", true, new TileActionDestroyOnClick(), "Press E when nearby to break. Drops: Log, Seeds"), Water("water", true, "You'll need a bridge to cross it", new String[] {"water.png", "water1.png"}), Water1("water1", true, "You'll need a bridge to cross it", new String[] {"water.png", "water1.png"}), Water2("water2", true), House("house", true, "It's a house, but the door doesn't work"), Brick("brick", true),
	Soil("soil"), WheatPlanted("wheatplanted", false, "It's growing! Not done yet, though"), WheatGrowing("wheatgrowing"), Rock("rock", true, "Huh. It's a rock. You'll need slaves to collect it."),
	WaterFish("waterfish", true, "Press E when nearby to collect fish"), Sapling("sapling", false, "It's growing into a tree"),
	Sand("sand"), Sand1("sand1"), Sand2("sand2"), Shore("shore"), Shore1("shore1"),
	Shore2("shore2"), ShoreDown("shoredown"), ShoreDark("shoredark"), Shore1Dark("shore1dark"), ShoreDarkUp("shoredarkup")
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
	
	TileType(String mapFileName, boolean solid, String tooltip, String[] spritePaths){
		this.animation = new Animation();
		for(String sprite : spritePaths){
			this.animation.addFrame(new Frame(new TextureRegion(new Texture(Gdx.files.internal("assets/" + sprite))), 5));
		}
		
		this.mapFileName = mapFileName;
		this.solid = solid;
	}
	
	public final String mapFileName;
	public final boolean solid;
	public TileAction tileAction;
	public String tooltip;
	
	public Animation animation;
	
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
