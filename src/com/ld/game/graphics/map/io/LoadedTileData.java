package com.ld.game.graphics.map.io;

import java.util.HashMap;

public class LoadedTileData {

	private HashMap<String, String> attributes;
	
	public LoadedTileData(HashMap<String, String> attributes){
		this.setAttributes(attributes);
	}

	public HashMap<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(HashMap<String, String> attributes) {
		this.attributes = attributes;
	}
	
	public void addAttribute(String name, String value){
		this.attributes.put(name, value);
	}
}
