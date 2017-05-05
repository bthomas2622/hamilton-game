package io.github.bthomas2622;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by bthom on 2/10/2017.
 */

public class ScreenTwo implements Screen, InputProcessor {
    final HamiltonGame game;
    private Stage stage;
    String paragraph0 = "\"A Full Vindication of the Measures of the Congress\", December 15, 1774";
    String paragraph1a = "Friends and Countrymen, It was hardly to be expected that any man could be so presumptuous, as openly to controvert the equity, wisdom, and authority of the measures, adopted by the congress: an assembly truly respectable on every account! Whether we consider the characters of the men, who composed it; the number, and dignity of their constituents, or the important ends for which they were appointed. But, however improbable such a degree of presumption might have seemed, we find there are some, in whom it exists.";
    String paragraph1b = "Attempts are daily making to diminish the influence of their decisions, and prevent the salutary effects, intended by them. The impotence of such insidious efforts is evident from the general indignation they are treated with; so that no material ill-consequences can be dreaded from them. But lest they should have a tendency to mislead, and prejudice the minds of a few; it cannot be deemed altogether useless to bestow some notice upon them...";
    String paragraph2 = "Under the auspices of tyranny, the life of the subject is often sported with; and the fruits of his daily toil are consumed in oppressive taxes, that serve to gratify the ambition, avarice and lusts of his superiors. Every court minion riots in the spoils of the honest labourer, and despises the hand by which he is fed. The page of history is replete with instances that loudly warn us to beware of slavery.";
    String paragraph3 = "Rome was the nurse of freedom. She was celebrated for her justice and lenity; but in what manner did she govern her dependent provinces? They were made the continual scene of rapine and cruelty. From thence let us learn, how little confidence is due to the wisdom and equity of the most exemplary nations.";
    String paragraph4a = "Should Americans submit to become the vassals of their fellow-subjects in Great Britain, their yoke will be peculiarly grievous and intolerable. A vast majority of mankind is intirely biassed by motives of self-interest. Most men are glad to remove any burthens off themselves, and place them upon the necks of their neighbours. We cannot therefore doubt, but that the British Parliament, with a view to the ease and advantage of itself, and its constituents, would oppress and grind the Americans as much as possible.";
    String paragraph4b = "Jealousy would concur with selfishness; and for fear of the future independence of America, if it should be permitted to rise to too great a height of splendor and opulence, every method would be taken to drain it of its wealth and restrain its prosperity. We are already suspected of aiming at independence, and that is one principal cause of the severity we experience. The same cause will always operate against us, and produce an uniform severity of treatment...";
    String paragraph5 = "All I ask is, that you will judge for yourselves. I don’t desire you to take my opinion or any man’s opinion, as the guide of your actions. I have stated a number of plain arguments; I have supported them with several well-known facts: It is your business to draw a conclusion and act accordingly... May God give you wisdom to see what is your true interest, and inspire you with becoming zeal for the cause of virtue and mankind. A Friend to America.";

    Array<String> writings = new Array<String>(true, 8);
    float fadeAlpha = 0.0f;
    HamiltonActor hamilton;
    HamiltonWritings vindicationWritings;


    public ScreenTwo(final HamiltonGame gam){
        game = gam;
        stage = new Stage(new FitViewport(game.aspectX, game.aspectY));
        Gdx.input.setInputProcessor(stage);
        hamilton = new HamiltonActor(game);
        writings.add(paragraph0);
        writings.add(paragraph1a);
        writings.add(paragraph1b);
        writings.add(paragraph2);
        writings.add(paragraph3);
        writings.add(paragraph4a);
        writings.add(paragraph4b);
        writings.add(paragraph5);
        vindicationWritings = new HamiltonWritings(writings, game);
        stage.addActor(hamilton);
        stage.addActor(vindicationWritings);
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
                game.setScreen(new ScreenThreeIntro(game));
            }
        }
        //Start the writing when hamilton sits down
        if (hamilton.getSeated() && vindicationWritings.getCurrentParagraph() == 0){
            vindicationWritings.nextParagraph();
        }
        if (vindicationWritings.getScreenFinished()){
            hamilton.setNextScreen(true);
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
            if (vindicationWritings.getVisibleWriting().equals("")){
                if (vindicationWritings.nextParagraph()){
                    hamilton.setFinished(true);
                    hamilton.setSeated(false);
                    System.out.println("finished");
                }
            } else {
                vindicationWritings.checkTyped(character);
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
        vindicationWritings.dispose();
    }
}
