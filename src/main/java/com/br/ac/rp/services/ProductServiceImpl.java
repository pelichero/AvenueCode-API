package com.br.ac.rp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.ac.rp.domain.Product;
import com.br.ac.rp.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Iterable<Product> listAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepository.findOne(id);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

	@Override
	public void deleteProduct(Integer id) {
		Product product = getProductById(id);
		if(product.getParts() != null && !product.getParts().isEmpty()){
			for (Product child : product.getParts()) {
				deleteProduct(child.getId());
			}
		}else{
			productRepository.delete(id);
		}
	}
}
