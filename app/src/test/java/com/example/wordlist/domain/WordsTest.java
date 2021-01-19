package com.example.wordlist.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WordsTest {

    @Test
    public void getWordName() {
        Words word = new Words("Hallo","Hi","hallo allemaal");
        assertEquals("Hallo",word.getWordName());
    }

    @Test
    public void getWordMeaning() {
        Words word = new Words("hi","hallo","hi everybody");
        assertEquals("hallo",word.getWordMeaning());
    }
    @Test
    public void getWordType() {
        Words word = new Words("Hallo","Hi","hallo allemaal");
        assertEquals("hallo allemaal",word.getWordType());
    }
}