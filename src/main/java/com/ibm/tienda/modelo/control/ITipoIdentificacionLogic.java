package com.ibm.tienda.modelo.control;

import com.ibm.tienda.modelo.TipoIdentificacion;
import com.ibm.tienda.modelo.dto.TipoIdentificacionDTO;

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
public interface ITipoIdentificacionLogic {
    public List<TipoIdentificacion> getTipoIdentificacion()
        throws Exception;

    /**
         * Save an new TipoIdentificacion entity
         */
    public void saveTipoIdentificacion(TipoIdentificacion entity)
        throws Exception;

    /**
         * Delete an existing TipoIdentificacion entity
         *
         */
    public void deleteTipoIdentificacion(TipoIdentificacion entity)
        throws Exception;

    /**
        * Update an existing TipoIdentificacion entity
        *
        */
    public void updateTipoIdentificacion(TipoIdentificacion entity)
        throws Exception;

    /**
         * Load an existing TipoIdentificacion entity
         *
         */
    public TipoIdentificacion getTipoIdentificacion(Long id)
        throws Exception;

    public List<TipoIdentificacion> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<TipoIdentificacion> findPageTipoIdentificacion(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberTipoIdentificacion() throws Exception;

    public List<TipoIdentificacionDTO> getDataTipoIdentificacion()
        throws Exception;

    public void validateTipoIdentificacion(
        TipoIdentificacion tipoIdentificacion) throws Exception;
}
