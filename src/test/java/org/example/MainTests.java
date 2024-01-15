package org.example;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.example.Main.wordIsValid;
import static org.junit.jupiter.api.Assertions.*;

class MainTests {

    @ParameterizedTest
    @MethodSource("dataProvider")
    void myParameterizedTest1(String input, boolean expected) {
        // Тестовая логика

        Assertions.assertEquals(expected, wordIsValid(input));
    }

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of("\"123123\"",true),
                Arguments.of("\"123\"123\"", false),
                Arguments.of("\"12312wwww\"", true));
    }

}