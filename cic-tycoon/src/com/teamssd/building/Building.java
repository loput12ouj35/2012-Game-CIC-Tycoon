package com.teamssd.building;


import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.teamssd.Finance.FINANCE_TYPE;
import com.teamssd.GameContext;
import com.teamssd.Renderable;


public class Building implements Renderable {
    ArrayList<Floor> floors;
    int current_floor;
	
    public Building() {
        floors = new ArrayList<Floor>();
		
        current_floor = 0;
		
        floors.add(new BaseFloor());
        floors.add(new Floor());
    }

    @Override
    public void render(SpriteBatch batch) {
        floors.get(current_floor).render(batch);
    }
	
    public boolean click(int x, int y) {
        return false;
    }
	
    public Floor getFloor() {
        return floors.get(current_floor);
    }
	
    public int curFloor() {
        return current_floor + 1;
    }
	
    public int maxFloor() {
        return floors.size();
    }

    public boolean destruct(int screenX, int screenY) {
        int ox, oy;
		
        ox = (screenX + 15) / 215;
        oy = (screenY + 15) / 145;
		
        floors.get(current_floor).destruct(oy * 4 + ox);
		
        return true;
    }
	
    public boolean move(int offset) {
        int n = current_floor + offset;
	    
        if (n < 0) {
            n = 0;
        } else if (n >= floors.size()) {
            n = floors.size() - 1;
        }
	    
        current_floor = n;
	    
        return true;
    }
	
    public boolean go(int floor) {
        if (floor < 0 || floor >= floors.size()) {
            return false;
        }
	    
        current_floor = floor;
	    
        return true;
    }
	
    public int count() {
        int ret = 0;
	    
        for (Iterator<Floor> it = floors.iterator(); it.hasNext();) {
            ret += it.next().count();
        }
	    
        return ret;
    }
	
    public int count(FINANCE_TYPE type) {
        int ret = 0;
	    
        for (Iterator<Floor> it = floors.iterator(); it.hasNext();) {
            ret += it.next().count(type);
        }
	    
        return ret;
    }
	
    public void extend() {
        if (GameContext.f.outgoing_make_floor(floors.size())) {
            floors.add(new Floor());
        }
    }
}
