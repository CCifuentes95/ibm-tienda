package com.ibm.tienda.dto.mapper;

import com.ibm.tienda.modelo.*;
import com.ibm.tienda.modelo.TipoIdentificacion;
import com.ibm.tienda.modelo.control.*;
import com.ibm.tienda.modelo.dto.TipoIdentificacionDTO;

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
public class TipoIdentificacionMapper implements ITipoIdentificacionMapper {
    private static final Logger log = LoggerFactory.getLogger(TipoIdentificacionMapper.class);

    @Transactional(readOnly = true)
    public TipoIdentificacionDTO tipoIdentificacionToTipoIdentificacionDTO(
        TipoIdentificacion tipoIdentificacion) throws Exception {
        try {
            TipoIdentificacionDTO tipoIdentificacionDTO = new TipoIdentificacionDTO();

            tipoIdentificacionDTO.setId(tipoIdentificacion.getId());
            tipoIdentificacionDTO.setCodigo((tipoIdentificacion.getCodigo() != null)
                ? tipoIdentificacion.getCodigo() : null);
            tipoIdentificacionDTO.setNombre((tipoIdentificacion.getNombre() != null)
                ? tipoIdentificacion.getNombre() : null);

            return tipoIdentificacionDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public TipoIdentificacion tipoIdentificacionDTOToTipoIdentificacion(
        TipoIdentificacionDTO tipoIdentificacionDTO) throws Exception {
        try {
            TipoIdentificacion tipoIdentificacion = new TipoIdentificacion();

            tipoIdentificacion.setId(tipoIdentificacionDTO.getId());
            tipoIdentificacion.setCodigo((tipoIdentificacionDTO.getCodigo() != null)
                ? tipoIdentificacionDTO.getCodigo() : null);
            tipoIdentificacion.setNombre((tipoIdentificacionDTO.getNombre() != null)
                ? tipoIdentificacionDTO.getNombre() : null);

            return tipoIdentificacion;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<TipoIdentificacionDTO> listTipoIdentificacionToListTipoIdentificacionDTO(
        List<TipoIdentificacion> listTipoIdentificacion)
        throws Exception {
        try {
            List<TipoIdentificacionDTO> tipoIdentificacionDTOs = new ArrayList<TipoIdentificacionDTO>();

            for (TipoIdentificacion tipoIdentificacion : listTipoIdentificacion) {
                TipoIdentificacionDTO tipoIdentificacionDTO = tipoIdentificacionToTipoIdentificacionDTO(tipoIdentificacion);

                tipoIdentificacionDTOs.add(tipoIdentificacionDTO);
            }

            return tipoIdentificacionDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<TipoIdentificacion> listTipoIdentificacionDTOToListTipoIdentificacion(
        List<TipoIdentificacionDTO> listTipoIdentificacionDTO)
        throws Exception {
        try {
            List<TipoIdentificacion> listTipoIdentificacion = new ArrayList<TipoIdentificacion>();

            for (TipoIdentificacionDTO tipoIdentificacionDTO : listTipoIdentificacionDTO) {
                TipoIdentificacion tipoIdentificacion = tipoIdentificacionDTOToTipoIdentificacion(tipoIdentificacionDTO);

                listTipoIdentificacion.add(tipoIdentificacion);
            }

            return listTipoIdentificacion;
        } catch (Exception e) {
            throw e;
        }
    }
}
