package com.ibm.tienda.dto.mapper;

import com.ibm.tienda.modelo.TipoProducto;
import com.ibm.tienda.modelo.dto.TipoProductoDTO;

import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public interface ITipoProductoMapper {
    public TipoProductoDTO tipoProductoToTipoProductoDTO(
        TipoProducto tipoProducto) throws Exception;

    public TipoProducto tipoProductoDTOToTipoProducto(
        TipoProductoDTO tipoProductoDTO) throws Exception;

    public List<TipoProductoDTO> listTipoProductoToListTipoProductoDTO(
        List<TipoProducto> tipoProductos) throws Exception;

    public List<TipoProducto> listTipoProductoDTOToListTipoProducto(
        List<TipoProductoDTO> tipoProductoDTOs) throws Exception;
}
