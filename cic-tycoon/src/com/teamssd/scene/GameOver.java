package com.teamssd.scene;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.teamssd.GameContext;
import com.teamssd.ui.InfiniteMessageBox;


public class GameOver implements Scene {

    @Override
    public void render(SpriteBatch batch) {// TODO Auto-generated method stub
    }

    @Override
    public boolean keyDown(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        GameContext.setScene(new GameScene());
	
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void set() {
        GameContext.newMessage(new InfiniteMessageBox("Game Over"));
    }

    @Override
    public void unset() {// TODO Auto-generated method stub
    }

}
