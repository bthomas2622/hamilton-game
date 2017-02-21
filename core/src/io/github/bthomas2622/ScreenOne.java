package io.github.bthomas2622;

/**
 * Created by bthom on 1/8/2017.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;


public class ScreenOne implements Screen, InputProcessor {
    final HamiltonGame game;
    private Stage stage;
    String writingInfo = "\"Account of a Hurricane\", September 6, 1772";
    String firstSentence = "Honoured Sir, I take up my pen just to give you an imperfect account of the most dreadful hurricane that memory or any records whatever can trace, which happened here on the 31st ultimo at night.";
    String paragraph1a = "Where now, Oh! vile worm, is all thy boasted fortitude and resolution? what is become of thy arrogance and self sufficiency? Why dost thou tremble and stand aghast? How humble, how helpless, how contemptible you now appear. And for why? The jarring of the elements? The discord of clouds? Oh, impotent presumptuous fool! How darest thou offend that omnipotence, whose nod alone were sufficient to quell the destruction that hovers over thee, or crush thee into atoms?";
    String paragraph1b = "See thy wretched helpless state and learn to know thyself. Learn to know thy best support. Despise thyself and adore thy God. How sweet how unutterably sweet were now the voice of an approving conscience; Then couldst thou say hence ye idle alarms, why do I shrink? What have I to fear? A pleasing calm suspense! A short repose from calamity to end in eternal bliss? Let the earth rend. Let the planets forsake their course. Let the sun be extinguished and the heavens burst asunder. Yet what have I to dread? My staff can never be broken, in omnipotence I trust...";
    String closingParagraphA = "Yet hold, Oh, vain mortal! Check thy ill timed joy. Art thou so selfish as to exult because thy lot is happy in a season of universal woe? Hast thou no feelings for the miseries of thy fellow creatures? And art thou incapable of the soft pangs of sympathetic sorrow? Look around thee and shudder at the view. See desolation and ruin wherever thou turnest thine eye! See thy fellow creatures pale and lifeless; their bodies mangled, their souls snatched into eternity, unexpecting. Alas! perhaps unprepared! Hark the bitter groans of distress. See sickness and infirmities exposed to the inclemencies of wind and water! See tender infancy pinched with hunger and hanging on the mother's knee for food! See the unhappy mother's anxiety. Her poverty denies relief";
    String closingParagraphB = "her breast heaves with pangs of maternal pity, her heart is bursting, the tears gush down her cheeks. Oh sights of woe! Oh distress unspeakable! My heart bleeds, but I have no power to solace! Oh ye, who revel in affluence, see the afflictions of humanity and bestow your superfluity to ease them. Say not, we have suffered also, and thence withhold your compassion. What are your sufferings compared to those? Ye have still more than enough left. Act wisely. Succour the miserable and lay up a treasure in Heaven.";
    Array<String> writings = new Array<String>(true, 6);
    float fadeAlpha = 0.0f;
    HamiltonActor hamilton;
    HamiltonWritings hurricaneWritings;


    public ScreenOne(final HamiltonGame gam){
        game = gam;
        stage = new Stage(new FitViewport(1920, 1080));
        Gdx.input.setInputProcessor(stage);
        hamilton = new HamiltonActor(game);
        writings.add(writingInfo);
        writings.add(firstSentence);
        writings.add(paragraph1a);
        writings.add(paragraph1b);
        writings.add(closingParagraphA);
        writings.add(closingParagraphB);
        hurricaneWritings = new HamiltonWritings(writings);
        stage.addActor(hamilton);
        stage.addActor(hurricaneWritings);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta){
        //make sure music is loaded
//        if (loaded == false){
//            loaded = startMusic();
//        }
        Gdx.gl.glClearColor(1f - fadeAlpha,1f - fadeAlpha,1f - fadeAlpha, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        if (hamilton.getNextScreen() && fadeAlpha < 1f){
            fadeAlpha += .01f;
            if (fadeAlpha >= 1f){
                game.setScreen(new ScreenTwoIntro(game));
            }
        }
        //Start the writing when hamilton sits down
        if (hamilton.getSeated() && hurricaneWritings.getCurrentParagraph() == 0){
            hurricaneWritings.nextParagraph();
        }
        if (hamilton.getSeated()){
            if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
                if (hurricaneWritings.nextParagraph()){

                }
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
                hamilton.setNextScreen(true);
            }
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
        if (hamilton.getSeated() == true){
            if (hurricaneWritings.getVisibleWriting().equals("")){
                if (hurricaneWritings.nextParagraph()){
                    hamilton.setFinished(true);
                    hamilton.setSeated(false);
                    System.out.println("finished");
                }
            } else {
                hurricaneWritings.checkTyped(character);
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
        dispose();
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
        hurricaneWritings.dispose();
    }
}
