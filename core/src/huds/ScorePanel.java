package huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
    Label scoreLabel, lifeLabel;
    Group scoreCoins = new Group();
    int scoreCounter = 0;

    public ScorePanel(MainGame game) {

        gameViewport = new StretchViewport(GameInfo.WORLD_WIDTH, GameInfo.WORLD_HEIGHT,
                new OrthographicCamera());
        stage = new Stage(gameViewport, game.getBatch());

        scoreLabel = new Label(String.valueOf(GameManager.getInstance().gameData.getHighScore()), new Label.LabelStyle(GameInfo.SCORE_FONT, Color.RED));
        scoreLabel.setPosition(30, GameInfo.WORLD_HEIGHT - 80);
        scoreLabel.setName("score");

        for (int i = 0; i < GameInfo.MAX_SCORE; i++) {
            Image coin = new Image(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("cards/coin.png")))));
            coin.setSize(50, 50);
            coin.setBounds(coin.getX(), coin.getY(), coin.getWidth(), coin.getHeight());
            coin.setPosition(50 + i * 100, GameInfo.WORLD_HEIGHT);
            coin.setName(String.valueOf(i));
            scoreCoins.addActor(coin);
        }
        stage.addActor(scoreCoins);
        stage.addActor(scoreLabel);
    }


    public int getScore() {
        return scoreCounter;
    }

    public void resetCounter() {
        scoreCounter = 0;
        scoreCoins.clear();
    }

    public void incrementScore() {
        Image coin = scoreCoins.findActor(String.valueOf(scoreCounter++));
        coin.addAction(moveTo(coin.getX(), 30, .5f));
        GameManager.getInstance().gameData.incrementHighScore(1);
//        scoreLabel = scoreCoins.findActor("score");
        scoreLabel.setText(String.valueOf(GameManager.getInstance().gameData.getHighScore()));
        scoreLabel.setPosition(30, GameInfo.WORLD_HEIGHT - 80);
    }

    public Stage getStage() {
        return stage;
    }
}
