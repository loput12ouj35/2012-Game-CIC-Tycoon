package com.teamssd;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "CIC Tycoon";
		cfg.useGL20 = true;
		cfg.width = 845;
		cfg.height = 525;
		
		new LwjglApplication(new CICTycoon(), cfg);
	}
}