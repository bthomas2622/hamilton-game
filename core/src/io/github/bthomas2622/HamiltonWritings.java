package io.github.bthomas2622;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

/**
 * Created by bthom on 2/10/2017.
 */

public class HamiltonWritings extends Actor {
    //font variables
    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    GlyphLayout glyphLayout;
    Array<String> totalWork;
    BitmapFont gameFont;
    int currentParagraph = 0;
    int numberOfParagraphs;
    float paragraphWidth;

    public HamiltonWritings(Array<String> totalWork){
        System.out.println("test");
        this.totalWork = totalWork;
        numberOfParagraphs = totalWork.size - 1;
        generator = new FreeTypeFontGenerator(Gdx.files.internal("JustAnotherHand.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 20;
        parameter.color = Color.BLACK;
        gameFont = generator.generateFont(parameter);
        //generating a glyph layout to get the length of the string so i can center it
        glyphLayout = new GlyphLayout();;
        glyphLayout.setText(gameFont,totalWork.get(currentParagraph));
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
        gameFont.draw(batch, totalWork.get(currentParagraph), Gdx.graphics.getWidth()/2 - paragraphWidth/2, Gdx.graphics.getHeight() - Gdx.graphics.getHeight()/6);
    }

    public void checkTyped(char character){
        if (totalWork.get(currentParagraph).charAt(0) == character) {
            totalWork.set(currentParagraph, totalWork.get(currentParagraph).substring(1));
        }
    }

    public boolean nextParagraph(){
        if (currentParagraph == numberOfParagraphs){
            return true;
        } else {
            currentParagraph++;
            return false;
        }
    }

    public String getVisibleWriting(){
        return totalWork.get(currentParagraph);
    }

    public void setTotalWork(Array<String> totalWork){
        this.totalWork = totalWork;
    }

    public Array<String> getTotalWork(){
        return totalWork;
    }

    public void dispose(){
        generator.dispose();
        gameFont.dispose();
    }

}
