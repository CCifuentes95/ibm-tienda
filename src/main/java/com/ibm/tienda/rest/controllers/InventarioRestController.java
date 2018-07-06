package com.ibm.tienda.rest.controllers;

import com.ibm.tienda.dto.mapper.IInventarioMapper;
import com.ibm.tienda.modelo.*;
import com.ibm.tienda.modelo.dto.InventarioDTO;
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
@RequestMapping("/inventario")
public class InventarioRestController {
    private static final Logger log = LoggerFactory.getLogger(InventarioRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private IInventarioMapper inventarioMapper;

    @PostMapping(value = "/saveInventario")
    public void saveInventario(@RequestBody
    InventarioDTO inventarioDTO) throws Exception {
        try {
            Inventario inventario = inventarioMapper.inventarioDTOToInventario(inventarioDTO);

            businessDelegatorView.saveInventario(inventario);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteInventario/{id}")
    public void deleteInventario(@PathVariable("id")
    Long id) throws Exception {
        try {
            Inventario inventario = businessDelegatorView.getInventario(id);

            businessDelegatorView.deleteInventario(inventario);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateInventario/")
    public void updateInventario(@RequestBody
    InventarioDTO inventarioDTO) throws Exception {
        try {
            Inventario inventario = inventarioMapper.inventarioDTOToInventario(inventarioDTO);

            businessDelegatorView.updateInventario(inventario);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataInventario")
    public List<InventarioDTO> getDataInventario() throws Exception {
        try {
            return businessDelegatorView.getDataInventario();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getInventario/{id}")
    public InventarioDTO getInventario(@PathVariable("id")
    Long id) throws Exception {
        try {
            Inventario inventario = businessDelegatorView.getInventario(id);

            return inventarioMapper.inventarioToInventarioDTO(inventario);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
