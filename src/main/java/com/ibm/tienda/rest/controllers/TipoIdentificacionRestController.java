package com.ibm.tienda.rest.controllers;

import com.ibm.tienda.dto.mapper.ITipoIdentificacionMapper;
import com.ibm.tienda.modelo.*;
import com.ibm.tienda.modelo.dto.TipoIdentificacionDTO;
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
@RequestMapping("/tipoIdentificacion")
public class TipoIdentificacionRestController {
    private static final Logger log = LoggerFactory.getLogger(TipoIdentificacionRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private ITipoIdentificacionMapper tipoIdentificacionMapper;

    @PostMapping(value = "/saveTipoIdentificacion")
    public void saveTipoIdentificacion(
        @RequestBody
    TipoIdentificacionDTO tipoIdentificacionDTO) throws Exception {
        try {
            TipoIdentificacion tipoIdentificacion = tipoIdentificacionMapper.tipoIdentificacionDTOToTipoIdentificacion(tipoIdentificacionDTO);

            businessDelegatorView.saveTipoIdentificacion(tipoIdentificacion);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteTipoIdentificacion/{id}")
    public void deleteTipoIdentificacion(@PathVariable("id")
    Long id) throws Exception {
        try {
            TipoIdentificacion tipoIdentificacion = businessDelegatorView.getTipoIdentificacion(id);

            businessDelegatorView.deleteTipoIdentificacion(tipoIdentificacion);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateTipoIdentificacion/")
    public void updateTipoIdentificacion(
        @RequestBody
    TipoIdentificacionDTO tipoIdentificacionDTO) throws Exception {
        try {
            TipoIdentificacion tipoIdentificacion = tipoIdentificacionMapper.tipoIdentificacionDTOToTipoIdentificacion(tipoIdentificacionDTO);

            businessDelegatorView.updateTipoIdentificacion(tipoIdentificacion);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataTipoIdentificacion")
    public List<TipoIdentificacionDTO> getDataTipoIdentificacion()
        throws Exception {
        try {
            return businessDelegatorView.getDataTipoIdentificacion();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getTipoIdentificacion/{id}")
    public TipoIdentificacionDTO getTipoIdentificacion(
        @PathVariable("id")
    Long id) throws Exception {
        try {
            TipoIdentificacion tipoIdentificacion = businessDelegatorView.getTipoIdentificacion(id);

            return tipoIdentificacionMapper.tipoIdentificacionToTipoIdentificacionDTO(tipoIdentificacion);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
