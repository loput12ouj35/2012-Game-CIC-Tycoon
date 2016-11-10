package com.teamssd;


import com.teamssd.ui.TimedMessageBox;


public class Finance {
	
    // ////////constants///////////////////////
	
    public enum FINANCE_TYPE {
        NONE, CLASS, LAB, LOUNGE, PASSAGE,
    }
	
    public final static int start_money = 1000; // default money
	
    public final static int tuition_fee = 1; // tuition fee : 1 per 6months ;
	
    public final static int pay_professorA = 4; // pay of professor of A Class : 4 per month;
    public final static int pay_professorB = 3; // pay of professor of B Class: 3 per month;
    public final static int pay_professorS = 5; // pay of professor of S Class : 5 per month;
	
    public final static int cost_maintenance_class = 2; // cost of maintenance of class : 2 per month;
    public final static int cost_maintenance_lab = 4; // cost of maintenance of lab : 4 per month;
    public final static int cost_maintenance_lounge = 1; // cost of maintenance of lounge : 1 per month;
    public final static int cost_maintenance_passage = 0; // cost of maintenance of passage : 0;
	
    public final static int cost_make_class = 10; // cost of make class : 10 per cell;
    public final static int cost_make_lab = 7; // cost of make lab : 7 per cell;
    public final static int cost_make_lounge = 3; // cost of make lounge : 3 per cell;
    public final static int cost_make_passage = 1; // cost of make passage : 1 per cell;
    public final static int cost_make_vending_machine = 1; // cost of make vending machine : 1 per cell;
	
    public final static int cost_extend_floor = 500; // cost of extend floor : 500 (default);
    public final static double floor_rate = 1.5; // rate of increase(extending floor) : 150%;
	
    // ///////////////////variables/////////////////

	
    private int current_money; // current money;

    // the number of students, professors and etc. will be got from other class;
	
    // ////////////////////constructor//////////////////
	
    public Finance() { // default start_money is 1000;
        this(start_money);
    }

    public Finance(int current_money) {
        this.current_money = current_money;
    }
	
    // ////////////default methods//////////////////////
	
    public boolean bankruptcy() { // current money<0;
        return current_money < 0;
    }
	
    public boolean not_enough(int need) { // current money<you need;
        return current_money < need;
    }
	
    public boolean will_be_bankrupt(int n_professorS, int n_professorA, int n_professorB, int n_class, int n_lab, int n_lounge, int n_passage) { // you will be bankrupt on next month; warning!;
        return this.not_enough(
                get_Cost_maintenace(n_professorS, n_professorA, n_professorB,
                n_class, n_lab, n_lounge, n_passage));
    }
	
    public int get_Money() { // return current_money;
        return current_money;
    }
	
    public void set_Money(int money) { // set money. unused;
        this.current_money = money;
    }
	
    public void add_Money(int money) { // add money;
        this.current_money += money;
    }
	
    public void spend_Money(int money) { // subtract money;
        this.current_money -= money;
    }
	
    public int get_Cost_maintenace(int n_professorS, int n_professorA, int n_professorB, int n_class, int n_lab, int n_lounge, int n_passage) { // current cost of maintenance (per month);
        int cost = 0;

        cost += cost_maintenance_class * n_class;
        cost += cost_maintenance_lab * n_lab;
        cost += cost_maintenance_lounge * n_lounge;
        cost += cost_maintenance_passage * n_passage;
        cost += pay_professorS * n_professorS;
        cost += pay_professorA * n_professorA;
        cost += pay_professorB * n_professorB;
		
        return cost;
    }
	
    public double get_Cost_make_floor(int number_floor) { // return current cost of extending floor;
        number_floor -= 2; // excepting 1st floor and 2nd floor
        return cost_extend_floor * Math.pow(floor_rate, number_floor);
    }
	
    // ///////////////other methods////////////////////////
	
    public void income_tuition_fee(int student) { // students are money!
        this.add_Money(tuition_fee * student);
    }
	
    public void outgoing_maintenance(int n_professorS, int n_professorA, int n_professorB, int n_class, int n_lab, int n_lounge, int n_passage) { // spend money as cost of maintenance
        this.spend_Money(
                this.get_Cost_maintenace(n_professorS, n_professorA,
                n_professorB, n_class, n_lab, n_lounge, n_passage));
    }

    public void outgoing_construct(int n_cells, FINANCE_TYPE kind) { // make (1.class 2.lab 3.lounge 4.passage 5. vending machine);
        switch (kind) {
        case CLASS:
            this.spend_Money(n_cells * cost_make_class);
            break;

        case LAB:
            this.spend_Money(n_cells * cost_make_lab);
            break;

        case LOUNGE:
            this.spend_Money(n_cells * cost_make_lounge);
            break;

        case PASSAGE:
            this.spend_Money(n_cells * cost_make_passage);
            break;
        }
    }
	
    public boolean outgoing_make_floor(int number_floor) { // make_floor
        int money = (int) this.get_Cost_make_floor(number_floor);
	
        if (not_enough(money)) {
            GameContext.newMessage(new TimedMessageBox("Not enough money"));
            return false;
        }
	
        this.spend_Money(money);
        
        return true;
    }
}
