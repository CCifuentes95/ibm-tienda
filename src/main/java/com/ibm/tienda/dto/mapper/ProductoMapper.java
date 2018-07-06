package com.ibm.tienda.dto.mapper;

import com.ibm.tienda.modelo.*;
import com.ibm.tienda.modelo.Producto;
import com.ibm.tienda.modelo.control.*;
import com.ibm.tienda.modelo.dto.ProductoDTO;

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
public class ProductoMapper implements IProductoMapper {
    private static final Logger log = LoggerFactory.getLogger(ProductoMapper.class);

    /**
    * Logic injected by Spring that manages TipoProducto entities
    *
    */
    @Autowired
    ITipoProductoLogic logicTipoProducto1;

    @Transactional(readOnly = true)
    public ProductoDTO productoToProductoDTO(Producto producto)
        throws Exception {
        try {
            ProductoDTO productoDTO = new ProductoDTO();

            productoDTO.setId(producto.getId());
            productoDTO.setCodigo((producto.getCodigo() != null)   ? producto.getCodigo() : null);
            productoDTO.setNombre((producto.getNombre() != null)  ? producto.getNombre() : null);
            productoDTO.setId_TipoProducto((producto.getTipoProducto() != null)  ? producto.getTipoProducto().getId() : null);

            return productoDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Producto productoDTOToProducto(ProductoDTO productoDTO)
        throws Exception {
        try {
            Producto producto = new Producto();

            producto.setId(productoDTO.getId());
            producto.setCodigo((productoDTO.getCodigo() != null) ? productoDTO.getCodigo() : null);
            producto.setNombre((productoDTO.getNombre() != null) ? productoDTO.getNombre() : null);

            TipoProducto tipoProducto = null;

            if (productoDTO.getId_TipoProducto() != null) {
                tipoProducto = logicTipoProducto1.getTipoProducto(productoDTO.getId_TipoProducto());
                producto.setTipoProducto(tipoProducto);
            }

            return producto;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<ProductoDTO> listProductoToListProductoDTO(
        List<Producto> listProducto) throws Exception {
        try {
            List<ProductoDTO> productoDTOs = new ArrayList<ProductoDTO>();

            for (Producto producto : listProducto) {
                ProductoDTO productoDTO = productoToProductoDTO(producto);

                productoDTOs.add(productoDTO);
            }

            return productoDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<Producto> listProductoDTOToListProducto(
        List<ProductoDTO> listProductoDTO) throws Exception {
        try {
            List<Producto> listProducto = new ArrayList<Producto>();

            for (ProductoDTO productoDTO : listProductoDTO) {
                Producto producto = productoDTOToProducto(productoDTO);

                listProducto.add(producto);
            }

            return listProducto;
        } catch (Exception e) {
            throw e;
        }
    }
}
