package io.github.bthomas2622;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by bthom on 3/5/2017.
 */

public class DeskActor extends Actor {
    float screenWidth = Gdx.graphics.getWidth();
    float screenHeight = Gdx.graphics.getHeight();
    float actorX, actorY;
    final HamiltonGame game;
    TextureRegion deskTextureRegion;

    public DeskActor(final HamiltonGame gam){
        game = gam;
        actorX = game.aspectX/2f - game.deskTexture.getWidth()/2f;
        actorY = game.aspectY/4f;
        setBounds(actorX, actorY, game.deskTexture.getWidth(), game.deskTexture.getHeight());
        deskTextureRegion = new TextureRegion(game.deskTexture);
    }

    @Override
    public void act(float delta){
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float alpha) {
//            Color color = getColor();
//            batch.setColor(color.r, color.g, color.b, color.a * alpha);
        batch.draw(deskTextureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    public void dispose(){
    }
}
