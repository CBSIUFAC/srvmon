package DAO;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import entity.Carro;

public class MasterDAO {
	public Session getSession(){
		return HibernateUtil.getSessionFactory().openSession();
	}
	//inserir
	public void inserirObjeto(Object obj){
		Session s = getSession();
		s.beginTransaction();
		s.save(obj);
		s.getTransaction().commit();
		s.close();
	}
	//deletar
	public void deletarObjeto(Object obj){
		Session s = getSession();
		s.beginTransaction();
		s.delete(obj);
		s.getTransaction().commit();
		s.close();
	}
	//atualizar
	public void atualizarObjeto(Object obj){
		Session s = getSession();
		s.beginTransaction();
		s.update(obj);
		s.getTransaction().commit();
		s.close();
	}
	//get
	public <T extends Serializable> T getObjeto(Class<T> classe, int id){
		Session s = getSession();
		s.beginTransaction();
		Serializable retorno = (Serializable)s.get(classe, id);
		s.getTransaction().commit();
		s.close();
		return (T)retorno;
	}
	
	//lista
	public <T extends Serializable> List<T> getLista(Class<T> classe, String str){
		Session s = getSession();
		s.beginTransaction();
		Query qr = s.createQuery(str);
		List<T> retorno = qr.list();
		s.getTransaction().commit();
		s.close();
		return retorno;
	}
	
}








