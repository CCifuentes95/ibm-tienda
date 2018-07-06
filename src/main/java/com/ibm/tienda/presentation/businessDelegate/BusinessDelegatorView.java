package com.ibm.tienda.presentation.businessDelegate;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ibm.tienda.modelo.DetalleInventario;
import com.ibm.tienda.modelo.DetallePedido;
import com.ibm.tienda.modelo.Empresa;
import com.ibm.tienda.modelo.Inventario;
import com.ibm.tienda.modelo.Pedido;
import com.ibm.tienda.modelo.Producto;
import com.ibm.tienda.modelo.TipoIdentificacion;
import com.ibm.tienda.modelo.TipoProducto;
import com.ibm.tienda.modelo.Usuario;
import com.ibm.tienda.modelo.control.IDetalleInventarioLogic;
import com.ibm.tienda.modelo.control.IDetallePedidoLogic;
import com.ibm.tienda.modelo.control.IEmpresaLogic;
import com.ibm.tienda.modelo.control.IInventarioLogic;
import com.ibm.tienda.modelo.control.IPedidoLogic;
import com.ibm.tienda.modelo.control.IProductoLogic;
import com.ibm.tienda.modelo.control.ITipoIdentificacionLogic;
import com.ibm.tienda.modelo.control.ITipoProductoLogic;
import com.ibm.tienda.modelo.control.IUsuarioLogic;
import com.ibm.tienda.modelo.dto.DetalleInventarioDTO;
import com.ibm.tienda.modelo.dto.DetallePedidoDTO;
import com.ibm.tienda.modelo.dto.EmpresaDTO;
import com.ibm.tienda.modelo.dto.InventarioDTO;
import com.ibm.tienda.modelo.dto.PedidoDTO;
import com.ibm.tienda.modelo.dto.ProductoDTO;
import com.ibm.tienda.modelo.dto.TipoIdentificacionDTO;
import com.ibm.tienda.modelo.dto.TipoProductoDTO;
import com.ibm.tienda.modelo.dto.UsuarioDTO;


