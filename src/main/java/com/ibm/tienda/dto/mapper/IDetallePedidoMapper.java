package com.ibm.tienda.dto.mapper;

import com.ibm.tienda.modelo.DetallePedido;
import com.ibm.tienda.modelo.dto.DetallePedidoDTO;

import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public interface IDetallePedidoMapper {
    public DetallePedidoDTO detallePedidoToDetallePedidoDTO(
        DetallePedido detallePedido) throws Exception;

    public DetallePedido detallePedidoDTOToDetallePedido(
        DetallePedidoDTO detallePedidoDTO) throws Exception;

    public List<DetallePedidoDTO> listDetallePedidoToListDetallePedidoDTO(
        List<DetallePedido> detallePedidos) throws Exception;

    public List<DetallePedido> listDetallePedidoDTOToListDetallePedido(
        List<DetallePedidoDTO> detallePedidoDTOs) throws Exception;
}
