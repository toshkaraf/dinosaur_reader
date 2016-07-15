package huds;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.toshkaraf.MainGame;

import cards.MenuCard;
import helpers.GameInfo;
import helpers.GameManager;
import scenes.HorisontalTetris;


/**
 * Created by Антон on 20.06.2016.
 */
public class MainMenuButtons extends MenuButtons {

    public MainMenuButtons(MainGame game) {
        super(game);
    }

    @Override
    void createAndPositionButtons() {
        button_1 = new MenuCard(redCard, -400, GameInfo.WORLD_HEIGHT / 2 + 90, "СЛОВА ИЗ ДВУХ СЛОГОВ", 20);
        button_2 = new MenuCard(blueCard, 800, GameInfo.WORLD_HEIGHT / 2 + 30, "СЛОВА ИЗ ТРЕХ СЛОГОВ", 20);
        button_3 = new MenuCard(redCard, -400, GameInfo.WORLD_HEIGHT / 2 - 30, "КАТЕГОРИЯ ПРИЗОВ", 20);
        button_4 = new MenuCard(blueCard, 800, GameInfo.WORLD_HEIGHT / 2 - 90, "ВКЛ. / ВЫКЛ. МУЗЫКУ", 20);
        button_5 = new MenuCard(redCard, -400, GameInfo.WORLD_HEIGHT / 2 - 150, "ВКЛ. / ВЫКЛ. МУЗЫКУ", 20);

    }

    @Override
    void addAllListeners() {
        super.addAllListeners();

        button_1.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int buttons) {
                GameManager.getInstance().quantityOfSyllables = 2;
                hideMenu_startNewScreen(new HorisontalTetris(game));
                return true;
            }
        });

        button_2.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int buttons) {
                GameManager.getInstance().quantityOfSyllables = 3;
                hideMenu_startNewScreen(new HorisontalTetris(game));
                return true;
            }
        });

        button_3.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int buttons) {
                hideMenu_startNewScreen(new HorisontalTetris(game));
                return true;
            }
        });

        button_4.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int buttons) {
                if (GameManager.getInstance().gameData.isMusicOn()) {
                    GameManager.getInstance().gameData.setMusicOn(false);
                    GameManager.getInstance().stopMusic();
                } else {
                    GameManager.getInstance().gameData.setMusicOn(true);
                    GameManager.getInstance().playMusic();
                }
                GameManager.getInstance().saveData();
                return true;
            }
        });

        button_5.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int buttons) {
                if (GameManager.getInstance().gameData.isSounds()) {
                    GameManager.getInstance().gameData.setSounds(false);
                    GameManager.getInstance().disposeSounds();
                } else {
                    GameManager.getInstance().gameData.setSounds(true);
                    GameManager.getInstance().initSounds();
                }
                return true;
            }
        });
    }

}