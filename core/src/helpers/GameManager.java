package helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import models.WordToRead;

/**
 * Created by Антон on 03.06.2016.
 */
public class GameManager {

    public int life = 3;
    public int score = 100;
    private Music music;
    public Array<String> prizeNamesArray = new Array<String>();

    public enum RenderMode {PrepareField, Portrait, ShowSyllables, PlayGame, ShowPrise, PullPictureCards, MoveCamToStartPosition, ShowReviewPanel, HideReviewPanel}

    public enum TypeOfCard {BlueDate, RedDate, BlueName, RedName}

    private static GameManager ourInstance = new GameManager();
    //    public WordToRead[] wordsForQuestion = new WordToRead[4];
    private static Json json = new Json();
    private FileHandle fileHandle = Gdx.files.local("bin/GameData.json");
    public Array<WordToRead> wordsForQuestion = new Array<WordToRead>();
    public static Array<WordToRead> WORDS_ARRAY;
    //    public static int firstPresidentInRange; // number in array (from 0)
//    public static int lastPresidentInRange; // number in array
    public int currentRightWord; // number in array
    public int quantityOfSyllables = 0;
    private String syllablesFileName;
    private String prizeFolderName = "dinosaurs";
    public static RenderMode renderMode;
    public GameData gameData = new GameData();
    public Sound[] rightSounds = new Sound[6];
    public Sound[] wrongSounds = new Sound[6];
    int soundsCounter = 0;

    private GameManager() {
    }

    public void initializeGameData() {
        if (!fileHandle.exists()) {
            gameData = new GameData();
            gameData.setHighScore(0);
            gameData.setSounds(true);
            gameData.setMusicOn(true);
            saveData();
        } else
            loadData();

        if (GameManager.getInstance().gameData.isMusicOn())
            GameManager.getInstance().playMusic();
        if (GameManager.getInstance().gameData.isSounds())
            GameManager.getInstance().initSounds();
    }

    public void saveData() {
        if (gameData != null) {
            fileHandle.writeString(Base64Coder.encodeString(json.prettyPrint(gameData)), false);
        }
    }

    public void loadData() {
        gameData = json.fromJson(GameData.class, Base64Coder.decodeString(fileHandle.readString()));
    }

    public void setQuantityOfSyllables(int quantityOfSyllables) {
        this.quantityOfSyllables = quantityOfSyllables;
        switch (quantityOfSyllables) {
            case 2:
                syllablesFileName = "two_syllables";
                break;
            case 3:
                syllablesFileName = "three_syllables";
// case 4:
//                syllablesFileName = "four_syllables";
        }
    }

    public String getSyllablesFileName() {
        return syllablesFileName;
    }

    private Array<WordToRead> initializeWordsArray(int quantityOfSyllables) {
        initPrizePictureArray();
        Array<WordToRead> wordsArray = new Array<WordToRead>();
        Array<JsonValue> list = json.fromJson(Array.class, Gdx.files.internal("data/" + syllablesFileName + ".json"));
        for (JsonValue v : list) {
            wordsArray.add(json.readValue(WordToRead.class, v));
            System.out.println(wordsArray.get(0).getName());
        }
        return wordsArray;
    }

    public void initSounds() {
        for (int i = 0; i < 6; i++) {
            rightSounds[i] = Gdx.audio.newSound(Gdx.files.internal("Sounds/right_answer/" + i + ".wav"));
            wrongSounds[i] = Gdx.audio.newSound(Gdx.files.internal("Sounds/wrong_answer/" + i + ".wav"));
        }
    }

    public void disposeSounds() {
        for (int i = 0; i < 6; i++) {
            rightSounds[i].dispose();
            wrongSounds[i].dispose();
        }
    }

    public Sound getRightSound() {
        if (soundsCounter < 5) soundsCounter++;
        else soundsCounter = 0;
        return rightSounds[soundsCounter];
    }

    public Sound getWrongSound() {
        if (soundsCounter < 5) soundsCounter++;
        else soundsCounter = 0;
        return wrongSounds[soundsCounter];
    }

    public void initNewGame() {
        WORDS_ARRAY = initializeWordsArray(quantityOfSyllables);
        initNewTask();
    }

    public boolean initNewTask() {
        renderMode = RenderMode.PrepareField;
        initWordsListForQuestionsArrayAndRightWord();
        return true;
    }

    public void initWordsListForQuestionsArrayAndRightWord() {
        WORDS_ARRAY.shuffle();
        WordToRead word = WORDS_ARRAY.get(0);
        wordsForQuestion.clear();
        wordsForQuestion.add(word);
        for (int i = 1; i <= 3; i++)
            wordsForQuestion.add(WORDS_ARRAY.get(i));
        wordsForQuestion.shuffle();
        currentRightWord = wordsForQuestion.indexOf(word, true);
    }

    public void clearRightWord() {
        WORDS_ARRAY.removeValue(wordsForQuestion.get(currentRightWord), true);
    }


    public void initPrizePictureArray() {
        prizeNamesArray.clear();
        String fileName;
        fileName = "data/dinosaurs.json";
        prizeNamesArray = json.fromJson(Array.class, Gdx.files.internal(fileName));

    }

    //    public static void setLastPresidentInRange(int lastPresidentInRange) {
//        GameManager.lastPresidentInRange = lastPresidentInRange - 1;
//    }
//
//    public static void setFirstPresidentInRange(int firstPresidentInRange) {
//        GameManager.firstPresidentInRange = firstPresidentInRange - 1;
//
//    }
//

//
//    public static boolean setNewCurrentPresident(boolean isRightAnswer) {
//        if (wordsForQuestion.size > 0) {
//            if (isRightAnswer) {
//                GameManager.currentRightWord = wordsForQuestion.removeIndex(0);
//                System.out.println(GameManager.currentRightWord + 1);
//                return true;
//            } else {
//                wordsForQuestion.add(GameManager.currentRightWord);
//                wordsForQuestion.shuffle();
//                GameManager.currentRightWord = wordsForQuestion.removeIndex(0);
//                System.out.println(GameManager.currentRightWord + 1);
//                return true;
//            }
//        } else return false;
//    }

    public Array<WordToRead> getWordsForQuestion() {
        return wordsForQuestion;
    }

//    public static Array<Integer> getCloneOfPresidentsListForQuestions() {
//        Array<Integer> tempArray = new Array<Integer>();
//        for (Integer number : wordsForQuestion) {
//            tempArray.add(number);
//        }
//        return tempArray;
//    }

    public void playMusic() {
        if (music == null) {
            music = Gdx.audio.newMusic(Gdx.files.internal("Sounds/Manhattan_Beach.mp3"));
        }

        if (!music.isPlaying()) {
            music = Gdx.audio.newMusic(Gdx.files.internal("Sounds/Manhattan_Beach.mp3"));
            music.setLooping(true);
            music.setVolume(.4f);
            music.play();
        }

    }

    public void stopMusic() {
        if (music.isPlaying()) {
            music.stop();
            music.dispose();
        }
    }


    public String getPrizeFolderName() {
        return prizeFolderName;
    }

    public void setPrizeFolderName(String prizeFolderName) {
        this.prizeFolderName = prizeFolderName;
    }

    public static GameManager getInstance() {
        return ourInstance;
    }
}
