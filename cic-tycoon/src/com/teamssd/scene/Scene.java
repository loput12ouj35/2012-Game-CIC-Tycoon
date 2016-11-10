package com.teamssd.scene;


import com.badlogic.gdx.InputProcessor;
import com.teamssd.Renderable;


public interface Scene extends Renderable, InputProcessor {
    public void set();
    public void unset();
}
