package com.ibm.tienda.presentation.businessDelegate;

import java.util.Date;
import java.util.List;

import com.ibm.tienda.modelo.DetalleInventario;
import com.ibm.tienda.modelo.DetallePedido;
import com.ibm.tienda.modelo.Empresa;
import com.ibm.tienda.modelo.Inventario;
import com.ibm.tienda.modelo.Pedido;
import com.ibm.tienda.modelo.Producto;
import com.ibm.tienda.modelo.TipoIdentificacion;
import com.ibm.tienda.modelo.TipoProducto;
import com.ibm.tienda.modelo.Usuario;
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
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public interface IBusinessDelegatorView {
    public List<DetalleInventario> getDetalleInventario()
        throws Exception;

    public void saveDetalleInventario(DetalleInventario entity)
        throws Exception;

    public void deleteDetalleInventario(DetalleInventario entity)
        throws Exception;

    public void updateDetalleInventario(DetalleInventario entity)
        throws Exception;

    public DetalleInventario getDetalleInventario(Long id)
        throws Exception;

    public List<DetalleInventario> findByCriteriaInDetalleInventario(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<DetalleInventario> findPageDetalleInventario(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberDetalleInventario() throws Exception;

    public List<DetalleInventarioDTO> getDataDetalleInventario()
        throws Exception;

    public void validateDetalleInventario(DetalleInventario detalleInventario)
        throws Exception;

    public List<DetallePedido> getDetallePedido() throws Exception;

    public void saveDetallePedido(DetallePedido entity)
        throws Exception;

    public void deleteDetallePedido(DetallePedido entity)
        throws Exception;

    public void updateDetallePedido(DetallePedido entity)
        throws Exception;

    public DetallePedido getDetallePedido(Long id) throws Exception;

    public List<DetallePedido> findByCriteriaInDetallePedido(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<DetallePedido> findPageDetallePedido(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberDetallePedido() throws Exception;

    public List<DetallePedidoDTO> getDataDetallePedido()
        throws Exception;

    public void validateDetallePedido(DetallePedido detallePedido)
        throws Exception;

    public List<Empresa> getEmpresa() throws Exception;

    public void saveEmpresa(Empresa entity) throws Exception;

    public void deleteEmpresa(Empresa entity) throws Exception;

    public void updateEmpresa(Empresa entity) throws Exception;

    public Empresa getEmpresa(Long id) throws Exception;

    public List<Empresa> findByCriteriaInEmpresa(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Empresa> findPageEmpresa(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberEmpresa() throws Exception;

    public List<EmpresaDTO> getDataEmpresa() throws Exception;

    public void validateEmpresa(Empresa empresa) throws Exception;

    public List<Inventario> getInventario() throws Exception;

    public void saveInventario(Inventario entity) throws Exception;

    public void deleteInventario(Inventario entity) throws Exception;

    public void updateInventario(Inventario entity) throws Exception;

    public Inventario getInventario(Long id) throws Exception;

    public List<Inventario> findByCriteriaInInventario(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Inventario> findPageInventario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberInventario() throws Exception;

    public List<InventarioDTO> getDataInventario() throws Exception;

    public void validateInventario(Inventario inventario)
        throws Exception;

    public List<Pedido> getPedido() throws Exception;

    public void savePedido(Pedido entity) throws Exception;

    public void deletePedido(Pedido entity) throws Exception;

    public void updatePedido(Pedido entity) throws Exception;

    public Pedido getPedido(Long id) throws Exception;

    public List<Pedido> findByCriteriaInPedido(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Pedido> findPagePedido(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPedido() throws Exception;

    public List<PedidoDTO> getDataPedido() throws Exception;

    public void validatePedido(Pedido pedido) throws Exception;

    public List<Producto> getProducto() throws Exception;

    public void saveProducto(Producto entity) throws Exception;

    public void deleteProducto(Producto entity) throws Exception;

    public void updateProducto(Producto entity) throws Exception;

    public Producto getProducto(Long id) throws Exception;

    public List<Producto> findByCriteriaInProducto(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Producto> findPageProducto(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberProducto() throws Exception;

    public List<ProductoDTO> getDataProducto() throws Exception;

    public void validateProducto(Producto producto) throws Exception;

    public List<TipoIdentificacion> getTipoIdentificacion()
        throws Exception;

    public void saveTipoIdentificacion(TipoIdentificacion entity)
        throws Exception;

    public void deleteTipoIdentificacion(TipoIdentificacion entity)
        throws Exception;

    public void updateTipoIdentificacion(TipoIdentificacion entity)
        throws Exception;

    public TipoIdentificacion getTipoIdentificacion(Long id)
        throws Exception;

    public List<TipoIdentificacion> findByCriteriaInTipoIdentificacion(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<TipoIdentificacion> findPageTipoIdentificacion(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberTipoIdentificacion() throws Exception;

    public List<TipoIdentificacionDTO> getDataTipoIdentificacion()
        throws Exception;

    public void validateTipoIdentificacion(
        TipoIdentificacion tipoIdentificacion) throws Exception;

    public List<TipoProducto> getTipoProducto() throws Exception;

    public void saveTipoProducto(TipoProducto entity) throws Exception;

    public void deleteTipoProducto(TipoProducto entity)
        throws Exception;

    public void updateTipoProducto(TipoProducto entity)
        throws Exception;

    public TipoProducto getTipoProducto(Long id) throws Exception;

    public List<TipoProducto> findByCriteriaInTipoProducto(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<TipoProducto> findPageTipoProducto(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberTipoProducto() throws Exception;

    public List<TipoProductoDTO> getDataTipoProducto()
        throws Exception;

    public void validateTipoProducto(TipoProducto tipoProducto)
        throws Exception;

    public List<Usuario> getUsuario() throws Exception;

    public void saveUsuario(Usuario entity) throws Exception;

    public void deleteUsuario(Usuario entity) throws Exception;

    public void updateUsuario(Usuario entity) throws Exception;

    public Usuario getUsuario(Long id) throws Exception;

    public List<Usuario> findByCriteriaInUsuario(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Usuario> findPageUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberUsuario() throws Exception;

    public List<UsuarioDTO> getDataUsuario() throws Exception;

    public void validateUsuario(Usuario usuario) throws Exception;
    
    public void registrarPedido( String nombre,	String apellido, Long idTipoId,	String identificacion,	String direccion, Date fechaPedido, List<DetallePedidoDTO> detallePedidoDTOs  ) throws Exception;
    
    public List<ProductoDTO> getProductosDTOByTipoProducto(Long idtipoProducto) throws Exception;

}
