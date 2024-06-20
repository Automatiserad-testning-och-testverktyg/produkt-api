package com.example.produktapi.service;

import com.example.produktapi.exception.BadRequestException;
import com.example.produktapi.exception.EntityNotFoundException;
import com.example.produktapi.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

public class ExceptionHandlerAdviceTest {

    private ExceptionHandlerAdvice exceptionHandlerAdvice;

    @BeforeEach
    public void setup() {
        exceptionHandlerAdvice = new ExceptionHandlerAdvice();
    }

    @Test
    @DisplayName("handleException (EntityNotFoundException)")
    void handleEntityNotFoundException() {
        //Arrange
        EntityNotFoundException entityNotFoundException = new EntityNotFoundException(0);
        //Act
        ResponseEntity<String> response = exceptionHandlerAdvice.handleException(entityNotFoundException);
        //Assert
        assertAll(
                () ->assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode()),
                () ->assertEquals("Produkt med id 0 hittades inte", response.getBody())
        );
    }

    @Test
    @DisplayName("unitTestofHandleBadRequestException")
    void handleBadRequestException() {
        //Arrange
        String title = "Example Product";
        Product product = new Product();
        product.setTitle(title);
        BadRequestException badRequestException = new BadRequestException("En produkt med titeln: "+ product.getTitle() + " finns redan");
        //Act
        ResponseEntity response = exceptionHandlerAdvice.handleException(badRequestException);
        //Assert
        assertAll(
                () -> assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode()),
                () -> assertEquals("En produkt med titeln: Example Product finns redan", response.getBody())
        );
    }

    @Test
    @DisplayName("unitTestofHandleNullPointerException")
    void handleNullPointerException() {
        //Arrange
        NullPointerException nullPointerException = new NullPointerException();
        //Act
        ResponseEntity<String> response = exceptionHandlerAdvice.handleNullPointerException(nullPointerException);
        //Assert
        assertAll(
                () -> assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode()),
                () -> assertEquals("Internal Server Error, retry or contact system admin", response.getBody())
        );
    }
}