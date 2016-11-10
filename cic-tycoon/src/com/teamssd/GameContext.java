package com.teamssd;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.teamssd.building.Building;
import com.teamssd.event.EventList;
import com.teamssd.scene.GameScene;
import com.teamssd.scene.Scene;
import com.teamssd.ui.MessageBox;


public class GameContext {
    private GameContext() {}
	
    public static enum CURSOR {
        NORMAL, BUILD, SELECT,
    }
	
    private static Scene scene;
    private static HashMap<String, Object> vars;
	
    public static float w;
    public static float h;
	
    public static BitmapFont font;
	
    public static Finance f;
    public static Building b;
    public static Timer t;
    public static EventList e;
    public static Resource r;
	
    private static Sprite cursNormal;
    private static Sprite cursBuild;
    private static Sprite cursSelect;
	
    private static Sprite curCurs;
	
    private static ArrayList<MessageBox> messages;
	
    private static Sprite board;
    private static Sprite[] border;
	
    public static void init() {
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();
		
        font = new BitmapFont(true);
        font.setColor(0, 0, 0, 1);

        vars = new HashMap<String, Object>();	
        scene = new GameScene();
	
        scene.set();
        Gdx.input.setInputProcessor(scene);
		
        messages = new ArrayList<MessageBox>();
		
        board = GameContext.sprite("data/white.png");
        board.setSize(w / 2.5f, h / 6);
        board.setPosition((w - board.getWidth()) / 2,
                (h - board.getHeight()) / 2);
		
        cursNormal = GameContext.sprite("data/cursor/normal.png");
        cursBuild = GameContext.sprite("data/cursor/build.png");
        cursSelect = GameContext.sprite("data/cursor/select.png");
		
        curCurs = cursNormal;
		
        border = new Sprite[4];
		
        border[0] = GameContext.sprite("data/line.png");
        border[0].setSize(w / 2.5f, 1);
        border[0].setPosition((w - board.getWidth()) / 2,
                (h - board.getHeight()) / 2);

        border[1] = GameContext.sprite("data/line.png");
        border[1].setSize(w / 2.5f, 1);
        border[1].setPosition((w - board.getWidth()) / 2,
                (h - board.getHeight()) / 2 + board.getHeight());

        border[2] = GameContext.sprite("data/line.png");
        border[2].setSize(1, h / 6);
        border[2].setPosition((w - board.getWidth()) / 2,
                (h - board.getHeight()) / 2);

        border[3] = GameContext.sprite("data/line.png");
        border[3].setSize(1, h / 6);
        border[3].setPosition((w - board.getWidth()) / 2 + board.getWidth(),
                (h - board.getHeight()) / 2);
    }
	
    public static void start(GameScene scene) {
        f = new Finance();
        b = new Building();
        e = new EventList();
        r = new Resource();
        t = new Timer(scene);
    }
	
    public static void setScene(Scene s) {
        messages.clear();
	    
        scene.unset();
        scene = s;
        scene.set();
		
        Gdx.input.setInputProcessor(scene);
    }
	
    public static void setCursor(CURSOR c) {
        switch (c) {
        case NORMAL:
            curCurs = cursNormal;
            break;

        case BUILD:
            curCurs = cursBuild;
            break;

        case SELECT:
            curCurs = cursSelect;
            break;
        }
    }
	
    private static void renderMesage(MessageBox m, SpriteBatch batch) {
        String str = m.content();
	    
        board.draw(batch);
	    
        for (Sprite s : border) {
            s.draw(batch);
        }
	    
        TextBounds bound = font.getMultiLineBounds(str);

        font.drawMultiLine(batch, str, (w - bound.width) / 2,
                (h - bound.height) / 2);
    }
	
    private static void renderCurs(SpriteBatch batch) {
        curCurs.setPosition(Gdx.input.getX(0), Gdx.input.getY(0));
	    
        curCurs.draw(batch);
    }
	
    public static void render(SpriteBatch batch) {
        scene.render(batch);
		
        for (Iterator<MessageBox> it = messages.iterator(); it.hasNext();) {
            MessageBox m = it.next();
		    
            if (m.closed()) {
                it.remove();
                continue;
            }
		    
            renderMesage(m, batch);
            break;
        }
		
        renderCurs(batch);
    }
	
    public static void set(String key, Object v) {
        vars.put(key, v);
    }
	
    @SuppressWarnings("unchecked")
    public static <T> T get(String key) {
        return (T) vars.get(key);
    }
	
    public static Sprite sprite(String file) {
        Sprite ret = new Sprite(new Texture(Gdx.files.internal(file)));
		
        ret.flip(false, true);
		
        return ret;
    }
	
    public static void newMessage(MessageBox m) {
        messages.add(m);
    }
}
