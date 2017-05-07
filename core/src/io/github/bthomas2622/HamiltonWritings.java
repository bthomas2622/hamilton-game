package io.github.bthomas2622;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
    String typed = "[BLUE]";
    String nontyped;
    int numberOfParagraphs;
    int currentLetter;
    float paragraphWidth;
    float textFieldWidth = 500f;
    float screenWidth;
    float screenHeight;
    boolean screenFinished = false;
    boolean notStarted = true;
    int paragraphLength;
    boolean paragraphFinished = false;

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
//        System.out.println(screenWidth);
        parameter.size = (int)(screenWidth / 102.4f);
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = .5f;
        //parameter.color = Color.BLACK;
        gameFont = generator.generateFont(parameter);
        //generating a glyph layout to get the length of the string so i can center it
        glyphLayout = new GlyphLayout();;
        glyphLayout.setText(gameFont,totalWork.get(currentParagraph));
        paragraphWidth = glyphLayout.width;
        gameFont.getData().markupEnabled = true;
        currentLetter = 13;
        for( int i = 0; i < totalWork.size; i++)
        {
            totalWork.set(i, "[BLUE][BLACK]" + totalWork.get(i));
            System.out.println(totalWork.get(i));
        }
    }
    @Override
    public void act(float delta){
        super.act(delta);
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            //screenFinished = true;
            this.nextParagraph();
        }
    }

    @Override
    public void draw(Batch batch, float alpha) {
        gameFont.draw(batch, totalWork.get(currentParagraph), screenWidth/2 - textFieldWidth/2, screenHeight - screenHeight/6, textFieldWidth, 8, true);
    }

    public void checkTyped(char character){
        if (String.valueOf(totalWork.get(currentParagraph).charAt(currentLetter)).toLowerCase().equals(String.valueOf(character).toLowerCase())) {
            typed = typed.concat(String.valueOf(totalWork.get(currentParagraph).charAt(currentLetter)));
            nontyped = nontyped.substring(0, 7) + nontyped.substring(8);
            if (notStarted){
                paragraphFinished = false;
                paragraphLength = totalWork.get(currentParagraph).length();
                totalWork.set(currentParagraph, totalWork.get(currentParagraph).substring(13));
                notStarted = false;
                System.out.println("removed");
                System.out.println("new current paragraph: " + totalWork.get(currentParagraph));
            }
//            System.out.println("typed");
//            System.out.println(typed);
//            System.out.println("nontyped");
//            System.out.println(nontyped);
//            System.out.println(currentLetter);
            currentLetter++;
            totalWork.set(currentParagraph, typed + nontyped);
            System.out.println("typed length: " + typed.length());
            System.out.println("paragraph length: " + paragraphLength);
            if (typed.length()+7 == paragraphLength){
                paragraphFinished = true;
            }
            //old method that removed the typed letter
            //totalWork.set(currentParagraph, totalWork.get(currentParagraph).substring(1));
        }
    }

    public boolean nextParagraph(){
        System.out.println("cp: " + currentParagraph);
        System.out.println("nop: " + numberOfParagraphs);
        if (currentParagraph == numberOfParagraphs){
            return true;
        } else {
            currentParagraph++;
            currentLetter = 13;
            paragraphFinished = false;
            notStarted = true;
            typed = "[BLUE]";
            nontyped = "[BLACK]".concat(totalWork.get(currentParagraph).substring(13));
            System.out.println(nontyped);
            return false;
        }
    }

    public boolean getWritingStatus(){
        return paragraphFinished;
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

    public boolean getScreenFinished(){
        return screenFinished;
    }

    public void dispose(){
        generator.dispose();
        gameFont.dispose();
    }

}
