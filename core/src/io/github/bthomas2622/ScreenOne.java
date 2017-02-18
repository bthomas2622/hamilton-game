package io.github.bthomas2622;

/**
 * Created by bthom on 1/8/2017.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;


public class ScreenOne implements Screen, InputProcessor {
    final HamiltonGame game;
    private Stage stage;
    String paragraph0 = "Para graph 1";
    String paragraph1 = "Paragraph2";
    String paragraph2 = "Paragraph3";
    Array<String> writings = new Array<String>(true, 3);
    float fadeAlpha = 0.0f;
    HamiltonActor hamilton;
    HamiltonWritings hurricaneWritings;


    public ScreenOne(final HamiltonGame gam){
        game = gam;
        stage = new Stage(new FitViewport(1920, 1080));
        Gdx.input.setInputProcessor(stage);
        hamilton = new HamiltonActor(game);
        writings.add(paragraph0);
        writings.add(paragraph1);
        writings.add(paragraph2);
        hurricaneWritings = new HamiltonWritings(writings);
        stage.addActor(hamilton);
        stage.addActor(hurricaneWritings);
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
        if (hamilton.getNextScreen() & fadeAlpha < 1f){
            fadeAlpha += .01f;
            if (fadeAlpha >= 1f){
                game.setScreen(new ScreenTwoIntro(game));
            }
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
            if (hurricaneWritings.getVisibleWriting().equals("")){
                if (hurricaneWritings.nextParagraph()){
                    hamilton.setFinished(true);
                    hamilton.setSeated(false);
                    System.out.println("finished");
                }
            } else {
                hurricaneWritings.checkTyped(character);
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
        hurricaneWritings.dispose();
    }
}
