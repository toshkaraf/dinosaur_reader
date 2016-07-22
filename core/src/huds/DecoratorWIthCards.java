package huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.toshkaraf.MainGame;

import cards.PictureCard;
import helpers.GameInfo;
import helpers.GameManager;
import helpers.RenderModeAction;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

/**
 * Created by Антон on 10.06.2016.
 */
public class DecoratorWIthCards {


//    private final Sprite redCardDate, blueCardDate, purpleCardDate;

    private MainGame game;
    private Stage stage;
    Sprite greenCard;
    Sprite beigeCard;
    Sprite purpleCard;
    private Sprite backgroundSprite;
    private Viewport gameViewport;
    Group pictureCardsArray;
    GameManager.TypeOfCard[] presidentsArray = new GameManager.TypeOfCard[44];
    Group numberCardsArray;
    OrthographicCamera camera;


    public DecoratorWIthCards(MainGame game) {

        this.game = game;
        pictureCardsArray = new Group();
        TextureAtlas atlas = game.getAssetManager().get("ready_texture/president_assets.atlas");
        beigeCard = new Sprite(new Texture(Gdx.files.internal("cards/beige_card.png")));
        greenCard = new Sprite(new Texture(Gdx.files.internal("cards/green_card.png")));
//        purpleCard = new Sprite(atlas.findRegion("card_of_president_purple"));
//        redCardDate = new Sprite(atlas.findRegion("card_of_president_red_date"));
//        blueCardDate = new Sprite(atlas.findRegion("card_of_president_blue_date"));
//        purpleCardDate = new Sprite(atlas.findRegion("card_of_president_purple_date"));

        camera = new OrthographicCamera();
//        camera.setToOrtho(false, GameInfo.WORLD_WIDTH, GameInfo.WORLD_HEIGHT);
        gameViewport = new StretchViewport(GameInfo.WORLD_WIDTH, GameInfo.WORLD_HEIGHT, camera);

        stage = new Stage(gameViewport,game.getBatch());
        stage.addActor(pictureCardsArray);
//        createAndPushCards();
    }


//    void initPresidentArray() {
//        for (int president = GameManager.firstPresidentInRange; president <= GameManager.lastPresidentInRange; president++)
//            this.presidentsArray[president] = GameManager.TypeOfCard.BlueDate;
//    }

    public void createAndPushCards() {
        for (int a = 0; a < GameManager.getInstance().wordsForQuestion.size; a++) {
            if (a%2 == 0) pictureCardsArray.addActor(new PictureCard(beigeCard, a));
        else pictureCardsArray.addActor(new PictureCard(greenCard, a));

        }
        pictureCardsArray.setBounds(pictureCardsArray.getX(),pictureCardsArray.getY(),pictureCardsArray.getWidth(),pictureCardsArray.getHeight());
        pictureCardsArray.setPosition(GameInfo.WORLD_WIDTH, 0);
        pictureCardsArray.addAction(sequence(moveTo(GameInfo.WORLD_WIDTH - GameInfo.WIDTH_OF_PICTURE_CARD, 0, 1f),
                delay(1f), new RenderModeAction(GameManager.RenderMode.PlayGame)));
    }

