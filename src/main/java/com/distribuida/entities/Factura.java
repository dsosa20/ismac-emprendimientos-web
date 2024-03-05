package com.distribuida.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "facturacion")
public class Factura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idFactura")
	private int idFactura;
	@Column(name = "NumeroFactura")
	private String numFactura;
	@Column(name = "Fechafactura")
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaFactura;
	@Column(name = "totalNeto")
	private Double totalNeto;
	@Column(name = "Iva")
	private Double iva;
	@Column(name = "total")
	private Double total;
	
	public Factura() {}
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="fk_idCliente")
	private Cliente cliente;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="fk_idPedido")
	private Pedido pedido;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="fk_idFormaPago")
	private FormaPago formaPago;
	
	public Factura(int idFactura, String numFactura, Date fechaFactura, Double totalNeto, Double iva, Double total) {
		this.idFactura = idFactura;
		this.numFactura = numFactura;
		this.fechaFactura = fechaFactura;
		this.totalNeto = totalNeto;
		this.iva = iva;
		this.total = total;
		//this.cliente = cliente;
		//this.pedido = pedido;
		//this.formaPago = formaPago;
	}
	
	public Factura (Cliente cliente){
		this.cliente = cliente;
	}
	public Factura (Pedido pedido) {
		this.pedido = pedido; 
	}
	public Factura (FormaPago formaPago) {
		this.formaPago = formaPago;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public String getNumFactura() {
		return numFactura;
	}

	public void setNumFactura(String numFactura) {
		this.numFactura = numFactura;
	}

	public Date getFechaFactura() {
		return fechaFactura;
	}

	public void setFechaFactura(Date fechaFactura) {
		this.fechaFactura = fechaFactura;
	}

	public Double getTotalNeto() {
		return totalNeto;
	}

	public void setTotalNeto(Double totalNeto) {
		this.totalNeto = totalNeto;
	}

	public Double getIva() {
		return iva;
	}

	public void setIva(Double iva) {
		this.iva = iva;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public FormaPago getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(FormaPago formaPago) {
		this.formaPago = formaPago;
	}

	@Override
	public String toString() {
		return "Factura [idFactura=" + idFactura + ", numFactura=" + numFactura + ", fechaFactura=" + fechaFactura
				+ ", totalNeto=" + totalNeto + ", iva=" + iva + ", total=" + total + ", cliente=" + cliente
				+ ", pedido=" + pedido + ", formaPago=" + formaPago + "]";
	}
}
