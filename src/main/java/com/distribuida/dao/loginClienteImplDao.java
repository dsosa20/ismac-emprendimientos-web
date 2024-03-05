package com.distribuida.dao;
import java.util.List;

 
import com.distribuida.entities.loginCliente;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class loginClienteImplDao implements loginClienteDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public List<loginCliente> findAll() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from loginCliente",loginCliente.class).getResultList();
		
 
		
	}
 
	@Override
	@Transactional
	public loginCliente findOne(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		
		return session.get(loginCliente.class, id);
	}
 
	@Override
	@Transactional
	public void add(loginCliente loginCliente) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		//loginCliente usuario1 = new loginCliente();
		//Actulizar
		//loginCliente loginCliente2 = new loginCliente();
		session.saveOrUpdate(loginCliente);
	}
 
	@Override
	@Transactional
	public void up(loginCliente loginCliente) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(loginCliente);
	}
 
	@Override
	@Transactional
	public void del(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(findOne(id));
	}
 
}


