package io.github.bthomas2622;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by bthom on 4/29/2017.
 */

public class ScreenThree implements Screen, InputProcessor {
    final HamiltonGame game;
    private Stage stage;
    String paragraph0 = "This is a test. This is a test.";
    String paragraph1 = "Paragraph2";
    String paragraph2 = "Paragraph3";
    Array<String> restOfWritings = new Array<String>(true, 3);
    float fadeAlpha = 0.0f;
    HamiltonActor hamilton;
    HamiltonWritings tbdWritings;


    public ScreenThree(final HamiltonGame gam){
        game = gam;
        stage = new Stage(new FitViewport(game.aspectX, game.aspectY));
        Gdx.input.setInputProcessor(stage);
        hamilton = new HamiltonActor(game);
        restOfWritings.add(paragraph0);
        restOfWritings.add(paragraph1);
        restOfWritings.add(paragraph2);
        tbdWritings = new HamiltonWritings(restOfWritings, game);
        stage.addActor(hamilton);
        stage.addActor(tbdWritings);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta){
        //make sure music is loaded
//        if (loaded == false){
//            loaded = startMusic();
//        }
        Gdx.gl.glClearColor(1f - fadeAlpha,1f - fadeAlpha,1f - fadeAlpha, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        if (hamilton.getNextScreen() && fadeAlpha < 1f){
            fadeAlpha += .01f;
            if (fadeAlpha >= 1f){
                game.setScreen(new ScreenOneIntro(game));
            }
        }
        //Start the writing when hamilton sits down
        if (hamilton.getSeated() && tbdWritings.getCurrentParagraph() == 0){
            tbdWritings.nextParagraph();
        }
        if (tbdWritings.getScreenFinished()){
            hamilton.setNextScreen(true);
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
            if (tbdWritings.getWritingStatus()){
                if (tbdWritings.nextParagraph()){
                    hamilton.setFinished(true);
                    hamilton.setSeated(false);
                    System.out.println("finished");
                }
            } else {
                tbdWritings.checkTyped(character);
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
        dispose();
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
        tbdWritings.dispose();
    }
}