package com.ibm.tienda.modelo.control;

import com.ibm.tienda.dataaccess.dao.*;
import com.ibm.tienda.dto.mapper.IPedidoMapper;
import com.ibm.tienda.exceptions.*;
import com.ibm.tienda.modelo.*;
import com.ibm.tienda.modelo.dto.DetallePedidoDTO;
import com.ibm.tienda.modelo.dto.PedidoDTO;
import com.ibm.tienda.utilities.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;


/**
 * @author Zathura Code Generator http://zathuracode.org
 * www.zathuracode.org
 *
 */
@Scope("singleton")
@Service("PedidoLogic")
public class PedidoLogic implements IPedidoLogic {
	private static final Logger log = LoggerFactory.getLogger(PedidoLogic.class);

	/**
	 * DAO injected by Spring that manages Pedido entities
	 *
	 */
	@Autowired
	private IPedidoDAO pedidoDAO;
	@Autowired
	private IPedidoMapper pedidoMapper;
	@Autowired
	private Validator validator;

	/**
	 * DAO injected by Spring that manages DetallePedido entities
	 *
	 */
	@Autowired
	private IDetallePedidoDAO detallePedidoDAO;

	/**
	 * Logic injected by Spring that manages Usuario entities
	 *
	 */
	@Autowired
	private IDetallePedidoLogic detallePedidoLogic;
	
	@Autowired
	private IProductoLogic productoLogic;
	
	@Autowired
	private IUsuarioLogic usuarioLogic ;
	
	@Autowired
	private ITipoIdentificacionLogic identificacionLogic;

