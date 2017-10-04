package com.br.ac.rp.bootstrap;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.br.ac.rp.domain.Image;
import com.br.ac.rp.domain.Product;
import com.br.ac.rp.repositories.ProductRepository;

@Component
public class ProductLoader implements ApplicationListener<ContextRefreshedEvent> {

    private ProductRepository productRepository;

    private Logger log = Logger.getLogger(ProductLoader.class);

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

		Product child = new Product();
		child.setDescription("RAM mem");
		child.setPrice(new BigDecimal("50"));
		child.setProductId("2");
		child.addImage(new Image("https://facilitandoainformatica.files.wordpress.com/2010/05/memoria-ram-blog.jpg"));
    	
        Product parent = new Product();
        parent.setDescription("Computer MotherBoard");
        parent.setPrice(new BigDecimal("100"));
        parent.setProductId("1");
        parent.addImage(new Image("http://media.gamersnexus.net/images/media/2014/guides/asus-rog-hero.png"));
        parent.addProduct(child);
        
        productRepository.save(parent);

        log.info("Saved Shirt - id: " + parent.getId());
    }
}
