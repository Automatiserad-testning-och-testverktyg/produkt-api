package com.example.produktapi.model;


import com.example.produktapi.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;



import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    //Testcases by Sweta


    private Product product1 = new Product("Test Product", 10.0, "Test Category", "Test Description", "https://example.com/img/placeholder.jpg");
    private Product product2 = new Product("Test Product 2", 20.0, "Test Category 2", "Test Description 2", "https://example.com/img/placeholder2.jpg");

    @Test
    void checkingTitleFor_product1AndProduct2() {
        Assertions.assertAll(
                () ->  assertEquals("Test Product", product1.getTitle()),
                () ->  assertNotEquals("Test Product 23", product2.getTitle())
        );

    }

    @Test
    void checkingPriceFor_product1AndProduct2() {
        Assertions.assertAll(
                () ->  assertEquals(10.0, product1.getPrice()),
                () ->  assertNotEquals(30.0, product2.getPrice())
        );
    }


    @Test
    void checkingCategoryFor_product1AndProduct2() {
        Assertions.assertAll(
                () ->  assertEquals("Test Category", product1.getCategory()),
                () ->  assertNotEquals("Test Category_23", product2.getCategory())
        );
    }


    @Test
    void checkingDescriptionFor_product1AndProduct2() {
        Assertions.assertAll(
                () ->  assertEquals("Test Description", product1.getDescription()),
                () ->  assertNotEquals("Test Description_23", product2.getDescription())
        );
    }

    @Test
    void checkingImageDetailFor_product1() {
        Assertions.assertEquals("https://example.com/img/placeholder.jpg",product1.getImage());
    }

    @Test //LinnBergstroem
    void changePriceProduct1() {
        product1.setPrice((double) 20.0);
        Assertions.assertEquals(20, product1.getPrice());
    }

    // Tests by Swapnal Hardikar
    @Test
    void setTitle_shouldSetTitle() {
        String expectedTitle = "New Title";
        String oldTitle = product1.getTitle();
        product1.setTitle(expectedTitle);
        Assertions.assertAll(
                () -> assertEquals(expectedTitle,product1.getTitle()),
                () -> assertNotEquals(oldTitle,product1.getTitle())
        );

    }

    @Test
    void setDescription_shouldSetDescription() {
        String oldDescription1 = product1.getDescription();
        String oldDescription2 = product2.getDescription();
        String expectedDescription1 = "New Description 1";
        String expectedDescription2 = "New Description 2";
        product1.setDescription(expectedDescription1);
        product2.setDescription(expectedDescription2);

        Assertions.assertAll(
                () -> assertEquals(expectedDescription1,product1.getDescription()),
                () -> assertEquals(expectedDescription2,product2.getDescription()),
                () -> assertNotEquals(oldDescription1,product1.getDescription()),
                () -> assertNotEquals(oldDescription2,product2.getDescription()),
                () -> assertNotEquals(product1.getDescription(),product2.getDescription())
        );
    }

}
