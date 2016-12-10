package com.ld.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by root on 9/19/16.
 */
public abstract class State {

    private StateManager stateManager;

    private Stage uiStage;

    private Skin defaultSkin;

    public State(StateManager stateManager){
        this.stateManager = stateManager;
        this.uiStage = new Stage();

        Gdx.input.setInputProcessor(this.getUiStage());

        this.initiate();
    }

    public abstract void initiate();

    public abstract void render(SpriteBatch batch);

    public abstract void update(OrthographicCamera camera);

    /**
     * For post-render operations, such as lighting and UI
     */
    public void afterRenderUpdate(){
        this.getUiStage().act();
        this.getUiStage().draw();
    }

    public Stage getUiStage(){
        return this.uiStage;
    }

    public Skin getDefaultSkin(){
        return defaultSkin;
    }

    public void setDefaultSkin(Skin defaultSkin){
        this.defaultSkin = defaultSkin;
    }

    public StateManager getStateManager(){
        return stateManager;
    }
}