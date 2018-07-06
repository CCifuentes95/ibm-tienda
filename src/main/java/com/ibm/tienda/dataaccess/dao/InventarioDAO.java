package com.ibm.tienda.dataaccess.dao;

import com.ibm.tienda.dataaccess.api.JpaDaoImpl;
import com.ibm.tienda.modelo.Inventario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * A data access object (DAO) providing persistence and search support for
 * Inventario entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.Inventario
 */
@Scope("singleton")
@Repository("InventarioDAO")
public class InventarioDAO extends JpaDaoImpl<Inventario, Long>
    implements IInventarioDAO {
    private static final Logger log = LoggerFactory.getLogger(InventarioDAO.class);
    @PersistenceContext
    private EntityManager entityManager;

    public static IInventarioDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IInventarioDAO) ctx.getBean("InventarioDAO");
    }
}
