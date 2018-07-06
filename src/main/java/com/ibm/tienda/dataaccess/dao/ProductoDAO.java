package com.ibm.tienda.dataaccess.dao;

import com.ibm.tienda.dataaccess.api.JpaDaoImpl;
import com.ibm.tienda.modelo.Producto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * A data access object (DAO) providing persistence and search support for
 * Producto entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.Producto
 */
@Scope("singleton")
@Repository("ProductoDAO")
public class ProductoDAO extends JpaDaoImpl<Producto, Long>
    implements IProductoDAO {
    private static final Logger log = LoggerFactory.getLogger(ProductoDAO.class);
    @PersistenceContext
    private EntityManager entityManager;

    public static IProductoDAO getFromApplicationContext(ApplicationContext ctx) {
        return (IProductoDAO) ctx.getBean("ProductoDAO");
    }
}
