package edu.uan.fis.jeesample.dao;

import edu.uan.fis.jeesample.dto.Empleado;
import java.util.List;

public interface EmpleadoDao {
  /**
     * Creates a new product. 
     * @param product
     * @return 
     */
    Empleado create(Empleado product);

    /**
     * Updates an existing product. Product id can't be modified.
     * @param empleado
     * @return 
     */
    Empleado update(Empleado product);

    /**
     * Deletes an existing product
     * @param empleado 
     */
    void delete(Empleado empleado);

    /**
     * Find a product by id
     * @param empleadoId
     * @return 
     */
    Empleado findById(Integer empleadoId);

    /**
     * Returns all the products in the database
     * @return 
     */
    List<Empleado> findAll();
}