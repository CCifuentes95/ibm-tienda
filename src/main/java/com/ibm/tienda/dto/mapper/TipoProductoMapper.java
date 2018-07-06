package com.ibm.tienda.dto.mapper;

import com.ibm.tienda.modelo.*;
import com.ibm.tienda.modelo.TipoProducto;
import com.ibm.tienda.modelo.control.*;
import com.ibm.tienda.modelo.dto.TipoProductoDTO;

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
public class TipoProductoMapper implements ITipoProductoMapper {
    private static final Logger log = LoggerFactory.getLogger(TipoProductoMapper.class);

    @Transactional(readOnly = true)
    public TipoProductoDTO tipoProductoToTipoProductoDTO(
        TipoProducto tipoProducto) throws Exception {
        try {
            TipoProductoDTO tipoProductoDTO = new TipoProductoDTO();

            tipoProductoDTO.setId(tipoProducto.getId());
            tipoProductoDTO.setCodigo((tipoProducto.getCodigo() != null)
                ? tipoProducto.getCodigo() : null);
            tipoProductoDTO.setNombre((tipoProducto.getNombre() != null)
                ? tipoProducto.getNombre() : null);

            return tipoProductoDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public TipoProducto tipoProductoDTOToTipoProducto(
        TipoProductoDTO tipoProductoDTO) throws Exception {
        try {
            TipoProducto tipoProducto = new TipoProducto();

            tipoProducto.setId(tipoProductoDTO.getId());
            tipoProducto.setCodigo((tipoProductoDTO.getCodigo() != null)
                ? tipoProductoDTO.getCodigo() : null);
            tipoProducto.setNombre((tipoProductoDTO.getNombre() != null)
                ? tipoProductoDTO.getNombre() : null);

            return tipoProducto;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<TipoProductoDTO> listTipoProductoToListTipoProductoDTO(
        List<TipoProducto> listTipoProducto) throws Exception {
        try {
            List<TipoProductoDTO> tipoProductoDTOs = new ArrayList<TipoProductoDTO>();

            for (TipoProducto tipoProducto : listTipoProducto) {
                TipoProductoDTO tipoProductoDTO = tipoProductoToTipoProductoDTO(tipoProducto);

                tipoProductoDTOs.add(tipoProductoDTO);
            }

            return tipoProductoDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<TipoProducto> listTipoProductoDTOToListTipoProducto(
        List<TipoProductoDTO> listTipoProductoDTO) throws Exception {
        try {
            List<TipoProducto> listTipoProducto = new ArrayList<TipoProducto>();

            for (TipoProductoDTO tipoProductoDTO : listTipoProductoDTO) {
                TipoProducto tipoProducto = tipoProductoDTOToTipoProducto(tipoProductoDTO);

                listTipoProducto.add(tipoProducto);
            }

            return listTipoProducto;
        } catch (Exception e) {
            throw e;
        }
    }
}
