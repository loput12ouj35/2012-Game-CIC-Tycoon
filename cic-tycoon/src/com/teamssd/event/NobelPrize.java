package com.teamssd.event;


import com.teamssd.GameContext;
import com.teamssd.ui.TimedMessageBox;


public class NobelPrize implements Event {
	
    private long prev_mon;
	    
    public NobelPrize() {
        prev_mon = 0;
    }
	    
    @Override
    public boolean check() {
        long gm = GameContext.t.getMonth() + 1;
        int needFame = GameContext.r.fame;
			
        System.out.println(gm);
			
        if (needFame < 2500) {
            return false;
        }
			
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
        return 0.1;
    }
	
    @Override
    public void run() {
        int addfame = GameContext.r.fame;
	    	
        addfame = (addfame < 5000) ? 1000 : addfame / 5;
        GameContext.r.fame += addfame;
        GameContext.newMessage(
                new TimedMessageBox(
                        "The professor of your college won the Nobel Prize!\n"
                                + "Fame +" + addfame));
    }

}
