package com.ibm.tienda.rest.controllers;

import com.ibm.tienda.dto.mapper.IProductoMapper;
import com.ibm.tienda.modelo.*;
import com.ibm.tienda.modelo.dto.ProductoDTO;
import com.ibm.tienda.presentation.businessDelegate.IBusinessDelegatorView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/producto")
public class ProductoRestController {
    private static final Logger log = LoggerFactory.getLogger(ProductoRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private IProductoMapper productoMapper;

    @PostMapping(value = "/saveProducto")
    public void saveProducto(@RequestBody
    ProductoDTO productoDTO) throws Exception {
        try {
            Producto producto = productoMapper.productoDTOToProducto(productoDTO);

            businessDelegatorView.saveProducto(producto);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteProducto/{id}")
    public void deleteProducto(@PathVariable("id")
    Long id) throws Exception {
        try {
            Producto producto = businessDelegatorView.getProducto(id);

            businessDelegatorView.deleteProducto(producto);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateProducto/")
    public void updateProducto(@RequestBody
    ProductoDTO productoDTO) throws Exception {
        try {
            Producto producto = productoMapper.productoDTOToProducto(productoDTO);

            businessDelegatorView.updateProducto(producto);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataProducto")
    public List<ProductoDTO> getDataProducto() throws Exception {
        try {
            return businessDelegatorView.getDataProducto();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getProducto/{id}")
    public ProductoDTO getProducto(@PathVariable("id")
    Long id) throws Exception {
        try {
            Producto producto = businessDelegatorView.getProducto(id);

            return productoMapper.productoToProductoDTO(producto);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
    
    @GetMapping(value = "/getProductoByTipoProducto/{id}")
    public List<ProductoDTO> getProductoByTipoProducto(@PathVariable("id")
    Long id) throws Exception {
        try {
            return businessDelegatorView.getProductosDTOByTipoProducto(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
