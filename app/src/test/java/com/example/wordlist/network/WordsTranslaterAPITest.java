package com.example.wordlist.network;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class WordsTranslaterAPITest {

    @Test
    public void testInstanceOf() {
        WordsTranslaterAPI.Data dataSubclass = new WordsTranslaterAPI.Data();
        assertThat(dataSubclass, instanceOf(WordsTranslaterAPI.class));
    }
}