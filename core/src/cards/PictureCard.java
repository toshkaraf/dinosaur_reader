package cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import helpers.GameInfo;
import helpers.GameManager;

/**
 * Created by Антон on 09.06.2016.
 */
public class PictureCard extends Table {

    public PictureCard(Sprite card, int numberOfWord) {
        super();

        setName(GameManager.getInstance().wordsForQuestion.get(numberOfWord).getName());
        card.setSize(GameInfo.WIDTH_OF_PICTURE_CARD, GameInfo.HEIGHT_OF_PICTURE_CARD);
        setBackground(new SpriteDrawable(card));
        setBounds(getX(), getY(), card.getWidth(), card.getHeight());
        setY(numberOfWord * card.getHeight());
        Sprite picture = new Sprite(new Texture(Gdx.files.internal("pictures/" +
                GameManager.getInstance().getSyllablesFileName()+ "/" + GameManager.getInstance().wordsForQuestion.get(numberOfWord).getName() + ".png")));
        picture.setSize(120, 100);
        add(new Image(new SpriteDrawable(picture)));
    }

}
