package io.github.bthomas2622;

/**
 * Created by bthom on 1/19/2017.
 */

/**
 * Created by bthom on 1/8/2017.
 */


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;


public class ScreenOneIntro implements Screen {
    final HamiltonGame game;
    private Stage stage;
    String hurricaneIntro = "Alexander Hamilton was born January 11th, 1757 on the island of Nevis in the British West Indies. Alexander was abandoned by his father. His mother succumbed to a severe fever and died shortly after Alexander’s 11th birthday. Alexander buried himself in books and obtained a job working as a clerk at a local import-export firm in St. Croix. On August 30, 1772 a devastating hurricane laid waste to the island. Hamilton wrote his father a detailed account of the hurricane that was later published to the Royal Danish-American Gazette. The essay was so well received amongst the community that a fund was collected to send young Hamilton to the North American colonies to receive an education.";
    IntroWritings hurricaneWritings;

    public ScreenOneIntro(final HamiltonGame gam){
        game = gam;
        stage = new Stage(new FitViewport(1920, 1080));
        hurricaneWritings = new IntroWritings(hurricaneIntro);
        stage.addActor(hurricaneWritings);
    }

    @Override
    public void render(float delta){
        //make sure music is loaded
//        if (loaded == false){
//            loaded = startMusic();
//        }
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
        if (hurricaneWritings.getIntroFinished()){
            game.setScreen(new ScreenOne(game));
        }
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
    public void resize(int width, int height){
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show(){
    }

    @Override
    public void hide(){
        dispose();
    }

    @Override
    public void pause(){
    }

    @Override
    public void resume(){
    }

    @Override
    public void dispose(){
        stage.dispose();
        hurricaneWritings.dispose();
    }
}
