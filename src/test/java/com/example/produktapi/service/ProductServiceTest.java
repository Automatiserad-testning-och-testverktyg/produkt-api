package com.example.produktapi.service;

import com.example.produktapi.exception.BadRequestException;
import com.example.produktapi.exception.EntityNotFoundException;
import com.example.produktapi.model.Product;
import com.example.produktapi.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductServiceTest { // Skrivet av Jamie Blomerus

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

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
    void getAllProducts() {
        Product product = new Product("Test Product", 10.0, "Test Category", "Test Description", "https://example.com/img/placeholder.jpg");
        Product product2 = new Product("Test Product 2", 20.0, "Test Category 2", "Test Description 2", "https://example.com/img/placeholder2.jpg");
        when(productRepository.findAll()).thenReturn(List.of(product, product2));

        List<Product> response = productService.getAllProducts();
        Assertions.assertAll(
                () -> assertEquals(2, response.size()),
                () -> assertEquals("Test Product", response.get(0).getTitle()),
                () -> assertEquals(10.0, response.get(0).getPrice()),
                () -> assertEquals("Test Category", response.get(0).getCategory()),
                () -> assertEquals("Test Description", response.get(0).getDescription()),
                () -> assertEquals("https://example.com/img/placeholder.jpg", response.get(0).getImage()),
                () -> assertEquals("Test Product 2", response.get(1).getTitle()),
                () -> assertEquals(20.0, response.get(1).getPrice()),
                () -> assertEquals("Test Category 2", response.get(1).getCategory()),
                () -> assertEquals("Test Description 2", response.get(1).getDescription()),
                () -> assertEquals("https://example.com/img/placeholder2.jpg", response.get(1).getImage())
        );
    }

    @Test
    void getAllCategories() {
        when(productRepository.findAllCategories()).thenReturn(List.of("Test Category", "Test Category 2"));
        List<String> response = productService.getAllCategories();
        Assertions.assertAll(
                () -> assertEquals(2, response.size()),
                () -> assertEquals("Test Category", response.get(0)),
                () -> assertEquals("Test Category 2", response.get(1))
        );
    }

    @Test
    void getProductsByCategory() {
        Product product = new Product("Test Product", 10.0, "Test Category", "Test Description", "https://example.com/img/placeholder.jpg");
        Product product2 = new Product("Test Product 2", 20.0, "Test Category", "Test Description 2", "https://example.com/img/placeholder2.jpg");
        when(productRepository.findByCategory("Test Category")).thenReturn(List.of(product, product2));

        List<Product> response = productService.getProductsByCategory("Test Category");
        Assertions.assertAll(
                () -> assertEquals(2, response.size()),
                () -> assertEquals("Test Product", response.get(0).getTitle()),
                () -> assertEquals(10.0, response.get(0).getPrice()),
                () -> assertEquals("Test Category", response.get(0).getCategory()),
                () -> assertEquals("Test Description", response.get(0).getDescription()),
                () -> assertEquals("https://example.com/img/placeholder.jpg", response.get(0).getImage()),
                () -> assertEquals("Test Product 2", response.get(1).getTitle()),
                () -> assertEquals(20.0, response.get(1).getPrice()),
                () -> assertEquals("Test Category", response.get(1).getCategory()),
                () -> assertEquals("Test Description 2", response.get(1).getDescription()),
                () -> assertEquals("https://example.com/img/placeholder2.jpg", response.get(1).getImage())
        );
    }

    @Test
    @DisplayName("getProductById() - Existing product")
    void getProductById_existing() {
        Product product = new Product("Test Product", 10.0, "Test Category", "Test Description", "https://example.com/img/placeholder.jpg");
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        Product response = Assertions.assertDoesNotThrow(() -> productService.getProductById(1));

        Assertions.assertAll(
                () -> assertEquals("Test Product", response.getTitle()),
                () -> assertEquals(10.0, response.getPrice()),
                () -> assertEquals("Test Category", response.getCategory()),
                () -> assertEquals("Test Description", response.getDescription()),
                () -> assertEquals("https://example.com/img/placeholder.jpg", response.getImage())
        );
    }

    @Test
    @DisplayName("getProductById() - Missing product")
    void getProductById_missing() {
        when(productRepository.findById(1)).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class,() -> productService.getProductById(1));
    }

    @Test
    @DisplayName("addProduct() - New product")
    void addProduct_new() {
        Product product = new Product("Test Product", 10.0, "Test Category", "Test Description", "https://example.com/img/placeholder.jpg");
        when(productRepository.findByTitle(product.getTitle())).thenReturn(Optional.empty());
        when(productRepository.save(product)).thenReturn(product);

        Product response = Assertions.assertDoesNotThrow(() -> productService.addProduct(product));

        Assertions.assertAll(
                () -> assertEquals("Test Product", response.getTitle()),
                () -> assertEquals(10.0, response.getPrice()),
                () -> assertEquals("Test Category", response.getCategory()),
                () -> assertEquals("Test Description", response.getDescription()),
                () -> assertEquals("https://example.com/img/placeholder.jpg", response.getImage())
        );
    }

    @Test
    @DisplayName("addProduct() - Existing product")
    void addProduct_existing() {
        Product product = new Product("Test Product", 10.0, "Test Category", "Test Description", "https://example.com/img/placeholder.jpg");
        when(productRepository.findByTitle("Test Product")).thenReturn(Optional.of(product));

        Assertions.assertThrows(BadRequestException.class,() -> productService.addProduct(product));
    }

    @Test
    @DisplayName("deleteProduct() - Existing product")
    void deleteProduct_existing() {
        Product product = new Product("Test Product", 10.0, "Test Category", "Test Description", "https://example.com/img/placeholder.jpg");
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        Assertions.assertDoesNotThrow(() -> productService.deleteProduct(1));

        verify(productRepository, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("deleteProduct() - Missing product")
    void deleteProduct_missing() {
        Assertions.assertThrows(EntityNotFoundException.class, () -> productService.deleteProduct(1));
    }
}