package com.ibm.tienda.dto.mapper;

import com.ibm.tienda.modelo.Inventario;
import com.ibm.tienda.modelo.dto.InventarioDTO;

import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public interface IInventarioMapper {
    public InventarioDTO inventarioToInventarioDTO(Inventario inventario)
        throws Exception;

    public Inventario inventarioDTOToInventario(InventarioDTO inventarioDTO)
        throws Exception;

    public List<InventarioDTO> listInventarioToListInventarioDTO(
        List<Inventario> inventarios) throws Exception;

    public List<Inventario> listInventarioDTOToListInventario(
        List<InventarioDTO> inventarioDTOs) throws Exception;
}
