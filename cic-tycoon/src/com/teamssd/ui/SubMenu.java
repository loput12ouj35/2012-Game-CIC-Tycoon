package com.teamssd.ui;


import com.teamssd.Renderable;


public interface SubMenu extends Renderable {
    public enum BUTTON {
        LEFT, RIGHT
    }
    
    public boolean click(int x, int y, BUTTON b);
    public boolean scroll(int amount);
    public void show();
    public void hide();
}
