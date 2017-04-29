package io.github.bthomas2622;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Created by bthom on 2/10/2017.
 */

public class IntroWritings extends Actor {
    final HamiltonGame game;
    //font variables
    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    BitmapFont gameFont;
    GlyphLayout glyphLayout;
    String intro;
    String introStaggered = "";
    float paragraphWidth;
    long time;
    long timeSinceLetter = 1000000000;
    int introLetter = 0;
    float alpha = 1f;
    boolean introFinished = false;
    float textFieldWidth = 1000f;
    float screenWidth;
    float screenHeight;

    public IntroWritings(String intro, final HamiltonGame gam){
        game = gam;
        screenHeight = game.aspectY;
        screenWidth = game.aspectX;
        textFieldWidth = game.aspectX / 1.92f;
        this.intro = intro;
//        System.out.println("test");
        generator = new FreeTypeFontGenerator(Gdx.files.internal("JustAnotherHand.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40;
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
                introStaggered = introStaggered.concat(String.valueOf(intro.charAt(introLetter)));
                introLetter++;
                time = TimeUtils.nanoTime();
            } catch (Exception e) {
                alpha -= 0.01f;
                parameter.color.a = alpha;
                gameFont = generator.generateFont(parameter);
                if (alpha <= 0){
                    introFinished = true;
                }
            }
        } else {
            timeSinceLetter += TimeUtils.nanoTime() - time;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            introFinished = true;
        }
    }

    @Override
    public void draw(Batch batch, float alpha) {
        gameFont.draw(batch, introStaggered, screenWidth/6, screenHeight - screenHeight/6, textFieldWidth, 8, true);
    }

    public boolean getIntroFinished(){
        return introFinished;
    }

    public void dispose(){
        generator.dispose();
    }

}
