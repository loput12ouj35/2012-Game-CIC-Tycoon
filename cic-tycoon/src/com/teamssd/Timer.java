package com.teamssd;


import com.teamssd.Finance.FINANCE_TYPE;
import com.teamssd.scene.GameScene;


public class Timer implements Runnable {
    private long proc_time; 
    private long prev_mon = 0;
	
    public static double yearPerHour = 60;
	
    private GameScene scene;
	
    public Timer(GameScene gameScene) {
        this.scene = gameScene;
    }
	
    public long getMonth() {
        return prev_mon;
    }
	
    private void checkMon() {
        long time = (long) (proc_time / 1000 * yearPerHour);
        long cur_mon = (time % 3600) / (3600 / 12);
	    
        if (cur_mon == prev_mon) {
            return;
        }
	    
        int mCost = GameContext.f.get_Cost_maintenace(0, 0, 0,
                GameContext.b.count(FINANCE_TYPE.CLASS),
                GameContext.b.count(FINANCE_TYPE.LAB),
                GameContext.b.count(FINANCE_TYPE.LOUNGE), 0);

        GameContext.f.spend_Money(mCost);
        GameContext.e.run();
	    
        prev_mon = cur_mon;
    }

    @Override
    public void run() {
        long prev_time = System.currentTimeMillis();
		
        while (!scene.stopped()) {			
            long cur_time = System.currentTimeMillis();
			
            proc_time += cur_time - prev_time;
			
            prev_time = cur_time;
			
            checkMon();
        }
    }

    public long getTime() {
        return proc_time;
    }
}
