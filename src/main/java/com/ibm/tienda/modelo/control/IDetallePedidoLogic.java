package com.ibm.tienda.modelo.control;

import com.ibm.tienda.modelo.DetallePedido;
import com.ibm.tienda.modelo.dto.DetallePedidoDTO;

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
public interface IDetallePedidoLogic {
    public List<DetallePedido> getDetallePedido() throws Exception;

    /**
         * Save an new DetallePedido entity
         */
    public void saveDetallePedido(DetallePedido entity)
        throws Exception;

    /**
         * Delete an existing DetallePedido entity
         *
         */
    public void deleteDetallePedido(DetallePedido entity)
        throws Exception;

    /**
        * Update an existing DetallePedido entity
        *
        */
    public void updateDetallePedido(DetallePedido entity)
        throws Exception;

    /**
         * Load an existing DetallePedido entity
         *
         */
    public DetallePedido getDetallePedido(Long id) throws Exception;

    public List<DetallePedido> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<DetallePedido> findPageDetallePedido(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberDetallePedido() throws Exception;

    public List<DetallePedidoDTO> getDataDetallePedido()
        throws Exception;

    public void validateDetallePedido(DetallePedido detallePedido)
        throws Exception;
}
