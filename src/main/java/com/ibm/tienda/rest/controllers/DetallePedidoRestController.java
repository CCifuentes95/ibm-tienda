package com.ibm.tienda.rest.controllers;

import com.ibm.tienda.dto.mapper.IDetallePedidoMapper;
import com.ibm.tienda.modelo.*;
import com.ibm.tienda.modelo.dto.DetallePedidoDTO;
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
@RequestMapping("/detallePedido")
public class DetallePedidoRestController {
    private static final Logger log = LoggerFactory.getLogger(DetallePedidoRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private IDetallePedidoMapper detallePedidoMapper;

    @PostMapping(value = "/saveDetallePedido")
    public void saveDetallePedido(@RequestBody
    DetallePedidoDTO detallePedidoDTO) throws Exception {
        try {
            DetallePedido detallePedido = detallePedidoMapper.detallePedidoDTOToDetallePedido(detallePedidoDTO);

            businessDelegatorView.saveDetallePedido(detallePedido);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteDetallePedido/{id}")
    public void deleteDetallePedido(@PathVariable("id")
    Long id) throws Exception {
        try {
            DetallePedido detallePedido = businessDelegatorView.getDetallePedido(id);

            businessDelegatorView.deleteDetallePedido(detallePedido);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateDetallePedido/")
    public void updateDetallePedido(
        @RequestBody
    DetallePedidoDTO detallePedidoDTO) throws Exception {
        try {
            DetallePedido detallePedido = detallePedidoMapper.detallePedidoDTOToDetallePedido(detallePedidoDTO);

            businessDelegatorView.updateDetallePedido(detallePedido);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataDetallePedido")
    public List<DetallePedidoDTO> getDataDetallePedido()
        throws Exception {
        try {
            return businessDelegatorView.getDataDetallePedido();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDetallePedido/{id}")
    public DetallePedidoDTO getDetallePedido(@PathVariable("id")
    Long id) throws Exception {
        try {
            DetallePedido detallePedido = businessDelegatorView.getDetallePedido(id);

            return detallePedidoMapper.detallePedidoToDetallePedidoDTO(detallePedido);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
