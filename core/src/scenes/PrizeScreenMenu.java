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
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.toshkaraf.MainGame;

import helpers.GameInfo;
import helpers.GameManager;

/**
 * Created by Антон on 15.07.2016.
 */
public class PrizeScreenMenu extends ScreenAdapter {

    private Stage stage;
    MainGame game;

    public PrizeScreenMenu(MainGame game) {
        this.game = game;

        Viewport gameViewport = new StretchViewport(GameInfo.WORLD_WIDTH, GameInfo.WORLD_HEIGHT, new OrthographicCamera());
        stage = new Stage(gameViewport, new SpriteBatch());

        initPictureTable();
        Gdx.input.setInputProcessor(stage);
    }

    private void initPictureTable() {
        Sprite background = new Sprite(new Texture(Gdx.files.internal("Backgrounds/background_1.jpg")));
        background.setSize(GameInfo.WORLD_WIDTH, GameInfo.WORLD_HEIGHT);

        Table pictureTable = new Table();
        pictureTable.setFillParent(true);
        pictureTable.setBackground(new SpriteDrawable(background));
        pictureTable.pad(20);
        pictureTable.defaults().pad(20);
        for (int i = 0; i < GameManager.getInstance().prizeNamesArray.size; i++) {
            Table pictureCard = new Table();
            Sprite spriteBackground = new Sprite(new Texture(Gdx.files.internal("cards/card_of_syllable_beige.png")));
            spriteBackground.setSize(150, 100);
            pictureCard.setBackground(new SpriteDrawable(spriteBackground));
            Sprite spritePicture = new Sprite(new Texture(Gdx.files.internal("data/prize_pictures/" +
                    GameManager.getInstance().getPrizeFolderName() + "/" + i + ".jpg")));
            spritePicture.setSize(120,80);
            final Image image = new Image(new SpriteDrawable(spritePicture));
            image.setName(String.valueOf(i));
            pictureCard.add(image);
            pictureCard.addListener(new ClickListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    game.setScreen(new PrizeScreen(game, image));
                    return true;
                }
            });
            if (i >= 4 & i % 4 == 0) pictureTable.row();
            pictureTable.add(pictureCard);


        }
        stage.addActor(pictureTable);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
