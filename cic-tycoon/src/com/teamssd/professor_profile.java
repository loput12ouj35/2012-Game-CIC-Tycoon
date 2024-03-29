package com.teamssd;


import com.badlogic.gdx.graphics.g2d.Sprite;


public class professor_profile {
	
    // ///////////constants/////////////
	
    public final static int S_Class = 1;
    public final static int A_Class = 2;
    public final static int B_Class = 3;
	
    // /////////////variables//////////
	
    private Sprite img;
    private String name;
    private int pro_class;
    private int hire_money;
    private int research_speed;
    private int fame_to_hire;
    private int rising_fame;
    // /////////constructor///////////////
	

    public professor_profile(Sprite img, String name,
            int pro_class, int hire_money, int research_speed,
            int fame_to_hire, int rising_fame) {
        this.img = img;
        this.name = name;
        this.pro_class = pro_class;
        this.hire_money = hire_money;
        this.research_speed = research_speed;
        this.fame_to_hire = fame_to_hire;
        this.rising_fame = rising_fame;
    }
	
    // ///////////method//////////////
	

    public Sprite getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public int getPro_class() {
        return pro_class;
    }

    public int getHire_money() {
        return hire_money;
    }

    public int getResearch_speed() {
        return research_speed;
    }

    public int getFame_to_hire() {
        return fame_to_hire;
    }

    public int getRising_fame() {
        return rising_fame;
    }

    public String getClassStr() {
        switch (pro_class) {
        case S_Class:
            return "S";

        case A_Class:
            return "A";

        case B_Class:
            return "B";
        }
	    
        return "";
    }
	
}
