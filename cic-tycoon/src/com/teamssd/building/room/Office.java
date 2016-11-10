package com.teamssd.building.room;


import com.teamssd.Animations;
import com.teamssd.Finance.FINANCE_TYPE;


public class Office implements Buildable {

    @Override
    public Room build(int id) {
        Room ret = new Room(FINANCE_TYPE.NONE, 4, id, Animations.office());
	
        ret.occ.add(id + 1);
        ret.occ.add(id + 4);
        ret.occ.add(id + 5);
	
        return ret;
    }

}
