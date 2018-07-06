package com.ibm.tienda.dto.mapper;

import com.ibm.tienda.modelo.*;
import com.ibm.tienda.modelo.Pedido;
import com.ibm.tienda.modelo.control.*;
import com.ibm.tienda.modelo.dto.PedidoDTO;

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
public class PedidoMapper implements IPedidoMapper {
    private static final Logger log = LoggerFactory.getLogger(PedidoMapper.class);

    /**
    * Logic injected by Spring that manages Usuario entities
    *
    */
    @Autowired
    IUsuarioLogic logicUsuario1;

    @Transactional(readOnly = true)
    public PedidoDTO pedidoToPedidoDTO(Pedido pedido) throws Exception {
        try {
            PedidoDTO pedidoDTO = new PedidoDTO();

            pedidoDTO.setId(pedido.getId());
            pedidoDTO.setCodigo((pedido.getCodigo() != null)
                ? pedido.getCodigo() : null);
            pedidoDTO.setDireccion((pedido.getDireccion() != null)
                ? pedido.getDireccion() : null);
            pedidoDTO.setFechaCreacion(pedido.getFechaCreacion());
            pedidoDTO.setFechaPedido(pedido.getFechaPedido());
            pedidoDTO.setTotal((pedido.getTotal() != null) ? pedido.getTotal()
                                                           : null);

            if (pedido.getUsuario() != null) {
                pedidoDTO.setId_Usuario(pedido.getUsuario().getId());
            } else {
                pedidoDTO.setId_Usuario(null);
            }

            return pedidoDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Pedido pedidoDTOToPedido(PedidoDTO pedidoDTO)
        throws Exception {
        try {
            Pedido pedido = new Pedido();

            pedido.setId(pedidoDTO.getId());
            pedido.setCodigo((pedidoDTO.getCodigo() != null)
                ? pedidoDTO.getCodigo() : null);
            pedido.setDireccion((pedidoDTO.getDireccion() != null)
                ? pedidoDTO.getDireccion() : null);
            pedido.setFechaCreacion(pedidoDTO.getFechaCreacion());
            pedido.setFechaPedido(pedidoDTO.getFechaPedido());
            pedido.setTotal((pedidoDTO.getTotal() != null)
                ? pedidoDTO.getTotal() : null);

            Usuario usuario = new Usuario();

            if (pedidoDTO.getId_Usuario() != null) {
                usuario = logicUsuario1.getUsuario(pedidoDTO.getId_Usuario());
            }

            if (usuario != null) {
                pedido.setUsuario(usuario);
            }

            return pedido;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<PedidoDTO> listPedidoToListPedidoDTO(List<Pedido> listPedido)
        throws Exception {
        try {
            List<PedidoDTO> pedidoDTOs = new ArrayList<PedidoDTO>();

            for (Pedido pedido : listPedido) {
                PedidoDTO pedidoDTO = pedidoToPedidoDTO(pedido);

                pedidoDTOs.add(pedidoDTO);
            }

            return pedidoDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<Pedido> listPedidoDTOToListPedido(List<PedidoDTO> listPedidoDTO)
        throws Exception {
        try {
            List<Pedido> listPedido = new ArrayList<Pedido>();

            for (PedidoDTO pedidoDTO : listPedidoDTO) {
                Pedido pedido = pedidoDTOToPedido(pedidoDTO);

                listPedido.add(pedido);
            }

            return listPedido;
        } catch (Exception e) {
            throw e;
        }
    }
}
