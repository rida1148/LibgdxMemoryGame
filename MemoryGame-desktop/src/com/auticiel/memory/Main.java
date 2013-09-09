package com.auticiel.memory;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "MemoryGame";
		cfg.useGL20 = false;
		cfg.width = 256;
		cfg.height = 512;
		
		new LwjglApplication(new MemoryGame(), cfg);
	}
}
