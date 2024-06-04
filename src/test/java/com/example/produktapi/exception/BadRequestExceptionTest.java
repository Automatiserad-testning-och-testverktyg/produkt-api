package com.example.produktapi.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BadRequestExceptionTest {

    @Test
    @DisplayName("Make sure exception message is set correctly")
    void testExceptionMessage() {
        String errorMessage = "This is an exception message";
        BadRequestException instance = assertThrows(BadRequestException.class, () -> {
            throw new BadRequestException(errorMessage);
        });

        String actualMessage = instance.getMessage();

        Assertions.assertEquals(errorMessage, actualMessage);
    }

    @Test
    @DisplayName("Make sure exception message handles empty strings")
    void testExceptionMessageWithEmptyString() {
        String errorMessage = "";
        BadRequestException instance = assertThrows(BadRequestException.class, () -> {
            throw new BadRequestException(errorMessage);
        });

        String actualMessage = instance.getMessage();

        Assertions.assertEquals(errorMessage, actualMessage);
    }

    @Test
    @DisplayName("Make sure exception message handles null")
    void testExceptionMessageWithNull() {
        BadRequestException instance = assertThrows(BadRequestException.class, () -> {
            throw new BadRequestException(null);
        });

        String actualMessage = instance.getMessage();

        Assertions.assertNull(actualMessage);
    }
}