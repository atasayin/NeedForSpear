package domain.controller;

import org.junit.jupiter.api.Test;

import java.util.BitSet;

import static org.junit.jupiter.api.Assertions.*;

class KeyboardControllerTest {
    BitSet keyBits;
    KeyboardController KC;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        keyBits = new BitSet();
        KC = new KeyboardController();

    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        keyBits = null;
        KC = null;
    }

    @org.junit.jupiter.api.Test
    void getInput() {
    }



}