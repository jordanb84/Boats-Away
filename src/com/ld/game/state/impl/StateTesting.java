package com.ld.game.state.impl;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.ld.game.entity.impl.EntityPlayer;
import com.ld.game.graphics.map.Map;
import com.ld.game.item.Inventory;
import com.ld.game.state.State;
import com.ld.game.state.StateManager;

public class StateTesting extends State {

	private Map map;
	
	public StateTesting(StateManager stateManager) throws SAXException, IOException, ParserConfigurationException {
		super(stateManager);
		this.map = new Map(new File("/home/oprsec/Desktop/aaaasprites/new/forestlake3.xml"));
		this.map.spawnEntity(new EntityPlayer(this.map, new Vector2(100, 100)));
	}

	@Override
	public void initiate() {
		
	}

	@Override
	public void render(SpriteBatch batch) {
		this.map.render(batch);
	}

	@Override
	public void update(OrthographicCamera camera) {
		this.map.update(camera);
	}

}
