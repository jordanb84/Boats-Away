package com.ld.game.state.impl;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ld.game.graphics.map.TimeManager;
import com.ld.game.item.Text;
import com.ld.game.state.State;
import com.ld.game.state.StateManager;

public class StateIntro extends State {

	String[] text = new String[] {
			"You wake up mysteriously on an island.",
			"You notice a small shack, trees, and some other objects.",
			"\"How am I going to escape\", you ask. You see a boat across",
			"the river: You need to build up an army, build a bridge and",
			"capture that boat. Hover everything you can see to figure out",
			"what you need to do.",
			"",
			"Good luck. Use WASD to move, E to interact. Hover objects for information.",
			"Press SPACE to start your quest."
	};
	
	private StateTesting gameState;
	
	private boolean playingGame;

	private TimeManager timeManager;
	
	public StateIntro(StateManager stateManager, OrthographicCamera camera) {
		super(stateManager);
		try {
			this.gameState = new StateTesting(stateManager);
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
		
		this.gameState.update(camera);
		
		this.timeManager = new TimeManager(1f, 0.5f);
	}

	@Override
	public void initiate() {
		
	}

	@Override
	public void render(SpriteBatch batch) {
		if(this.playingGame){
			this.gameState.render(batch);
		}else{
			this.gameState.render(batch);
			this.timeManager.render(batch);
			
			int lineIndex = 0;
			for(String textLine : this.text){
				Text.Small.FONT.draw(batch, textLine, 100, Gdx.graphics.getHeight() / 3 - lineIndex * 16);
				lineIndex++;
			}
		}
	}

	@Override
	public void update(OrthographicCamera camera) {
		if(this.playingGame){
			this.gameState.update(camera);
		}else{
			this.timeManager.update();
			
			if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
				this.playingGame = true;
			}
		}
	}

}
