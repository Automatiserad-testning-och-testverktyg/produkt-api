package com.example.produktapi.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BadRequestExceptionTest {

    @Test
    @DisplayName("Exception message is set correctly")
    void testExceptionMessage() {
        String errorMessage = "This is an exception message";
        BadRequestException instance = assertThrows(BadRequestException.class, () -> {
            throw new BadRequestException(errorMessage);
        });

        String actualMessage = instance.getMessage();

        Assertions.assertEquals(errorMessage, actualMessage);
    }

    @Test
    @DisplayName("Exception message handles empty strings")
    void testExceptionMessage_emptyString() {
        String errorMessage = "";
        BadRequestException instance = assertThrows(BadRequestException.class, () -> {
            throw new BadRequestException(errorMessage);
        });

        String actualMessage = instance.getMessage();

        Assertions.assertEquals(errorMessage, actualMessage);
    }

    @Test
    @DisplayName("Exception message handles null")
    void testExceptionMessage_null() {
        BadRequestException instance = assertThrows(BadRequestException.class, () -> {
            throw new BadRequestException(null);
        });

        String actualMessage = instance.getMessage();

        Assertions.assertNull(actualMessage);
    }
}