package io.github.bthomas2622;

/**
 * Created by bthom on 1/8/2017.
 */

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import javafx.scene.shape.MoveTo;


public class ScreenOne implements Screen, InputProcessor {
    final HamiltonGame game;
    private Stage stage;
    String hurricanePoem = "This is a test. This is a test.";
    float fadeAlpha = 0.0f;
    Sprite blackFade;
    HamiltonActor hamilton;
    HamiltonWritings hurricaneWritings;


    public ScreenOne(final HamiltonGame gam){
        game = gam;
        stage = new Stage(new FitViewport(1920, 1080));
        Gdx.input.setInputProcessor(stage);
        hamilton = new HamiltonActor(game);
        hurricaneWritings = new HamiltonWritings(hurricanePoem);
        stage.addActor(hamilton);
        stage.addActor(hurricaneWritings);
        blackFade = new Sprite(game.blackBackdrop);
        Gdx.input.setInputProcessor(this);
    }

//    public void create() {
//        stage = new Stage(new FitViewport(1920, 1080));
//        Gdx.input.setInputProcessor(stage);
//        HamiltonActor hamilton = new HamiltonActor();
//        stage.addActor(hamilton);
//        stage.setKeyboardFocus(hamilton);
//    }

    @Override
    public void render(float delta){
        //make sure music is loaded
//        if (loaded == false){
//            loaded = startMusic();
//        }
        Gdx.gl.glClearColor(1, 1, 1, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        if (hamilton.getNextScreen() & fadeAlpha < 1f){
            fadeAlpha += .01f;
            blackFade.setColor(blackFade.getColor().r, blackFade.getColor().g, blackFade.getColor().b, fadeAlpha);
            stage.getBatch().begin();
            blackFade.draw(stage.getBatch());
            //blackFade.draw(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            stage.getBatch().end();
        }
        stage.draw();
    }

    public boolean startMusic() {
//        if(assetManager.isLoaded("intro.mp3")) {
//            introMusic = assetManager.get("intro.mp3", Music.class);
//            introMusic.setLooping(true);
//            introMusic.setVolume(0.5f);
//            introMusic.play();
//            return true;
//        }else {
//            //System.out.println("not loaded yet");
//            return false;
//        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        if (hamilton.getSeated() == true){
            if (hurricaneWritings.getHurricanePoem().equals("")){
                hamilton.setFinished(true);
                hamilton.setSeated(false);
                System.out.println("finished");
            } else {
                if (hurricaneWritings.getHurricanePoem().charAt(0) == character) {
                    hurricaneWritings.setHurricanePoem(hurricaneWritings.getHurricanePoem().substring(1));
                }
            }
        }
        return false;
    }

    @Override
    public void resize(int width, int height){
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show(){
    }

    @Override
    public void hide(){
    }

    @Override
    public void pause(){
    }

    @Override
    public void resume(){
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public void dispose(){
        stage.dispose();
        hurricaneWritings.dispose();
    }
}
