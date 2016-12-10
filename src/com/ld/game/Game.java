package com.ld.game;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.lwjgl.opengl.GL11;
import org.xml.sax.SAXException;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ld.game.state.StateManager;
import com.ld.game.state.impl.StateTesting;

public class Game extends ApplicationAdapter {

	private SpriteBatch batch;
	
	private OrthographicCamera camera;
	
	private StateManager stateManager;
	
	@Override
	public void create(){
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, 480, 320);
		
		this.batch = new SpriteBatch();
		
		this.stateManager = new StateManager(this.camera);
		try {
			this.stateManager.setState(new StateTesting(this.stateManager));
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void render(){
		this.stateManager.update();
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		this.batch.setProjectionMatrix(this.camera.combined);
		this.batch.begin();
		this.stateManager.render(this.batch);
		this.batch.end();
		
		System.out.println(Gdx.graphics.getFramesPerSecond());
	}
	
}
