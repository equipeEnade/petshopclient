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
import static org.mockito.Mockito.*;

public class ProductRepositoryTest {

    @Mock
    private ProductDAO productDAO;

    @InjectMocks
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() {
        List<ProductEntity> productList = new ArrayList<>();
        productList.add(new ProductEntity());
        when(productDAO.findAll()).thenReturn(productList);

        List<ProductEntity> result = productRepository.findAll();

        assertEquals(1, result.size());
    }

    @Test
    public void testFindById() {
        ProductEntity product = new ProductEntity();
        product.setId(1L);
        when(productDAO.findById(1L)).thenReturn(Optional.of(product));

        ProductEntity result = productRepository.findById(1L);

        assertEquals(1L, result.getId());
    }

    @Test
    public void testSave() {
        ProductEntity product = new ProductEntity();

        productRepository.save(product);

        verify(productDAO, times(1)).save(product);
    }

    @Test
    public void testDelete() {
        productRepository.delete(1L);

        verify(productDAO, times(1)).deleteById(1L);
    }

    @Test
    public void testManageStock() {
        ProductEntity product = new ProductEntity();
        product.setId(1L);
        product.setStock(10);

        when(productDAO.findById(1L)).thenReturn(Optional.of(product));

        productRepository.manageStock(1L, 5);

        assertEquals(15, product.getStock());
        verify(productDAO, times(1)).save(product);
    }
}
