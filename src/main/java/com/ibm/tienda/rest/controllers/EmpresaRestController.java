package com.ibm.tienda.rest.controllers;

import com.ibm.tienda.dto.mapper.IEmpresaMapper;
import com.ibm.tienda.modelo.*;
import com.ibm.tienda.modelo.dto.EmpresaDTO;
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
@RequestMapping("/empresa")
public class EmpresaRestController {
    private static final Logger log = LoggerFactory.getLogger(EmpresaRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private IEmpresaMapper empresaMapper;

    @PostMapping(value = "/saveEmpresa")
    public void saveEmpresa(@RequestBody
    EmpresaDTO empresaDTO) throws Exception {
        try {
            Empresa empresa = empresaMapper.empresaDTOToEmpresa(empresaDTO);

            businessDelegatorView.saveEmpresa(empresa);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteEmpresa/{id}")
    public void deleteEmpresa(@PathVariable("id")
    Long id) throws Exception {
        try {
            Empresa empresa = businessDelegatorView.getEmpresa(id);

            businessDelegatorView.deleteEmpresa(empresa);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateEmpresa/")
    public void updateEmpresa(@RequestBody
    EmpresaDTO empresaDTO) throws Exception {
        try {
            Empresa empresa = empresaMapper.empresaDTOToEmpresa(empresaDTO);

            businessDelegatorView.updateEmpresa(empresa);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataEmpresa")
    public List<EmpresaDTO> getDataEmpresa() throws Exception {
        try {
            return businessDelegatorView.getDataEmpresa();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getEmpresa/{id}")
    public EmpresaDTO getEmpresa(@PathVariable("id")
    Long id) throws Exception {
        try {
            Empresa empresa = businessDelegatorView.getEmpresa(id);

            return empresaMapper.empresaToEmpresaDTO(empresa);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
