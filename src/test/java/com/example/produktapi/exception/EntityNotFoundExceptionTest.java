package com.example.produktapi.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntityNotFoundExceptionTest { // Skrivet av Jamie Blomerus

    @Test
    @DisplayName("Exception message follow expected pattern")
    void testExceptionMessage() {
        int id = 27;
        EntityNotFoundException instance = assertThrows(EntityNotFoundException.class, () -> {
            throw new EntityNotFoundException(id);
        });

        String expected = "Produkt med id " + id + " hittades inte";
        String actual   = instance.getMessage();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Exception message works correctly with high numbers")
    void testExceptionMessageWithHighId() {
        int id = Integer.MAX_VALUE;
        EntityNotFoundException instance = assertThrows(EntityNotFoundException.class, () -> {
            throw new EntityNotFoundException(id);
        });

        String expected = "Produkt med id " + id + " hittades inte";
        String actual   = instance.getMessage();

        Assertions.assertEquals(expected, actual);
    }
}
