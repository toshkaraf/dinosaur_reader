package huds;

//import com.awesometuts.jackthegiant.GameMain;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.toshkaraf.MainGame;

import cards.SyllableCard;
import helpers.GameInfo;
import helpers.GameManager;
import helpers.RenderModeAction;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;


/**
 * Created by Fahir on 5/1/16.
 */
public class SyllablePanel {

    private MainGame game;
    private Stage stage;
    private Viewport gameViewport;
    private Group syllableCards;
    Sprite bg = new Sprite(new Texture("cards/card_of_syllable_beige.png"));


    public SyllablePanel(MainGame game) {
        this.game = game;

        gameViewport = new StretchViewport(GameInfo.WORLD_WIDTH, GameInfo.WORLD_HEIGHT,
                new OrthographicCamera());
        stage = new Stage(gameViewport, game.getBatch());

        createSyllablePanel();
        stage.addActor(syllableCards);
    }

    private void createSyllablePanel() {
        syllableCards = new Group();
        for (int i = 0; i <= GameManager.getInstance().quantityOfSyllables - 1; i++) {
            syllableCards.addActor(new SyllableCard(bg, i));
        }
    }

    public void changeSyllableForword(int numberOfSillable) {
            SyllableCard firstCard = syllableCards.findActor(String.valueOf(numberOfSillable));
            SyllableCard secondCard = syllableCards.findActor(String.valueOf(numberOfSillable + 1));
            firstCard.addAction(moveTo(-firstCard.getWidth(), firstCard.getY(), .3f));
            secondCard.addAction(moveTo(secondCard.getFinalPositionOfCard_X(), secondCard.getY(), .3f));
    }

    public void changeSyllableBack(int numberOfSillable) {
        SyllableCard firstCard = syllableCards.findActor(String.valueOf(numberOfSillable));
        SyllableCard secondCard = syllableCards.findActor(String.valueOf(numberOfSillable - 1));
        firstCard.addAction(moveTo(GameInfo.WORLD_WIDTH, firstCard.getY(), .3f));
        secondCard.addAction(moveTo(secondCard.getFinalPositionOfCard_X(), secondCard.getY(), .3f));

    }

    public void pushFirstSyllable(int numberOfSillable) {
        SyllableCard card = syllableCards.findActor(String.valueOf(numberOfSillable));
        card.addAction(moveTo(card.getFinalPositionOfCard_X(), card.getY(), .3f));
    }

    public void pullLastSyllable(int numberOfSillable) {
        SyllableCard card = syllableCards.findActor(String.valueOf(numberOfSillable));
        card.addAction(sequence(moveTo(-card.getWidth(), card.getY(), .3f), delay(.5f),
                new RenderModeAction(GameManager.RenderMode.PrepareField)));
    }

    public Stage getStage() {
        return this.stage;
    }

} // UI Hud














