    public void pullCards() {
        pictureCardsArray.addAction(sequence(moveTo(GameInfo.WORLD_WIDTH, 0, 1f), delay(1f), new RenderModeAction(GameManager.RenderMode.ShowPrise)));
    }

//    public void generateHints() {
//        int quantityOfHints = GameManager.quantityOfSyllables;
//        if (GameManager.quantityOfSyllables >= GameManager.getWordsForQuestion().size)
//            quantityOfHints = GameManager.getWordsForQuestion().size;
//        Array<Integer> hintArray = new Array<Integer>(quantityOfHints);
//        Array<Integer> arrayToChooseRandomHints = GameManager.getCloneOfPresidentsListForQuestions();
//        Random random = new Random();
//        int hint;
//        for (int h = 0; h <= quantityOfHints - 1; h++) {
//            do
//                hint = arrayToChooseRandomHints.removeIndex(random.nextInt(arrayToChooseRandomHints.size));
//            while (hintArray.contains(hint, true));
//            hintArray.add(hint);
//            presidentsArray[hint] = GameManager.TypeOfCard.RedDate;
//        }
//        // add right answer
//        presidentsArray[GameManager.currentRightWord] = GameManager.TypeOfCard.RedDate;
//    }

//    public void pushHintCards() {
//        for (int a = GameManager.firstPresidentInRange; a <= GameManager.lastPresidentInRange; a++) {
//            if (presidentsArray[a] == GameManager.TypeOfCard.RedDate) {
//                PictureCard actor = pictureCardsArray.findActor("blue_date_of_" + a);
//                actor.addAction(sequence(moveTo(GameInfo.WORLD_WIDTH, actor.getY(), 1f), delay(1f), new RenderModeAction(GameManager.RenderMode.PlayGame)));
//                PictureCard newActor = new PictureCard(beigeCard, GameManager.TypeOfCard.RedDate, a);
//                newActor.push();
//                pictureCardsArray.addActor(newActor);
//            }
//        }
//    }

//    public void pushRightNameCardIfRightAnswer() {
//        for (int a = GameManager.firstPresidentInRange; a <= GameManager.lastPresidentInRange; a++) {
//            if (presidentsArray[a] == GameManager.TypeOfCard.RedDate) {
//                PictureCard pullDateCard = pictureCardsArray.findActor("red_date_of_" + a);
//                pullDateCard.addAction(moveTo(GameInfo.WORLD_WIDTH, pullDateCard.getY(), 1f));
//                if (a != GameManager.currentRightWord) {
//                    PictureCard pushDateCard = pictureCardsArray.findActor("blue_date_of_" + a);
//                    pushDateCard.push();
//                } else {
//                    SyllableCard rightSyllableCard = new SyllableCard(greenCard, GameManager.currentRightWord);
//                    rightSyllableCard.addAction(sequence(moveTo(rightSyllableCard.getFinalPositionOfCard_X(), rightSyllableCard.getY(), 1f), delay(3f),
//                            new RenderModeAction(GameManager.RenderMode.MoveCamToStartPosition)));
//                    pictureCardsArray.addActor(rightSyllableCard); //push right name card
//                }
//            }
//        }
//    }


//
//    public void showRightNameCardIfWrongAnswer() {
//        for (int a = GameManager.firstPresidentInRange; a <= GameManager.lastPresidentInRange; a++) {
//            if (presidentsArray[a] == GameManager.TypeOfCard.RedDate) {
//                PictureCard pullDateCard = pictureCardsArray.findActor("red_date_of_" + a);
//                pullDateCard.addAction(moveTo(GameInfo.WORLD_WIDTH, pullDateCard.getY(), 1f));
//                if (a != GameManager.currentRightWord) {
//                    PictureCard pushDateCard = pictureCardsArray.findActor("blue_date_of_" + a);
//                    pushDateCard.push();
//                } else {
//                    SyllableCard rightSyllableCard = new SyllableCard(purpleCard, GameManager.currentRightWord);
//                    rightSyllableCard.addAction(sequence(moveTo(rightSyllableCard.getFinalPositionOfCard_X(), rightSyllableCard.getY(), 1f), delay(3f),
//                            moveTo(GameInfo.WORLD_WIDTH, rightSyllableCard.getY(), 1f),
//                            new RenderModeAction(GameManager.RenderMode.MoveCamToStartPosition)));
//                    pictureCardsArray.addActor(rightSyllableCard); //push right name card
//                }
//            }
//        }
//        // return blue date card
//        PictureCard pushRightDateCard = pictureCardsArray.findActor("blue_date_of_" + GameManager.currentRightWord);
//        pushRightDateCard.addAction(sequence(delay(3f),moveTo(pushRightDateCard.getFinalPositionOfCard_X(),pushRightDateCard.getY(),1f)));
//    }
//
//    public void renewData(boolean answer) {
//        clearPresidentCardsArrayFromRedCards();
//        if (!answer)
//            pictureCardsArray.findActor("name_of_" + GameManager.currentRightWord).remove();
//        initPresidentArray();
//    }
//
//    private void clearPresidentCardsArrayFromRedCards() {
//        for (int a = GameManager.firstPresidentInRange; a <= GameManager.lastPresidentInRange; a++) {
//            if (presidentsArray[a] == GameManager.TypeOfCard.RedDate)
//                pictureCardsArray.findActor("red_date_of_" + a).remove();
//        }
//    }

    public Stage getStage() {
        return stage;
    }

    public Sprite getBackgroundSprite() {
        return backgroundSprite;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }


}
