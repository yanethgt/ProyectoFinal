package edu.uan.fis.jeesample.dao;

import edu.uan.fis.jeesample.dto.Usuario;
import java.util.List;

public interface UsuarioDao {

    /**
     * Creates a new Usuario. 
     * @param usuario
     * @return 
     */
    Usuario create(Usuario usuario);

    /**
     * Updates an existing usuario. Usuario id can't be modified.
     * @param usuario
     * @return 
     */
    Usuario update(Usuario Usuario);

    /**
     * Deletes an existing usuario
     * @param usuario 
     */
    void delete(Usuario Usuario);

    /**
     * Find a usuario by id
     * @param usuarioId
     * @return 
     */
    Usuario findById(Integer UsuarioId);

    /**
     * Returns all the Usuarios in the database
     * @return 
     */
    List<Usuario> findAll();
}
