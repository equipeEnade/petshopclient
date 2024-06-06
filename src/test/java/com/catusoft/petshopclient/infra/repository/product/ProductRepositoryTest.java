package com.catusoft.petshopclient.infra.repository.product;

import com.catusoft.petshopclient.infra.dao.product.ProductDAO;
import com.catusoft.petshopclient.infra.dao.product.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ProductRepositoryTest {

    @Mock
    private ProductDAO productDAO;

    @InjectMocks
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        // Arrange
        List<ProductEntity> productList = new ArrayList<>();
        productList.add(new ProductEntity());
        when(productDAO.findAll()).thenReturn(productList);

        // Act
        List<ProductEntity> result = productRepository.findAll();

        // Assert
        assertEquals(1, result.size());
    }

    @Test
    public void testFindById() {
        // Arrange
        ProductEntity product = new ProductEntity();
        product.setId(1L);
        when(productDAO.findById(1L)).thenReturn(Optional.of(product));

        // Act
        ProductEntity result = productRepository.findById(1L);

        // Assert
        assertEquals(1L, result.getId());
    }

    @Test
    public void testSave() {
        // Arrange
        ProductEntity product = new ProductEntity();

        // Act
        productRepository.save(product);

        // Assert
        verify(productDAO, times(1)).save(product);
    }

    @Test
    public void testDelete() {
        // Arrange
        long productId = 1L;

        // Act
        productRepository.delete(productId);

        // Assert
        verify(productDAO, times(1)).deleteById(productId);
    }

    @Test
    public void testManageStock() {
        // Arrange
        ProductEntity product = new ProductEntity();
        product.setId(1L);
        product.setStock(10);
        when(productDAO.findById(1L)).thenReturn(Optional.of(product));

        // Act
        productRepository.manageStock(1L, 5);

        // Assert
        assertEquals(15, product.getStock());
        verify(productDAO, times(1)).save(product);
    }
}
