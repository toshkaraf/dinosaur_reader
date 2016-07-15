package huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.toshkaraf.MainGame;

import helpers.GameInfo;
import helpers.GameManager;

/**
 * Created by Антон on 15.07.2016.
 */
public class PrizeScreen extends ScreenAdapter {

    private final Viewport gameViewport;
    private final Stage stage;
    MainGame game;
    Sprite background;
    Table table;


    public PrizeScreen(MainGame game) {
        this.game = game;

        gameViewport = new StretchViewport(GameInfo.WORLD_WIDTH, GameInfo.WORLD_HEIGHT,
                new OrthographicCamera());
        stage = new Stage(gameViewport, new SpriteBatch());

        background = new Sprite(new Texture(Gdx.files.internal("Backgrounds/background_1.jpg")));
        background.setSize(GameInfo.WORLD_WIDTH, GameInfo.WORLD_HEIGHT);

        table.setFillParent(true);
        table.pad(20);
        table.defaults().pad(20);
        for (int i = 0; i < GameManager.getInstance().prizeNamesArray.size; i++){

        }
    }
}
