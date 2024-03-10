package com.distribuida.dto;


import java.util.List;

import com.distribuida.entities.Empresa_producto;




public interface Empresa_productoService {
	
	public List<Empresa_producto> findAll();
	
	public Empresa_producto finOne(int id);
	
	public void add(int idempresa_producto, String producto, String descripcion, int idEmpresa,int fdProducto);
	
	public void up(int idempresa_producto, String producto, String descripcion, int idEmpresa,int idProducto);
	
	public void del(int id);
	
	public List<Empresa_producto> findAll(String busqueda);


}
