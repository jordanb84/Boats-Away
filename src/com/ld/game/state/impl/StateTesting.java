package com.ld.game.state.impl;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.ld.game.entity.building.impl.BuildingBarracks;
import com.ld.game.entity.building.impl.BuildingBridge;
import com.ld.game.entity.building.impl.BuildingFirePit;
import com.ld.game.entity.building.impl.BuildingMeatFactory;
import com.ld.game.entity.building.impl.BuildingSlaves;
import com.ld.game.entity.impl.EntityBoat;
import com.ld.game.entity.impl.EntityNative;
import com.ld.game.entity.impl.EntityPig;
import com.ld.game.entity.impl.EntityPlayer;
import com.ld.game.graphics.map.Map;
import com.ld.game.sound.SoundPlayer;
import com.ld.game.state.State;
import com.ld.game.state.StateManager;

public class StateTesting extends State {

	private Map map;
	
	public StateTesting(StateManager stateManager) throws SAXException, IOException, ParserConfigurationException {
		super(stateManager);
		this.map = new Map(Gdx.files.internal("assets/map.xml"), stateManager);
		
		EntityPlayer player = new EntityPlayer(this.map, new Vector2(100-16, 70-16));
		this.map.spawnEntity(player);
		
		this.map.spawnEntity(new BuildingBarracks(this.map, new Vector2(50-16, 40-16), player.getInventory()));
		this.map.spawnEntity(new BuildingMeatFactory(this.map, new Vector2(112, 48), player.getInventory()));
		this.map.spawnEntity(new BuildingBridge(this.map, new Vector2(342-16, 144-16), player.getInventory()));
		this.map.spawnEntity(new BuildingSlaves(this.map, new Vector2(50-16, 75-16), player.getInventory()));
		this.map.spawnEntity(new BuildingFirePit(this.map, new Vector2(270-16, 70-16), player.getInventory()));
		
		this.map.spawnEntity(new EntityPig(this.map, new Vector2(150-16, 250-16)));
		
		this.map.spawnEntity(new EntityBoat(this.map, new Vector2(400-16, 150-16)));
		
		//this.map.spawnEntity(new EntitySlave(0, this.map, new Vector2(100, 90)));
		
		this.map.spawnEntity(new EntityNative(this.map, new Vector2(400-16, 100-16)));
		this.map.spawnEntity(new EntityNative(this.map, new Vector2(380-16, 80-16)));
		this.map.spawnEntity(new EntityNative(this.map, new Vector2(390-16, 50-16)));
		this.map.spawnEntity(new EntityNative(this.map, new Vector2(390-16, 40-16)));
		this.map.spawnEntity(new EntityNative(this.map, new Vector2(400-16, 30-16)));
		
		SoundPlayer.play(SoundPlayer.Happy.MUSIC);
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
