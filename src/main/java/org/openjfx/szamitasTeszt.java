package org.openjfx;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class szamitasTeszt {
    private BmiController underTest;

    @BeforeEach
    void setUp(){
        underTest = new BmiController();
    }

    @AfterEach
    void tearDown(){
        underTest = null;
    }

    @Test
    void testbinaryAddition() {
        assertEquals(23.529,underTest.szamitBMI(68,170));
    }

}