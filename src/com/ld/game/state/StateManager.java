package com.ld.game.state;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by root on 9/19/16.
 */
public class StateManager {

    private State currentState;

    private OrthographicCamera camera;

    public StateManager(OrthographicCamera camera){
        this.camera = camera;
    }

    public void render(SpriteBatch batch){
        this.currentState.render(batch);
    }

    public void update(){
        this.currentState.update(this.getCamera());
    }

    /**
     * For post-render operations, such as lighting and UI
     */
    public void afterRenderUpdate(){
        this.currentState.afterRenderUpdate();
    }

    public void replaceState(State newState){
        this.currentState = null;
        this.currentState = newState;
    }

    public void setState(State newState){
        this.currentState = newState;
    }

    public OrthographicCamera getCamera(){
        return this.camera;
    }
}