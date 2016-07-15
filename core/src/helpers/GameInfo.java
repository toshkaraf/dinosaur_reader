package helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.toshkaraf.MainGame;

public class GameInfo {

    public static BitmapFont SYLLABLE_FONT,NAME_FONT;

    public static final int WORLD_WIDTH = 800;
    public static final int WORLD_HEIGHT = 480;

    public static final int MAX_SCORE = 2;

    public static final float RESIZE_PROPORTION_X = (float)Gdx.graphics.getWidth()/(float)WORLD_WIDTH;
    public static final float RESIZE_PROPORTION_Y = (float)Gdx.graphics.getHeight()/(float)WORLD_HEIGHT;

    public static final int TETRIS_FULL_BACKGROUND_HEIGHT = 2652;

    public static final float HIGH_OF_PICTURE_CARD = 120;
    public static final float WIDTH_OF_PICTURE_CARD = 195;

    public static final float HIGH_OF_SYLLABLE_CARD = 100;
    public static final float WIDTH_OF_SYLLABLE_CARD = 175;

    public static final float STEP_FOR_TETRIS_X = 5;
    public static final float SLOW_STEP_FOR_TETRIS_Y = 20f;
    public static final float FAll_STEP_FOR_TETRIS_Y = 20f;

    public static final float STEP_FOR_TETRIS_Y = GameInfo.HIGH_OF_PICTURE_CARD;
    public static final float SLOW_STEP_FOR_TETRIS_X = 0.3f;
    public static final float FAll_STEP_FOR_TETRIS_X = 20f;

    public static final int FINFALE_DATE_OF_LAST_PRESIDENT = 2017;
    public static final int INITIAL_DATE_OF_FIRST_PRESIDENT = 1789;

    public static final int START_X_POSITION_OF_TETRIS_PLAYER = Gdx.graphics.getWidth() / 20;

    public static final float STEP_FOR_TETRIS_CARDS_X = 10;

    public GameInfo(MainGame game) {
//        SYLLABLE_FONT = game.getAssetManager().get("fonts/CardArialFont.fnt");
//        SYLLABLE_FONT = new BitmapFont(Gdx.files.internal("fonts/CardArialFont.fnt"));
        SYLLABLE_FONT = MyFontGenerator.getFont("fonts/arial.ttf", 100);
        NAME_FONT = MyFontGenerator.getFont("fonts/arial.ttf", 30);

    }
}
