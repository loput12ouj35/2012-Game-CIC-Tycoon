package com.teamssd.building;


import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.teamssd.Finance.FINANCE_TYPE;
import com.teamssd.GameContext;
import com.teamssd.Renderable;
import com.teamssd.building.room.EV;
import com.teamssd.building.room.Room;
import com.teamssd.building.room.Stair;
import com.teamssd.ui.StatusBar;


public class Floor implements Renderable {
    protected Sprite background;
    protected ArrayList<Room> rooms;
	
    public Floor() {
        rooms = new ArrayList<Room>();
		
        rooms.add(new EV().build(4));
        rooms.add(new Stair().build(8));
		
        background = GameContext.sprite("data/background.png");
        background.setPosition(0, StatusBar.y_pos + 1);
    }
	
    @Override
    public void render(SpriteBatch batch) {
        background.draw(batch);
		
        Iterator<Room> it = rooms.iterator();
		
        while (it.hasNext()) {
            it.next().render(batch);
        }
    }
	
    public boolean check(Room r) {
        for (Iterator<Room> it = rooms.iterator(); it.hasNext();) {
            if (it.next().collapse(r)) {
                return false;
            }
        }
		
        return true;
    }
	
    public boolean build(Room r) {
        if (!check(r)) {
            return false;
        }
		
        rooms.add(r);
        GameContext.f.outgoing_construct(r.count, r.type);
		
        return true;
    }
	
    public void destruct(int id) {
        if (id == 8 || id == 4) {
            return;
        }
	    
        for (Iterator<Room> it = rooms.iterator(); it.hasNext();) {
            if (it.next().collapse(id)) {
                it.remove();
            }
        }
    }
	
    public int count() {
        int ret = 0;
	   
        for (Room r : rooms) {
            ret += r.count;
        }
	   
        return ret;
    }
	
    public int count(FINANCE_TYPE type) {
        int ret = 0;
	    
        for (Iterator<Room> it = rooms.iterator(); it.hasNext();) {
            Room r = it.next();
		
            if (r.type == type) {
                ret += r.count;
            }
        }
	    
        return ret;
    }
}
