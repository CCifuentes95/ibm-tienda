package com.ibm.tienda.dto.mapper;

import com.ibm.tienda.modelo.Pedido;
import com.ibm.tienda.modelo.dto.PedidoDTO;

import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public interface IPedidoMapper {
    public PedidoDTO pedidoToPedidoDTO(Pedido pedido) throws Exception;

    public Pedido pedidoDTOToPedido(PedidoDTO pedidoDTO)
        throws Exception;

    public List<PedidoDTO> listPedidoToListPedidoDTO(List<Pedido> pedidos)
        throws Exception;

    public List<Pedido> listPedidoDTOToListPedido(List<PedidoDTO> pedidoDTOs)
        throws Exception;
}
