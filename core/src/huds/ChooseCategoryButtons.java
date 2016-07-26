package huds;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.toshkaraf.MainGame;

import cards.MenuCard;
import helpers.GameInfo;
import helpers.GameManager;
import scenes.Menu;

/**
 * Created by Антон on 20.06.2016.
 */
public class ChooseCategoryButtons extends MenuButtons {

    public ChooseCategoryButtons(MainGame game) {
        super(game);
    }

    @Override
    void createAndPositionButtons() {
        button_1 = new MenuCard(blueCard, -400, GameInfo.WORLD_HEIGHT / 2 + 90, "Динозавры", 20);
        button_2 = new MenuCard(redCard, 800, GameInfo.WORLD_HEIGHT / 2 + 30, "Черепахи", 20);
        button_3 = new MenuCard(blueCard, -400, GameInfo.WORLD_HEIGHT / 2 - 30, "Ящерицы", 20);
        button_4 = new MenuCard(redCard, 800, GameInfo.WORLD_HEIGHT / 2 - 90, "Назад", 20);
        button_5 = new MenuCard(blueCard, -400, GameInfo.WORLD_HEIGHT / 2 - 150, "Назад", 20);
    }

    @Override
    void addAllListeners() {

        super.addAllListeners();

        button_1.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int buttons) {
                GameManager.getInstance().setPrizeFolderName("dinosaurs");
                hideMenu_startNewScreen(new Menu(game, new MainMenuButtons(game)));
                return true;
            }
        });

        button_2.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int buttons) {
                GameManager.getInstance().setPrizeFolderName("turtles");
                hideMenu_startNewScreen(new Menu(game, new MainMenuButtons(game)));
                return true;
            }
        });

        button_3.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int buttons) {
                GameManager.getInstance().setPrizeFolderName("lizards");
                hideMenu_startNewScreen(new Menu(game, new MainMenuButtons(game)));
                return true;
            }
        });

        button_4.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int buttons) {
                hideMenu_startNewScreen(new Menu(game, new MainMenuButtons(game)));
                return true;
            }
        });

        button_5.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int buttons) {
                hideMenu_startNewScreen(new Menu(game, new MainMenuButtons(game)));
                return true;
            }
        });
    }
}
