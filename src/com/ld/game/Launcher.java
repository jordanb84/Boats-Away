package com.ld.game;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Launcher {

	public static void main(String[] args){
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = ("LD 37");
		config.width = (416*2);
		config.height = (272*2);
		
		new LwjglApplication(new Game(), config);
	}
	
}
