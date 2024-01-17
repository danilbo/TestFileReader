package org.example;

import java.util.Objects;

public class WordPosition {
    int position;
    String word;

    public WordPosition(String word, int position) {
        this.word = word;
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordPosition that = (WordPosition) o;
        return position == that.position && Objects.equals(word, that.word);
    }

    @Override
    public String toString() {
        return "WordPosition{" +
                "position=" + position +
                ", word='" + word + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, word);
    }
}
