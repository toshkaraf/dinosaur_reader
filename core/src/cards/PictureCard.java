package cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import helpers.GameInfo;
import helpers.GameManager;

/**
 * Created by Антон on 09.06.2016.
 */
public class PictureCard extends Table {

//    float finalPositionOfCard_X;

    public PictureCard(Sprite card, int numberOfWord) {
        super();

        setName(GameManager.getInstance().wordsForQuestion[numberOfWord].getName());
        card.setSize(GameInfo.WIDTH_OF_PICTURE_CARD, GameInfo.HIGH_OF_PICTURE_CARD);
        setBackground(new SpriteDrawable(card));
//        finalPositionOfCard_X = GameInfo.WORLD_WIDTH - GameInfo.WIDTH_OF_PICTURE_CARD;
        setBounds(getX(), getY(), card.getWidth(), card.getHeight());
        setY(numberOfWord * card.getHeight());
        Sprite picture = new Sprite(new Texture(Gdx.files.internal("portraits/" + GameManager.getInstance().wordsForQuestion[numberOfWord].getName() + ".png")));
        picture.setSize(120, 100);
        add(new Image(new SpriteDrawable(picture)));
    }

//    public void push() {
//        addAction(Actions.moveTo(finalPositionOfCard_X, getY(), 1f));
//    }

//    public float getFinalPositionOfCard_X() {
//        return finalPositionOfCard_X;
//    }
}
