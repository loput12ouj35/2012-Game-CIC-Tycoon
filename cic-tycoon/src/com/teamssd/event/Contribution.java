package com.teamssd.event;


import com.teamssd.GameContext;
import com.teamssd.ui.TimedMessageBox;


public class Contribution implements Event {
	
    private long prev_mon;
	    
    public Contribution() {
        prev_mon = 0;
    }
	    
    @Override
    public boolean check() {
        long gm = GameContext.t.getMonth() + 1;
			
        System.out.println(gm);
			
        if ((gm % 12) == 0) {
            if (prev_mon != gm) {
                prev_mon = gm;
                return true;
            }
        }
			
        return false;
    }
	
    @Override
    public double getProb() {
        return 0.5;
    }
	
    @Override
    public void run() {
        int income = GameContext.r.student / 2;
	    	
        GameContext.f.add_Money(income);
        GameContext.newMessage(
                new TimedMessageBox(
                        "Some students donated to your college!\n" + "$ +"
                        + income));
    }

}
