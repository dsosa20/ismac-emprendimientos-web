package com.distribuida.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.distribuida.dto.ClienteService;
import com.distribuida.dto.Empresa_productoService;
import com.distribuida.dto.FacturaService;
import com.distribuida.dto.FormaPagoService;
import com.distribuida.dto.PedidoService;
import com.distribuida.entities.Cliente;
import com.distribuida.entities.Empresa_producto;
import com.distribuida.entities.FacturaDetalle;
import com.distribuida.entities.FormaPago;
import com.distribuida.entities.Pedido;

@org.springframework.stereotype.Controller
@RequestMapping("/facturacion")
public class FacturaController {

	@Autowired
	private FacturaService facturaService;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private FormaPagoService formapagoService;
	@Autowired 
	private Empresa_productoService empresa_productoService;
	
	private static String numFactura;
	private static Date fechaFactura;
	private static Double totalNeto;
	private static Double iva;
	private static Double total;
	private static Cliente cliente;
	private static Pedido pedido;
	private static FormaPago formapago;

	private static List<FacturaDetalle> list = new ArrayList<FacturaDetalle>();
	private static List<Cliente> clientes;
	private static List<Pedido> pedidos;
	private static List<FormaPago> formapagos;
	private static List<Empresa_producto> empresa_productos;
	private List<Empresa_producto> empresa_productosAgregados;
	
	@SuppressWarnings("static-access")
	@GetMapping("/principal")
	private String cabeceraFactura(ModelMap modelMap) {
		
		try {
			load(modelMap);
			//this.empresa_productosAgregados.add(empresa_productoService).findOne(1));
		} catch (Exception e) {
			//TODO: handle exception
		}
		
		return "facturacion";
	}
	
	@SuppressWarnings("static-access")
	@GetMapping("/findOneCliente")
	private String findOneCliente(@RequestParam("idCliente") @Nullable Integer idCliente, ModelMap modelMap) {
		
		try {
			if(idCliente != null) this.cliente = clienteService.findOne(idCliente);
			load(modelMap);
		} catch (Exception e) {
			//TODO: handler exception
		}
		return "redirect:/facturacion/principal";
	}
	
	
	@SuppressWarnings("static-access")
	@GetMapping("/findOnePedido")
	private String findOnePedido(@RequestParam("idPedido") @Nullable Integer idPedido, ModelMap modelMap) {
		
		try {
			if(idPedido != null) this.pedido = pedidoService.findOne(idPedido);
			load(modelMap);
		} catch (Exception e) {
			//TODO: handler exception
		}
		return "redirect:/facturacion/principal";
	}
	
	
	@SuppressWarnings("static-access")
	@GetMapping("/findOneFormaPago")
	private String findOneFormaPago(@RequestParam("idFormaPago") @Nullable Integer idFormaPago, ModelMap modelMap) {
		
		try {
			if(idFormaPago != null) this.formapago = formapagoService.findOne(idFormaPago);
			load(modelMap);
		} catch (Exception e) {
			//TODO: handler exception
		}
		return "redirect:/facturacion/principal";
	}
	
	
	
	
	
	
	
	
	
	
	

	private void load(ModelMap modelMap) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	
	
	
}
