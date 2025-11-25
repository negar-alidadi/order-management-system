package org.ordermanagement.service.dto;

public class ProductSalesReportDTO {
    private Long productId;
    private String productName;
    private double price;
    private int totalSold;
    private double totalRevenue;

    public ProductSalesReportDTO(Long productId, String productName, double price) {
        this.productId = productId;
        this.productName = productName;
    }

    public ProductSalesReportDTO() {
    }

    public int getTotalSold() {
        return totalSold;
    }

    public void setTotalSold(int totalSold) {
        this.totalSold = totalSold;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
