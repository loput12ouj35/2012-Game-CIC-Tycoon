package com.teamssd.event;


import com.teamssd.Finance.FINANCE_TYPE;
import com.teamssd.ui.TimedMessageBox;
import com.teamssd.GameContext;


public class LoungeEvent implements Event {

    private long prev_mon;
    double progress = 0.0;
	
    public LoungeEvent() {
        prev_mon = 0;
    }

    @Override
    public boolean check() {
        long gm = GameContext.t.getMonth() + 1;
		
        System.out.println(gm);
		
        if ((gm % 3) == 0) {
            if (prev_mon != gm) {
                prev_mon = gm;
                return true;
            }
        }
		
        return false;
    }

    @Override
    public double getProb() {
        double Prob = 0.0;
		
        Prob += GameContext.b.count(FINANCE_TYPE.LOUNGE) / 20;
        Prob = (Prob > 0.5) ? 0.5 : Prob;
        return 0.0;
    }

    @Override
    public void run() {
        double random = Math.random();
        double percent = (random >= 0.5) ? ((random >= 0.8) ? 0.2 : 0.1) : 0.05;
    	
        GameContext.r.fame += GameContext.r.fame * percent;
        GameContext.newMessage(
                new TimedMessageBox(
                        "Students like lounges of your college!\n" + "Fame +"
                        + percent * 100 + "%"));

    }

}
