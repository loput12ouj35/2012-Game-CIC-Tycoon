package com.teamssd.event;


import com.teamssd.GameContext;


public class Debt implements Event {	
    public final static double rate = 0.05;

    public Debt() {}

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
        GameContext.f.spend_Money((int) (GameContext.r.debt * rate));
    }

}
