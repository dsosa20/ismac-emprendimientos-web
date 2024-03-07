package com.distribuida.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
import com.distribuida.dto.ProductoService;
import com.distribuida.entities.Cliente;
import com.distribuida.entities.Empresa_producto;
import com.distribuida.entities.FacturaDetalle;
import com.distribuida.entities.FormaPago;
import com.distribuida.entities.Pedido;
import com.distribuida.entities.Producto;

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
	@Autowired
	private ProductoService productoService;
	
	private static String numFactura;
	private static String fechaAtual;
	private static Double totalNeto;
	private static Double iva;
	private static Double total;
	private static Cliente cliente;
	
	private static Pedido pedido;
	private static FormaPago formapago;
	private static Producto producto;
	private static Empresa_producto empresa_producto;

	private static List<FacturaDetalle> list = new ArrayList<FacturaDetalle>();
	private static List<Cliente> clientes;
	private static List<Pedido> pedidos;
	private static List<FormaPago> formapagos;
	private static List<Producto> productos;
	private static List<Empresa_producto> empresa_productos;

//	private static List<Empresa_producto> empresa_productosAgregados;
//	private static List<Producto> productoAgregados;
	
	List<Empresa_producto> empresa_productosAgregados = new ArrayList<Empresa_producto>();
	List<Producto> productosAgregados = new ArrayList<Producto>();

	
	@SuppressWarnings("static-access")
	@GetMapping("/principal")
	private String cabeceraFactura(ModelMap modelMap) {
		
		try {
			load(modelMap);
			this.empresa_productosAgregados.add(empresa_productoService.finOne(1));
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
	
	@SuppressWarnings("static-access")
	@GetMapping("/findOneProducto")
	private String fingOneProducto(@RequestParam("idProducto") @Nullable Integer idProducto, ModelMap modelMap) {
		
		try {
			if(idProducto != null) this.producto = productoService.finOne(idProducto);
			load(modelMap);
		} catch (Exception e) {
			//TODO: handler exception
		}
		return "redirect:/facturacion/principal";
	}
	
	@SuppressWarnings("static-access")
	@GetMapping("/findOneEmpresaProducto")
	public String findOneEmpresaProducto(@RequestParam("idempresa_producto") @Nullable Integer idempresa_producto, ModelMap modelMap) {
	    try {
	        if(idempresa_producto != null) {
	            Empresa_producto empresa_producto = empresa_productoService.finOne(idempresa_producto);
	            
	            if(empresa_producto != null) {
	                Producto producto = empresa_producto.getIdproducto();
	                
	                if (producto != null) {
	                    if (!productosAgregados.contains(producto)) {
	                        
	                        productosAgregados.add(producto);
	                        
	                        double precio = producto.getPrecioProducto();
	                        this.totalNeto = getFormatNumber(this.totalNeto + precio * 1);
	                        this.iva = getFormatNumber(this.totalNeto * 0.12);
	                        this.total = getFormatNumber(this.totalNeto + this.iva);
	                    } 
	                }
	            }
	        }
	    } catch (Exception e) {
			//TODO: handler exception
	    }
	    return "redirect:/facturacion/principal";
	}


	@SuppressWarnings("static-access")
	private void load(ModelMap modelMap) {
		// TODO Auto-generated method stub
		this.fechaAtual = getFecha();
		this.numFactura = getNumFactura();
		this.clientes = clienteService.findAll();
		this.pedidos = pedidoService.findAll();
		this.formapagos = formapagoService.findAll();
		this.empresa_productos = empresa_productoService.findAll();
		this.productos = productoService.finAll();
		
		modelMap.addAttribute("fechaActual", this.fechaAtual);
		modelMap.addAttribute("numFcatura", this.numFactura);
		modelMap.addAttribute("totalNeto", this.totalNeto);
		modelMap.addAttribute("iva", this.iva);
		modelMap.addAttribute("total",this.total);
		
		modelMap.addAttribute("cliente",this.cliente);
		modelMap.addAttribute("pedido", this.pedido);
		modelMap.addAttribute("formapago", this.formapago);
		modelMap.addAttribute("empresa_prodcuto", this.empresa_producto);
		modelMap.addAttribute("producto", this.producto);
		
		modelMap.addAttribute("list", this.list);
		
		modelMap.addAttribute("clientes", this.clientes);
		modelMap.addAttribute("pedidos", this.pedidos);
		modelMap.addAttribute("formapagos", this.formapagos);
		modelMap.addAttribute("empresa_productos", this.empresa_productos);
		modelMap.addAttribute("productos", this.productos);
	}
	
	@SuppressWarnings("static-access")
	private void clear(ModelMap modelMap) {
		// TODO Auto-generated method stub
		this.fechaAtual = getFecha();
		this.numFactura = getNumFactura();
		this.totalNeto = 0.0;
		this.iva = 0.0;
		this.total = 0.0;
		this.list.clear();
		
		this.cliente = null;
		this.clientes = clienteService.findAll();
		this.pedido = null;
		this.pedidos = pedidoService.findAll();
		
		this.formapagos = formapagoService.findAll();
		
		this.empresa_productos = empresa_productoService.findAll();
		
		this.productos = productoService.finAll();
		
		modelMap.addAttribute("fechaActual", this.fechaAtual);
		modelMap.addAttribute("numFcatura", this.numFactura);
		modelMap.addAttribute("totalNeto", this.totalNeto);
		modelMap.addAttribute("iva", this.iva);
		modelMap.addAttribute("total",this.total);
		
		modelMap.addAttribute("cliente",this.cliente);
		modelMap.addAttribute("pedido", this.pedido);
		modelMap.addAttribute("formapago", this.formapago);
		modelMap.addAttribute("empresa_prodcuto", this.empresa_producto);
		modelMap.addAttribute("producto", this.producto);
		
		modelMap.addAttribute("list", this.list);
		
		modelMap.addAttribute("clientes", this.clientes);
		modelMap.addAttribute("pedidos", this.pedidos);
		modelMap.addAttribute("formapagos", this.formapagos);
		modelMap.addAttribute("empresa_productos", this.empresa_productos);
		modelMap.addAttribute("productos", this.productos);
	}
	
	
	private String getFecha() {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");		
		String fecha = formato.format(new Date());
		return fecha;
	}
	
	private String getNumFactura() {
		int max = facturaService.findMax();		
		String numFactura = String.join("-", "FAC","000".concat(String.valueOf(max+1)));
		return numFactura;
		
	}
	
	private Double getFormatNumber(Double numero) {
		DecimalFormat df = new DecimalFormat("#.00");
		return Double.parseDouble(df.format(numero));
//		return (double) Math.round(numero * 100d) / 100d;
	}

	
	
	
	
	
	
	@SuppressWarnings("static-access")
	public void del(int idFDList) {
				
		this.list.remove(idFDList);			
	
		for (FacturaDetalle facturaDetalle : this.list) {
			int index = this.list.indexOf(facturaDetalle);		
			facturaDetalle.setIdFacturaDetalle(index);		
			this.list.set(index, facturaDetalle);
		}
			
	}
	
	@SuppressWarnings("static-access")
	public List<FacturaDetalle> findAll(){
		return this.list;
	}
	
}