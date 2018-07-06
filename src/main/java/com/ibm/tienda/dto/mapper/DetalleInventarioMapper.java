package com.ibm.tienda.dto.mapper;

import com.ibm.tienda.modelo.*;
import com.ibm.tienda.modelo.DetalleInventario;
import com.ibm.tienda.modelo.control.*;
import com.ibm.tienda.modelo.dto.DetalleInventarioDTO;

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
public class DetalleInventarioMapper implements IDetalleInventarioMapper {
    private static final Logger log = LoggerFactory.getLogger(DetalleInventarioMapper.class);

    /**
    * Logic injected by Spring that manages Inventario entities
    *
    */
    @Autowired
    IInventarioLogic logicInventario1;

    /**
    * Logic injected by Spring that manages Producto entities
    *
    */
    @Autowired
    IProductoLogic logicProducto2;

    @Transactional(readOnly = true)
    public DetalleInventarioDTO detalleInventarioToDetalleInventarioDTO(
        DetalleInventario detalleInventario) throws Exception {
        try {
            DetalleInventarioDTO detalleInventarioDTO = new DetalleInventarioDTO();

            detalleInventarioDTO.setId(detalleInventario.getId());
            detalleInventarioDTO.setCantidad((detalleInventario.getCantidad() != null)
                ? detalleInventario.getCantidad() : null);
            detalleInventarioDTO.setPrecioBase((detalleInventario.getPrecioBase() != null)
                ? detalleInventario.getPrecioBase() : null);

            if (detalleInventario.getInventario() != null) {
                detalleInventarioDTO.setId_Inventario(detalleInventario.getInventario().getId());
            } else {
                detalleInventarioDTO.setId_Inventario(null);
            }
            Producto producto = detalleInventario.getProducto();
            if (producto != null) {
                detalleInventarioDTO.setId_Producto(producto.getId());
                detalleInventarioDTO.setNombrePoducto(producto.getNombre());
            } else {
                detalleInventarioDTO.setId_Producto(null);
            }

            return detalleInventarioDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public DetalleInventario detalleInventarioDTOToDetalleInventario(
        DetalleInventarioDTO detalleInventarioDTO) throws Exception {
        try {
            DetalleInventario detalleInventario = new DetalleInventario();

            detalleInventario.setId(detalleInventarioDTO.getId());
            detalleInventario.setCantidad((detalleInventarioDTO.getCantidad() != null)
                ? detalleInventarioDTO.getCantidad() : null);
            detalleInventario.setPrecioBase((detalleInventarioDTO.getPrecioBase() != null)
                ? detalleInventarioDTO.getPrecioBase() : null);

            Inventario inventario = null;

            if (detalleInventarioDTO.getId_Inventario() != null) {
                inventario = logicInventario1.getInventario(detalleInventarioDTO.getId_Inventario());
                detalleInventario.setInventario(inventario);
            }

            Producto producto = null;

            if (detalleInventarioDTO.getId_Producto() != null) {
                producto = logicProducto2.getProducto(detalleInventarioDTO.getId_Producto());
                detalleInventario.setProducto(producto);
            }


            return detalleInventario;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<DetalleInventarioDTO> listDetalleInventarioToListDetalleInventarioDTO(
        List<DetalleInventario> listDetalleInventario)
        throws Exception {
        try {
            List<DetalleInventarioDTO> detalleInventarioDTOs = new ArrayList<DetalleInventarioDTO>();

            for (DetalleInventario detalleInventario : listDetalleInventario) {
                DetalleInventarioDTO detalleInventarioDTO = detalleInventarioToDetalleInventarioDTO(detalleInventario);

                detalleInventarioDTOs.add(detalleInventarioDTO);
            }

            return detalleInventarioDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<DetalleInventario> listDetalleInventarioDTOToListDetalleInventario(
        List<DetalleInventarioDTO> listDetalleInventarioDTO)
        throws Exception {
        try {
            List<DetalleInventario> listDetalleInventario = new ArrayList<DetalleInventario>();

            for (DetalleInventarioDTO detalleInventarioDTO : listDetalleInventarioDTO) {
                DetalleInventario detalleInventario = detalleInventarioDTOToDetalleInventario(detalleInventarioDTO);

                listDetalleInventario.add(detalleInventario);
            }

            return listDetalleInventario;
        } catch (Exception e) {
            throw e;
        }
    }
}
