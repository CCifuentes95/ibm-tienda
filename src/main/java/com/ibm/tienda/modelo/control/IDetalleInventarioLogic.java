package com.ibm.tienda.modelo.control;

import com.ibm.tienda.modelo.DetalleInventario;
import com.ibm.tienda.modelo.dto.DetalleInventarioDTO;

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
public interface IDetalleInventarioLogic {
    public List<DetalleInventario> getDetalleInventario()
        throws Exception;

    /**
         * Save an new DetalleInventario entity
         */
    public void saveDetalleInventario(DetalleInventario entity)
        throws Exception;

    /**
         * Delete an existing DetalleInventario entity
         *
         */
    public void deleteDetalleInventario(DetalleInventario entity)
        throws Exception;

    /**
        * Update an existing DetalleInventario entity
        *
        */
    public void updateDetalleInventario(DetalleInventario entity)
        throws Exception;

    /**
         * Load an existing DetalleInventario entity
         *
         */
    public DetalleInventario getDetalleInventario(Long id)
        throws Exception;

    public List<DetalleInventario> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<DetalleInventario> findPageDetalleInventario(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberDetalleInventario() throws Exception;

    public List<DetalleInventarioDTO> getDataDetalleInventario()
        throws Exception;

    public void validateDetalleInventario(DetalleInventario detalleInventario)
        throws Exception;
}
