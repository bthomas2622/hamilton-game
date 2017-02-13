package io.github.bthomas2622;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by bthom on 2/10/2017.
 */

public class HamiltonWritings extends Actor {
    //font variables
    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    GlyphLayout glyphLayout;
    String visibleWriting;
    String restOfWriting;
    BitmapFont gameFont;
    float paragraphWidth;

    public HamiltonWritings(String visibleWriting, String restOfWriting){
        System.out.println("test");
        this.visibleWriting = visibleWriting;
        this.restOfWriting = restOfWriting;
        this.gameFont = gameFont;
        generator = new FreeTypeFontGenerator(Gdx.files.internal("JustAnotherHand.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 20;
        parameter.color = Color.BLACK;
        gameFont = generator.generateFont(parameter);
        //generating a glyph layout to get the length of the string so i can center it
        glyphLayout = new GlyphLayout();;
        glyphLayout.setText(gameFont,visibleWriting);
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
        gameFont.draw(batch, visibleWriting, Gdx.graphics.getWidth()/2 - paragraphWidth/2, Gdx.graphics.getHeight() - Gdx.graphics.getHeight()/6);
    }

    public void setVisibleWriting(String visibleWriting){
        this.visibleWriting = visibleWriting;
    }

    public String getVisibleWriting(){
        return visibleWriting;
    }

    public void setRestOfWriting(String restOfWriting){
        this.restOfWriting = restOfWriting;
    }

    public String getRestOfWriting(){
        return restOfWriting;
    }

    public void dispose(){
        generator.dispose();
    }

}
