package com.ibm.tienda.dto.mapper;

import com.ibm.tienda.modelo.*;
import com.ibm.tienda.modelo.DetallePedido;
import com.ibm.tienda.modelo.control.*;
import com.ibm.tienda.modelo.dto.DetallePedidoDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
@Component
@Scope("singleton")
public class DetallePedidoMapper implements IDetallePedidoMapper {
    private static final Logger log = LoggerFactory.getLogger(DetallePedidoMapper.class);

    /**
    * Logic injected by Spring that manages Pedido entities
    *
    */
    @Autowired
    IPedidoLogic logicPedido1;

    /**
    * Logic injected by Spring that manages Producto entities
    *
    */
    @Autowired
    IProductoLogic logicProducto2;

    @Transactional(readOnly = true)
    public DetallePedidoDTO detallePedidoToDetallePedidoDTO(
        DetallePedido detallePedido) throws Exception {
        try {
            DetallePedidoDTO detallePedidoDTO = new DetallePedidoDTO();

            detallePedidoDTO.setId(detallePedido.getId());
            detallePedidoDTO.setCantidad((detallePedido.getCantidad() != null)
                ? detallePedido.getCantidad() : null);
            detallePedidoDTO.setPrecio((detallePedido.getPrecio() != null)
                ? detallePedido.getPrecio() : null);

            if (detallePedido.getPedido() != null) {
                detallePedidoDTO.setId_Pedido(detallePedido.getPedido().getId());
            } else {
                detallePedidoDTO.setId_Pedido(null);
            }

            if (detallePedido.getProducto() != null) {
                detallePedidoDTO.setId_Producto(detallePedido.getProducto()
                                                             .getId());
            } else {
                detallePedidoDTO.setId_Producto(null);
            }

            return detallePedidoDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public DetallePedido detallePedidoDTOToDetallePedido(
        DetallePedidoDTO detallePedidoDTO) throws Exception {
        try {
            DetallePedido detallePedido = new DetallePedido();

            detallePedido.setId(detallePedidoDTO.getId());
            detallePedido.setCantidad((detallePedidoDTO.getCantidad() != null)
                ? detallePedidoDTO.getCantidad() : null);
            detallePedido.setPrecio((detallePedidoDTO.getPrecio() != null)
                ? detallePedidoDTO.getPrecio() : null);

            Pedido pedido = new Pedido();

            if (detallePedidoDTO.getId_Pedido() != null) {
                pedido = logicPedido1.getPedido(detallePedidoDTO.getId_Pedido());
            }

            if (pedido != null) {
                detallePedido.setPedido(pedido);
            }

            Producto producto = new Producto();

            if (detallePedidoDTO.getId_Producto() != null) {
                producto = logicProducto2.getProducto(detallePedidoDTO.getId_Producto());
            }

            if (producto != null) {
                detallePedido.setProducto(producto);
            }

            return detallePedido;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<DetallePedidoDTO> listDetallePedidoToListDetallePedidoDTO(
        List<DetallePedido> listDetallePedido) throws Exception {
        try {
            List<DetallePedidoDTO> detallePedidoDTOs = new ArrayList<DetallePedidoDTO>();

            for (DetallePedido detallePedido : listDetallePedido) {
                DetallePedidoDTO detallePedidoDTO = detallePedidoToDetallePedidoDTO(detallePedido);

                detallePedidoDTOs.add(detallePedidoDTO);
            }

            return detallePedidoDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<DetallePedido> listDetallePedidoDTOToListDetallePedido(
        List<DetallePedidoDTO> listDetallePedidoDTO) throws Exception {
        try {
            List<DetallePedido> listDetallePedido = new ArrayList<DetallePedido>();

            for (DetallePedidoDTO detallePedidoDTO : listDetallePedidoDTO) {
                DetallePedido detallePedido = detallePedidoDTOToDetallePedido(detallePedidoDTO);

                listDetallePedido.add(detallePedido);
            }

            return listDetallePedido;
        } catch (Exception e) {
            throw e;
        }
    }
}
