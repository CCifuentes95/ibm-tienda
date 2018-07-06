package com.ibm.tienda.modelo.control;

import com.ibm.tienda.modelo.Pedido;
import com.ibm.tienda.modelo.dto.DetallePedidoDTO;
import com.ibm.tienda.modelo.dto.PedidoDTO;

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
public interface IPedidoLogic {
    public List<Pedido> getPedido() throws Exception;

    /**
         * Save an new Pedido entity
         */
    public void savePedido(Pedido entity) throws Exception;

    /**
         * Delete an existing Pedido entity
         *
         */
    public void deletePedido(Pedido entity) throws Exception;

    /**
        * Update an existing Pedido entity
        *
        */
    public void updatePedido(Pedido entity) throws Exception;

    /**
         * Load an existing Pedido entity
         *
         */
    public Pedido getPedido(Long id) throws Exception;

    public List<Pedido> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Pedido> findPagePedido(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPedido() throws Exception;

    public List<PedidoDTO> getDataPedido() throws Exception;

    public void validatePedido(Pedido pedido) throws Exception;
    
    /**
     * Este método se encarga de registrar un pedido y sus detalles
     * @author Camilo Andrés Cifuentes Grass
     * @version 2018-07-04
     * @param nombre nombre de la persona que realiza el pedido
     * @param apellido apellido de la persona que realiza el pedido
     * @param idTipoId id del tipo de identificacion
     * @param identificacion la identificacion de quien realiza el pedido
     * @param direccion la direccion donde se enviará
     * @param fechaPedido la fecha y hora que debe llegar el pedido
     * @param detallePedidoDTOs la lista de items con cantidades 
     * @throws Exception
     */
    public void registrarPedido( String nombre,	String apellido, Long idTipoId,	String identificacion,	String direccion, Date fechaPedido, List<DetallePedidoDTO> detallePedidoDTOs  ) throws Exception;

}
