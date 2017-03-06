package io.github.bthomas2622;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by bthom on 2/10/2017.
 */

public class HamiltonActor extends Actor {
    float actorX = 0, actorY = 0;
    final HamiltonGame game;
    boolean seated = false;
    boolean finished = false;
    boolean nextScreen = false;
    TextureRegion hamiltonTextureRegion;
    float screenWidth;
    float screenHeight;

    public HamiltonActor(final HamiltonGame gam){
        game = gam;
        screenHeight = game.aspectY;
        screenWidth = game.aspectX;
        setBounds(actorX, actorY, game.hamiltonTexture.getWidth(), game.hamiltonTexture.getHeight());
        hamiltonTextureRegion = new TextureRegion(game.hamiltonTexture);
    }

    @Override
    public void act(float delta){
        super.act(delta);
        if (seated){
        } else {
            if (finished){
                if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                    if(this.getX() >= screenWidth / 2  - this.getWidth() / 2 && this.getX() < screenWidth  + this.getWidth()){
                        if (this.getX() <= screenWidth + this.getWidth() - 5){
                            this.setPosition(getX() + 5, getY());
                        }
                        else {
                            nextScreen = true;
                        }
                    }
                }
                if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                    if(this.getX() >= screenWidth / 2  - this.getWidth() / 2 && this.getX() < screenWidth + this.getWidth() - 5){
                        if (this.getX() >= screenWidth / 2  - this.getWidth() / 2 + 5){
                            this.setPosition(getX() - 5, getY());
                        }
                    }
                }
            } else {
                if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                    if(this.getX() >= 0 && this.getX() < screenWidth / 2 - this.getWidth() / 2) {
                        this.setPosition(getX() + 5, getY());
                        if (this.getX() >= screenWidth / 2 - this.getWidth() / 2) {
                            seated = true;
                        }
                    }
                }
                if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                    if(this.getX() >= 0 && this.getX() < screenWidth / 2 - this.getWidth() / 2){
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

    public void setSeated(boolean seated){
        this.seated = seated;
    }

    public boolean getSeated(){
        return seated;
    }

    public void setFinished(boolean finished){
        this.finished = finished;
    }

    public boolean getFinished(){
        return finished;
    }

    public void setNextScreen(boolean nextScreen){
        this.nextScreen = nextScreen;
    }

    public boolean getNextScreen(){
        return nextScreen;
    }

    public void dispose(){
    }
}