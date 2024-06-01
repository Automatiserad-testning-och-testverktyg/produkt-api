package com.example.produktapi.model;

import com.example.produktapi.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    //Testcases by Sweta


    private Product product = new Product("Test Product", 10.0, "Test Category", "Test Description", "https://example.com/img/placeholder.jpg");
    private Product product2 = new Product("Test Product 2", 20.0, "Test Category 2", "Test Description 2", "https://example.com/img/placeholder2.jpg");

    @Test
    void checkingTitleFor_Product1AndProduct2() {
        Assertions.assertAll(
                () ->  assertEquals("Test Product", product.getTitle()),
                () ->  assertNotEquals("Test Product 23", product2.getTitle())
        );

    }

    @Test
    void checkingPriceFor_Product1AndProduct2() {
        Assertions.assertAll(
                () ->  assertEquals(10.0, product.getPrice()),
                () ->  assertNotEquals(30.0, product2.getPrice())
        );
    }


    @Test
    void checkingCategoryFor_Product1AndProduct2() {
        Assertions.assertAll(
                () ->  assertEquals("Test Category", product.getCategory()),
                () ->  assertNotEquals("Test Category_23", product2.getCategory())
        );
    }


    @Test
    void checkingDescriptionFor_Product1AndProduct2() {
        Assertions.assertAll(
                () ->  assertEquals("Test Description", product.getDescription()),
                () ->  assertNotEquals("Test Description_23", product2.getDescription())
        );
    }

}
