package com.ibm.tienda.dto.mapper;

import com.ibm.tienda.modelo.Producto;
import com.ibm.tienda.modelo.dto.ProductoDTO;

import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public interface IProductoMapper {
    public ProductoDTO productoToProductoDTO(Producto producto)
        throws Exception;

    public Producto productoDTOToProducto(ProductoDTO productoDTO)
        throws Exception;

    public List<ProductoDTO> listProductoToListProductoDTO(
        List<Producto> productos) throws Exception;

    public List<Producto> listProductoDTOToListProducto(
        List<ProductoDTO> productoDTOs) throws Exception;
}
