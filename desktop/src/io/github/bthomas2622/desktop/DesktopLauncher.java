package io.github.bthomas2622.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import io.github.bthomas2622.HamiltonGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Hamilton Game";
        config.width = 1920;
        config.height = 1080;
		new LwjglApplication(new HamiltonGame(), config);
	}
}
