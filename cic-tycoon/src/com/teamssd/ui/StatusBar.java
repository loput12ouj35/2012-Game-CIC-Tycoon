package com.teamssd.ui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.teamssd.GameContext;
import com.teamssd.Renderable;
import com.teamssd.Timer;
import com.teamssd.event.ProfessorResearch;


public class StatusBar implements Renderable {
    public static final float y_pos = 30;
	
    private Sprite line;
	
    public StatusBar() {
        this.line = new Sprite(new Texture(Gdx.files.internal("data/line.png")));
		
        line.setPosition(0, y_pos);
        line.setSize(GameContext.w, 1);
    }
	
    @Override
    public void render(SpriteBatch batch) {
        long time = (long) (GameContext.t.getTime() / 1000 * Timer.yearPerHour);
		
        long y = time / 3600;
        long m = (time % 3600) / (3600 / 12);
        long d = (time % 3600) / (3600 / (30 * 12)) % 30;
		
        line.draw(batch);

        GameContext.font.draw(batch, "time: " + y + "y" + m + "m" + d + "d", 10,
                10);
        GameContext.font.draw(batch, "$" + GameContext.f.get_Money(), 140, 10);
        GameContext.font.draw(batch, "students: " + GameContext.r.student, 240,
                10);
        GameContext.font.draw(batch, "fame: " + GameContext.r.fame, 340, 10);
		
        int per = 100 * ProfessorResearch.getProgress()
                / ProfessorResearch.getNeedProgress();
		
        GameContext.font.draw(batch, "Research: " + per + "%", 700, 10);
    }

}
