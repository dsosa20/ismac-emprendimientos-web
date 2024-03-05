package com.distribuida.dao;
import java.util.List;

import com.distribuida.entities.loginCliente;

public interface loginClienteDao {
 
		public List<loginCliente> findAll();		
		public loginCliente findOne(int id);
		public void add( loginCliente loginCliente);
		public void up(loginCliente loginCliente);
		public void del(int id);
		
		
		
	}

