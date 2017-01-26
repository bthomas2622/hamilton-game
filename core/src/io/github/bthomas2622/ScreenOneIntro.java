package io.github.bthomas2622;

/**
 * Created by bthom on 1/19/2017.
 */

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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import javafx.scene.shape.MoveTo;


public class ScreenOneIntro implements Screen {
    final HamiltonGame game;
    private Stage stage;
    //font variables
    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    BitmapFont gameFont;
    GlyphLayout glyphLayout;
    String hurricaneIntro = "Alexander Hamilton was born January 11th, 1757 on the island of Nevis in the British West Indies. \nThis is a new line                              ";
    String hurricaneIntroStaggered = "";
    float paragraphWidth;
    long time;
    long timeSinceLetter = 1000000000;
    int introLetter = 0;
    float alpha = 1f;

    public ScreenOneIntro(final HamiltonGame gam){
        stage = new Stage(new FitViewport(1920, 1080));
        HamiltonWritings hurricaneWritings = new HamiltonWritings();
        stage.addActor(hurricaneWritings);
        game = gam;
    }

    public class HamiltonWritings extends Actor {

        public HamiltonWritings(){
            System.out.println("test");
            generator = new FreeTypeFontGenerator(Gdx.files.internal("JustAnotherHand.ttf"));
            parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.size = 40;
            //parameter.color = Color.WHITE;
            parameter.color = Color.WHITE;
            parameter.color.a = alpha;
            gameFont = generator.generateFont(parameter);
            //generating a glyph layout to get the length of the string so i can center it
//            glyphLayout = new GlyphLayout();;
//            glyphLayout.setText(gameFont,hurricaneIntroStaggered);
//            paragraphWidth = glyphLayout.width;

        }
        @Override
        public void act(float delta){
            super.act(delta);
            if (timeSinceLetter > 70000000){
                try {
                    timeSinceLetter = 0;
                    hurricaneIntroStaggered = hurricaneIntroStaggered.concat(String.valueOf(hurricaneIntro.charAt(introLetter)));
                    introLetter++;
                    time = TimeUtils.nanoTime();
                } catch (Exception e) {
                    alpha -= 0.01f;
                    parameter.color.a = alpha;
                    gameFont = generator.generateFont(parameter);
                    if (alpha <= 0){
                        game.setScreen(new ScreenOne(game));
                        dispose();
                    }
                }
            } else {
                timeSinceLetter += TimeUtils.nanoTime() - time;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
                game.setScreen(new ScreenOne(game));
                dispose();
            }
        }

        @Override
        public void draw(Batch batch, float alpha) {
            gameFont.draw(batch, hurricaneIntroStaggered, Gdx.graphics.getWidth()/6, Gdx.graphics.getHeight() - Gdx.graphics.getHeight()/6);
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
        Gdx.gl.glClearColor(0, 0, 0, 0);
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
        generator.dispose();
    }
}
