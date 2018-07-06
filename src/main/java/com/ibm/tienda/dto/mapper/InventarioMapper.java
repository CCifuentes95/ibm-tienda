package com.ibm.tienda.dto.mapper;

import com.ibm.tienda.modelo.*;
import com.ibm.tienda.modelo.Inventario;
import com.ibm.tienda.modelo.control.*;
import com.ibm.tienda.modelo.dto.InventarioDTO;

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
public class InventarioMapper implements IInventarioMapper {
    private static final Logger log = LoggerFactory.getLogger(InventarioMapper.class);

    /**
    * Logic injected by Spring that manages Empresa entities
    *
    */
    @Autowired
    IEmpresaLogic logicEmpresa1;

    @Transactional(readOnly = true)
    public InventarioDTO inventarioToInventarioDTO(Inventario inventario)
        throws Exception {
        try {
            InventarioDTO inventarioDTO = new InventarioDTO();

            inventarioDTO.setId(inventario.getId());
            inventarioDTO.setFechaCreacion(inventario.getFechaCreacion());

            if (inventario.getEmpresa() != null) {
                inventarioDTO.setId_Empresa(inventario.getEmpresa().getId());
            } else {
                inventarioDTO.setId_Empresa(null);
            }

            return inventarioDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Inventario inventarioDTOToInventario(InventarioDTO inventarioDTO)
        throws Exception {
        try {
            Inventario inventario = new Inventario();

            inventario.setId(inventarioDTO.getId());
            inventario.setFechaCreacion(inventarioDTO.getFechaCreacion());

            Empresa empresa = new Empresa();

            if (inventarioDTO.getId_Empresa() != null) {
                empresa = logicEmpresa1.getEmpresa(inventarioDTO.getId_Empresa());
            }

            if (empresa != null) {
                inventario.setEmpresa(empresa);
            }

            return inventario;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<InventarioDTO> listInventarioToListInventarioDTO(
        List<Inventario> listInventario) throws Exception {
        try {
            List<InventarioDTO> inventarioDTOs = new ArrayList<InventarioDTO>();

            for (Inventario inventario : listInventario) {
                InventarioDTO inventarioDTO = inventarioToInventarioDTO(inventario);

                inventarioDTOs.add(inventarioDTO);
            }

            return inventarioDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<Inventario> listInventarioDTOToListInventario(
        List<InventarioDTO> listInventarioDTO) throws Exception {
        try {
            List<Inventario> listInventario = new ArrayList<Inventario>();

            for (InventarioDTO inventarioDTO : listInventarioDTO) {
                Inventario inventario = inventarioDTOToInventario(inventarioDTO);

                listInventario.add(inventario);
            }

            return listInventario;
        } catch (Exception e) {
            throw e;
        }
    }
}
