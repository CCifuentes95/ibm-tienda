package com.ibm.tienda.dto.mapper;

import com.ibm.tienda.modelo.DetalleInventario;
import com.ibm.tienda.modelo.dto.DetalleInventarioDTO;

import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public interface IDetalleInventarioMapper {
    public DetalleInventarioDTO detalleInventarioToDetalleInventarioDTO(
        DetalleInventario detalleInventario) throws Exception;

    public DetalleInventario detalleInventarioDTOToDetalleInventario(
        DetalleInventarioDTO detalleInventarioDTO) throws Exception;

    public List<DetalleInventarioDTO> listDetalleInventarioToListDetalleInventarioDTO(
        List<DetalleInventario> detalleInventarios) throws Exception;

    public List<DetalleInventario> listDetalleInventarioDTOToListDetalleInventario(
        List<DetalleInventarioDTO> detalleInventarioDTOs)
        throws Exception;
}
