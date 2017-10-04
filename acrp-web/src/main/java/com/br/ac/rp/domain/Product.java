package com.br.ac.rp.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_product")
    private Integer id;

    @Version
    private Integer version;

    @ManyToOne(optional=true, fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="partOf_id")
    private Product partOf;
    
    @OneToMany(mappedBy="partOf", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Product> parts;
    
    @OneToMany(mappedBy="product", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Image> images;
    
    private String productId;
    private String description;
    private BigDecimal price;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

	public Product getPartOf() {
		return partOf;
	}

	public void setPartOf(Product partOf) {
		this.partOf = partOf;
	}

	public List<Product> getParts() {
		return parts;
	}

	public void setParts(List<Product> parts) {
		this.parts = parts;
	}
    
	public void addProduct(Product product){
		if(this.parts == null){
			this.parts = new ArrayList<Product>();
		}
		
		this.parts.add(product);
		product.setPartOf(this);		
	}
	
	public void addImage(Image image){
		if(this.images == null){
			this.images = new ArrayList<Image>();
		}
		
		this.images.add(image);
		image.setProduct(this);
	}
}
