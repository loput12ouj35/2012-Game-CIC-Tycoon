package com.teamssd.building.room;


import com.teamssd.Animations;
import com.teamssd.Finance.FINANCE_TYPE;


public class Stair implements Buildable {

    @Override
    public Room build(int id) {
        return new Room(FINANCE_TYPE.NONE, 1, 8, Animations.stairs());
    }

}
