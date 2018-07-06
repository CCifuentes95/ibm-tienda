package com.ibm.tienda.rest.controllers;

import com.ibm.tienda.dto.mapper.IDetalleInventarioMapper;
import com.ibm.tienda.modelo.*;
import com.ibm.tienda.modelo.dto.DetalleInventarioDTO;
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
@RequestMapping("/detalleInventario")
public class DetalleInventarioRestController {
    private static final Logger log = LoggerFactory.getLogger(DetalleInventarioRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private IDetalleInventarioMapper detalleInventarioMapper;

    @PostMapping(value = "/saveDetalleInventario")
    public void saveDetalleInventario(
        @RequestBody
    DetalleInventarioDTO detalleInventarioDTO) throws Exception {
        try {
            DetalleInventario detalleInventario = detalleInventarioMapper.detalleInventarioDTOToDetalleInventario(detalleInventarioDTO);

            businessDelegatorView.saveDetalleInventario(detalleInventario);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteDetalleInventario/{id}")
    public void deleteDetalleInventario(@PathVariable("id")
    Long id) throws Exception {
        try {
            DetalleInventario detalleInventario = businessDelegatorView.getDetalleInventario(id);

            businessDelegatorView.deleteDetalleInventario(detalleInventario);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateDetalleInventario/")
    public void updateDetalleInventario(
        @RequestBody
    DetalleInventarioDTO detalleInventarioDTO) throws Exception {
        try {
            DetalleInventario detalleInventario = detalleInventarioMapper.detalleInventarioDTOToDetalleInventario(detalleInventarioDTO);

            businessDelegatorView.updateDetalleInventario(detalleInventario);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataDetalleInventario")
    public List<DetalleInventarioDTO> getDataDetalleInventario()
        throws Exception {
        try {
            return businessDelegatorView.getDataDetalleInventario();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDetalleInventario/{id}")
    public DetalleInventarioDTO getDetalleInventario(@PathVariable("id")
    Long id) throws Exception {
        try {
            DetalleInventario detalleInventario = businessDelegatorView.getDetalleInventario(id);

            return detalleInventarioMapper.detalleInventarioToDetalleInventarioDTO(detalleInventario);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
