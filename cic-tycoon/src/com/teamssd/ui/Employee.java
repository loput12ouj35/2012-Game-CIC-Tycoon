package com.teamssd.ui;


import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.teamssd.GameContext;
import com.teamssd.Resource.Professor;
import com.teamssd.professor_profile;


public class Employee implements SubMenu {
    private Sprite board;
    
    private Sprite vline;
    private Sprite hline;
    
    private Sprite hire;
    private Sprite fire;
    
    int scroll_offset = 0;
    int selected;
    
    public Employee() {
        board = GameContext.sprite("data/white.png");
        board.setPosition(0, StatusBar.y_pos + 1);
        board.setSize(GameContext.w, GameContext.h - 75 - StatusBar.y_pos - 1);
	
        vline = GameContext.sprite("data/line.png");
        vline.setSize(1, GameContext.h - 75 - StatusBar.y_pos);
        vline.setPosition(GameContext.w / 4, StatusBar.y_pos + 1);
	
        hline = GameContext.sprite("data/line.png");
        hline.setSize(GameContext.w * 3 / 4, 1);
        hline.setPosition(GameContext.w / 4, GameContext.h - 150);
	
        hire = GameContext.sprite("data/hire.png");
        hire.setPosition(GameContext.w * 5 / 8 - 75, GameContext.h - 140);
	
        fire = GameContext.sprite("data/fire.png");
        fire.setPosition(GameContext.w * 5 / 8 - 75, GameContext.h - 140);
    }

    @Override
    public void render(SpriteBatch batch) {
        board.draw(batch);
        vline.draw(batch);
        hline.draw(batch);
	
        BitmapFont f = GameContext.font;
	
        float offset = scroll_offset + StatusBar.y_pos + 1;
        Professor[] profs = GameContext.r.professors();
	
        for (Professor p : profs) {
            if (offset > StatusBar.y_pos - 5 && offset < GameContext.h - 85) {
                if (p.hired) {
                    f.setColor(0, 0, 1, 1);
                }
		
                f.draw(batch, p.p.getName(), 10, offset);
		
                if (p.hired) {
                    f.setColor(0, 0, 0, 1);
                }
            }
	    
            offset += 15;
        }
	
        if (selected != -1) {
            Professor P = profs[selected];
            professor_profile p = P.p;

            p.getImg().draw(batch);
            f.draw(batch, p.getName(), GameContext.w * 2 / 3,
                    StatusBar.y_pos + 20);
            f.draw(batch, "Class: " + p.getClassStr(), GameContext.w / 2,
                    StatusBar.y_pos + 50);
            f.draw(batch, "Fame Needed: " + p.getFame_to_hire(),
                    GameContext.w / 2, StatusBar.y_pos + 70);
            f.draw(batch, "Money Needed: " + p.getHire_money(),
                    GameContext.w / 2, StatusBar.y_pos + 90);
            f.draw(batch, "Research Speed: " + p.getResearch_speed(),
                    GameContext.w / 2, StatusBar.y_pos + 130);
            f.draw(batch, "Rising Fame: " + p.getRising_fame(),
                    GameContext.w / 2, StatusBar.y_pos + 150);
	    
            if (P.hired) {
                fire.draw(batch);
            } else {
                hire.draw(batch);
            }
        }
	
        int n = profs.length
                - (int) ((GameContext.h - 75 - StatusBar.y_pos) / 15);
	
        if (scroll_offset > 0) {
            scroll_offset = 0; 
        } else if (scroll_offset < -n * 15) {
            scroll_offset = -n * 15;
        }
    }
    
    private boolean click_list(int x, int y) {
        if (y < StatusBar.y_pos || y > GameContext.h - 75) {
            return true;
        }
	
        int offset = (int) ((y - StatusBar.y_pos - scroll_offset) / 15);
	
        selected = offset;
	
        return false;
    }
    
    private boolean click_button(int x, int y) {
        if (selected == -1) {
            return false;
        }
	
        Professor[] profs = GameContext.r.professors();
	
        Professor P = profs[selected];
        professor_profile p = P.p;
	
        if (P.hired) {
            P.hired = false;
            return false;
        }
	
        if (p.getFame_to_hire() > GameContext.r.fame) {
            GameContext.newMessage(new TimedMessageBox("Not enough fame"));
            return false;
        }
	
        if (GameContext.f.not_enough(p.getHire_money())) {
            GameContext.newMessage(new TimedMessageBox("Not enough money"));
            return false;
        }
	
        P.hired = true;
        GameContext.f.spend_Money(p.getHire_money());
	
        return false;
    }

    @Override
    public boolean click(int x, int y, BUTTON b) {
        if (x < GameContext.w / 4) {
            return click_list(x, y);
        }
	
        if (x > GameContext.w / 4 && y > GameContext.h - 140) {
            return click_button(x, y);
        }
	
        return false;
    }

    @Override
    public void show() {
        selected = -1;
    }

    @Override
    public void hide() {// TODO Auto-generated method stub
    }

    @Override
    public boolean scroll(int amount) {
        System.out.println("wahaha");
	
        scroll_offset -= amount * 2;
	
        return true;
    }

}
