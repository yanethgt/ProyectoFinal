package edu.uan.fis.jeesample.dao;

import edu.uan.fis.jeesample.dto.Cliente;
import java.util.List;


public interface ClienteDao {
  
    /**
     * Creates a new Cliente. 
     * @param cliente
     * @return 
     */
    Cliente create(Cliente cliente);

    /**
     * Updates an existing Cliente. Cliente id can't be modified.
     * @param Cliente
     * @return 
     */
    Cliente update(Cliente Cliente);

    /**
     * Deletes an existing Cliente
     * @param Cliente 
     */
    void delete(Cliente cliente);

    /**
     * Find a cliente by id
     * @param clienteId
     * @return 
     */
    Cliente findById(Integer ClienteId);

    /**
     * Returns all the clientes in the database
     * @return 
     */
    List<Cliente> findAll();
    }