package com.ibm.tienda.modelo.control;

import com.ibm.tienda.modelo.Inventario;
import com.ibm.tienda.modelo.dto.InventarioDTO;

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
public interface IInventarioLogic {
    public List<Inventario> getInventario() throws Exception;

    /**
         * Save an new Inventario entity
         */
    public void saveInventario(Inventario entity) throws Exception;

    /**
         * Delete an existing Inventario entity
         *
         */
    public void deleteInventario(Inventario entity) throws Exception;

    /**
        * Update an existing Inventario entity
        *
        */
    public void updateInventario(Inventario entity) throws Exception;

    /**
         * Load an existing Inventario entity
         *
         */
    public Inventario getInventario(Long id) throws Exception;

    public List<Inventario> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Inventario> findPageInventario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberInventario() throws Exception;

    public List<InventarioDTO> getDataInventario() throws Exception;

    public void validateInventario(Inventario inventario)
        throws Exception;
}
