package com.distribuida.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.distribuida.dto.ClienteService;
import com.distribuida.dto.EmpresaService;
import com.distribuida.dto.Empresa_productoService;
import com.distribuida.dto.FacturaService;
import com.distribuida.dto.FormaPagoService;
import com.distribuida.dto.PedidoService;
import com.distribuida.dto.ProductoService;
import com.distribuida.entities.Cliente;
import com.distribuida.entities.Empresa;
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
	@Autowired
	private EmpresaService empresaService;
	
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
	@SuppressWarnings("unused")
	private static Empresa empresa;

	private static List<FacturaDetalle> list = new ArrayList<FacturaDetalle>();
	private static List<Cliente> clientes;
	private static List<Pedido> pedidos;
	private static List<FormaPago> formapagos;
	private static List<Producto> productos;
	private static List<Empresa_producto> empresa_productos;
	private static List<Producto> productosAgregados = new ArrayList<Producto>();
	private static List<Empresa_producto> empresa_productosAgregados = new ArrayList<Empresa_producto>();


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
	private String findOneProducto(@RequestParam("idProducto") @Nullable Integer idProducto, ModelMap modelMap) {
		
		try {
			if(idProducto != null) this.producto = productoService.findOne(idProducto);
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
	        load(modelMap);
	    } catch (Exception e) {
			//TODO: handler exception
	    }
	    return "redirect:/facturacion/principal";
	}
	
	
	@SuppressWarnings("static-access")
	@GetMapping("/addDetalleTemporal")
	public String add(@RequestParam("idFDList") @Nullable Integer idFDList,
	                  @RequestParam("cantidad") @Nullable Integer cantidad,
	                  @RequestParam("opcionDetalle") @Nullable Integer opcionDetalle,
	                  ModelMap modelMap) {

	 try {
	    if (opcionDetalle == 1) {
	        FacturaDetalle facturaDetalle = this.list.get(idFDList);
	        
	        if (cantidad != facturaDetalle.getCantidad()) {
	        	
	        	double subtotal=0;
	        	if (cantidad > 5) {
	        	    subtotal = facturaDetalle.getEmpresaProducto().getIdproducto().getPrecioProducto()*0.05  * facturaDetalle.getCantidad();
	        	    
	        	} else if(cantidad > 10) {
	        		subtotal = facturaDetalle.getEmpresaProducto().getIdproducto().getPrecioProducto()*0.10  * facturaDetalle.getCantidad();
	        	}else {
	        		subtotal = facturaDetalle.getEmpresaProducto().getIdproducto().getPrecioProducto() * facturaDetalle.getCantidad();
	        	}
	        	
	        	
	        	this.totalNeto = this.totalNeto - subtotal; //facturaDetalle.getEmpresaProducto().getIdproducto().getPrecioProducto() * facturaDetalle.getCantidad();
	            this.totalNeto = getFormatNumber(this.totalNeto + subtotal);
	            this.iva = getFormatNumber(this.totalNeto * 0.12);
	            this.total = getFormatNumber(this.totalNeto + this.iva);
	            up(cantidad, idFDList);
	        }
	    } else {
	        FacturaDetalle facturaDetalle = this.list.get(idFDList);

	            this.totalNeto = getFormatNumber(this.totalNeto - facturaDetalle.getEmpresaProducto().getIdproducto().getPrecioProducto() * facturaDetalle.getCantidad());
	            this.iva = getFormatNumber(this.totalNeto * 0.12);
	            this.total = getFormatNumber(this.totalNeto + this.iva);
	            del(idFDList);
	        } 

	    load(modelMap);
	 } catch (Exception e) {
	    // TODO: handle exception
	}

	    return "redirect:/facturacion/principal";
	}


	@SuppressWarnings({"static-access", "deprecation"})
	@PostMapping("/addDetalle")
	public String add(@RequestParam("numFactura") @Nullable String numFactura,
	                  @RequestParam("fechaActual") @Nullable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaActual,
	                  @RequestParam("totalNeto") @Nullable Double totalNeto,
	                  @RequestParam("iva") @Nullable Double iva,
	                  @RequestParam("total") @Nullable Double total,
	                  @RequestParam("idCliente") @Nullable Integer idCliente,
	                  @RequestParam("idPedido") @Nullable Integer idPedido,
	                  @RequestParam("idFormaPago") @Nullable Integer idFormaPago,
	                  @RequestParam("idempresa_producto") @Nullable Integer idempresa_producto,
	                  @RequestParam("idProducto") @Nullable Integer idProducto,
	                  @RequestParam("idEmpresa") @Nullable Integer idEmpresa,
	                  @RequestParam("list") @Nullable List<FacturaDetalle> list,
	                  ModelMap modelMap) {

	    try {
	        load(modelMap);
	        this.cliente = clienteService.findOne(idCliente);
	        this.pedido = pedidoService.findOne(idPedido);
	        this.formapago = formapagoService.findOne(idFormaPago);
	        this.empresa_producto = empresa_productoService.finOne(idempresa_producto);
	        this.producto = productoService.findOne(idProducto);
	        this.empresa = empresaService.finOne(idEmpresa);
	        

	        facturaService.add(0, numFactura, new Date(), totalNeto, iva, total, idCliente, idPedido, idFormaPago);

	        this.list.forEach(item -> {
	            facturaService.add(0, item.getProducto(), item.getCantidad(), item.getEmpresaProducto().getIdproducto().getPrecioProducto(), item.getSubTotal(), 0.0, 0.0, facturaService.findMax(), item.getEmpresaProducto().getIdempresa_producto());
	        });

	        this.list.forEach(item -> {
	            Empresa_producto empresa_producto = item.getEmpresaProducto();

	            int idEmpresaProducto = empresa_producto.getIdempresa_producto();
	            String producto = empresa_producto.getProducto(); 
	            String descripcion = empresa_producto.getDescripcion();  

	            empresa_productoService.up(idEmpresaProducto, producto, descripcion, idEmpresa, idProducto);
	        
	        });
	        clear(modelMap);
	    } catch (Exception e) {
	        // TODO: handle exception
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
		this.productos = productoService.findAll();
		
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
		
		this.productos = productoService.findAll();
		
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
		//return (double) Math.round(numero * 100d) / 100d;
	}

	
	@SuppressWarnings("static-access")
	public void add( int cantidad , Empresa_producto empresa_producto) {
		FacturaDetalle facturaDetalle = new FacturaDetalle();
		
		facturaDetalle.setEmpresaProducto(empresa_producto);
		facturaDetalle.setCantidad(cantidad);
		facturaDetalle.setDescuento1(0.0);
		facturaDetalle.setDescuento2(0.0);
		
	    double subtotal = empresa_producto.getIdproducto().getPrecioProducto() * cantidad;
	    facturaDetalle.setSubTotal(subtotal);

	    int nuevoStock = empresa_producto.getIdproducto().getStock() - cantidad;
	    empresa_producto.getIdproducto().setStock(nuevoStock);
		
		this.list.add(facturaDetalle);
		int index= this.list.indexOf(facturaDetalle);
		facturaDetalle.setIdFacturaDetalle(index);
		this.list.set(index, facturaDetalle);
	}
	
	
	@SuppressWarnings("static-access")
	public void up(int cantidad, int idFDList) {
	    FacturaDetalle facturaDetalle = findAll().get(idFDList);

	    Producto producto = facturaDetalle.getEmpresaProducto().getIdproducto();

	    int nuevoStock = producto.getStock() - cantidad;
	    producto.setStock(nuevoStock);

	    facturaDetalle.setCantidad(cantidad);
	    facturaDetalle.setSubTotal(producto.getPrecioProducto() * cantidad);

	    this.list.set(idFDList, facturaDetalle);
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