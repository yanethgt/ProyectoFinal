package edu.uan.fis.jeesample.dao;

import edu.uan.fis.jeesample.dto.Product;
import java.util.List;

public interface ProductDao {

    /**
     * Creates a new product. 
     * @param product
     * @return 
     */
    Product create(Product product);

    /**
     * Updates an existing product. Product id can't be modified.
     * @param product
     * @return 
     */
    Product update(Product product);

    /**
     * Deletes an existing product
     * @param product 
     */
    void delete(Product product);

    /**
     * Find a product by id
     * @param productId
     * @return 
     */
    Product findById(Integer productId);

    /**
     * Returns all the products in the database
     * @return 
     */
    List<Product> findAll();
    
    
}
