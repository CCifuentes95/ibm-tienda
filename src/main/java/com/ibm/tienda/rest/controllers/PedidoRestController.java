package com.ibm.tienda.rest.controllers;

import com.ibm.tienda.dto.mapper.IPedidoMapper;
import com.ibm.tienda.modelo.*;
import com.ibm.tienda.modelo.dto.PedidoDTO;
import com.ibm.tienda.modelo.dto.RegistrarPedidoDTO;
import com.ibm.tienda.modelo.dto.ResponseDTO;
import com.ibm.tienda.presentation.businessDelegate.IBusinessDelegatorView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/pedido")
public class PedidoRestController {
	private static final Logger log = LoggerFactory.getLogger(PedidoRestController.class);
	@Autowired
	private IBusinessDelegatorView businessDelegatorView;
	@Autowired
	private IPedidoMapper pedidoMapper;

	@PostMapping(value = "/savePedido")
	public void savePedido(@RequestBody
			PedidoDTO pedidoDTO) throws Exception {
		try {
			Pedido pedido = pedidoMapper.pedidoDTOToPedido(pedidoDTO);

			businessDelegatorView.savePedido(pedido);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@DeleteMapping(value = "/deletePedido/{id}")
	public void deletePedido(@PathVariable("id")
	Long id) throws Exception {
		try {
			Pedido pedido = businessDelegatorView.getPedido(id);

			businessDelegatorView.deletePedido(pedido);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@PutMapping(value = "/updatePedido/")
	public void updatePedido(@RequestBody
			PedidoDTO pedidoDTO) throws Exception {
		try {
			Pedido pedido = pedidoMapper.pedidoDTOToPedido(pedidoDTO);

			businessDelegatorView.updatePedido(pedido);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@GetMapping(value = "/getDataPedido")
	public List<PedidoDTO> getDataPedido() throws Exception {
		try {
			return businessDelegatorView.getDataPedido();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@GetMapping(value = "/getPedido/{id}")
	public PedidoDTO getPedido(@PathVariable("id")
	Long id) throws Exception {
		try {
			Pedido pedido = businessDelegatorView.getPedido(id);

			return pedidoMapper.pedidoToPedidoDTO(pedido);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return null;
	}

	@PostMapping(value = "/registrarPedido")
	public ResponseDTO registrarPedido(@RequestBody
			RegistrarPedidoDTO data) throws Exception {
		ResponseDTO dto = new ResponseDTO();
		try {
			log.info("Entró en registrarPedido");
			
			businessDelegatorView.registrarPedido(data.getNombre(), data.getApellido(), data.getIdTipoId(), data.getIdentificacion(), data.getDireccion(), data.getFechaPedido(), data.getDetallePedidoDTOs());


			dto.setError("0");
			dto.setMensaje("Se guardó correctamente el pedido");

			return dto;
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			dto.setError("1");
			dto.setMensaje(e.getMessage());

			return dto;        
		}
	}
}
