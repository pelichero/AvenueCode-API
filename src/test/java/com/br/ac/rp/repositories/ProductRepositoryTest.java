package com.br.ac.rp.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.br.ac.rp.configuration.RepositoryConfiguration;
import com.br.ac.rp.domain.Image;
import com.br.ac.rp.domain.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class ProductRepositoryTest {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Test
    public void testSaveProduct(){
        //setup product
    	Product child = new Product();
		child.setDescription("RAM mem");
		child.setPrice(new BigDecimal("50"));
		child.setProductId("2");
		child.setImages(Collections.singletonList(new Image("https://facilitandoainformatica.files.wordpress.com/2010/05/memoria-ram-blog.jpg")));
    	
        Product parent = new Product();
        parent.setDescription("Computer MotherBoard");
        parent.setPrice(new BigDecimal("100"));
        parent.setProductId("1");
        parent.setImages(Collections.singletonList(new Image("http://media.gamersnexus.net/images/media/2014/guides/asus-rog-hero.png")));
        parent.addProduct(child);

        //save product, verify has ID value after save
        assertNull(parent.getId()); //null before save
        productRepository.save(parent);
        assertNotNull(parent.getId()); //not null after save
        //fetch from DB
        Product fetchedProduct = productRepository.findOne(parent.getId());

        //should not be null
        assertNotNull(fetchedProduct);

        //should equal
        assertEquals(parent.getId(), fetchedProduct.getId());
        assertEquals(parent.getDescription(), fetchedProduct.getDescription());

        //update description and save
        fetchedProduct.setDescription("New Description");
        productRepository.save(fetchedProduct);

        //get from DB, should be updated
        Product fetchedUpdatedProduct = productRepository.findOne(fetchedProduct.getId());
        assertEquals(fetchedProduct.getDescription(), fetchedUpdatedProduct.getDescription());

        //verify count of products in DB
        long productCount = productRepository.count();
        assertEquals(productCount, 1);

        //get all products, list should only have one
        Iterable<Product> products = productRepository.findAll();

        int count = 0;

        for(Product p : products){
            count++;
        }

        assertEquals(count, 1);
    }
}
