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
    //font variables
    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    BitmapFont gameFont;
    GlyphLayout glyphLayout;
    String hurricanePoem = "This is a test. This is a test.";
    float paragraphWidth;
    Boolean finished = false;
    Boolean seated = false;
    Boolean nextScreen = false;
    float fadeAlpha = 0.0f;
    Sprite blackFade;


    public ScreenOne(final HamiltonGame gam){
        stage = new Stage(new FitViewport(1920, 1080));
        Gdx.input.setInputProcessor(stage);
        HamiltonActor hamilton = new HamiltonActor();
        HamiltonWritings hurricaneWritings = new HamiltonWritings();
        stage.addActor(hamilton);
        stage.addActor(hurricaneWritings);
        game = gam;
        blackFade = new Sprite(game.blackBackdrop);
        Gdx.input.setInputProcessor(this);
    }


    public class HamiltonActor extends Actor {
        TextureRegion hamiltonTextureRegion = new TextureRegion(game.hamiltonTexture);
        float actorX = 0, actorY = 0;

        public HamiltonActor(){
            setBounds(actorX, actorY, game.hamiltonTexture.getWidth(), game.hamiltonTexture.getHeight());
        }

        @Override
        public void act(float delta){
            super.act(delta);
            if (seated){
            } else {
                if (finished){
                    if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                        if(this.getX() >= Gdx.graphics.getWidth() / 2  - this.getWidth() / 2 && this.getX() < Gdx.graphics.getWidth()  + this.getWidth()){
                            if (this.getX() <= Gdx.graphics.getWidth() + this.getWidth() - 5){
                                this.setPosition(getX() + 5, getY());
                            }
                            else {
                                nextScreen = true;
                            }
                        }
                    }
                    if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                        if(this.getX() >= Gdx.graphics.getWidth() / 2  - this.getWidth() / 2 && this.getX() < Gdx.graphics.getWidth() + this.getWidth() - 5){
                            if (this.getX() >= Gdx.graphics.getWidth() / 2  - this.getWidth() / 2 + 5){
                                this.setPosition(getX() - 5, getY());
                            }
                        }
                    }
                } else {
                    if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                        if(this.getX() >= 0 && this.getX() < Gdx.graphics.getWidth() / 2 - this.getWidth() / 2) {
                            this.setPosition(getX() + 5, getY());
                            if (this.getX() >= Gdx.graphics.getWidth() / 2 - this.getWidth() / 2) {
                                seated = true;
                            }
                        }
                    }
                    if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                        if(this.getX() >= 0 && this.getX() < Gdx.graphics.getWidth() / 2 - this.getWidth() / 2){
                            if (this.getX() >= 5){
                                this.setPosition(getX() - 5, getY());
                            }
                        }
                    }
                }
            }
        }

        @Override
        public void draw(Batch batch, float alpha) {
//            Color color = getColor();
//            batch.setColor(color.r, color.g, color.b, color.a * alpha);
            batch.draw(hamiltonTextureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        }
    }

    public class HamiltonWritings extends Actor {

        public HamiltonWritings(){
            System.out.println("test");
            generator = new FreeTypeFontGenerator(Gdx.files.internal("JustAnotherHand.ttf"));
            parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.size = 20;
            parameter.color = Color.BLACK;
            gameFont = generator.generateFont(parameter);
            //generating a glyph layout to get the length of the string so i can center it
            glyphLayout = new GlyphLayout();;
            glyphLayout.setText(gameFont,hurricanePoem);
            paragraphWidth = glyphLayout.width;

        }
        @Override
        public void act(float delta){
            super.act(delta);
//            if(Gdx.input.isKeyPressed(int key){
//
//                if(this.getX() < Gdx.graphics.getWidth() / 2){
//                    this.setPosition(getX() + 5, getY());
//                }
//            }
        }

        @Override
        public void draw(Batch batch, float alpha) {
            gameFont.draw(batch, hurricanePoem, Gdx.graphics.getWidth()/2 - paragraphWidth/2, Gdx.graphics.getHeight() - Gdx.graphics.getHeight()/6);
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
        if (nextScreen & fadeAlpha < 1f){
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
        if (seated == true){
            if (hurricanePoem.equals("")){
                finished = true;
                seated = false;
                System.out.println("finished");
            } else {
                if (hurricanePoem.charAt(0) == character) {
                    hurricanePoem = hurricanePoem.substring(1);
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
        generator.dispose();
    }
}
