package com.teamssd.event;


import com.teamssd.GameContext;
import com.teamssd.Finance.FINANCE_TYPE;


public class Research implements Event {
    public Research() {}

    @Override
    public boolean check() {
        return true;
    }

    @Override
    public double getProb() {
        return 1.0;
    }

    @Override
    public void run() {
        ProfessorResearch.research(GameContext.b.count(FINANCE_TYPE.LAB) * 10);
    }

}
