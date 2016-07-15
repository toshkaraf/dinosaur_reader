package huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.toshkaraf.MainGame;

import helpers.GameInfo;
import helpers.GameManager;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;

/**
 * Created by Антон on 06.07.2016.
 */
public class ScorePanel {

    Viewport gameViewport;
    Stage stage;
    Table scoreTable;
    Label scoreLabel, lifeLabel;
        Group scoreCoins = new Group();
//    Array<Image> scoreCoins = new Array<Image>();
    int scoreCounter = 0;

    public ScorePanel(MainGame game) {

        gameViewport = new StretchViewport(GameInfo.WORLD_WIDTH, GameInfo.WORLD_HEIGHT,
                new OrthographicCamera());
        stage = new Stage(gameViewport, game.getBatch());

        for (int i = 0; i < GameInfo.MAX_SCORE; i++) {
            Image coin = new Image(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("cards/coin.png")))));
            coin.setSize(50, 50);
            coin.setBounds(coin.getX(),coin.getY(),coin.getWidth(),coin.getHeight());
            coin.setPosition(50 + i*100, GameInfo.WORLD_HEIGHT);
            coin.setName(String.valueOf(i));
            scoreCoins.addActor(coin);
        }
        stage.addActor(scoreCoins);



//        scoreTable = new Table().top().padTop(20);
//        scoreTable.setFillParent(true);
//
//        scoreTable.add(lifeLabel).spaceRight(100);
//        scoreTable.add(scoreLabel);
//        scoreTable.setBounds(scoreTable.getX(),scoreTable.getY(),scoreTable.getWidth(),scoreTable.getHeight());
//        stage.addActor(scoreTable);
    }


    public int getScore() {
        return scoreCounter;
    }

    public void resetCounter(){ scoreCounter = 0;
    scoreCoins.clear();
    }

    public void incrementScore() {
        Image coin = scoreCoins.findActor(String.valueOf(scoreCounter++));
        coin.addAction(moveTo(coin.getX(), 30, .5f));
    }

    public void decrementLife() {
        GameManager.getInstance().life--;
        lifeLabel.setText(String.valueOf(GameManager.getInstance().life));
    }

    public Stage getStage() {
        return stage;
    }
}
