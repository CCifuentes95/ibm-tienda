package com.ibm.tienda.modelo.control;

import com.ibm.tienda.modelo.Usuario;
import com.ibm.tienda.modelo.dto.UsuarioDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public interface IUsuarioLogic {
    public List<Usuario> getUsuario() throws Exception;

    /**
         * Save an new Usuario entity
         */
    public void saveUsuario(Usuario entity) throws Exception;

    /**
         * Delete an existing Usuario entity
         *
         */
    public void deleteUsuario(Usuario entity) throws Exception;

    /**
        * Update an existing Usuario entity
        *
        */
    public void updateUsuario(Usuario entity) throws Exception;

    /**
         * Load an existing Usuario entity
         *
         */
    public Usuario getUsuario(Long id) throws Exception;

    public List<Usuario> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Usuario> findPageUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberUsuario() throws Exception;

    public List<UsuarioDTO> getDataUsuario() throws Exception;

    public void validateUsuario(Usuario usuario) throws Exception;
    
    /** Obtiene un usuario dado una identificacion
     * @author Camilo Andrés Cifuentes Grass
     * @version 2018-07-04
     * @param identificacion
     * @return
     * @throws Exception
     */
    public Usuario getUsuarioByIdentificacion(String identificacion) throws Exception;
    
    /** Obtiene un usuario DTO dado una identificacion
     * @author Camilo Andrés Cifuentes Grass
     * @version 2018-07-04
     * @param identificacion
     * @return
     * @throws Exception
     */
    public UsuarioDTO getUsuarioDTOByIdentificacion(String identificacion) throws Exception;


}
