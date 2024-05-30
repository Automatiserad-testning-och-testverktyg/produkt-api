package com.example.produktapi.controller;

import com.example.produktapi.model.Product;
import com.example.produktapi.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ProductControllerTest { // Skrivet av Jamie Blomerus

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    // AutoCloseable resource for MockitoAnnotations.openMocks()
    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void hello() {
        Assertions.assertEquals("Hello, world!", productController.hello().getBody());
    }

    @Test
    void getAllProducts() {
        Product product = new Product("Test Product", 10.0, "Test Category", "Test Description", "https://example.com/img/placeholder.jpg");
        Product product2 = new Product("Test Product 2", 20.0, "Test Category 2", "Test Description 2", "https://example.com/img/placeholder2.jpg");
        when(productService.getAllProducts()).thenReturn(List.of(product, product2));
        ResponseEntity<List<Product>> response = productController.getAllProducts();
        Assertions.assertAll(
                () -> assertEquals(2, response.getBody().size()),
                () -> assertEquals("Test Product", response.getBody().get(0).getTitle()),
                () -> assertEquals(10.0, response.getBody().get(0).getPrice()),
                () -> assertEquals("Test Category", response.getBody().get(0).getCategory()),
                () -> assertEquals("Test Description", response.getBody().get(0).getDescription()),
                () -> assertEquals("https://example.com/img/placeholder.jpg", response.getBody().get(0).getImage()),
                () -> assertEquals("Test Product 2", response.getBody().get(1).getTitle()),
                () -> assertEquals(20.0, response.getBody().get(1).getPrice()),
                () -> assertEquals("Test Category 2", response.getBody().get(1).getCategory()),
                () -> assertEquals("Test Description 2", response.getBody().get(1).getDescription()),
                () -> assertEquals("https://example.com/img/placeholder2.jpg", response.getBody().get(1).getImage())
        );
    }

    @Test
    void getAllCategories() {
        when(productService.getAllCategories()).thenReturn(List.of("Test Category", "Test Category 2"));
        ResponseEntity<List<String>> response = productController.getAllCategories();
        Assertions.assertAll(
                () -> assertEquals(2, response.getBody().size()),
                () -> assertEquals("Test Category", response.getBody().get(0)),
                () -> assertEquals("Test Category 2", response.getBody().get(1))
        );
    }

    @Test
    void getProductsByCategory() {
        Product product = new Product("Test Product", 10.0, "Test Category", "Test Description", "https://example.com/img/placeholder.jpg");
        Product product2 = new Product("Test Product 2", 20.0, "Test Category", "Test Description 2", "https://example.com/img/placeholder2.jpg");
        when(productService.getProductsByCategory("Test Category")).thenReturn(List.of(product, product2));
        ResponseEntity<List<Product>> response = productController.getProductsByCategory("Test Category");
        Assertions.assertAll(
                () -> assertEquals(2, response.getBody().size()),
                () -> assertEquals("Test Product", response.getBody().get(0).getTitle()),
                () -> assertEquals(10.0, response.getBody().get(0).getPrice()),
                () -> assertEquals("Test Category", response.getBody().get(0).getCategory()),
                () -> assertEquals("Test Description", response.getBody().get(0).getDescription()),
                () -> assertEquals("https://example.com/img/placeholder.jpg", response.getBody().get(0).getImage()),
                () -> assertEquals("Test Product 2", response.getBody().get(1).getTitle()),
                () -> assertEquals(20.0, response.getBody().get(1).getPrice()),
                () -> assertEquals("Test Category", response.getBody().get(1).getCategory()),
                () -> assertEquals("Test Description 2", response.getBody().get(1).getDescription()),
                () -> assertEquals("https://example.com/img/placeholder2.jpg", response.getBody().get(1).getImage())
        );
    }

    @Test
    void getProductById() {
        Product product = new Product("Test Product", 10.0, "Test Category", "Test Description", "https://example.com/img/placeholder.jpg");
        when(productService.getProductById(1)).thenReturn(product);
        ResponseEntity<Product> response = productController.getProductById(1);
        Assertions.assertAll(
                () -> assertEquals("Test Product", response.getBody().getTitle()),
                () -> assertEquals(10.0, response.getBody().getPrice()),
                () -> assertEquals("Test Category", response.getBody().getCategory()),
                () -> assertEquals("Test Description", response.getBody().getDescription()),
                () -> assertEquals("https://example.com/img/placeholder.jpg", response.getBody().getImage())
        );
    }
}
