package org.ordermanagement.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.ordermanagement.dao.InventoryTransactionDAO;
import org.ordermanagement.entity.InventoryTransaction;
import org.ordermanagement.entity.Product;
import org.ordermanagement.service.dto.ProductSalesReportDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalesReportService {

    private final EntityManagerFactory emf;
    private final InventoryTransactionDAO inventoryTransactionDAO;

    public SalesReportService(EntityManagerFactory emf,InventoryTransactionDAO inventoryTransactionDAO) {
        this.emf = emf;
        this.inventoryTransactionDAO = inventoryTransactionDAO;
    }

    public void printSalesReport() {
        EntityManager em = emf.createEntityManager();

        try {
            List<InventoryTransaction> sales = inventoryTransactionDAO.findByTypeWithInventory();

            Map<Long, ProductSalesReportDTO> report = new HashMap<>();
            for (InventoryTransaction tx : sales) {
                Product product = tx.getInventory().getProduct();


                ProductSalesReportDTO row = report.computeIfAbsent(
                        product.getId(),
                        k -> new ProductSalesReportDTO(product.getId(), product.getName(), product.getPrice())
                );

                row.setTotalSold(row.getTotalSold() + tx.getQuantity());
                row.setTotalRevenue(row.getTotalRevenue() + (tx.getQuantity() * product.getPrice()));

            }
            System.out.println("\n===== SALES REPORT =====");

            for (ProductSalesReportDTO r : report.values()) {
                System.out.println(
                        "Product: " + r.getProductName() +
                                " | Sold: " + r.getTotalSold() +
                                " | Revenue: " + r.getTotalRevenue()
                );
            }

            System.out.println("========================\n");

        } finally {
            em.close();
        }
    }

}
