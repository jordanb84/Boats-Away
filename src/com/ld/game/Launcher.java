package com.ld.game;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Launcher {

	public static void main(String[] args){
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = ("LD 37");
		config.width = (480);
		config.height = (320);
		
		new LwjglApplication(new Game(), config);
	}
	
}
