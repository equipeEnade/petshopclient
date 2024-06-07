package com.catusoft.petshopclient.infra.repository.product;

import com.catusoft.petshopclient.infra.dao.product.ProductDAO;
import com.catusoft.petshopclient.infra.dao.product.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class ProductRepositoryIntegrationTest {

    @Autowired
    private ProductDAO productDAO;

    @BeforeEach
    public void setup() {
        productDAO.deleteAll(); // Limpa o banco de dados antes de cada teste
    }

    @Test
    public void testFindAllWithDB() {
        // Arrange
        ProductEntity product = new ProductEntity();
        product.setStock(10);
        productDAO.save(product);

        // Act
        List<ProductEntity> response = productDAO.findAll();

        // Assert
        assertEquals(1, response.size());
    }

    @Test
    public void testFindByIdWithDB() {
        // Arrange
        ProductEntity product = new ProductEntity();
        product.setStock(10);
        ProductEntity savedProduct = productDAO.save(product);

        // Act
        var response = productDAO.findById(savedProduct.getId());

        // Assert
        assertTrue(response.isPresent());
        assertEquals(savedProduct, response.get());
    }

    @Test
    public void testUpdateProductWithDB() {
        // Arrange
        ProductEntity product = new ProductEntity();
        product.setStock(10);
        ProductEntity savedProduct = productDAO.save(product);

        // Act
        savedProduct.setStock(20);
        productDAO.save(savedProduct);
        Optional<ProductEntity> updatedProduct = productDAO.findById(savedProduct.getId());

        // Assert
        assertTrue(updatedProduct.isPresent());
        assertEquals(20, updatedProduct.get().getStock());
    }

    @Test
    public void testDeleteProductWithDB() {
        // Arrange
        ProductEntity product = new ProductEntity();
        product.setStock(10);
        ProductEntity savedProduct = productDAO.save(product);

        // Act
        productDAO.deleteById(savedProduct.getId());
        Optional<ProductEntity> deletedProduct = productDAO.findById(savedProduct.getId());

        // Assert
        assertTrue(deletedProduct.isEmpty());
    }

    @Test
    public void testManageStockWithDB() {
        // Arrange
        ProductEntity product = new ProductEntity();
        product.setStock(10);
        ProductEntity savedProduct = productDAO.save(product);

        // Act
        savedProduct.setStock(savedProduct.getStock() + 5);
        productDAO.save(savedProduct);
        Optional<ProductEntity> updatedProduct = productDAO.findById(savedProduct.getId());

        // Assert
        assertTrue(updatedProduct.isPresent());
        assertEquals(15, updatedProduct.get().getStock());
    }
}
