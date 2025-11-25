package org.ordermanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "PRODUCT_IMAGES")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // store URL/path rather than raw bytes by default
    @Column(nullable = false)
    private String url;

    private String altText;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    public ProductImage() {}
    public ProductImage(String url, String altText) {
        this.url = url;
        this.altText = altText;
    }

    public Long getId() {
        return id;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getAltText() {
        return altText;
    }
    public void setAltText(String altText) {
        this.altText = altText;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
}
