package com.ld.game;

import org.lwjgl.opengl.GL11;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.ld.game.state.StateManager;
import com.ld.game.state.impl.StateIntro;

public class Game extends ApplicationAdapter {

	private SpriteBatch batch;
	
	private OrthographicCamera camera;
	
	private StateManager stateManager;
	
	public static ShapeRenderer shape;
	public static boolean globalDebug;
	
	@Override
	public void create(){
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, 416, 272);
		
		this.batch = new SpriteBatch();
		
		this.stateManager = new StateManager(this.camera);
		this.stateManager.setState(new StateIntro(this.stateManager, this.camera));
		
		shape = new ShapeRenderer();
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
		
		if(this.globalDebug){
			this.shape.setProjectionMatrix(this.batch.getProjectionMatrix());
		}
		
	//	System.out.println(Gdx.graphics.getFramesPerSecond());
	}
	
}
