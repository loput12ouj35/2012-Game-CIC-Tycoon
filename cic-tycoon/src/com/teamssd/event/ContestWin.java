package com.teamssd.event;


import com.teamssd.GameContext;
import com.teamssd.ui.TimedMessageBox;


public class ContestWin implements Event {

    private long prev_mon;
	    
    public ContestWin() {
        prev_mon = 0;
    }
	    
    @Override
    public boolean check() {
        long gm = GameContext.t.getMonth() + 1;
			
        System.out.println(gm);
			
        if ((gm % 6) == 0) {
            if (prev_mon != gm) {
                prev_mon = gm;
                return true;
            }
        }
			
        return false;
    }
	
    @Override
    public double getProb() {
        double Prob = 0.2;
	    	
        Prob += (GameContext.r.student / 200) * 0.1;
        Prob = (Prob > 0.4) ? 0.4 : Prob;
        return Prob;
    }
	
    @Override
    public void run() {
        int addfame = GameContext.r.fame;
	    	
        addfame = (addfame < 2000) ? 60 : addfame / 30;
        GameContext.r.fame += addfame;	
        GameContext.newMessage(
                new TimedMessageBox(
                        "The students of your college won the contest!\n"
                                + "Fame +" + addfame));
    }
}
