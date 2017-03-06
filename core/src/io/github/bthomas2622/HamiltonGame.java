package io.github.bthomas2622;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HamiltonGame extends Game {
	public static Texture hamiltonTexture;
	public static Texture deskTexture;
	public static int aspectX = 2560;
	public static int aspectY = 1440;
	
	@Override
	public void create () {
		//batch = new SpriteBatch();
        this.hamiltonTexture = new Texture(Gdx.files.internal("hamilton.png"));
		this.deskTexture = new Texture(Gdx.files.internal("desk.png"));
		this.setScreen(new ScreenOneIntro(this));
	}

//	@Override
//	public void render () {
//		super.render();
//	}
	
	@Override
	public void dispose () {
		//batch.dispose();
	}
}
