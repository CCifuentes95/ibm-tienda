package com.ibm.tienda.test;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
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

import com.ibm.tienda.modelo.DetallePedido;
import com.ibm.tienda.modelo.Pedido;
import com.ibm.tienda.modelo.Producto;
import com.ibm.tienda.modelo.TipoIdentificacion;
import com.ibm.tienda.modelo.Usuario;
import com.ibm.tienda.modelo.control.IDetallePedidoLogic;
import com.ibm.tienda.modelo.control.IPedidoLogic;
import com.ibm.tienda.modelo.control.IProductoLogic;
import com.ibm.tienda.modelo.control.ITipoIdentificacionLogic;
import com.ibm.tienda.modelo.control.IUsuarioLogic;
import com.ibm.tienda.modelo.dto.DetallePedidoDTO;
import com.ibm.tienda.utilities.Utilities;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class TestPedidos {
	
	private static final Logger log = LoggerFactory.getLogger(TestPedidos.class);
	
	@Autowired
	private IPedidoLogic pedidoLogic;
	
	@Autowired
	private IDetallePedidoLogic detallePedidoLogic;
	
	@Autowired
	private IProductoLogic productoLogic;
	
	@Autowired
	private IUsuarioLogic usuarioLogic ;
	
	@Autowired
	private ITipoIdentificacionLogic identificacionLogic;

	//@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void testGenerarPedidoSinMetodo() {
		try {
			//emulo Datos que llegan
			String nombre = "Camilo Andrés";
			String apellido = "Cifuentes Grass";
			Long idTipoId	= 1L;
			String identificacion = "1144079277";
			String direccion = "Av siempre viva";
			Date fechaPedido = new Date();
			
			//Emulo unos productos que selecciono
			Producto producto1 = productoLogic.getProducto(1L);
			Producto producto2 = productoLogic.getProducto(6L);
			Producto producto3 = productoLogic.getProducto(11L);
			
			//Asigno una lista de productos
			List<DetallePedido> detallePedidos = new ArrayList<>();

			//Asigno el primer producto
			DetallePedido detallePedido1 = new DetallePedido();
			detallePedido1.setProducto(producto1);
			detallePedido1.setCantidad(5);
			detallePedido1.setPrecio(5000d);
			
			//Asigno el segundo producto
			DetallePedido detallePedido2 = new DetallePedido();
			detallePedido2.setProducto(producto2);
			detallePedido2.setCantidad(2);
			detallePedido2.setPrecio(4000d);

			//Asigno el tercer producto
			DetallePedido detallePedido3 = new DetallePedido();
			detallePedido3.setProducto(producto3);
			detallePedido3.setCantidad(3);
			detallePedido3.setPrecio(3000d);
			
			//guardo los detalles
			detallePedidos.add(detallePedido1);
			detallePedidos.add(detallePedido2);
			detallePedidos.add(detallePedido3);

			//-----------------------------
			
			Pedido pedido = new Pedido();
			
			pedido.setCodigo(Utilities.generarToken());//Genero un codigo aleatorio
			pedido.setFechaPedido(fechaPedido);//Asigno la fecha
			pedido.setFechaCreacion(new Date());//asigno la fecha de creacion
			pedido.setDireccion(direccion);//asigno la direccion
			
			
			Usuario usuario = usuarioLogic.getUsuarioByIdentificacion(identificacion);//Consulto el usuario dado la identificacion
			
			//Si el usuario no existe
			if(usuario == null){
				//Creo el nuevo usuario
				usuario = new Usuario();
				
				usuario.setNombre(nombre);//asigno el nombre
				usuario.setApellido(apellido);//asigno el apellido
				usuario.setIdentificacion(identificacion);//asigno la identificacion
				
				TipoIdentificacion tipoIdentificacion = identificacionLogic.getTipoIdentificacion(idTipoId);//Consulto el tipo de identificacion
				if(tipoIdentificacion != null){//si existe el tipo de identificacion
					usuario.setTipoIdentificacion(tipoIdentificacion);//asigno el tipo de identificacion
				}
				//Guardo el usuario
				usuarioLogic.saveUsuario(usuario);
				
			}
			
			//Asigno el usuario
			pedido.setUsuario(usuario);
			
			//Guardo el peddo
			pedidoLogic.savePedido(pedido);
			
			//Asigno los detalles del pedido
			
			//Si existen productos seleccionados
			if(detallePedidos != null && !detallePedidos.isEmpty()){
				//Recorro los Productos Seleccionados
				for (DetallePedido detallePedidoSeleccionado : detallePedidos) {
					detallePedidoSeleccionado.setPedido(pedido);//Asigno los detalles
					
					detallePedidoLogic.saveDetallePedido(detallePedidoSeleccionado);//Guardo los detalles
				}
				
			}
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor= Exception.class)
	public void testRegistrarPedido() {
		try {
			//emulo Datos que llegan
			String nombre = "Camilo Andrés";
			String apellido = "Cifuentes Grass";
			Long idTipoId	= 1L;
			String identificacion = "1144079277";
			String direccion = "Av siempre viva";
			Date fechaPedido = new Date();
			
			//Emulo unos productos que selecciono
			Producto producto1 = productoLogic.getProducto(1L);
			Producto producto2 = productoLogic.getProducto(6L);
			Producto producto3 = productoLogic.getProducto(11L);
			
			//Asigno una lista de productos
			List<DetallePedidoDTO> detallePedidosDTO = new ArrayList<>();

			DetallePedidoDTO detallePedidoDTO1 = new DetallePedidoDTO();
			detallePedidoDTO1.setId_Producto(producto1.getId());
			detallePedidoDTO1.setCantidad(3);
			detallePedidoDTO1.setPrecio(3000d);
			
			DetallePedidoDTO detallePedidoDTO2 = new DetallePedidoDTO();
			detallePedidoDTO2.setId_Producto(producto2.getId());
			detallePedidoDTO2.setCantidad(4);
			detallePedidoDTO2.setPrecio(4000d);
			
			DetallePedidoDTO detallePedidoDTO3 = new DetallePedidoDTO();
			detallePedidoDTO3.setId_Producto(producto3.getId());
			detallePedidoDTO3.setCantidad(5);
			detallePedidoDTO3.setPrecio(5000d);
			
			//guardo los detalles
			detallePedidosDTO.add(detallePedidoDTO1);
			detallePedidosDTO.add(detallePedidoDTO2);
			detallePedidosDTO.add(detallePedidoDTO3);
			
			
			//Registro el pedido
			pedidoLogic.registrarPedido(nombre, apellido, idTipoId, identificacion, direccion, fechaPedido, detallePedidosDTO);
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
