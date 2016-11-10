package com.teamssd.building.room;


import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.teamssd.AnimatedSprite;
import com.teamssd.Finance.FINANCE_TYPE;
import com.teamssd.FinanceUnit;
import com.teamssd.Renderable;


public class Room extends FinanceUnit implements Renderable {
    protected ArrayList<Integer> occ;
    protected AnimatedSprite sprite;
    private int id;
	
    public Room(FINANCE_TYPE type, int count, int id, AnimatedSprite sprite) {
        super(type, count);
		
        this.id = id;
        this.sprite = sprite;
		
        this.occ = new ArrayList<Integer>();
		
        occ.add(id);
		
        sprite.setPosition(calcX(), calcY());
    }
	
    public boolean collapse(Room r) {
        for (Iterator<Integer> it = occ.iterator(); it.hasNext();) {
            Integer i = it.next();
			
            for (Iterator<Integer> jt = r.occ.iterator(); jt.hasNext();) {
                Integer j = jt.next();
				
                if (i.equals(j)) {
                    return true;
                }
            }
        }
		
        return false;
    }
	
    public boolean collapse(int i) {
        for (Iterator<Integer> it = occ.iterator(); it.hasNext();) {
            if (it.next() == i) {
                return true;
            }
        }
		
        return false;
    }
	
    protected int calcX() {
        return 215 * (id % 4);
    }
	
    protected int calcY() {
        return 30 + 145 * (id / 4);
    }
	
    public void render(SpriteBatch batch) {
        sprite.render(batch);
    }
	
    public static boolean check(Room r) {
        int fl = r.occ.get(0) / 4;
        int pr = r.occ.get(0);
		
        for (Iterator<Integer> it = r.occ.iterator(); it.hasNext();) {
            Integer i = it.next();
			
            if ((i / 4) != fl && (i - pr) == 1) {
                return false;
            }
			
            if (i < 0 || i > 11) {
                return false;
            }
			
            fl = i / 4;
            pr = i;
        }
		
        return true;
    }
}
