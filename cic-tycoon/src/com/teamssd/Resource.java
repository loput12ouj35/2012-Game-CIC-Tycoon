package com.teamssd;


import java.util.ArrayList;

import com.teamssd.ui.StatusBar;


public class Resource {
    public int fame;
    public int student;
    public int debt;
    
    private ArrayList<Professor> profs;
    
    public class Professor {
        public final professor_profile p;
        public boolean hired;
	
        public Professor(professor_profile p) {
            this.p = p;
            this.hired = false;
        }
    }
    
    public Resource() {
        fame = 0;
        student = 150;
        debt = 0;
	
        profs = new ArrayList<Resource.Professor>();
	
        for (professor_profile p : professor_list.professor_list) {
            p.getImg().flip(false, true);
            p.getImg().setPosition(GameContext.w / 3, StatusBar.y_pos + 25);
	    
            profs.add(new Professor(p));
        }
    }
    
    public Professor[] professors() {
        return profs.toArray(new Professor[0]);
    }
    
    public void borrow() {
        if (debt >= 5000) {
            return;
        }
	
        debt += 500;
        GameContext.f.add_Money(500);
    }
    
    public void redeem() {
        if (debt < 500) {
            return;
        } else if (GameContext.f.get_Money()<500){
        	return;
        }
        
        debt -= 500;
        GameContext.f.spend_Money(500);
    }
    
    public void donate() {
        if (GameContext.f.get_Money() >= 500) {
            GameContext.f.spend_Money(500);
            fame *= 1.01;
            fame += 500;
        }
    }
}
