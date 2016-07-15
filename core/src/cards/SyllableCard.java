package cards;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import helpers.GameInfo;
import helpers.GameManager;

/**
 * Created by Антон on 09.06.2016.
 */
public class SyllableCard extends Table {

    float finalPositionOfCard_X;
    float initialPositionOfCard_X;

    public SyllableCard(Sprite bg, int numberOfSyllable) {
        super();
        setName(String.valueOf(numberOfSyllable));
        bg.setSize(GameInfo.WIDTH_OF_SYLLABLE_CARD, GameInfo.HIGH_OF_SYLLABLE_CARD);
        setBackground(new SpriteDrawable(bg));
        setBounds(getX(), getY(), bg.getWidth(), bg.getHeight());
        add(new Label(GameManager.getInstance().wordsForQuestion[GameManager.getInstance().currentRightWord].getSyllables()[numberOfSyllable],
                new Label.LabelStyle(GameInfo.SYLLABLE_FONT, Color.WHITE)));

        switch (numberOfSyllable) {
            case 0:
                initialPositionOfCard_X = -getWidth();
                break;
            case 1:
                initialPositionOfCard_X = GameInfo.WORLD_WIDTH;
                break;
            case 2:
                initialPositionOfCard_X = -getWidth();
                break;
            case 3:
                initialPositionOfCard_X = GameInfo.WORLD_WIDTH;
        }

        setPosition(initialPositionOfCard_X, GameInfo.WORLD_HEIGHT / 2 - GameInfo.HIGH_OF_SYLLABLE_CARD / 2);

        switch (GameManager.getInstance().quantityOfSyllables) {
            case 2:
                finalPositionOfCard_X = 215 + numberOfSyllable * 195;
                break;
            case 3:
                finalPositionOfCard_X = 117 + numberOfSyllable * 195;
                break;
            case 4:
                finalPositionOfCard_X = 20 + numberOfSyllable * 195;
        }

    }

    public float getFinalPositionOfCard_X() {
        return finalPositionOfCard_X;
    }

    public float getInitialPositionOfCard_X() {
        return initialPositionOfCard_X;
    }
}