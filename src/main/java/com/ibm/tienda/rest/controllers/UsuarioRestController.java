package com.ibm.tienda.rest.controllers;

import com.ibm.tienda.dto.mapper.IUsuarioMapper;
import com.ibm.tienda.modelo.*;
import com.ibm.tienda.modelo.dto.UsuarioDTO;
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
@RequestMapping("/usuario")
public class UsuarioRestController {
    private static final Logger log = LoggerFactory.getLogger(UsuarioRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private IUsuarioMapper usuarioMapper;

    @PostMapping(value = "/saveUsuario")
    public void saveUsuario(@RequestBody
    UsuarioDTO usuarioDTO) throws Exception {
        try {
            Usuario usuario = usuarioMapper.usuarioDTOToUsuario(usuarioDTO);

            businessDelegatorView.saveUsuario(usuario);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteUsuario/{id}")
    public void deleteUsuario(@PathVariable("id")
    Long id) throws Exception {
        try {
            Usuario usuario = businessDelegatorView.getUsuario(id);

            businessDelegatorView.deleteUsuario(usuario);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateUsuario/")
    public void updateUsuario(@RequestBody
    UsuarioDTO usuarioDTO) throws Exception {
        try {
            Usuario usuario = usuarioMapper.usuarioDTOToUsuario(usuarioDTO);

            businessDelegatorView.updateUsuario(usuario);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataUsuario")
    public List<UsuarioDTO> getDataUsuario() throws Exception {
        try {
            return businessDelegatorView.getDataUsuario();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getUsuario/{id}")
    public UsuarioDTO getUsuario(@PathVariable("id")
    Long id) throws Exception {
        try {
            Usuario usuario = businessDelegatorView.getUsuario(id);

            return usuarioMapper.usuarioToUsuarioDTO(usuario);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
