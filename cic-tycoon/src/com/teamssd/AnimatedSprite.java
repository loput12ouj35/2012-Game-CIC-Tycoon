package com.teamssd;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class AnimatedSprite implements Renderable {
    private Sprite[] frames;
    private int offset;
	
    private float prev_time;
    private float cur_time;
	
    public AnimatedSprite(Sprite[] frames) {
        this.frames = frames;
		
        offset = 0;
    }
	
    public void setPosition(float x, float y) {
        for (Sprite s : frames) {
            s.setPosition(x, y);
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        frames[offset].draw(batch);
		
        cur_time += Gdx.graphics.getDeltaTime();

        if (cur_time > (prev_time + 0.1)) {
            ++offset;
            prev_time += 0.1;
			
            if (offset == frames.length) {
                offset = 0;
            }
        }
    }
}
