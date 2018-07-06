package com.ibm.tienda.dto.mapper;

import com.ibm.tienda.modelo.*;
import com.ibm.tienda.modelo.Empresa;
import com.ibm.tienda.modelo.control.*;
import com.ibm.tienda.modelo.dto.EmpresaDTO;

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
public class EmpresaMapper implements IEmpresaMapper {
    private static final Logger log = LoggerFactory.getLogger(EmpresaMapper.class);

    @Transactional(readOnly = true)
    public EmpresaDTO empresaToEmpresaDTO(Empresa empresa)
        throws Exception {
        try {
            EmpresaDTO empresaDTO = new EmpresaDTO();

            empresaDTO.setId(empresa.getId());
            empresaDTO.setContacto((empresa.getContacto() != null)
                ? empresa.getContacto() : null);
            empresaDTO.setDescripcion((empresa.getDescripcion() != null)
                ? empresa.getDescripcion() : null);
            empresaDTO.setImagen((empresa.getImagen() != null)
                ? empresa.getImagen() : null);
            empresaDTO.setNombre((empresa.getNombre() != null)
                ? empresa.getNombre() : null);

            return empresaDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Empresa empresaDTOToEmpresa(EmpresaDTO empresaDTO)
        throws Exception {
        try {
            Empresa empresa = new Empresa();

            empresa.setId(empresaDTO.getId());
            empresa.setContacto((empresaDTO.getContacto() != null)
                ? empresaDTO.getContacto() : null);
            empresa.setDescripcion((empresaDTO.getDescripcion() != null)
                ? empresaDTO.getDescripcion() : null);
            empresa.setImagen((empresaDTO.getImagen() != null)
                ? empresaDTO.getImagen() : null);
            empresa.setNombre((empresaDTO.getNombre() != null)
                ? empresaDTO.getNombre() : null);

            return empresa;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<EmpresaDTO> listEmpresaToListEmpresaDTO(
        List<Empresa> listEmpresa) throws Exception {
        try {
            List<EmpresaDTO> empresaDTOs = new ArrayList<EmpresaDTO>();

            for (Empresa empresa : listEmpresa) {
                EmpresaDTO empresaDTO = empresaToEmpresaDTO(empresa);

                empresaDTOs.add(empresaDTO);
            }

            return empresaDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<Empresa> listEmpresaDTOToListEmpresa(
        List<EmpresaDTO> listEmpresaDTO) throws Exception {
        try {
            List<Empresa> listEmpresa = new ArrayList<Empresa>();

            for (EmpresaDTO empresaDTO : listEmpresaDTO) {
                Empresa empresa = empresaDTOToEmpresa(empresaDTO);

                listEmpresa.add(empresa);
            }

            return listEmpresa;
        } catch (Exception e) {
            throw e;
        }
    }
}
