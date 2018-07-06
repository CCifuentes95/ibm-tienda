package com.ibm.tienda.dto.mapper;

import com.ibm.tienda.modelo.Empresa;
import com.ibm.tienda.modelo.dto.EmpresaDTO;

import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public interface IEmpresaMapper {
    public EmpresaDTO empresaToEmpresaDTO(Empresa empresa)
        throws Exception;

    public Empresa empresaDTOToEmpresa(EmpresaDTO empresaDTO)
        throws Exception;

    public List<EmpresaDTO> listEmpresaToListEmpresaDTO(List<Empresa> empresas)
        throws Exception;

    public List<Empresa> listEmpresaDTOToListEmpresa(
        List<EmpresaDTO> empresaDTOs) throws Exception;
}
