package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.toshkaraf.MainGame;

import helpers.GameInfo;

/**
 * Created by Антон on 16.07.2016.
 */
public class PrizeScreen extends ScreenAdapter {
    private Viewport gameViewport;
    private Stage stage;
    MainGame game;
    Sprite background;
    Image prizeImage;


    public PrizeScreen(final MainGame game, Image prizeImage) {
        this.game = game;
        this.prizeImage = prizeImage;

        gameViewport = new StretchViewport(GameInfo.WORLD_WIDTH, GameInfo.WORLD_HEIGHT,
                new OrthographicCamera());
        stage = new Stage(gameViewport, new SpriteBatch());

//        background = new Sprite(new Texture(Gdx.files.internal("Backgrounds/background_1.jpg")));
        prizeImage.setPosition(0, 0);
        prizeImage.setSize(GameInfo.WORLD_WIDTH, GameInfo.WORLD_HEIGHT);
//        background.setSize(GameInfo.WORLD_WIDTH, GameInfo.WORLD_HEIGHT);
        prizeImage.addListener(new ClickListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new HorisontalTetris(game));
                return true;
            }
        });
        stage.addActor(prizeImage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.getBatch().begin();
        game.getBatch().end();
        stage.draw();
    }

    @Override
    public void dispose(){
        stage.dispose();
    }
}
