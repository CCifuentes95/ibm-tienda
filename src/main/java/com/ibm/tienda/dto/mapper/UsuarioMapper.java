package com.ibm.tienda.dto.mapper;

import com.ibm.tienda.modelo.*;
import com.ibm.tienda.modelo.Usuario;
import com.ibm.tienda.modelo.control.*;
import com.ibm.tienda.modelo.dto.UsuarioDTO;

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
public class UsuarioMapper implements IUsuarioMapper {
    private static final Logger log = LoggerFactory.getLogger(UsuarioMapper.class);

    /**
    * Logic injected by Spring that manages TipoIdentificacion entities
    *
    */
    @Autowired
    ITipoIdentificacionLogic logicTipoIdentificacion1;

    @Transactional(readOnly = true)
    public UsuarioDTO usuarioToUsuarioDTO(Usuario usuario)
        throws Exception {
        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO();

            usuarioDTO.setId(usuario.getId());
            usuarioDTO.setApellido((usuario.getApellido() != null)
                ? usuario.getApellido() : null);
            usuarioDTO.setContrasena((usuario.getContrasena() != null)
                ? usuario.getContrasena() : null);
            usuarioDTO.setEmail((usuario.getEmail() != null)
                ? usuario.getEmail() : null);
            usuarioDTO.setFechaNacimiento(usuario.getFechaNacimiento());
            usuarioDTO.setIdentificacion((usuario.getIdentificacion() != null)
                ? usuario.getIdentificacion() : null);
            usuarioDTO.setNombre((usuario.getNombre() != null)
                ? usuario.getNombre() : null);
            usuarioDTO.setId_TipoIdentificacion((usuario.getTipoIdentificacion()
                                                        .getId() != null)
                ? usuario.getTipoIdentificacion().getId() : null);

            return usuarioDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Usuario usuarioDTOToUsuario(UsuarioDTO usuarioDTO)
        throws Exception {
        try {
            Usuario usuario = new Usuario();

            usuario.setId(usuarioDTO.getId());
            usuario.setApellido((usuarioDTO.getApellido() != null)
                ? usuarioDTO.getApellido() : null);
            usuario.setContrasena((usuarioDTO.getContrasena() != null)
                ? usuarioDTO.getContrasena() : null);
            usuario.setEmail((usuarioDTO.getEmail() != null)
                ? usuarioDTO.getEmail() : null);
            usuario.setFechaNacimiento(usuarioDTO.getFechaNacimiento());
            usuario.setIdentificacion((usuarioDTO.getIdentificacion() != null)
                ? usuarioDTO.getIdentificacion() : null);
            usuario.setNombre((usuarioDTO.getNombre() != null)
                ? usuarioDTO.getNombre() : null);

            TipoIdentificacion tipoIdentificacion = new TipoIdentificacion();

            if (usuarioDTO.getId_TipoIdentificacion() != null) {
                tipoIdentificacion = logicTipoIdentificacion1.getTipoIdentificacion(usuarioDTO.getId_TipoIdentificacion());
            }

            if (tipoIdentificacion != null) {
                usuario.setTipoIdentificacion(tipoIdentificacion);
            }

            return usuario;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<UsuarioDTO> listUsuarioToListUsuarioDTO(
        List<Usuario> listUsuario) throws Exception {
        try {
            List<UsuarioDTO> usuarioDTOs = new ArrayList<UsuarioDTO>();

            for (Usuario usuario : listUsuario) {
                UsuarioDTO usuarioDTO = usuarioToUsuarioDTO(usuario);

                usuarioDTOs.add(usuarioDTO);
            }

            return usuarioDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<Usuario> listUsuarioDTOToListUsuario(
        List<UsuarioDTO> listUsuarioDTO) throws Exception {
        try {
            List<Usuario> listUsuario = new ArrayList<Usuario>();

            for (UsuarioDTO usuarioDTO : listUsuarioDTO) {
                Usuario usuario = usuarioDTOToUsuario(usuarioDTO);

                listUsuario.add(usuario);
            }

            return listUsuario;
        } catch (Exception e) {
            throw e;
        }
    }
}