	public void validatePedido(Pedido pedido) throws Exception {
		try {
			Set<ConstraintViolation<Pedido>> constraintViolations = validator.validate(pedido);

			if (constraintViolations.size() > 0) {
				StringBuilder strMessage = new StringBuilder();

				for (ConstraintViolation<Pedido> constraintViolation : constraintViolations) {
					strMessage.append(constraintViolation.getPropertyPath()
							.toString());
					strMessage.append(" - ");
					strMessage.append(constraintViolation.getMessage());
					strMessage.append(". \n");
				}

				throw new Exception(strMessage.toString());
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public List<Pedido> getPedido() throws Exception {
		log.debug("finding all Pedido instances");

		List<Pedido> list = new ArrayList<Pedido>();

		try {
			list = pedidoDAO.findAll();
		} catch (Exception e) {
			log.error("finding all Pedido failed", e);
			throw new ZMessManager().new GettingException(ZMessManager.ALL +
					"Pedido");
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void savePedido(Pedido entity) throws Exception {
		log.debug("saving Pedido instance");

		try {
			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("Pedido");
			}

			validatePedido(entity);

			//            if (getPedido(entity.getId()) != null) {
			//                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
			//            }

			pedidoDAO.save(entity);
			log.debug("save Pedido successful");
		} catch (Exception e) {
			log.error("save Pedido failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deletePedido(Pedido entity) throws Exception {
		log.debug("deleting Pedido instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Pedido");
		}

		if (entity.getId() == null) {
			throw new ZMessManager().new EmptyFieldException("id");
		}

		List<DetallePedido> detallePedidos = null;

		try {
			detallePedidos = detallePedidoDAO.findByProperty("pedido.id",
					entity.getId());

			if (Utilities.validationsList(detallePedidos) == true) {
				throw new ZMessManager().new DeletingException("detallePedidos");
			}

			pedidoDAO.deleteById(entity.getId());
			log.debug("delete Pedido successful");
		} catch (Exception e) {
			log.error("delete Pedido failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updatePedido(Pedido entity) throws Exception {
		log.debug("updating Pedido instance");

		try {
			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("Pedido");
			}

			validatePedido(entity);

			pedidoDAO.update(entity);

			log.debug("update Pedido successful");
		} catch (Exception e) {
			log.error("update Pedido failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public List<PedidoDTO> getDataPedido() throws Exception {
		try {
			List<Pedido> pedido = pedidoDAO.findAll();

			List<PedidoDTO> pedidoDTO = new ArrayList<PedidoDTO>();

			for (Pedido pedidoTmp : pedido) {
				PedidoDTO pedidoDTO2 = pedidoMapper.pedidoToPedidoDTO(pedidoTmp);
				pedidoDTO.add(pedidoDTO2);
			}

			return pedidoDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public Pedido getPedido(Long id) throws Exception {
		log.debug("getting Pedido instance");

		Pedido entity = null;

		try {
			entity = pedidoDAO.findById(id);
		} catch (Exception e) {
			log.error("get Pedido failed", e);
			throw new ZMessManager().new FindingException("Pedido");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public List<Pedido> findPagePedido(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
					throws Exception {
		List<Pedido> entity = null;

		try {
			entity = pedidoDAO.findPage(sortColumnName, sortAscending,
					startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("Pedido Count");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public Long findTotalNumberPedido() throws Exception {
		Long entity = null;

		try {
			entity = pedidoDAO.count();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("Pedido Count");
		} finally {
		}

		return entity;
	}

	/**
	 *
	 * @param varibles
	 *            este arreglo debera tener:
	 *
	 * [0] = String variable = (String) varibles[i]; representa como se llama la
	 * variable en el pojo
	 *
	 * [1] = Boolean booVariable = (Boolean) varibles[i + 1]; representa si el
	 * valor necesita o no ''(comillas simples)usado para campos de tipo string
	 *
	 * [2] = Object value = varibles[i + 2]; representa el valor que se va a
	 * buscar en la BD
	 *
	 * [3] = String comparator = (String) varibles[i + 3]; representa que tipo
	 * de busqueda voy a hacer.., ejemplo: where nombre=william o where nombre<>william,
	 * en este campo iria el tipo de comparador que quiero si es = o <>
	 *
	 * Se itera de 4 en 4..., entonces 4 registros del arreglo representan 1
	 * busqueda en un campo, si se ponen mas pues el continuara buscando en lo
	 * que se le ingresen en los otros 4
	 *
	 *
	 * @param variablesBetween
	 *
	 * la diferencia son estas dos posiciones
	 *
	 * [0] = String variable = (String) varibles[j]; la variable ne la BD que va
	 * a ser buscada en un rango
	 *
	 * [1] = Object value = varibles[j + 1]; valor 1 para buscar en un rango
	 *
	 * [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en un rango
	 * ejempolo: a > 1 and a < 5 --> 1 seria value y 5 seria value2
	 *
	 * [3] = String comparator1 = (String) varibles[j + 3]; comparador 1
	 * ejemplo: a comparator1 1 and a < 5
	 *
	 * [4] = String comparator2 = (String) varibles[j + 4]; comparador 2
	 * ejemplo: a comparador1>1  and a comparador2<5  (el original: a > 1 and a <
	 * 5) *
	 * @param variablesBetweenDates(en
	 *            este caso solo para mysql)
	 *  [0] = String variable = (String) varibles[k]; el nombre de la variable que hace referencia a
	 *            una fecha
	 *
	 * [1] = Object object1 = varibles[k + 2]; fecha 1 a comparar(deben ser
	 * dates)
	 *
	 * [2] = Object object2 = varibles[k + 3]; fecha 2 a comparar(deben ser
	 * dates)
	 *
	 * esto hace un between entre las dos fechas.
	 *
	 * @return lista con los objetos que se necesiten
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public List<Pedido> findByCriteria(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
					throws Exception {
		List<Pedido> list = new ArrayList<Pedido>();
		String where = new String();
		String tempWhere = new String();

		if (variables != null) {
			for (int i = 0; i < variables.length; i++) {
				if ((variables[i] != null) && (variables[i + 1] != null) &&
						(variables[i + 2] != null) &&
						(variables[i + 3] != null)) {
					String variable = (String) variables[i];
					Boolean booVariable = (Boolean) variables[i + 1];
					Object value = variables[i + 2];
					String comparator = (String) variables[i + 3];

					if (booVariable.booleanValue()) {
						tempWhere = (tempWhere.length() == 0)
								? ("(model." + variable + " " + comparator + " \'" +
										value + "\' )")
										: (tempWhere + " AND (model." + variable + " " +
												comparator + " \'" + value + "\' )");
					} else {
						tempWhere = (tempWhere.length() == 0)
								? ("(model." + variable + " " + comparator + " " +
										value + " )")
										: (tempWhere + " AND (model." + variable + " " +
												comparator + " " + value + " )");
					}
				}

				i = i + 3;
			}
		}

		if (variablesBetween != null) {
			for (int j = 0; j < variablesBetween.length; j++) {
				if ((variablesBetween[j] != null) &&
						(variablesBetween[j + 1] != null) &&
						(variablesBetween[j + 2] != null) &&
						(variablesBetween[j + 3] != null) &&
						(variablesBetween[j + 4] != null)) {
					String variable = (String) variablesBetween[j];
					Object value = variablesBetween[j + 1];
					Object value2 = variablesBetween[j + 2];
					String comparator1 = (String) variablesBetween[j + 3];
					String comparator2 = (String) variablesBetween[j + 4];
					tempWhere = (tempWhere.length() == 0)
							? ("(" + value + " " + comparator1 + " " + variable +
									" and " + variable + " " + comparator2 + " " + value2 +
									" )")
									: (tempWhere + " AND (" + value + " " + comparator1 +
											" " + variable + " and " + variable + " " +
											comparator2 + " " + value2 + " )");
				}

				j = j + 4;
			}
		}

		if (variablesBetweenDates != null) {
			for (int k = 0; k < variablesBetweenDates.length; k++) {
				if ((variablesBetweenDates[k] != null) &&
						(variablesBetweenDates[k + 1] != null) &&
						(variablesBetweenDates[k + 2] != null)) {
					String variable = (String) variablesBetweenDates[k];
					Object object1 = variablesBetweenDates[k + 1];
					Object object2 = variablesBetweenDates[k + 2];
					String value = null;
					String value2 = null;

					try {
						Date date1 = (Date) object1;
						Date date2 = (Date) object2;
						value = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date1);
						value2 = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date2);
					} catch (Exception e) {
						list = null;
						throw e;
					}

					tempWhere = (tempWhere.length() == 0)
							? ("(model." + variable + " between " + value +
									" and " + value2 + ")")
									: (tempWhere + " AND (model." + variable + " between " +
											value + " and " + value2 + ")");
				}

				k = k + 2;
			}
		}

		if (tempWhere.length() == 0) {
			where = null;
		} else {
			where = "(" + tempWhere + ")";
		}

		try {
			list = pedidoDAO.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ibm.tienda.modelo.control.IPedidoLogic#registrarPedido(java.lang.String, java.lang.String, java.lang.Long, java.lang.String, java.lang.String, java.util.Date, java.util.List)
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void registrarPedido(String nombre, String apellido, Long idTipoId, String identificacion, String direccion,
			Date fechaPedido, List<DetallePedidoDTO> detallePedidoDTOs) throws Exception {
		log.debug("Entro en registrarPedido");
		try {
			//Consulto todos los pedidos y los obtengo en una lista
			List<Producto> allProducts = productoLogic.getProducto();
			//Creo un HasMap para guardar todos los productos, con su id como llave y valor, para evitaar hace multiples llamados a la BD en un for
			Map<Long, Producto> mapProductos = new HashMap<>();
			
			//Si exsten productos
			if(allProducts != null && !allProducts.isEmpty()){
				//Recorro los productos
				for (Producto producto : allProducts) {
					mapProductos.put(producto.getId(), producto);//Los guardo en el Map
				}
			}else{
				throw new Exception("No se hallaron productos");
			}
			
			//1. Creo la cabecera del Pedido
			Pedido pedido = new Pedido();
			
			pedido.setCodigo(Utilities.generarToken());//Genero un codigo aleatorio
			pedido.setFechaPedido(fechaPedido);//Asigno la fecha
			pedido.setFechaCreacion(new Date());//asigno la fecha de creacion
			pedido.setDireccion(direccion);//asigno la direccion
			
			//Consulto el usuario dado la identificacion
			Usuario usuario = usuarioLogic.getUsuarioByIdentificacion(identificacion);
			
			//Si el usuario no existe
			if(usuario == null){
				//Creo el nuevo usuario
				usuario = new Usuario();
				
				usuario.setNombre(nombre);//asigno el nombre
				usuario.setApellido(apellido);//asigno el apellido
				usuario.setIdentificacion(identificacion);//asigno la identificacion
				
				//Consulto el tipo de identificacion
				TipoIdentificacion tipoIdentificacion = identificacionLogic.getTipoIdentificacion(idTipoId);
				//si existe el tipo de identificacion
				if(tipoIdentificacion != null){
					usuario.setTipoIdentificacion(tipoIdentificacion);//asigno el tipo de identificacion
				}else{//Si no existe
					throw new Exception("No se halló el tipo de identificacion");
				}
				//Guardo el usuario
				usuarioLogic.saveUsuario(usuario);
				
			}
			
			//Asigno el usuario al pedido
			pedido.setUsuario(usuario);
			
			//Guardo el pedido
			savePedido(pedido);
			
			//2. Asigno los detalles del pedido
			
			//Si existen productos seleccionados
			if(detallePedidoDTOs != null && !detallePedidoDTOs.isEmpty()){
				
				Producto productoActual = null;
				DetallePedido detallePedidoSeleccionado = null;
				double total = 0;
				//Recorro los Productos Seleccionados
				for (DetallePedidoDTO detallePedidoDTO : detallePedidoDTOs) {
					//Verifico que el producto enviado exista en el map, para evitar consultas a la BD
					if(mapProductos.containsKey( detallePedidoDTO.getId_Producto() ) ){
						productoActual = mapProductos.get( detallePedidoDTO.getId_Producto() );//Obtengo el producto 
					}else{
						throw new Exception("No se halló el producto con id: "+detallePedidoDTO.getId_Producto());
					}
					
					//Creo el nuevo detalle
					detallePedidoSeleccionado = new DetallePedido();
					
					detallePedidoSeleccionado.setPedido(pedido);//Asigno el pedido
					detallePedidoSeleccionado.setProducto(productoActual);//Asigno el producto
					detallePedidoSeleccionado.setPrecio(detallePedidoDTO.getPrecio());//asigno el precio que venía en ese detalle
					detallePedidoSeleccionado.setCantidad(detallePedidoDTO.getCantidad());//asigno la cantidad
					
					detallePedidoLogic.saveDetallePedido(detallePedidoSeleccionado);//Guardo los detalles
					
					total += detallePedidoDTO.getPrecio() * detallePedidoDTO.getCantidad();//sumo el precio del detalle actual
				}
				
				//Asigno el total de los items
				pedido.setTotal(total);
				
				//Actualizo el pedido
				updatePedido(pedido);
			}else{
				throw new Exception("No hay detalles");
			}
			
			
		} catch (Exception e) {
			log.error("Hubo un error en registrarPedido: "+e.getMessage(), e);
			throw e;
		}
	}
}
