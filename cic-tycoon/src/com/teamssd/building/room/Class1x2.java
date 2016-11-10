package com.teamssd.building.room;


import com.teamssd.Animations;
import com.teamssd.Finance.FINANCE_TYPE;


public class Class1x2 implements Buildable {

    @Override
    public Room build(int id) {
        Room ret = new Room(FINANCE_TYPE.CLASS, 2, id, Animations.class1x2());
		
        ret.occ.add(id + 4);
		
        return ret;
    }

}
