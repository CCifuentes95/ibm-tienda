package com.ibm.tienda.dto.mapper;

import com.ibm.tienda.modelo.TipoIdentificacion;
import com.ibm.tienda.modelo.dto.TipoIdentificacionDTO;

import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public interface ITipoIdentificacionMapper {
    public TipoIdentificacionDTO tipoIdentificacionToTipoIdentificacionDTO(
        TipoIdentificacion tipoIdentificacion) throws Exception;

    public TipoIdentificacion tipoIdentificacionDTOToTipoIdentificacion(
        TipoIdentificacionDTO tipoIdentificacionDTO) throws Exception;

    public List<TipoIdentificacionDTO> listTipoIdentificacionToListTipoIdentificacionDTO(
        List<TipoIdentificacion> tipoIdentificacions) throws Exception;

    public List<TipoIdentificacion> listTipoIdentificacionDTOToListTipoIdentificacion(
        List<TipoIdentificacionDTO> tipoIdentificacionDTOs)
        throws Exception;
}
