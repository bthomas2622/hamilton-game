package io.github.bthomas2622;

/**
 * Created by bthom on 1/8/2017.
 */

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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


public class ScreenOne implements Screen {
    //final HamiltonGame game;
    private Stage stage;

    public ScreenOne(final HamiltonGame gam){
        stage = new Stage(new FitViewport(1920, 1080));
        Gdx.input.setInputProcessor(stage);
        HamiltonActor hamilton = new HamiltonActor();
        stage.addActor(hamilton);
        //game = gam;
    }


    public class HamiltonActor extends Actor {
        Texture texture = new Texture(Gdx.files.internal("hamilton.png"));
        TextureRegion textureRegion = new TextureRegion(texture);
        float actorX = 0, actorY = 0;

        public HamiltonActor(){
            setBounds(actorX, actorY, texture.getWidth(), texture.getHeight());
        }

        @Override
        public void act(float delta){
            super.act(delta);
            if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                if(this.getX() < Gdx.graphics.getWidth() / 2){
                    this.setPosition(getX() + 5, getY());
                }
            }
        }

        @Override
        public void draw(Batch batch, float alpha) {
            Color color = getColor();
            batch.setColor(color.r, color.g, color.b, color.a * alpha);
            batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        }
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
    public void resize(int width, int height){
        stage.getViewport().update(width, height, true);
    }
//
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
    public void dispose(){
        stage.dispose();
    }
}
