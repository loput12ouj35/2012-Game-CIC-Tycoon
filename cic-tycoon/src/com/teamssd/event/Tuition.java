package com.teamssd.event;


import com.teamssd.Finance;
import com.teamssd.GameContext;
import com.teamssd.ui.TimedMessageBox;


public class Tuition implements Event {
    private long prev_mon;
    
    public Tuition() {
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
        return 1.0;
    }

    @Override
    public void run() {
        String fee = "$" + Finance.tuition_fee * GameContext.r.student;
	
        GameContext.f.income_tuition_fee(GameContext.r.student);
        GameContext.newMessage(new TimedMessageBox("Tuition Income\n" + fee));
    }

}
