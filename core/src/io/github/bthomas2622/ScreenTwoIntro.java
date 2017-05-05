package io.github.bthomas2622;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by bthom on 2/10/2017.
 */

public class ScreenTwoIntro implements Screen {
    final HamiltonGame game;
    private Stage stage;
    String vindicationIntro = "Hamilton reached the thirteen colonies of North America in October, 1772. He never did return to the caribbean. Hamilton studied at Elizabethtown Academy, a grammar school in New Jersey, before gaining acceptance to The King’s College (now Columbia University) in NYC at the age of 17. Hamilton ascended rapidly, gaining a reputation as both an intellectual and impassioned advocate for patriot grievances. In 1774, Church of England clergyman “A. W. Farmer” (later identified Samuel Seabury) published a series of pamphlets promoting the Loyalist cause. Hamilton responded with \"A Full Vindication of the Measures of the Congress\" and later \"The Farmer Refuted\". These first anonymously published political essays were just the beginning. Alexander Hamilton would become the most prolific and influential political writer of the era.";
    IntroWritings tbdWritings;

    public ScreenTwoIntro(final HamiltonGame gam){
        game = gam;
        stage = new Stage(new FitViewport(game.aspectX, game.aspectY));
        tbdWritings = new IntroWritings(vindicationIntro, game);
        stage.addActor(tbdWritings);
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
        if (tbdWritings.getIntroFinished()){
            game.setScreen(new ScreenTwo(game));
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
        tbdWritings.dispose();
    }
}
