package com.microservices.product.service;

import static org.junit.jupiter.api.Assertions.assertEquals; 
import org.easymock.EasyMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microservices.product.service.controller.ProductController;
import com.microservices.product.service.dto.ProductForm;
import com.microservices.product.service.service.ProductService;

public class ProductServiceTest {

    private ProductController pController;
    private ProductService pService;

    @BeforeEach
    public void setUp() {

        pService = EasyMock.createMock(ProductService.class); 
        pController = new ProductController(); 
        pController.setProductService(pService); 
    }
    @AfterEach
    public void tearDown() {

		pController = null;
        EasyMock.reset(pService);
    }
    @Test
    public void addProductTest() {
        
        ProductForm form = new ProductForm();
        form.setTitle("Sample Product");
        EasyMock.expect(pService.addProduct(1L, form)).andReturn("Product Created Successfully");
        EasyMock.replay(pService);
        String result = pController.addProduct(1L, form);
        assertEquals("Product Created Successfully", result);
        EasyMock.verify(pService);
    }
    @Test
    public void updateProductTest() {
        
        ProductForm form = new ProductForm();
        form.setTitle("Updated Product Title");
        EasyMock.expect(pService.updateProduct(1L, 100L, form)).andReturn("Product Updated Successfully");
        EasyMock.replay(pService);
        String result = pController.updateProduct(1L, 100L, form);
        assertEquals("Product Updated Successfully", result);
        EasyMock.verify(pService);
    }

}
