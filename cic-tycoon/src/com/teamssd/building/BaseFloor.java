package com.teamssd.building;


import com.teamssd.building.room.Office;


public class BaseFloor extends Floor {
    public BaseFloor() {
        super();
	
        rooms.add(new Office().build(2));
    }

    @Override
    public void destruct(int id) {
        switch (id) {
        case 2:
        case 3:
        case 6:
        case 7:
            return;
        }
	
        super.destruct(id);
    }
}
