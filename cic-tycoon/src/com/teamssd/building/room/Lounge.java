package com.teamssd.building.room;


import com.teamssd.Animations;
import com.teamssd.Finance.FINANCE_TYPE;


public class Lounge implements Buildable {

    @Override
    public Room build(int id) {
        Room ret = new Room(FINANCE_TYPE.LOUNGE, 1, id, Animations.lounge());
	
        ret.occ.add(id + 1);
        ret.occ.add(id + 4);
        ret.occ.add(id + 5);
		
        return ret;
    }

}
