package com.ld.game.item;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

public enum Text {
	Default(""), Small("", 0.5f)
	;
	
	Text(String fontPath){
		this.FONT = new BitmapFont();
	}
	
	Text(String fontPath, float size){
		this.FONT = new BitmapFont();
		this.FONT.getData().setScale(size);
	}
	
	public final BitmapFont FONT;
	
}
