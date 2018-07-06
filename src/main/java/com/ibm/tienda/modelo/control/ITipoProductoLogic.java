package com.ibm.tienda.modelo.control;

import com.ibm.tienda.modelo.TipoProducto;
import com.ibm.tienda.modelo.dto.TipoProductoDTO;

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
public interface ITipoProductoLogic {
    public List<TipoProducto> getTipoProducto() throws Exception;

    /**
         * Save an new TipoProducto entity
         */
    public void saveTipoProducto(TipoProducto entity) throws Exception;

    /**
         * Delete an existing TipoProducto entity
         *
         */
    public void deleteTipoProducto(TipoProducto entity)
        throws Exception;

    /**
        * Update an existing TipoProducto entity
        *
        */
    public void updateTipoProducto(TipoProducto entity)
        throws Exception;

    /**
         * Load an existing TipoProducto entity
         *
         */
    public TipoProducto getTipoProducto(Long id) throws Exception;

    public List<TipoProducto> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<TipoProducto> findPageTipoProducto(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberTipoProducto() throws Exception;

    public List<TipoProductoDTO> getDataTipoProducto()
        throws Exception;

    public void validateTipoProducto(TipoProducto tipoProducto)
        throws Exception;
}
