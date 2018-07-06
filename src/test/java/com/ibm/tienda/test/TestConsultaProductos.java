package com.ibm.tienda.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.tienda.modelo.control.IDetalleInventarioLogic;
import com.ibm.tienda.modelo.dto.DetalleInventarioDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class TestConsultaProductos {
	
	private static final Logger log = LoggerFactory.getLogger(TestConsultaProductos.class);
	
	@Autowired
	private IDetalleInventarioLogic detalleInventarioLogic;

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void test() {
		try {
			
			List<DetalleInventarioDTO> list = detalleInventarioLogic.getDataDetalleInventario();
			
			for (DetalleInventarioDTO detalleInventarioDTO : list) {
				log.info("El producto: "+detalleInventarioDTO.getNombrePoducto() + ", tiene un precio de: $"+detalleInventarioDTO.getPrecioBase()+",  y una cantidad de "+detalleInventarioDTO.getCantidad());
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
