package com.distribuida.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.distribuida.dto.ClienteService;
import com.distribuida.dto.Empresa_productoService;
import com.distribuida.dto.FacturaService;
import com.distribuida.dto.FormaPagoService;
import com.distribuida.dto.PedidoService;

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
	
	private static int numFactura;
	
	
	
	@SuppressWarnings("static-access")
	@GetMapping("/principal")
	private String cabeceraFactura(ModelMap modelMap) {
		
	}
	
	
	
	
	
	
	
	
	
	
}
