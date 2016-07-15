package models;

/**
 * Created by Антон on 03.06.2016.
 */
public class WordToRead {

    String name;
    String[] syllables;

    public WordToRead() {
    }

    public WordToRead(String name, String[] syllables) {
        this.name = name;
        this.syllables = syllables;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getSyllables() {
        return syllables;
    }

    public void setSyllables(String[] syllables) {
        this.syllables = syllables;
    }
}
