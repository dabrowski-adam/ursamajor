package com.pbl.ursa.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.pbl.ursa.UrsaGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Ursa Major";
		config.width = 320;
		config.height = 480;
		new LwjglApplication(new UrsaGame(), config);
	}
}
