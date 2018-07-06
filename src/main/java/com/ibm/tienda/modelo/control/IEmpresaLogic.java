package com.ibm.tienda.modelo.control;

import com.ibm.tienda.modelo.Empresa;
import com.ibm.tienda.modelo.dto.EmpresaDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public interface IEmpresaLogic {
    public List<Empresa> getEmpresa() throws Exception;

    /**
         * Save an new Empresa entity
         */
    public void saveEmpresa(Empresa entity) throws Exception;

    /**
         * Delete an existing Empresa entity
         *
         */
    public void deleteEmpresa(Empresa entity) throws Exception;

    /**
        * Update an existing Empresa entity
        *
        */
    public void updateEmpresa(Empresa entity) throws Exception;

    /**
         * Load an existing Empresa entity
         *
         */
    public Empresa getEmpresa(Long id) throws Exception;

    public List<Empresa> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Empresa> findPageEmpresa(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberEmpresa() throws Exception;

    public List<EmpresaDTO> getDataEmpresa() throws Exception;

    public void validateEmpresa(Empresa empresa) throws Exception;
}
