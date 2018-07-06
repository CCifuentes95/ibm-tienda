package com.ibm.tienda.rest.controllers;

import com.ibm.tienda.dto.mapper.ITipoProductoMapper;
import com.ibm.tienda.modelo.*;
import com.ibm.tienda.modelo.dto.TipoProductoDTO;
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
@RequestMapping("/tipoProducto")
public class TipoProductoRestController {
    private static final Logger log = LoggerFactory.getLogger(TipoProductoRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private ITipoProductoMapper tipoProductoMapper;

    @PostMapping(value = "/saveTipoProducto")
    public void saveTipoProducto(@RequestBody
    TipoProductoDTO tipoProductoDTO) throws Exception {
        try {
            TipoProducto tipoProducto = tipoProductoMapper.tipoProductoDTOToTipoProducto(tipoProductoDTO);

            businessDelegatorView.saveTipoProducto(tipoProducto);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteTipoProducto/{id}")
    public void deleteTipoProducto(@PathVariable("id")
    Long id) throws Exception {
        try {
            TipoProducto tipoProducto = businessDelegatorView.getTipoProducto(id);

            businessDelegatorView.deleteTipoProducto(tipoProducto);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateTipoProducto/")
    public void updateTipoProducto(@RequestBody
    TipoProductoDTO tipoProductoDTO) throws Exception {
        try {
            TipoProducto tipoProducto = tipoProductoMapper.tipoProductoDTOToTipoProducto(tipoProductoDTO);

            businessDelegatorView.updateTipoProducto(tipoProducto);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataTipoProducto")
    public List<TipoProductoDTO> getDataTipoProducto()
        throws Exception {
        try {
            return businessDelegatorView.getDataTipoProducto();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getTipoProducto/{id}")
    public TipoProductoDTO getTipoProducto(@PathVariable("id")
    Long id) throws Exception {
        try {
            TipoProducto tipoProducto = businessDelegatorView.getTipoProducto(id);

            return tipoProductoMapper.tipoProductoToTipoProductoDTO(tipoProducto);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
