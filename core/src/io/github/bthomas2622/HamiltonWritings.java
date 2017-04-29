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
    final HamiltonGame game;
    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    GlyphLayout glyphLayout;
    Array<String> totalWork;
    BitmapFont gameFont;
    int currentParagraph = 0;
    int numberOfParagraphs;
    float paragraphWidth;
    float textFieldWidth = 500f;
    float screenWidth;
    float screenHeight;

    public HamiltonWritings(Array<String> totalWork, final HamiltonGame gam){
        game = gam;
        screenWidth = game.aspectX;
        screenHeight = game.aspectY;
        textFieldWidth = screenWidth / 3.84f;
        //System.out.println("test");
        this.totalWork = totalWork;
        numberOfParagraphs = totalWork.size - 1;
        generator = new FreeTypeFontGenerator(Gdx.files.internal("PatrickHand-Regular.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        System.out.println(screenWidth);
        parameter.size = (int)(screenWidth / 102.4f);
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = .5f;
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
    }

    @Override
    public void draw(Batch batch, float alpha) {
        gameFont.draw(batch, totalWork.get(currentParagraph), screenWidth/2 - textFieldWidth/2, screenHeight - screenHeight/6, textFieldWidth, 8, true);
    }

    public void checkTyped(char character){
        if (String.valueOf(totalWork.get(currentParagraph).charAt(0)).toLowerCase().equals(String.valueOf(character).toLowerCase())) {
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

    public int getCurrentParagraph(){
        return currentParagraph;
    }

    public void dispose(){
        generator.dispose();
        gameFont.dispose();
    }

}