/**
* Use a Business Delegate to reduce coupling between presentation-tier clients and business services.
* The Business Delegate hides the underlying implementation details of the business service, such as lookup and access details of the EJB architecture.
*
* The Business Delegate acts as a client-side business abstraction; it provides an abstraction for, and thus hides,
* the implementation of the business services. Using a Business Delegate reduces the coupling between presentation-tier clients and
* the system's business services. Depending on the implementation strategy, the Business Delegate may shield clients from possible
* volatility in the implementation of the business service API. Potentially, this reduces the number of changes that must be made to the
* presentation-tier client code when the business service API or its underlying implementation changes.
*
* However, interface methods in the Business Delegate may still require modification if the underlying business service API changes.
* Admittedly, though, it is more likely that changes will be made to the business service rather than to the Business Delegate.
*
* Often, developers are skeptical when a design goal such as abstracting the business layer causes additional upfront work in return
* for future gains. However, using this pattern or its strategies results in only a small amount of additional upfront work and provides
* considerable benefits. The main benefit is hiding the details of the underlying service. For example, the client can become transparent
* to naming and lookup services. The Business Delegate also handles the exceptions from the business services, such as java.rmi.Remote
* exceptions, Java Messages Service (JMS) exceptions and so on. The Business Delegate may intercept such service level exceptions and
* generate application level exceptions instead. Application level exceptions are easier to handle by the clients, and may be user friendly.
* The Business Delegate may also transparently perform any retry or recovery operations necessary in the event of a service failure without
* exposing the client to the problem until it is determined that the problem is not resolvable. These gains present a compelling reason to
* use the pattern.
*
* Another benefit is that the delegate may cache results and references to remote business services. Caching can significantly improve performance,
* because it limits unnecessary and potentially costly round trips over the network.
*
* A Business Delegate uses a component called the Lookup Service. The Lookup Service is responsible for hiding the underlying implementation
* details of the business service lookup code. The Lookup Service may be written as part of the Delegate, but we recommend that it be
* implemented as a separate component, as outlined in the Service Locator pattern (See "Service Locator" on page 368.)
*
* When the Business Delegate is used with a Session Facade, typically there is a one-to-one relationship between the two.
* This one-to-one relationship exists because logic that might have been encapsulated in a Business Delegate relating to its interaction
* with multiple business services (creating a one-to-many relationship) will often be factored back into a Session Facade.
*
* Finally, it should be noted that this pattern could be used to reduce coupling between other tiers, not simply the presentation and the
* business tiers.
*
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("BusinessDelegatorView")
public class BusinessDelegatorView implements IBusinessDelegatorView {
    @Autowired
    private IDetalleInventarioLogic detalleInventarioLogic;
    @Autowired
    private IDetallePedidoLogic detallePedidoLogic;
    @Autowired
    private IEmpresaLogic empresaLogic;
    @Autowired
    private IInventarioLogic inventarioLogic;
    @Autowired
    private IPedidoLogic pedidoLogic;
    @Autowired
    private IProductoLogic productoLogic;
    @Autowired
    private ITipoIdentificacionLogic tipoIdentificacionLogic;
    @Autowired
    private ITipoProductoLogic tipoProductoLogic;
    @Autowired
    private IUsuarioLogic usuarioLogic;

    public List<DetalleInventario> getDetalleInventario()
        throws Exception {
        return detalleInventarioLogic.getDetalleInventario();
    }

    public void saveDetalleInventario(DetalleInventario entity)
        throws Exception {
        detalleInventarioLogic.saveDetalleInventario(entity);
    }

    public void deleteDetalleInventario(DetalleInventario entity)
        throws Exception {
        detalleInventarioLogic.deleteDetalleInventario(entity);
    }

    public void updateDetalleInventario(DetalleInventario entity)
        throws Exception {
        detalleInventarioLogic.updateDetalleInventario(entity);
    }

    public DetalleInventario getDetalleInventario(Long id)
        throws Exception {
        DetalleInventario detalleInventario = null;

        try {
            detalleInventario = detalleInventarioLogic.getDetalleInventario(id);
        } catch (Exception e) {
            throw e;
        }

        return detalleInventario;
    }

    public List<DetalleInventario> findByCriteriaInDetalleInventario(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return detalleInventarioLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<DetalleInventario> findPageDetalleInventario(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return detalleInventarioLogic.findPageDetalleInventario(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberDetalleInventario() throws Exception {
        return detalleInventarioLogic.findTotalNumberDetalleInventario();
    }

    public List<DetalleInventarioDTO> getDataDetalleInventario()
        throws Exception {
        return detalleInventarioLogic.getDataDetalleInventario();
    }

    public void validateDetalleInventario(DetalleInventario detalleInventario)
        throws Exception {
        detalleInventarioLogic.validateDetalleInventario(detalleInventario);
    }

    public List<DetallePedido> getDetallePedido() throws Exception {
        return detallePedidoLogic.getDetallePedido();
    }

    public void saveDetallePedido(DetallePedido entity)
        throws Exception {
        detallePedidoLogic.saveDetallePedido(entity);
    }

    public void deleteDetallePedido(DetallePedido entity)
        throws Exception {
        detallePedidoLogic.deleteDetallePedido(entity);
    }

    public void updateDetallePedido(DetallePedido entity)
        throws Exception {
        detallePedidoLogic.updateDetallePedido(entity);
    }

    public DetallePedido getDetallePedido(Long id) throws Exception {
        DetallePedido detallePedido = null;

        try {
            detallePedido = detallePedidoLogic.getDetallePedido(id);
        } catch (Exception e) {
            throw e;
        }

        return detallePedido;
    }

    public List<DetallePedido> findByCriteriaInDetallePedido(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return detallePedidoLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<DetallePedido> findPageDetallePedido(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return detallePedidoLogic.findPageDetallePedido(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberDetallePedido() throws Exception {
        return detallePedidoLogic.findTotalNumberDetallePedido();
    }

    public List<DetallePedidoDTO> getDataDetallePedido()
        throws Exception {
        return detallePedidoLogic.getDataDetallePedido();
    }

    public void validateDetallePedido(DetallePedido detallePedido)
        throws Exception {
        detallePedidoLogic.validateDetallePedido(detallePedido);
    }

    public List<Empresa> getEmpresa() throws Exception {
        return empresaLogic.getEmpresa();
    }

    public void saveEmpresa(Empresa entity) throws Exception {
        empresaLogic.saveEmpresa(entity);
    }

    public void deleteEmpresa(Empresa entity) throws Exception {
        empresaLogic.deleteEmpresa(entity);
    }

    public void updateEmpresa(Empresa entity) throws Exception {
        empresaLogic.updateEmpresa(entity);
    }

    public Empresa getEmpresa(Long id) throws Exception {
        Empresa empresa = null;

        try {
            empresa = empresaLogic.getEmpresa(id);
        } catch (Exception e) {
            throw e;
        }

        return empresa;
    }

    public List<Empresa> findByCriteriaInEmpresa(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return empresaLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Empresa> findPageEmpresa(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return empresaLogic.findPageEmpresa(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberEmpresa() throws Exception {
        return empresaLogic.findTotalNumberEmpresa();
    }

    public List<EmpresaDTO> getDataEmpresa() throws Exception {
        return empresaLogic.getDataEmpresa();
    }

    public void validateEmpresa(Empresa empresa) throws Exception {
        empresaLogic.validateEmpresa(empresa);
    }

    public List<Inventario> getInventario() throws Exception {
        return inventarioLogic.getInventario();
    }

    public void saveInventario(Inventario entity) throws Exception {
        inventarioLogic.saveInventario(entity);
    }

    public void deleteInventario(Inventario entity) throws Exception {
        inventarioLogic.deleteInventario(entity);
    }

    public void updateInventario(Inventario entity) throws Exception {
        inventarioLogic.updateInventario(entity);
    }

    public Inventario getInventario(Long id) throws Exception {
        Inventario inventario = null;

        try {
            inventario = inventarioLogic.getInventario(id);
        } catch (Exception e) {
            throw e;
        }

        return inventario;
    }

    public List<Inventario> findByCriteriaInInventario(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return inventarioLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Inventario> findPageInventario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return inventarioLogic.findPageInventario(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberInventario() throws Exception {
        return inventarioLogic.findTotalNumberInventario();
    }

    public List<InventarioDTO> getDataInventario() throws Exception {
        return inventarioLogic.getDataInventario();
    }

    public void validateInventario(Inventario inventario)
        throws Exception {
        inventarioLogic.validateInventario(inventario);
    }

    public List<Pedido> getPedido() throws Exception {
        return pedidoLogic.getPedido();
    }

    public void savePedido(Pedido entity) throws Exception {
        pedidoLogic.savePedido(entity);
    }

    public void deletePedido(Pedido entity) throws Exception {
        pedidoLogic.deletePedido(entity);
    }

    public void updatePedido(Pedido entity) throws Exception {
        pedidoLogic.updatePedido(entity);
    }

    public Pedido getPedido(Long id) throws Exception {
        Pedido pedido = null;

        try {
            pedido = pedidoLogic.getPedido(id);
        } catch (Exception e) {
            throw e;
        }

        return pedido;
    }

    public List<Pedido> findByCriteriaInPedido(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return pedidoLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Pedido> findPagePedido(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return pedidoLogic.findPagePedido(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberPedido() throws Exception {
        return pedidoLogic.findTotalNumberPedido();
    }

    public List<PedidoDTO> getDataPedido() throws Exception {
        return pedidoLogic.getDataPedido();
    }

    public void validatePedido(Pedido pedido) throws Exception {
        pedidoLogic.validatePedido(pedido);
    }

    public List<Producto> getProducto() throws Exception {
        return productoLogic.getProducto();
    }

    public void saveProducto(Producto entity) throws Exception {
        productoLogic.saveProducto(entity);
    }

    public void deleteProducto(Producto entity) throws Exception {
        productoLogic.deleteProducto(entity);
    }

    public void updateProducto(Producto entity) throws Exception {
        productoLogic.updateProducto(entity);
    }

    public Producto getProducto(Long id) throws Exception {
        Producto producto = null;

        try {
            producto = productoLogic.getProducto(id);
        } catch (Exception e) {
            throw e;
        }

        return producto;
    }

    public List<Producto> findByCriteriaInProducto(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return productoLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Producto> findPageProducto(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return productoLogic.findPageProducto(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberProducto() throws Exception {
        return productoLogic.findTotalNumberProducto();
    }

    public List<ProductoDTO> getDataProducto() throws Exception {
        return productoLogic.getDataProducto();
    }

    public void validateProducto(Producto producto) throws Exception {
        productoLogic.validateProducto(producto);
    }

    public List<TipoIdentificacion> getTipoIdentificacion()
        throws Exception {
        return tipoIdentificacionLogic.getTipoIdentificacion();
    }

    public void saveTipoIdentificacion(TipoIdentificacion entity)
        throws Exception {
        tipoIdentificacionLogic.saveTipoIdentificacion(entity);
    }

    public void deleteTipoIdentificacion(TipoIdentificacion entity)
        throws Exception {
        tipoIdentificacionLogic.deleteTipoIdentificacion(entity);
    }

    public void updateTipoIdentificacion(TipoIdentificacion entity)
        throws Exception {
        tipoIdentificacionLogic.updateTipoIdentificacion(entity);
    }

    public TipoIdentificacion getTipoIdentificacion(Long id)
        throws Exception {
        TipoIdentificacion tipoIdentificacion = null;

        try {
            tipoIdentificacion = tipoIdentificacionLogic.getTipoIdentificacion(id);
        } catch (Exception e) {
            throw e;
        }

        return tipoIdentificacion;
    }

    public List<TipoIdentificacion> findByCriteriaInTipoIdentificacion(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return tipoIdentificacionLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<TipoIdentificacion> findPageTipoIdentificacion(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return tipoIdentificacionLogic.findPageTipoIdentificacion(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberTipoIdentificacion() throws Exception {
        return tipoIdentificacionLogic.findTotalNumberTipoIdentificacion();
    }

    public List<TipoIdentificacionDTO> getDataTipoIdentificacion()
        throws Exception {
        return tipoIdentificacionLogic.getDataTipoIdentificacion();
    }

    public void validateTipoIdentificacion(
        TipoIdentificacion tipoIdentificacion) throws Exception {
        tipoIdentificacionLogic.validateTipoIdentificacion(tipoIdentificacion);
    }

    public List<TipoProducto> getTipoProducto() throws Exception {
        return tipoProductoLogic.getTipoProducto();
    }

    public void saveTipoProducto(TipoProducto entity) throws Exception {
        tipoProductoLogic.saveTipoProducto(entity);
    }

    public void deleteTipoProducto(TipoProducto entity)
        throws Exception {
        tipoProductoLogic.deleteTipoProducto(entity);
    }

    public void updateTipoProducto(TipoProducto entity)
        throws Exception {
        tipoProductoLogic.updateTipoProducto(entity);
    }

    public TipoProducto getTipoProducto(Long id) throws Exception {
        TipoProducto tipoProducto = null;

        try {
            tipoProducto = tipoProductoLogic.getTipoProducto(id);
        } catch (Exception e) {
            throw e;
        }

        return tipoProducto;
    }

    public List<TipoProducto> findByCriteriaInTipoProducto(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return tipoProductoLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<TipoProducto> findPageTipoProducto(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return tipoProductoLogic.findPageTipoProducto(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberTipoProducto() throws Exception {
        return tipoProductoLogic.findTotalNumberTipoProducto();
    }

    public List<TipoProductoDTO> getDataTipoProducto()
        throws Exception {
        return tipoProductoLogic.getDataTipoProducto();
    }

    public void validateTipoProducto(TipoProducto tipoProducto)
        throws Exception {
        tipoProductoLogic.validateTipoProducto(tipoProducto);
    }

    public List<Usuario> getUsuario() throws Exception {
        return usuarioLogic.getUsuario();
    }

    public void saveUsuario(Usuario entity) throws Exception {
        usuarioLogic.saveUsuario(entity);
    }

    public void deleteUsuario(Usuario entity) throws Exception {
        usuarioLogic.deleteUsuario(entity);
    }

    public void updateUsuario(Usuario entity) throws Exception {
        usuarioLogic.updateUsuario(entity);
    }

    public Usuario getUsuario(Long id) throws Exception {
        Usuario usuario = null;

        try {
            usuario = usuarioLogic.getUsuario(id);
        } catch (Exception e) {
            throw e;
        }

        return usuario;
    }

    public List<Usuario> findByCriteriaInUsuario(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return usuarioLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Usuario> findPageUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return usuarioLogic.findPageUsuario(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberUsuario() throws Exception {
        return usuarioLogic.findTotalNumberUsuario();
    }

    public List<UsuarioDTO> getDataUsuario() throws Exception {
        return usuarioLogic.getDataUsuario();
    }

    public void validateUsuario(Usuario usuario) throws Exception {
        usuarioLogic.validateUsuario(usuario);
    }

	@Override
	public void registrarPedido(String nombre, String apellido, Long idTipoId, String identificacion, String direccion,
			Date fechaPedido, List<DetallePedidoDTO> detallePedidoDTOs) throws Exception {
		pedidoLogic.registrarPedido(nombre, apellido, idTipoId, identificacion, direccion, fechaPedido, detallePedidoDTOs);
	}

	@Override
	public List<ProductoDTO> getProductosDTOByTipoProducto(Long idtipoProducto) throws Exception {
		return productoLogic.getProductosDTOByTipoProducto(idtipoProducto);
	}
}
