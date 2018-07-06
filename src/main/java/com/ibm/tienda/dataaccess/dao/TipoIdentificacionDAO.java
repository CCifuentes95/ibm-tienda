package com.ibm.tienda.dataaccess.dao;

import com.ibm.tienda.dataaccess.api.JpaDaoImpl;
import com.ibm.tienda.modelo.TipoIdentificacion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * A data access object (DAO) providing persistence and search support for
 * TipoIdentificacion entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.TipoIdentificacion
 */
@Scope("singleton")
@Repository("TipoIdentificacionDAO")
public class TipoIdentificacionDAO extends JpaDaoImpl<TipoIdentificacion, Long>
    implements ITipoIdentificacionDAO {
    private static final Logger log = LoggerFactory.getLogger(TipoIdentificacionDAO.class);
    @PersistenceContext
    private EntityManager entityManager;

    public static ITipoIdentificacionDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (ITipoIdentificacionDAO) ctx.getBean("TipoIdentificacionDAO");
    }
}
