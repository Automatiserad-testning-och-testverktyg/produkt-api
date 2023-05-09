package com.example.produktapi.model;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;


import java.util.List;


@SpringBootTest
public class ProductUnitTest {


    Product product = new Product();

    //public Product(String title, Double price, String category, String description, String image)


    @Test
    void CheckProductTitle() {
        product.setTitle("Powerboots Original Stövlar");
      String newtitle = product.getTitle();
      Assertions.assertEquals( newtitle,"Powerboots Original Stövlar");
// Test commit
    }
   


}
