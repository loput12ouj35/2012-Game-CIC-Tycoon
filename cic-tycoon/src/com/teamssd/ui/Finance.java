package com.teamssd.ui;


import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.teamssd.Finance.FINANCE_TYPE;
import com.teamssd.GameContext;
import com.teamssd.Resource.Professor;
import com.teamssd.professor_profile;
import com.teamssd.event.Debt;


public class Finance implements SubMenu {
    private Sprite board;
    
    private Sprite vline;
    private Sprite dline;
    private Sprite sline;
    
    private Sprite borrow;
    private Sprite redeem;
    
    private Sprite donate;
    
    public Finance() {
        board = GameContext.sprite("data/white.png");
        board.setPosition(0, StatusBar.y_pos + 1);
        board.setSize(GameContext.w, GameContext.h - 75 - StatusBar.y_pos - 1);
	
        vline = GameContext.sprite("data/line.png");
        vline.setPosition(GameContext.w / 2, StatusBar.y_pos + 1);
        vline.setSize(1, GameContext.h - 75 - StatusBar.y_pos + 1);
	
        dline = GameContext.sprite("data/line.png");
        dline.setPosition(GameContext.w / 2, 150);
        dline.setSize(GameContext.w / 2, 1);
	
        sline = GameContext.sprite("data/line.png");
        sline.setPosition(GameContext.w / 2, 280);
        sline.setSize(GameContext.w / 2, 1);
	
        borrow = GameContext.sprite("data/borrow.png");
        borrow.setPosition(GameContext.w / 2 + 10, 80);
	
        redeem = GameContext.sprite("data/redeem.png");
        redeem.setPosition(GameContext.w / 2 + 200, 80);
	
        donate = GameContext.sprite("data/donate.png");
        donate.setPosition(GameContext.w * 3 / 4 - donate.getWidth() / 2, 210);
    }

    @Override
    public void render(SpriteBatch batch) {
        board.draw(batch);
	
        vline.draw(batch);
        dline.draw(batch);
        sline.draw(batch);
	
        borrow.draw(batch);
        redeem.draw(batch);
	
        donate.draw(batch);
	
        int classS = 0;
        int classA = 0;
        int classB = 0;
	
        BitmapFont f = GameContext.font;
	
        for (Professor p : GameContext.r.professors()) {
            if (!p.hired) {
                continue;
            }
		
            switch (p.p.getPro_class()) {
            case professor_profile.S_Class:
                ++classS;
                break;

            case professor_profile.A_Class:
                ++classA;
                break;

            case professor_profile.B_Class:
                ++classB;
                break;
            }
        }
	
        f.draw(batch, "Maintenance", 10, 50);
	
        f.draw(batch,
                "(class: " + GameContext.b.count(FINANCE_TYPE.CLASS)
                + ") * (cost: -" + com.teamssd.Finance.cost_maintenance_class
                + ")",
                10,
                80);
        f.draw(batch,
                "= "
                + (-GameContext.b.count(FINANCE_TYPE.CLASS)
                        * com.teamssd.Finance.cost_maintenance_class),
                        300,
                        80);
	
        f.draw(batch,
                "(lab: " + GameContext.b.count(FINANCE_TYPE.LAB)
                + ") * (cost: -" + com.teamssd.Finance.cost_maintenance_lab
                + ")",
                10,
                100);
        f.draw(batch,
                "= "
                + (-GameContext.b.count(FINANCE_TYPE.LAB)
                        * com.teamssd.Finance.cost_maintenance_lab),
                        300,
                        100);
        f.draw(batch,
                "(lounge: " + GameContext.b.count(FINANCE_TYPE.LOUNGE)
                + ") * (cost: -" + com.teamssd.Finance.cost_maintenance_lounge
                + ")",
                10,
                120);
        f.draw(batch,
                "= "
                + (-GameContext.b.count(FINANCE_TYPE.LOUNGE)
                        * com.teamssd.Finance.cost_maintenance_lounge),
                        300,
                        120);
        f.draw(batch,
                "(professor_class B: " + classB + ") * (cost: -"
                + com.teamssd.Finance.pay_professorB + ")",
                10,
                140);
        f.draw(batch, "= " + (-classB * com.teamssd.Finance.pay_professorB), 300,
                140);
        f.draw(batch,
                "(professor_class A: " + classA + ") * (cost: -"
                + com.teamssd.Finance.pay_professorA + ")",
                10,
                160);
        f.draw(batch, "= " + (-classA * com.teamssd.Finance.pay_professorA), 300,
                160);
        f.draw(batch,
                "(professor_class S: " + classS + ") * (cost: -"
                + com.teamssd.Finance.pay_professorS + ")",
                10,
                180);
        f.draw(batch, "= " + (-classS * com.teamssd.Finance.pay_professorS), 300,
                180);
	
        f.draw(batch, "Income", 10, 210);
        f.draw(batch,
                "(student: " + GameContext.r.student + ") * (tuition: "
                + com.teamssd.Finance.tuition_fee + ") / (6 months)",
                10,
                240);
        f.draw(batch,
                "= " + (GameContext.r.student * com.teamssd.Finance.tuition_fee / 6),
                300, 240);
		
        f.draw(batch, "total: ", 10, 290);
        f.draw(batch,
                "= "
                + ((-GameContext.b.count(FINANCE_TYPE.CLASS)
                        * com.teamssd.Finance.cost_maintenance_class)
                                + (-classS * com.teamssd.Finance.pay_professorS)
                                + (-classA * com.teamssd.Finance.pay_professorA)
                                + (-classB * com.teamssd.Finance.pay_professorB)
                                + (-GameContext.b.count(FINANCE_TYPE.LOUNGE)
                                        * com.teamssd.Finance.cost_maintenance_lounge)
                                        + (-GameContext.b.count(FINANCE_TYPE.LAB)
                                                * com.teamssd.Finance.cost_maintenance_lab)
                                                + (GameContext.r.student
                                                        * com.teamssd.Finance.tuition_fee)
                                                                / 6),
                                                                300,
                                                                290);
	
        f.draw(batch, "Dept ", 10, 340);
        f.draw(batch, "loan: ", 10, 360);
        f.draw(batch, "= " + (GameContext.r.debt), 300, 360);
        f.draw(batch, "rate: ", 10, 380);
        f.draw(batch, "" + Debt.rate, 300, 380);
        f.draw(batch, "total: ", 10, 410);
        f.draw(batch, "" + (GameContext.r.debt * -Debt.rate), 300, 410);

        f.draw(batch, "Debt(MAX $5000): $" + GameContext.r.debt, GameContext.w / 2 + 10, 50);
        f.draw(batch, "Donate $500 to a scholarship fund. (You'll get fame)",
                GameContext.w / 2 + 10, 170);
    }

    @Override
    public boolean click(int x, int y, BUTTON b) {
        if (borrow.getBoundingRectangle().contains(x, y)) {
            GameContext.r.borrow();
            return false;
        } else if (redeem.getBoundingRectangle().contains(x, y)) {
            GameContext.r.redeem();
            return false;
        } else if (donate.getBoundingRectangle().contains(x, y)) {
            GameContext.r.donate();
            return false;
        }
	
        return true;
    }

    @Override
    public void show() {}

    @Override
    public void hide() {}

    @Override
    public boolean scroll(int amount) {
        // TODO Auto-generated method stub
        return false;
    }

}
